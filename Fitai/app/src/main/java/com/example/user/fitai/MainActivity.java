package com.example.user.fitai;

/**
 * Created by kunal on 27/8/17.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.fb_activity);
        callbackManager = CallbackManager.Factory.create();
        textView = (TextView)findViewById(R.id.textView);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText(
                        "Login Sucess \n" +
                        "User ID: " + loginResult.getAccessToken().getUserId()
                                + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel()
            {
                textView.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException exception) {
                textView.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}