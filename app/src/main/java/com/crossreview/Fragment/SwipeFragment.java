package com.crossreview.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crossreview.Activity.MainActivity;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;

public class SwipeFragment extends Fragment implements View.OnClickListener {

    private int mParam1;
    private ImageView imageslider;
    private TextView txt_done_btn;


    public static SwipeFragment newInstance(int image) {
        SwipeFragment fragment = new SwipeFragment();
        Bundle args = new Bundle();
        args.putInt(KeyClass.IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(KeyClass.IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageslider = view.findViewById(R.id.imageslider);
        txt_done_btn = view.findViewById(R.id.txt_done_btn);


        imageslider.setImageResource(mParam1);
        txt_done_btn.setOnClickListener(this);


        if (mParam1 == R.drawable.ic_slider3) {

            txt_done_btn.setVisibility(View.VISIBLE);
        } else {

            txt_done_btn.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txt_done_btn:

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();


                break;
        }

    }
}