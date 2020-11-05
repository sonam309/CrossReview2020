package com.crossreview.Fragment.CriminalDetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationDetailFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Fragment.PreviewFragment;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.google.gson.JsonObject;


public class CriminalBackgroundStatusFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Observer<ClsSaveEmployeeDetailModel> {

    private View mview;
    private Context mctx;
    private RelativeLayout employer_Detail_rl, employment_Detail_rl, education_detail_rl;
    private RadioGroup criminal_bg_Rg;
    private RadioButton radioButton;
    private String policeVarification;
    private Boolean criminal_status = false;
    private CardView next_btn;
    private EmployeeDetailsViewModel employeeDetailsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_criminal_background_status, container, false);
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

        employer_Detail_rl = mview.findViewById(R.id.employer_Detail_rl);
        employment_Detail_rl = mview.findViewById(R.id.employment_Detail_rl);
        education_detail_rl = mview.findViewById(R.id.education_detail_rl);
        criminal_bg_Rg = mview.findViewById(R.id.criminal_bg_Rg);
        next_btn = mview.findViewById(R.id.next_btn);


    }

    private void viewModelSetup() {

    }

    private void viewSetup() {

        employer_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        education_detail_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        criminal_bg_Rg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.employer_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), true, KeyClass.FRAGMENT_EMPLOYEE_DETAIL,
                        KeyClass.FRAGMENT_EMPLOYEE_DETAIL);

                break;

            case R.id.employment_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployementDetailsFragment(), true, KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS,
                        KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS);

                break;
            case R.id.education_detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EducationDetailFragment(), true, KeyClass.FRAGMENT_EDUCATION_DETAIL,
                        KeyClass.FRAGMENT_EDUCATION_DETAIL);

                break;

            case R.id.next_btn:

                savepolicevarification();

                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        int radioId = criminal_bg_Rg.getCheckedRadioButtonId();
        radioButton = mview.findViewById(radioId);

        policeVarification = radioButton.getText().toString();

        if (policeVarification.equalsIgnoreCase("no")) {

            criminal_status = false;
        } else {

            criminal_status = true;
        }

    }

    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

    }

    public void savepolicevarification() {

        JsonObject object = new JsonObject();
        object.addProperty(Constant.EmployeePoliceVarification, criminal_status);

        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Constant.data, object);

        JsonObject data = new JsonObject();
        data.add(Constant.data, jsonObject);
        if (employeeDetailsViewModel != null) {

            employeeDetailsViewModel.saveEmployeeDetail(data);
        }

        if (criminal_status) {

            ((MainActivity) getActivity()).replaceFragment(new CriminalDetailCompleteFragment(), true, KeyClass.FRAGMENT_CRIMINAL_DETAILS,
                    KeyClass.FRAGMENT_CRIMINAL_DETAILS);

        } else {

            ((MainActivity) getActivity()).replaceFragment(new PreviewFragment(), true, KeyClass.FRAGMENT_PREVIEW,
                    KeyClass.FRAGMENT_PREVIEW);

        }

    }
}