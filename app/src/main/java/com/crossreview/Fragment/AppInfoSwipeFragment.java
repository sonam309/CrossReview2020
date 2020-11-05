package com.crossreview.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crossreview.Adapters.SwipeFragmentViewPagerAdapter;
import com.crossreview.R;


public class AppInfoSwipeFragment extends Fragment {

    private View mview;
    private Context mctx;
    private ViewPager swipe_view_pager;
    private SwipeFragmentViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mview==null) {
            mctx=getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_app_info_swipe, container, false);
        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindView();
        viewModelSetup();
        viewSetup();
    }

    private void bindView() {


        swipe_view_pager=mview.findViewById(R.id.swipe_view_pager);

    }

    private void viewModelSetup() {

    }

    private void viewSetup() {

        adapter= new SwipeFragmentViewPagerAdapter(getChildFragmentManager());
        swipe_view_pager.setAdapter(adapter);





    }
}