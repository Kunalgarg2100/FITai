package com.example.user.fitai.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fitai.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kunal on 9/11/17.
 */

public class CustomProgramAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgid;
    public TextView txtTitle, extratxt;
    public ImageView imageView;

    public CustomProgramAdapter(Activity context, String[] itemname, String[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist2, null,true);

        txtTitle = (TextView) rowView.findViewById(R.id.item);
        imageView = (ImageView) rowView.findViewById(R.id.goals_image);
        extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        extratxt.setText(imgid[position]);
        if(position==0)
            rowView.setBackgroundResource(R.drawable.program1);
        else if(position==1)
            rowView.setBackgroundResource(R.drawable.program2);
        else if(position==2)
            rowView.setBackgroundResource(R.drawable.program3);
        return rowView;

    };


}