package com.example.user.fitai.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.fitai.LoginActivity;
import com.example.user.fitai.Profile;
import com.example.user.fitai.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    public TextView weight1, weight2, weight, height1, height2, height, age, textWeight, textAge, textHeight;
    public String user;
    public ListView list;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;
    public String weight_unit;
    public String weight_val;
    public String height_unit;
    public String height_val;
    public String age_val;
    public NumberPicker np1, np2;

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
                textAge = (TextView) view.findViewById(R.id.text_age);
                textHeight = (TextView) view.findViewById(R.id.text_height);
                textWeight = (TextView) view.findViewById(R.id.text_weight);
                weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
                weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
                height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
                height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");
                user= sharedprefs.getString(LoginActivity.Name, "");
                age_val = sharedprefs.getString(LoginActivity.DOB, "");

                Log.d("weight_unit",weight_unit);
                Log.d("weight_val",weight_val);
                Log.d("height_unit",height_unit);
                Log.d("height_val",height_val);
                if(age_val == null){
                    textAge.setText("11/15/17");
                    SharedPreferences.Editor editor = sharedprefs.edit();
                    editor.putString(LoginActivity.DOB, "11/15/17");
                    editor.commit();
                }
                if(age_val!=null){
                    textAge.setText(age_val);
                }
                if(weight_unit==null){
                    SharedPreferences.Editor editor = sharedprefs.edit();
                    editor.putString(LoginActivity.WEIGHT_UNIT, "kg");
                    editor.commit();
                }
                if(weight_val==null){
                    SharedPreferences.Editor editor = sharedprefs.edit();
                    editor.putString(LoginActivity.WEIGHT_VAL, "72");
                    editor.commit();
                }
                if(height_unit==null){
                    SharedPreferences.Editor editor = sharedprefs.edit();
                    editor.putString(LoginActivity.HEIGHT_UNIT, "cm");
                    editor.commit();
                }
                if(height_val==null){
                    SharedPreferences.Editor editor = sharedprefs.edit();
                    editor.putString(LoginActivity.HEIGHT_VAL, "175");
                    editor.commit();
                }

                weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
                weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
                height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
                height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");
                age_val = sharedprefs.getString(LoginActivity.DOB, "");

                if(weight_val!=null){
                    textWeight.setText(weight_val);
                    if(weight_unit=="kg"){
                        weight1.setBackgroundResource(R.drawable.profile_box2);
                        weight2.setBackgroundResource(R.drawable.profile_box);
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                    }else{
                        weight1.setBackgroundResource(R.drawable.profile_box);
                        weight2.setBackgroundResource(R.drawable.profile_box2);
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.active));
                    }
                }

                if(height_val!=null){
                    textHeight.setText(height_val);
                    if(height_unit=="cm"){
                        weight1.setBackgroundResource(R.drawable.profile_box2);
                        weight2.setBackgroundResource(R.drawable.profile_box);
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                    }else{
                        height1.setBackgroundResource(R.drawable.profile_box);
                        height2.setBackgroundResource(R.drawable.profile_box2);
                        height1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        height2.setTextColor(context.getResources().getColorStateList(R.color.active));
                    }
                }

                weight1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.WEIGHT_UNIT, "kg");
                        editor.putString(LoginActivity.WEIGHT_VAL, "72");
                        editor.commit();
                        weight1.setBackgroundResource(R.drawable.profile_box2);
                        weight2.setBackgroundResource(R.drawable.profile_box);
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        textWeight.setText("72");
                    }
                });
                weight2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.WEIGHT_UNIT, "lb");
                        editor.putString(LoginActivity.WEIGHT_VAL, "150");
                        editor.commit();
                        weight1.setBackgroundResource(R.drawable.profile_box);
                        weight2.setBackgroundResource(R.drawable.profile_box2);
                        weight1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        weight2.setTextColor(context.getResources().getColorStateList(R.color.active));
                        textWeight.setText("150");
                    }
                });
                height1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.HEIGHT_UNIT, "cm");
                        editor.putString(LoginActivity.HEIGHT_VAL, "175");
                        editor.commit();
                        height1.setBackgroundResource(R.drawable.profile_box2);
                        height2.setBackgroundResource(R.drawable.profile_box);
                        height1.setTextColor(context.getResources().getColorStateList(R.color.active));
                        height2.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        textHeight.setText("175");
                    }
                });
                height2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.HEIGHT_UNIT, "feet");
                        editor.putString(LoginActivity.HEIGHT_VAL, "6");
                        editor.commit();
                        height1.setBackgroundResource(R.drawable.profile_box);
                        height2.setBackgroundResource(R.drawable.profile_box2);
                        height1.setTextColor(context.getResources().getColorStateList(R.color.inactive));
                        height2.setTextColor(context.getResources().getColorStateList(R.color.active));
                        textHeight.setText("6");
                    }
                });

                weight_unit = sharedprefs.getString(LoginActivity.WEIGHT_UNIT, "");
                weight_val = sharedprefs.getString(LoginActivity.WEIGHT_VAL, "");
                height_unit = sharedprefs.getString(LoginActivity.HEIGHT_UNIT, "");
                height_val = sharedprefs.getString(LoginActivity.HEIGHT_VAL, "");

                final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //updateLabel();
                        String myFormat = "MM/dd/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        textAge.setText(sdf.format(myCalendar.getTime()));
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.putString(LoginActivity.DOB,sdf.format(myCalendar.getTime()));
                        editor.commit();

                    }

                };

                textAge.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        new DatePickerDialog(activity, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                textHeight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //b = (Button) findViewById(R.id.button_height);
                        final Dialog d = new Dialog(activity);
                        d.setTitle("Select your height");
                        d.setContentView(R.layout.profile_dialog);
                        Button b1 = (Button) d.findViewById(R.id.save);
                        Button b2 = (Button) d.findViewById(R.id.cancel);
                        np1 = d.findViewById(R.id.num1);
                        np2 = d.findViewById(R.id.num2);
                        if(height_unit == "cm"){
                            np1.setMaxValue(200);
                            np1.setMinValue(100);
                            np2.setMaxValue(9);
                            np2.setMinValue(0);
                        }
                        else{
                            np1.setMaxValue(8);
                            np1.setMinValue(3);
                            np2.setMaxValue(11);
                            np2.setMinValue(0);
                        }
                        np2.setWrapSelectorWheel(false);
                        np1.setWrapSelectorWheel(false);
                        b1.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                String s2 = String.valueOf(np1.getValue())+"."+String.valueOf(np2.getValue());
                                textHeight.setText(s2);
                                SharedPreferences.Editor editor = sharedprefs.edit();
                                editor.putString(LoginActivity.HEIGHT_VAL, s2);
                                editor.commit();
                                d.dismiss();
                            }
                        });
                        b2.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                d.dismiss(); // dismiss the dialog
                            }
                        });
                        d.show();
                    }
                });

                textWeight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //b = (Button) findViewById(R.id.button_height);
                        final Dialog d = new Dialog(activity);
                        d.setTitle("Select your Weight");
                        d.setContentView(R.layout.profile_dialog);
                        Button b1 = (Button) d.findViewById(R.id.save);
                        Button b2 = (Button) d.findViewById(R.id.cancel);
                        np1 = d.findViewById(R.id.num1);
                        np2 = d.findViewById(R.id.num2);
                        if(weight_unit == "kg"){
                            np1.setMaxValue(200);
                            np1.setMinValue(30);
                            np2.setMaxValue(9);
                            np2.setMinValue(0);
                        }
                        else{
                            np1.setMaxValue(440);
                            np1.setMinValue(66);
                            np2.setMaxValue(11);
                            np2.setMinValue(0);
                        }
                        np1.setWrapSelectorWheel(false);
                        np2.setWrapSelectorWheel(false);
                        b1.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                String s2 = String.valueOf(np1.getValue())+"."+String.valueOf(np2.getValue());
                                textWeight.setText(s2);
                                SharedPreferences.Editor editor = sharedprefs.edit();
                                editor.putString(LoginActivity.WEIGHT_VAL, s2);
                                editor.commit();
                                d.dismiss();
                            }
                        });
                        b2.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                d.dismiss(); // dismiss the dialog
                            }
                        });
                        d.show();
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

    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID){
            return new DatePickerDialog(activity, dpickerlistener, year_x, month_x, day_x);
        }
        else
            return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerlistener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day){
            year_x = year;
            month_x = month + 1;
            day_x = day;
            Button bt = (Button) view.findViewById(R.id.button_dateOfBirth);
            String date = day_x+"/"+month_x+"/"+year_x;
            bt.setText(date);
            SharedPreferences date_sp = context.getSharedPreferences("prof",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = date_sp.edit();
            ed.putString("user_dob", date);
            ed.commit();
        }
    };
}
