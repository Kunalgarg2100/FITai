package com.example.user.fitai.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.fitai.LoginActivity;
import com.example.user.fitai.R;

import static android.app.PendingIntent.getActivity;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by mohit on 29/10/17.
 */

public class MpagerAdapter extends PagerAdapter {

    private int[] layouts;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private int[] images = {R.drawable.male, R.drawable.male_black, R.drawable.female, R.drawable.female_black};
    private int[] images1 = {R.drawable.home, R.drawable.home_black, R.drawable.gym, R.drawable.gym_black};
    public ImageView Bmale, Bfemale, Bhome, Bgym;
    public TextView text;
    public SharedPreferences sharedprefs;
    public TextView weight1, weight2, weight, height1, height2, height, age;
    public String user;
    public ListView list;

    public MpagerAdapter(int[] layouts, Context context, Activity activity){
        this.layouts = layouts;
        this.context = context;
        this.activity = activity;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = layoutInflater.inflate(layouts[position], container, false);
        sharedprefs = context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        switch (position) {

            case 0:
                //Log.d("page","1");
                view = layoutInflater.inflate(R.layout.activity_screen1, null, false);
                Bmale = (ImageView) view.findViewById(R.id.male_btn);
                Bfemale = (ImageView) view.findViewById(R.id.female_btn);
                text = (TextView) view.findViewById(R.id.gender_text);
                String gender= sharedprefs.getString(LoginActivity.Gender, "");
                user= sharedprefs.getString(LoginActivity.Name, "");
                text.setText("Hey "+user+", Select your Gender");
                if(gender == "male"){
                    Bmale.setImageResource(images[0]);
                }
                else if(gender == "female"){
                    Bfemale.setImageResource(images[2]);

                }
                Bmale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.Gender, "male");
                        editor.commit();
                        Bmale.setImageResource(images[0]);
                        Bfemale.setImageResource(images[3]);
                    }
                });

                Bfemale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.Gender, "female");
                        editor.commit();
                        Bmale.setImageResource(images[1]);
                        Bfemale.setImageResource(images[2]);
                    }
                });
                break;

            case 1:

                view = layoutInflater.inflate(R.layout.activity_screen2, null, false);

                age = (TextView) view.findViewById(R.id.text_age);
                weight1 = (TextView) view.findViewById(R.id.weight1);
                weight2 = (TextView) view.findViewById(R.id.weight2);
                weight = (TextView) view.findViewById(R.id.text_weight);
                height1 = (TextView) view.findViewById(R.id.height1);
                height2 = (TextView) view.findViewById(R.id.height2);
                height = (TextView) view.findViewById(R.id.text_height);

                weight1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weight1.setBackgroundResource(R.drawable.profile_box2);
                        weight2.setBackgroundResource(R.drawable.profile_box);
                        //weight1.setBackgroundColor(context.getResources().getColor(R.color.inactive));
                        //weight2.setBackgroundColor(context.getResources().getColor(R.color.dot_light_screen2));
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                    }
                });
                weight2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weight1.setBackgroundResource(R.drawable.profile_box);
                        weight2.setBackgroundResource(R.drawable.profile_box2);
                        //weight2.setBackgroundColor(context.getResources().getColor(R.color.inactive));
                        //weight1.setBackgroundColor(context.getResources().getColor(R.color.dot_light_screen2));
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.active));
                    }
                });
                height1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        height1.setBackgroundResource(R.drawable.profile_box2);
                        height2.setBackgroundResource(R.drawable.profile_box);
                        height1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        height2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                    }
                });
                height2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        height1.setBackgroundResource(R.drawable.profile_box);
                        height2.setBackgroundResource(R.drawable.profile_box2);
                        height1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        height2.setTextColor(context.getResources().getColorStateList(R.color.active));
                    }
                });
                break;

            case 2:
                //Log.d("page","1");
                view = layoutInflater.inflate(R.layout.activity_screen3, null, false);
                Bhome = (ImageView) view.findViewById(R.id.home_btn);
                Bgym = (ImageView) view.findViewById(R.id.gym_btn);
                text = (TextView) view.findViewById(R.id.workout_text);
                String place= sharedprefs.getString(LoginActivity.PLACE, "");
                user= sharedprefs.getString(LoginActivity.Name, "");
                text.setText(user+", Where do you workout?");
                if(place == "home"){
                    Bhome.setImageResource(images1[0]);
                }
                else if(place == "gym"){
                    Bgym.setImageResource(images1[2]);

                }
                Bhome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.PLACE, "home");
                        editor.commit();
                        Bhome.setImageResource(images1[0]);
                        Bgym.setImageResource(images1[3]);
                    }
                });

                Bgym.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.PLACE, "gym");
                        editor.commit();
                        Bhome.setImageResource(images1[1]);
                        Bgym.setImageResource(images1[2]);
                    }
                });
                break;

            case 3:
                view = layoutInflater.inflate(R.layout.activity_screen4, null, false);
                //Resources res = getResources();
                final String [] itemname ={
                        "Safari",
                        "Camera",
                        "Global",
                        "FireFox",
                        "UC Browser",
                        "Android Folder",
                        "VLC Player",
                        "Cold War"
                };

                final Integer[] imgid={
                        R.drawable.body1,
                        R.drawable.body2,
                        R.drawable.body3,
                        R.drawable.body4,
                        R.drawable.body5,
                        R.drawable.body6,
                        R.drawable.body7,
                        R.drawable.body8,
                };
                CustomListAdapter adapter=new CustomListAdapter(activity, itemname, imgid);
                list=(ListView) view.findViewById(R.id.list);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem= itemname[+position];
                        Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                    }
                });
                break;

            default:
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        View view = (View)object;
        container.removeView(view);
    }

}
