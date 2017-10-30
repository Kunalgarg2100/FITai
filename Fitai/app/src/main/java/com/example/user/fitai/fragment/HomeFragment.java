package com.example.user.fitai.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.fitai.Dashboard;
import com.example.user.fitai.LoginActivity;
import com.example.user.fitai.R;
import com.example.user.fitai.SetProfile;

public class HomeFragment extends TabsFragment{
    TextView setProfile,name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home, container, false);

        setProfile = (TextView) view.findViewById(R.id.set_profile);

        setProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetProfile.class));
            }
        });

        name = (TextView) view.findViewById(R.id.username);
        name.setText("name :");
        return view;
    }

}
