package com.example.user.fitai;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.user.fitai.adapter.CustomListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ListActivity extends AppCompatActivity {

    String[] link_list = {};
    String[] title_list = {};
    SwipeRefreshLayout mSwipeContainer;
    ListAdapter my_list_adapter;
    ListView listView;
    public static final String LINK = "link";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Intent intent = getIntent();
        try {
            JSONObject object = new JSONObject(loadJSONFromAsset(intent.getStringExtra("ID_OF_CALLER")));
            JSONArray my_array = object.getJSONArray("details_list");
            for (int i = 0; i < my_array.length(); i++) {
                JSONObject temp = my_array.getJSONObject(i);
                String title_name = temp.getString("name");
                String link = temp.getString("link");
                int parse_position = link.indexOf("=");
                String final_link = link.substring(parse_position + 1, link.length());
                link_list = add(link_list, final_link);
                title_list = add(title_list, title_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        my_list_adapter = new CustomListAdapter(this, title_list, link_list);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(my_list_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent hello = new Intent(ListActivity.this, VideoActivity.class);
                String link_to_be_passed = link_list[(int) id];
                hello.putExtra(LINK, link_to_be_passed);
                startActivity(hello);
                //Toast.makeText(getApplicationContext(),link_list[(int) id],Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(intent);
                finish();
            }
        });
        if (isNetworkConnected(getApplicationContext())) {
            mSwipeContainer.setRefreshing(false);
            mSwipeContainer.setEnabled(false);
        }

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

    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager conManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
