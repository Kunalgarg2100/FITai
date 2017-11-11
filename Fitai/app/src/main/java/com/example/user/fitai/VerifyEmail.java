package com.example.user.fitai;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class VerifyEmail extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    TextView textView, resend_btn, error;
    EditText c1, c2, c3, c4, c5;
    Button verify_btn;
    String code, subject, message, user, email, password;
    byte[] inputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        Intent intent = getIntent();
        code = intent.getStringExtra("Code").trim();
        user = intent.getStringExtra("user");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        // Capture the layout's TextView and set the string as its text
        textView = (TextView) findViewById(R.id.code);
        resend_btn = (TextView) findViewById(R.id.resend);
        error = (TextView) findViewById(R.id.error);
        //textView.setText(code);
        Log.d("ii", user);
        Log.d("hi", email);
        Log.d("hi", code);
        subject = "Email Cofirmation";
        message = "Hello " + user + "!!!\nThanks for registering on Fitai Fitness App.\n" +
                "Your Verification code is-\n" +
                code + "\nUse this code to verify your Enail address!!";
        SendMail sm = new SendMail(this, email, subject, message);
        sm.execute();
        c1 = (EditText) findViewById(R.id.editText7);
        c2 = (EditText) findViewById(R.id.editText9);
        c3 = (EditText) findViewById(R.id.editText8);
        c4 = (EditText) findViewById(R.id.editText6);
        c5 = (EditText) findViewById(R.id.editText11);
        verify_btn = (Button) findViewById(R.id.verify_btn);
        //check_code();
        try {
            check_code();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        resend_code();

        c1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (c1.getText().toString().length() == 1)     //size as per your requirement
                {
                    c2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        c2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (c2.getText().toString().length() == 1)     //size as per your requirement
                {
                    c3.requestFocus();
                }
                if (c2.getText().toString().length() == 0)     //size as per your requirement
                {
                    c1.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        c3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (c3.getText().toString().length() == 1)     //size as per your requirement
                {
                    c4.requestFocus();
                }
                if (c3.getText().toString().length() == 0)     //size as per your requirement
                {
                    c2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        c4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (c4.getText().toString().length() == 1)     //size as per your requirement
                {
                    c5.requestFocus();
                }
                if (c4.getText().toString().length() == 0)     //size as per your requirement
                {
                    c3.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        c5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (c5.getText().toString().length() == 0)     //size as per your requirement
                {
                    c4.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

    }


    public void check_code() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri profile_pic = null;
                String ver_code = c1.getText().toString().trim() + c2.getText().toString().trim() + c3.getText().toString().trim() + c4.getText().toString().trim() + c5.getText().toString().trim();
                ver_code = ver_code.trim();
                //String ver_code1 = code;
                Uri profile_pic = Uri.parse("android.resource://com.example.user.fitai/drawable/profile");
                Log.d("profile_pic", profile_pic.toString());
                if (ver_code.equals(code)) {
                    //textView.setText("yes "+ver_code+" "+code);
                    boolean signup = false;
                    boolean ins_image;
                    try {
                        InputStream iStream = getContentResolver().openInputStream(profile_pic);
                        inputData = Utils.getBytes(iStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        signup = LoginActivity.myDB.insertData(user, email, LoginActivity.SHA1(password), inputData);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //ins_image = saveImageInDB(profile_pic);
                    if (signup) {
                        Toast.makeText(VerifyEmail.this, "SignUp successfull", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(VerifyEmail.this, LoginActivity.class));
                        finish();
                    } else
                        Toast.makeText(VerifyEmail.this, "SignUp not successfull", Toast.LENGTH_LONG).show();

                } else {
                    error.setText("Verification code is invalid. Please try one more time!");
                }
            }
        });
    }

    private void resend_code() {
        resend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail sm = new SendMail(VerifyEmail.this, email, subject, message);
                sm.execute();
                //Toast.makeText(VerifyEmail.this, "Code is Successfully sent", Toast.LENGTH_LONG).show();
            }
        });
    }

    Boolean saveImageInDB(Uri selectedImageUri) {

        try {
            //dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            Log.d("Uri", inputData.toString());
            LoginActivity.myDB.insertImage(inputData);
            //dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            //dbHelper.close();
            return false;
        }

    }

    /*Boolean loadImageFromDB() {
        try {
            //dbHelper.open();
            byte[] bytes = LoginActivity.myDB.retreiveImageFromDB();
            //dbHelper.close();
            imgView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }*/

}
