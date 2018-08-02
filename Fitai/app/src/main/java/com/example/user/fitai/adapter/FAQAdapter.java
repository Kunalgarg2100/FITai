package com.example.user.fitai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.fitai.R;

/**
 * Created by laferrari on 10/11/17.
 */

public class FAQAdapter extends ArrayAdapter<String> {

    private String questions_list[];
    private String answers_list[];

    public FAQAdapter(Context context, String[] questions, String[] answers) {
        super(context, R.layout.custom_faq_layout, questions);
        this.questions_list = questions;
        this.answers_list = answers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater my_layoutinflater = LayoutInflater.from(getContext());
        View customView = my_layoutinflater.inflate(R.layout.custom_faq_layout, parent, false);
        TextView question_textview = customView.findViewById(R.id.question);
        TextView answer_textview = customView.findViewById(R.id.answer);
        String Question = questions_list[position];
        String Answer = answers_list[position];
        question_textview.setText(Question);
        answer_textview.setText(Answer);
        return customView;
    }
}
