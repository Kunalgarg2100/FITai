package com.example.mohit.signup;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    public static DataBaseHelper myDB;
    //public static DataBaseHelper myDB;
    //public DataBaseHelper myDB;
    EditText username_text, email_text, pass1_text, pass2_text;
    Button signup_btn, view_data_btn, signup_page_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myDB = new DataBaseHelper(this);

        username_text = (EditText)findViewById(R.id.user_text);
        email_text = (EditText)findViewById(R.id.email_text);
        pass1_text = (EditText)findViewById(R.id.pass1_text);
        pass2_text = (EditText) findViewById(R.id.pass2_text);
        signup_btn = (Button) findViewById(R.id.signup_btn);
        view_data_btn = (Button)findViewById(R.id.view_btn);
        signup_page_btn = (Button)findViewById(R.id.signup_page_btn);
        newPage();
        addData();
        viewData();
    }

    public void newPage(){
        signup_page_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(it);
                    }
                }
        );
    }

    public void addData(){
        signup_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = username_text.getText().toString();
                        String email = email_text.getText().toString();
                        String pass1 = null;
                        try {
                            pass1 = SHA1(pass1_text.getText().toString());
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String pass2 = null;
                        try {
                            pass2 = SHA1(pass2_text.getText().toString());
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(username.equals("") || email.equals("") || pass1.equals("") || pass2.equals("")){
                            Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
                            return ;
                        }
                        if(!pass1.equals(pass2)){
                            //Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                            pass1_text.setError("Passwords do not match!");
                            return;
                        }
                        if(myDB.verifySignup(email, username)==true){
                            boolean signup = myDB.insertData(username, email, pass1);
                            if(signup == true)
                                Toast.makeText(MainActivity.this, "SignUp successfull", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "SignUp not successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Email already exists!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewData(){
        view_data_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDB.getData();
                        if(res.getCount() == 0) {
                            getDialog("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Username :"+ res.getString(1)+"\n");
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("Password :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        getDialog("Data",buffer.toString());
                    }
                }
        );
    }

    public void getDialog(String title, String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes("iso-8859-1");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}
