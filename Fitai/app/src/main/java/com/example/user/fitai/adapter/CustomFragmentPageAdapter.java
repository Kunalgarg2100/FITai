package com.example.user.fitai.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.fitai.fragment.HomeFragment;
import com.example.user.fitai.fragment.TrainingProgramsFragment;
public class CustomFragmentPageAdapter extends FragmentPagerAdapter{
    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT = 3;
    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new TrainingProgramsFragment();
            case 2:
                return new TrainingProgramsFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Workouts";
            case 2:
                return "Training Programs";
        }
        return null;
    }
}