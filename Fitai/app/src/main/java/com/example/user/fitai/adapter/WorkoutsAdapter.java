package com.example.user.fitai.adapter;

import com.example.user.fitai.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.fitai.GraphActivity;
import com.example.user.fitai.R;

import java.util.List;

/**
 * Created by nirvan on 25/10/17.
 */

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Workout> workoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, descript, instruct;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            instruct = view.findViewById(R.id.instructionheader);
            descript = view.findViewById(R.id.descript);
            thumbnail = view.findViewById(R.id.thumbnail);
            /*
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"You just clicked me!!", Toast.LENGTH_LONG).show();

                }
            });*/
        }
    }


    public WorkoutsAdapter(Context mContext, List<Workout> workoutList) {
        this.mContext = mContext;
        this.workoutList = workoutList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Workout workout = workoutList.get(position);
        holder.title.setText(workout.getName());
        holder.descript.setText(workout.getDescription());
        holder.instruct.setText(R.string.instruction_head);
        // loading workout cover using Glide library
        Glide.with(mContext).load(workout.getThumbnail()).into(holder.thumbnail);

        holder.instruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_whatever_you_want(view, workout);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgraphactivity(view, workout);
            }
        });


    }

    public void do_whatever_you_want(View view, Workout workout) {
        Intent intent = new Intent(view.getContext(), ListActivity.class);
        intent.putExtra("ID_OF_CALLER", "zumba.json");
        mContext.startActivity(intent);
        /*
        Intent intent = new Intent(view.getContext(), GraphActivity.class);
        intent.putExtra("title", workout.getName());
        mContext.startActivity(intent);
        */
    }

    public void startgraphactivity(View view, Workout workout) {
        Intent intent = new Intent(view.getContext(), GraphActivity.class);
        intent.putExtra("title", workout.getName());
        mContext.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}