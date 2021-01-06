package com.crossreview.Fragment.Welcome;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationDetailFragment;
import com.crossreview.Fragment.EducationDetail.EducationStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Fragment.EmployerInformationFragment;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;

public class WelcomeFragment extends Fragment implements View.OnClickListener
{
    private View mview;
    private Context mctx;
    private TextView varify_now_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_welcome, container, false);
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

        varify_now_btn=mview.findViewById(R.id.varify_now_btn);

    }

    private void viewModelSetup() {

    }

    private void viewSetup() {

        varify_now_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.varify_now_btn:


                    ((MainActivity) getActivity()).replaceFragment(new EmployementDetailsFragment(), false, KeyClass.FRAGMENT_EMPLOYER_INFORMATION,
                            KeyClass.FRAGMENT_EMPLOYER_INFORMATION);


                break;
        }

    }
}