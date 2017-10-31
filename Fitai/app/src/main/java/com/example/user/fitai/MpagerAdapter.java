package com.example.user.fitai;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

/**
 * Created by mohit on 29/10/17.
 */

public class MpagerAdapter extends PagerAdapter {

    private int[] layouts;
    private LayoutInflater layoutInflater;
    private Context context;
    private int[] images = {R.drawable.male, R.drawable.male_black, R.drawable.female, R.drawable.female_black};
    ImageView Bmale, Bfemale;
    public TextView text;
    public SharedPreferences sharedprefs;

    public MpagerAdapter(int[] layouts, Context context){
        this.layouts = layouts;
        this.context = context;
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
                String user= sharedprefs.getString(LoginActivity.Name, "");
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
