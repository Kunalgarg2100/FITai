package com.example.user.fitai;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.fitai.fragment.ScheduleFragment;
import com.example.user.fitai.fragment.TabsFragment;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.Calendar;
import java.util.Date;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname = sharedpreferences.getString("nameKey", "");
        String uemail = sharedpreferences.getString("emailKey", "");
        String uphoto = sharedpreferences.getString("photoKey", "");
        String fblogin = sharedpreferences.getString("loginKey", "");
        //byte[] photoByte = Base64.decode(uphoto, Base64.DEFAULT);

        /*Cursor userInfo = LoginActivity.myDB.getEmail(uemail);
        if (userInfo.moveToFirst()) {
            height = userInfo.getFloat(5);
            weight = userInfo.getFloat(6);
            dob = userInfo.getInt(7);
            gender = userInfo.getString(8);
        }
        else if (userInfo == null){
            Toast.makeText(Dashboard.this, "Error fetching user information!", Toast.LENGTH_LONG).show();
        }
        if(height != null || weight != null || dob != null || gender != null){
            setProfile.setVisibility(View.GONE);
        } else{
            setProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, SetProfile.class));
                    //finish();
                }
            });
        }*/





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setCancelable(false);
                builder.setMessage("Start Chat?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        startActivity(new Intent(Dashboard.this, ChatActivity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new TabsFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView username = (TextView)header.findViewById(R.id.dashboard_username);
        TextView useremail = (TextView)header.findViewById(R.id.dashboard_email);
        username.setText(uname);
        useremail.setText(uemail);
        //String personPhotoUrl = sharedpreferences.getString("photoKey", "");
        ImageView imgProfilePic = (ImageView)header.findViewById(R.id.dashboard_image);
        if(fblogin == "yes"){
            Glide.with(getApplicationContext()).load(uphoto)
                .thumbnail(0.3f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfilePic);
        }else if(fblogin == "no"){
            byte[] photoByte = Base64.decode(uphoto, Base64.DEFAULT);
            imgProfilePic.setImageBitmap(Utils.getImage(photoByte));
        }
        //ImageView myImage = (ImageView) findViewById(R.id.imageView);

        /*Glide.with(getApplicationContext()).load(personPhotoUrl)
                .thumbnail(0.3f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfilePic);*/
        //imgProfilePic.setImageBitmap(Utils.getImage(photoByte));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to Exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    public void disp() {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int mnth = cal.get(Calendar.MONTH);
        int yr = cal.get(Calendar.YEAR);
        int dte = cal.get(Calendar.DATE);
        int tym = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        cal.set(yr, mnth, dte, tym, min);
        Uri uri = Uri.parse("content://com.android.calendar/time/"
                + String.valueOf(cal.getTimeInMillis()));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // Use the Calendar app to view the time.
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, AboutActivity.class));
        }else if(id == R.id.nav_dashboard){
            fragment = new TabsFragment();
            getSupportActionBar().setTitle("Dashboard");
        }
        else if (id == R.id.nav_workout_plan) {
            startActivity(new Intent(this, WorkoutActivity.class));
        }else if (id == R.id.nav_schedule) {
            disp();
        }
        else if (id == R.id.nav_settings){
            startActivity(new Intent(this, Profile.class));
        }
        else if (id == R.id.nav_fitness_goals){
            startActivity(new Intent(this, GoalActivity1.class));
        }
        else if (id == R.id.nav_logout)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to Logout?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    if(isLoggedIn())
                        LoginManager.getInstance().logOut();
                    startActivity(new Intent(Dashboard.this, LoginActivity.class));
                    finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            }
        else if (id == R.id.nav_support) {
            startActivity(new Intent(this, ChatActivity.class));
        } else if (id == R.id.nav_faqs) {
            startActivity(new Intent(this, FrequentlyAskedQuestionsActivity.class));
        }


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container_wrapper, fragment);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
