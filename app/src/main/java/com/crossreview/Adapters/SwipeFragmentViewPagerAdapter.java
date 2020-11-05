package com.crossreview.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.crossreview.Fragment.SwipeFragment;
import com.crossreview.R;

import java.util.List;

public class SwipeFragmentViewPagerAdapter extends FragmentPagerAdapter {

    Fragment fragment =null;
    Integer [] backgroundImage={R.drawable.ic_slider1,R.drawable.ic_slider2,R.drawable.ic_slider3};

    public SwipeFragmentViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public SwipeFragmentViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for (int i = 0; i < 3; i++) {
            if (i == position) {
                fragment = SwipeFragment.newInstance(backgroundImage[i]);
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
