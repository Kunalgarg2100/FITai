package com.example.user.fitai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.fitai.R;

public class HomeFragment extends TabsFragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home, container, false);
        return view;
    }
}
