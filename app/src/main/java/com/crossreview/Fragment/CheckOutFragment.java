package com.crossreview.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.crossreview.Activity.MainActivity;
import com.crossreview.CustomView.AddPoints_popup;
import com.crossreview.R;


public class CheckOutFragment extends Fragment implements View.OnClickListener {

    private View mview;
    private Context mctx;
    private TextView txt_avaliable_points, txt_employment_bg_check_tv, txt_employment_bg_check_points_tv, txt_employment_edu_check_tv,
            txt_employment_edu_check_points_tv, txt_employment_criminal_bg_check_tv, txt_employment_criminal_bg_check_points_tv,
            txt_total_paid_points_tv, txt_back_btn;
    private CardView pay_now_btn;
    AddPoints_popup addPointsPopup = new AddPoints_popup(MainActivity.context, R.style.DialogDim);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_check_out, container, false);

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

        txt_avaliable_points = mview.findViewById(R.id.txt_avaliable_points);
        txt_employment_bg_check_tv = mview.findViewById(R.id.txt_employment_bg_check_tv);
        txt_employment_bg_check_points_tv = mview.findViewById(R.id.txt_employment_bg_check_points_tv);
        txt_employment_edu_check_tv = mview.findViewById(R.id.txt_employment_edu_check_tv);
        txt_employment_edu_check_points_tv = mview.findViewById(R.id.txt_employment_edu_check_points_tv);
        txt_employment_criminal_bg_check_tv = mview.findViewById(R.id.txt_employment_criminal_bg_check_tv);
        txt_employment_criminal_bg_check_points_tv = mview.findViewById(R.id.txt_employment_criminal_bg_check_points_tv);
        txt_total_paid_points_tv = mview.findViewById(R.id.txt_total_paid_points_tv);


        txt_back_btn = mview.findViewById(R.id.txt_back_btn);
        pay_now_btn = mview.findViewById(R.id.pay_now_btn);
    }

    private void viewModelSetup() {

    }

    private void viewSetup() {

        txt_back_btn.setOnClickListener(this);
        pay_now_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txt_back_btn:

                getActivity().onBackPressed();

                break;

            case R.id.pay_now_btn:

                openDialog();
                break;
        }

    }

    private void openDialog(){

        addPointsPopup.show();
        Window window = addPointsPopup.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }
}