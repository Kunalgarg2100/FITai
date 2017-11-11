package com.example.user.fitai.adapter;

/**
 * Created by kunal on 7/11/17.
 */

import com.example.user.fitai.PlayerConfig;
import com.example.user.fitai.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason;
import com.google.android.youtube.player.YouTubeThumbnailView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private YouTubeThumbnailLoader youTubeThumbnailLoader;
    private String link_list[];
    private String title_list[];

    public CustomListAdapter(Context context, String[] titles, String[] links) {
        super(context, R.layout.custom_layout, titles);
        this.link_list = links;
        this.title_list = titles;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom_view = inflater.inflate(R.layout.custom_layout, parent, false);
        TextView txt = custom_view.findViewById(R.id.title);
        YouTubeThumbnailView thumbnail = custom_view.findViewById(R.id.thumbnail);
        String title_name = title_list[position];
        final String video_id = link_list[position];
        txt.setText(title_name);
        YouTubeThumbnailView.OnInitializedListener onInitializedListener = new YouTubeThumbnailView.
                OnInitializedListener() {

            @Override
            public void onInitializationFailure(YouTubeThumbnailView thumbnailView,
                                                YouTubeInitializationResult error) {

                Toast.makeText(getContext(),
                        "Page Could not be generated, Please restart the app.",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onInitializationSuccess(YouTubeThumbnailView thumbnailView,
                                                YouTubeThumbnailLoader thumbnailLoader) {

                youTubeThumbnailLoader = thumbnailLoader;
                thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());

                youTubeThumbnailLoader.setVideo(video_id);

            }

        };
        thumbnail.initialize(PlayerConfig.API_KEY, onInitializedListener);
        return custom_view;
    }

    private final class ThumbnailLoadedListener implements
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onThumbnailError(YouTubeThumbnailView arg0, ErrorReason arg1) {
            Toast.makeText(getContext(),
                    "Thumbnails could not be loaded, Please check internet connection",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView arg0, String arg1) {
        }
    }
}