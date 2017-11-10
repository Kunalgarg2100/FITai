package com.example.user.fitai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.user.fitai.adapter.FAQAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FrequentlyAskedQuestionsActivity extends AppCompatActivity {

    String[] question_list = {};
    String[] answer_list = {};
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_asked_questions);
        try {
            JSONObject object = new JSONObject(loadJSONFromAsset("faq.json"));
            JSONArray my_array = object.getJSONArray("list");
            for (int i = 0; i < my_array.length(); i++) {
                JSONObject temp = my_array.getJSONObject(i);
                String question = temp.getString("question");
                String answer = temp.getString("answer");
                question_list = add(question_list, question);
                answer_list = add(answer_list, answer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter faq_list_adapter = new FAQAdapter(this, question_list, answer_list);
        listview = (ListView) findViewById(R.id.faq_list_view);
        listview.setAdapter(faq_list_adapter);
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String[] add(String[] originalArray, String newItem) {
        int currentSize = originalArray.length;
        int newSize = currentSize + 1;
        String[] tempArray = new String[newSize];
        for (int i = 0; i < currentSize; i++) {
            tempArray[i] = originalArray[i];
        }
        tempArray[newSize - 1] = newItem;
        return tempArray;
    }
}
