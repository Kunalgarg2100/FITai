package com.example.user.fitai;

import android.*;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    Animation Fade_in, Fade_out;
    ViewFlipper viewFlipper;
    final Context context = this;
    final CharSequence[] items = {"Camera","Gallery"};
    public String TAG = "MyActivity";
    ImageButton img;
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;
    static final Integer CAMERA = 0x5;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;
    Button b1, b2, b3, b4,b;
    final String height_unit[] = {"cm", "inch"};
    final String weight_unit[] = {"kg", "lbs"};
    Button gender;
    String pictureName = String.format("%d.jpg", System.currentTimeMillis()), pic_name;
    Cursor user_info;
    Double height, weight;
    String dob, sex;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.v);
        Fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        viewFlipper.setInAnimation(Fade_in);
        viewFlipper.setOutAnimation(Fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.startFlipping();
        img = (ImageButton) findViewById(R.id.imageButton);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname = sharedpreferences.getString("nameKey", "");
        Log.d("user_name", uname);
/*        user_info = LoginActivity.myDB.getEmail(uname);
        if (user_info.moveToFirst()) {
            image = user_info.getBlob(4);
            height = user_info.getDouble(5);
            weight = user_info.getDouble(6);
            dob = user_info.getString(7);
            sex = user_info.getString(8);
        }
        else if (user_info == null){
            Toast.makeText(Profile.this, "Error fetching user information!", Toast.LENGTH_LONG).show();
        }
  */      //TextView userInfo = (TextView) findViewById(R.id.userName);
        //userInfo.setText(height.toString()+weight.toString()+dob+sex);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        SharedPreferences sp = getSharedPreferences("prof",Context.MODE_PRIVATE);
        //SharedPreferences sp = getSharedPreferences(LoginActivity.MyPREFERENCES,Context.MODE_PRIVATE);
        String image_string = sp.getString(LoginActivity.PhotoUrl,"");
        String weight_string = sp.getString("user_weight", "");
        String height_string = sp.getString("user_height", "");
        String dateOfBirth = sp.getString("user_dob", "");
        String gender_string = sp.getString("user_gender", "");
        //SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        //String uname = sharedpreferences.getString("nameKey", "");
        if(!image_string.equals("")){
            Log.d("image_string", image_string);
            Bitmap resized = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(image_string), 310, 310);
            ImageView image = (ImageView) findViewById(R.id.imageButton);
            image.setImageBitmap(resized);
        }
        else{
            ImageView imageView = (ImageView) findViewById(R.id.imageButton);
            imageView.setImageResource(R.drawable.profilepic);
        }
        if(!weight_string.equals("")){
            b1 = (Button) findViewById(R.id.button_weight);
            b1.setText(weight_string);
        }
        if(!height_string.equals("")){
            b2 = (Button) findViewById(R.id.button_height);
            b2.setText(height_string);
        }
        if(!dateOfBirth.equals("")){
            b3 = (Button) findViewById(R.id.button_dateOfBirth);
            b3.setText(dateOfBirth);
        }
        if(!gender_string.equals("")){
            b4 = (Button) findViewById(R.id.button_gender);
            b4.setText(gender_string);
        }
/*
        if(!height.equals("")){
            b1.setText(height.toString()+" cm");
        }
        if(!weight.equals("")){
            b2.setText(weight.toString());
        }
        if(!dob.equals("")){
            b3.setText(dob);
        }
        if(!sex.equals("")){
            b4.setText(sex);
        }
  */  }

    public void dateUpdate(View v){
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialog(DIALOG_ID);
    }

    public void heightUpdate(View v){
        b = (Button) findViewById(R.id.button_height);
        final Dialog d = new Dialog(Profile.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final NumberPicker npp = (NumberPicker) d.findViewById(R.id.numberPicker);
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np2.setMinValue(0);
        np2.setMaxValue(1);
        np2.setDisplayedValues(height_unit);
        np.setMaxValue(250); // max value 250
        np.setMinValue(140);   // min value 0
        npp.setMinValue(0);
        npp.setMaxValue(9);
        //np.setWrapSelectorWheel(false);
        //npp.setWrapSelectorWheel(false);
        np2.setWrapSelectorWheel(false);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i("value is",""+ String.valueOf(i1));
                if(np2.getValue() == 0){
                    np.setMaxValue(250);
                    np.setMinValue(140);
                    npp.setMaxValue(9);
                    npp.setMinValue(0);
                }
                else{
                    np.setMaxValue(100);
                    np.setMinValue(48);
                    npp.setMaxValue(11);
                    npp.setMinValue(0);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String s2 = String.valueOf(np.getValue())+"."+String.valueOf(npp.getValue()) + " " + height_unit[np2.getValue()];
                b.setText(s2);
                SharedPreferences weight_sp = getSharedPreferences("prof",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = weight_sp.edit();
                ed.putString("user_height", s2);
                ed.commit();
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

    public void weightUpdate(View v){
        b = (Button) findViewById(R.id.button_weight);
        final Dialog d = new Dialog(Profile.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final NumberPicker npp = (NumberPicker) d.findViewById(R.id.numberPicker);
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np2.setMinValue(0);
        np2.setMaxValue(1);
        np2.setDisplayedValues(weight_unit);
        np.setMaxValue(400); // max value 250
        np.setMinValue(40);   // min value 0
        npp.setMinValue(0);
        npp.setMaxValue(9);
        //np.setWrapSelectorWheel(false);
        //npp.setWrapSelectorWheel(false);
        np2.setWrapSelectorWheel(false);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i("value is",""+ String.valueOf(i1));
                if(np2.getValue() == 0){
                    np.setMaxValue(400);
                    np.setMinValue(0);
                    npp.setMaxValue(9);
                    npp.setMinValue(0);
                }
                else {
                    np.setMaxValue(882);
                    np.setMinValue(88);
                    npp.setMaxValue(9);
                    npp.setMinValue(0);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String s1 = String.valueOf(np.getValue())+"."+String.valueOf(npp.getValue()) + " " + weight_unit[np2.getValue()];
                b.setText(s1);
                SharedPreferences weight_sp = getSharedPreferences("prof",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = weight_sp.edit();
                ed.putString("user_weight", s1);
                ed.commit();
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

    public void genderUpdate(View v){
        final Dialog d = new Dialog(Profile.this);
        gender = (Button) findViewById(R.id.button_gender);
        d.setTitle("Choose Your Gender");
        d.setContentView(R.layout.gender);
        Button b1 = (Button) d.findViewById(R.id.confirm_gender);
        Button b2 = (Button) d.findViewById(R.id.cancel_gender);
        final RadioButton r1 = (RadioButton) d.findViewById(R.id.button_male);
        final RadioButton r2 = (RadioButton) d.findViewById(R.id.button_female);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text;
                if(r1.isChecked())
                    text = (String) r1.getText();
                else if(r2.isChecked())
                    text = (String) r2.getText();
                else
                    text = "GENDER";
                gender.setText(text);
                SharedPreferences gender_sp = getSharedPreferences("prof",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = gender_sp.edit();
                ed.putString("user_gender", text);
                ed.commit();
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                d.dismiss();
            }
        });
        d.show();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerlistener, year_x, month_x, day_x);
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
            Button bt = (Button) findViewById(R.id.button_dateOfBirth);
            String date = day_x+"/"+month_x+"/"+year_x;
            bt.setText(date);
            SharedPreferences date_sp = getSharedPreferences("prof",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = date_sp.edit();
            ed.putString("user_dob", date);
            ed.commit();
        }
    };

    public void selectImage(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Pick a way to choose...");
        AlertDialog.Builder builder = alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i] == "Camera"){
                    askForPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXST);
                }
                else if(items[i] == "Gallery"){
                    askForPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
                }
            }
        });
        alertDialogBuilder.show();
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Profile.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Profile.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Profile.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            if(requestCode == 3){
                askForPermission(android.Manifest.permission.CAMERA,CAMERA);
            }
            if(requestCode == 5){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File picture = new File(pictureDirectory, pictureName);
                Uri picUri = Uri.fromFile(picture);
                //Log.d("MAINACTIVITY", picture.getName());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                startActivityForResult(intent, 1);
            }
            if(requestCode == 4){
                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Write external Storage
                case 3:
                    askForPermission(android.Manifest.permission.CAMERA,CAMERA);
                    break;
                //Read storage
                case 4:
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                    //Camera
                case 5:
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File picture = new File(pictureDirectory, pictureName);
                    Uri picUri = Uri.fromFile(picture);
                    //Log.d("MAINACTIVITY", picture.getName());
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    startActivityForResult(intent1, 1);
                    break;
            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }



    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File picture = new File(pictureDirectory, pictureName);
            ImageView img = (ImageView) findViewById(R.id.imageButton);
            Log.d("MAINACITYT",String.valueOf(img.getHeight()));
            //img.setImageBitmap(getThumbnailBitmap(picture.getAbsolutePath(), 289));
            Bitmap resized = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(picture.getPath()), 310, 310);
            img.setImageBitmap(resized);
            SharedPreferences sp = getSharedPreferences("prof",MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString(LoginActivity.PhotoUrl,picture.getAbsolutePath());
            ed.commit();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(picture);
            intent.setData(uri);
            this.sendBroadcast(intent);

        }
        else if(requestCode == 2 && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            // String picturePath contains the path of selected Image
            // Show the Selected Image on ImageView
            Bitmap bm = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] image = baos.toByteArray();
            String img_str = Base64.encodeToString(image, 0);
            Log.d("Mainactivity", picturePath);
            Bitmap tempbm = BitmapFactory.decodeFile(picturePath);

            //tempbm = getCircularBitmap(tempbm);
            img.setImageBitmap(tempbm);
            SharedPreferences sharedPref  = getSharedPreferences("prof", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(LoginActivity.PhotoUrl, img_str);
            editor.commit();
        }
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        // GET CURRENT SIZE
        int width = bm.getWidth();
        int height = bm.getHeight();
        // GET SCALE SIZE
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
}
