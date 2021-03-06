package com.example.user.fitai;

/**
 * Created by nirvan on 30/10/17.
 */

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.fitai.adapter.CustomWorkoutsAdapter;
import com.example.user.fitai.adapter.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private CustomWorkoutsAdapter adapter;
    private List<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //initCollapsingToolbar();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        workoutList = new ArrayList<>();
        adapter = new CustomWorkoutsAdapter(this, workoutList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareWorkouts();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll

     private void initCollapsingToolbar() {
     final CollapsingToolbarLayout collapsingToolbar =
     (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
     collapsingToolbar.setTitle(" ");
     AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
     appBarLayout.setExpanded(true);

     // hiding & showing the title when toolbar expanded & collapsed
     appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
     boolean isShow = false;
     int scrollRange = -1;

     @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
     if (scrollRange == -1) {
     scrollRange = appBarLayout.getTotalScrollRange();
     }
     if (scrollRange + verticalOffset == 0) {

     collapsingToolbar.setTitle(getString(R.string.app_name));
     isShow = true;
     } else if (isShow) {
     collapsingToolbar.setTitle(" ");
     isShow = false;
     }
     }
     });
     }*/

    /**
     * Adding few workouts for testing
     */
    private void prepareWorkouts() {
        int[] covers = new int[]{
                R.drawable.pilates,
                R.drawable.zumba,
                R.drawable.suryanamaskar,
                R.drawable.aerobics,
                R.drawable.yoga,
                R.drawable.brainyoga,
                R.drawable.deskyoga,
                R.drawable.onflightyoga,
                R.drawable.meditation,


        };

        Workout a = new Workout("Pilates", "This is a must if you want to increase your strength", covers[0]);
        workoutList.add(a);

        a = new Workout("Zumba", "Improves strengthening of leg and thigh muscles", covers[1]);
        workoutList.add(a);

        a = new Workout("Suryanamaskar", "Really important part of a workout", covers[2]);
        workoutList.add(a);

        a = new Workout("Aerobics", "Improves muscle strength ", covers[3]);
        workoutList.add(a);
        a = new Workout("Yoga", "For getting an edge over others :D", covers[4]);
        workoutList.add(a);
        a = new Workout("Brain Yoga", "Improves flexibility and relaxes joints", covers[5]);
        workoutList.add(a);
        a = new Workout("Desk Yoga", "Desk yoga and relaxes joints", covers[6]);
        workoutList.add(a);
        a = new Workout("On flight Yoga", "On flight yoga and relaxes joints", covers[7]);
        workoutList.add(a);
        a = new Workout("Meditation", "Meditation gives peace of mind", covers[8]);
        workoutList.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}