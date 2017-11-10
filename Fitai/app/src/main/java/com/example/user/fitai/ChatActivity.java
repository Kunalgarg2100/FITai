package com.example.user.fitai;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.fitai.adapter.ChatMessageAdapter;

import java.util.ArrayList;

/**
 * Created by kunal on 2/11/17.
 */

public class ChatActivity extends AppCompatActivity{
    private ListView mListView;
    private ImageView mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (ImageView) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);

//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                sendMessage(message);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });
        mimicOtherMessage("Not sure how Fitai works? \n " + "Feel free to ask anything");
    }

    @Override
    public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Exit Chat?");
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
    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        if(message.equals("Hello") || message.equals("hello") || message.equals("hi") ||
                message.equals("Hi")) {
            mimicOtherMessage("Hello! How can I help you?");
        }


        else if(message.equals("Workouts") || message.equals("workouts") || message.equals("workout") || message.equals("Workout")){
            mimicOtherMessage("There are 4 workouts featured \nby our app");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mimicOtherMessage("Yoga");
                    mimicOtherMessage("Zumba");
                    mimicOtherMessage("Pilates");
                    mimicOtherMessage("Aerobics");
                }
            },2000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mimicOtherMessage("Which one would you like to try out?");
                }
            },1000);


        }
        else if(message.equals("Training Programs") || message.equals("training programs") ||
                message.equals("programs") || message.equals("Programs") || message.equals("training program")
                || message.equals("program")){
            mimicOtherMessage("There are 3 different training programs \nfeatured by our app");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mimicOtherMessage("21-day-plan");
                    mimicOtherMessage("45-day-plan");
                    mimicOtherMessage("60-day-plan");
                }
            },2000);

        }
        else if(message.equals("Goodbye") || message.equals("goodbye")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Exit Chat?");
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
            //finish();
        }

        else {
            mimicOtherMessage("Sorry, I could not understand\n what you are trying to say.");
        }

    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true);
        mAdapter.add(chatMessage);
        mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false, true);
        mAdapter.add(chatMessage);
    }
}
