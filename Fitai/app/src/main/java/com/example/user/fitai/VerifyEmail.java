package com.example.user.fitai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class VerifyEmail extends AppCompatActivity {
    TextView textView;
    EditText c1,c2,c3,c4,c5;
    Button verify_btn;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        Intent intent = getIntent();
        code = intent.getStringExtra("Code").trim();
        String user = intent.getStringExtra("user");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        // Capture the layout's TextView and set the string as its text
        textView = (TextView) findViewById(R.id.code);
        textView.setText(code);
        String subject = "Email Cofirmation";
        String message = "Thanks for registering on Fitai Fitness App.\n"+
                          "Your Verification code is\n"+
                            code+"\nUse this code to verify your Enail address!!";
        SendMail sm = new SendMail(this, email, subject, message);
        sm.execute();
        c1 = (EditText) findViewById(R.id.editText7);
        c2 = (EditText) findViewById(R.id.editText9);
        c3 = (EditText) findViewById(R.id.editText8);
        c4 = (EditText) findViewById(R.id.editText6);
        c5 = (EditText) findViewById(R.id.editText11);
        verify_btn = (Button) findViewById(R.id.verify_btn);
        check_code();

    }

    public void check_code(){
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ver_code = c1.getText().toString().trim() +c2.getText().toString().trim() +c3.getText().toString().trim() +c4.getText().toString().trim()+c5.getText().toString().trim();
                ver_code = ver_code.trim();
                String ver_code1 = code;
                if(ver_code == ver_code1){
                    textView.setText("yes "+ver_code+" "+code);
                } else{
                    textView.setText("no "+ver_code+" "+code);
                }
            }
        });
    }
}
