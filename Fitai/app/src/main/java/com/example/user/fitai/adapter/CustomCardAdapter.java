package com.example.user.fitai.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fitai.R;

/**
 * Created by mohit on 3/11/17.
 */

public class CustomCardAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;

    public CustomCardAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = rowView.findViewById(R.id.item);
        ImageView imageView = rowView.findViewById(R.id.icon);
        TextView extratxt = rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
//        imageView.setImageResource(imgid[position]);
        extratxt.setText("Description " + itemname[position]);
        return rowView;

    }

}