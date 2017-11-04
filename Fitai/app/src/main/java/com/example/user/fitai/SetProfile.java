package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.fitai.adapter.MpagerAdapter;

//import static com.example.user.fitai.R.id.thing_proto;


public class SetProfile extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private int[] layouts = {R.layout.activity_screen1, R.layout.activity_screen2, R.layout.activity_screen3, R.layout.activity_screen4, R.layout.activity_screen5};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout Dots_layout;
    private ImageView[] dots;
    private Button BnNext, BnSkip;
    public SharedPreferences sharedprefs;
    //TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_set_profile);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts,this,SetProfile.this);
        mPager.setAdapter(mpagerAdapter);
        sharedprefs = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedprefs.edit();
        Dots_layout = (LinearLayout)findViewById(R.id.dotsLayout);
        BnNext = (Button)findViewById(R.id.bnNext);
        BnSkip = (Button)findViewById(R.id.bnSkip);
        BnNext.setOnClickListener(this);
        BnSkip.setOnClickListener(this);
        createDots(0);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1){
                    BnNext.setText("Start");
                    BnSkip.setVisibility(View.INVISIBLE);
                }
                else{
                    BnNext.setText("Next");
                    BnSkip.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void createDots(int current_position)
    {
        if(Dots_layout!=null)
            Dots_layout.removeAllViews();

        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++) {

            dots[i] = new ImageView(this);
            if (i == current_position) {

                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));

            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            Dots_layout.addView(dots[i], params);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bnNext:
                loadNextSlide();
                break;

            case R.id.bnSkip:
                loadHome();
                break;
        }

    }

    private void loadHome(){
        SharedPreferences.Editor editor = sharedprefs.edit();
        editor.putString(LoginActivity.Gender, null);
        editor.commit();
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void loadNextSlide(){
        int next_slide = mPager.getCurrentItem()+1;
        if(next_slide<layouts.length){
            mPager.setCurrentItem(next_slide);
        }
        else{
            loadHome();
        }
    }
}
