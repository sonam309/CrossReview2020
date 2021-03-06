package com.crossreview.Fragment.CriminalDetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationDetailFragment;
import com.crossreview.Fragment.EducationDetail.EducationStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Fragment.PreviewFragment;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.google.gson.JsonObject;


public class CriminalBackgroundStatusFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Observer<ClsSaveEmployeeDetailModel>, View.OnTouchListener {

    private Context mctx;
    private RelativeLayout employer_Detail_rl, employment_Detail_rl, education_detail_rl, education_status_rl,employee_status_rl;
    private RadioGroup criminal_bg_Rg;
    private RadioButton radioButton, txt_yes_criminal_bg_rb, txt_no_criminal_bg_rb;
    private String policeVarification = "no";
    private Boolean criminal_status = false;
    private CardView next_btn;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private LinearLayout mainll;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelSetup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mctx = getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_criminal_background_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        viewSetup();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void bindView(View mview) {

        employer_Detail_rl = mview.findViewById(R.id.employer_Detail_rl);
        employment_Detail_rl = mview.findViewById(R.id.employment_Detail_rl);
        education_detail_rl = mview.findViewById(R.id.education_detail_rl);
        criminal_bg_Rg = mview.findViewById(R.id.criminal_bg_Rg);
        next_btn = mview.findViewById(R.id.next_btn);
        mainll = mview.findViewById(R.id.mainll);
        education_status_rl = mview.findViewById(R.id.education_status_rl);
        employee_status_rl = mview.findViewById(R.id.employee_status_rl);
        txt_yes_criminal_bg_rb = mview.findViewById(R.id.txt_yes_criminal_bg_rb);
        txt_no_criminal_bg_rb = mview.findViewById(R.id.txt_no_criminal_bg_rb);


    }

    private void viewModelSetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);


    }

    private void viewSetup() {

        String criStatus = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.CriminalBgStatus);
        String empStatus = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployeeStatus);
        String eduStatus = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EducationStatus);
        if (criStatus != null) {
            if (criStatus.equalsIgnoreCase("true")) {


                txt_yes_criminal_bg_rb.setChecked(true);
                txt_no_criminal_bg_rb.setChecked(false);

            } else {

                txt_yes_criminal_bg_rb.setChecked(false);
                txt_no_criminal_bg_rb.setChecked(true);

            }
        }

        if (empStatus != null) {

            if (empStatus.equalsIgnoreCase("Fresher")) {

                employment_Detail_rl.setVisibility(View.GONE);
            } else {

                employment_Detail_rl.setVisibility(View.VISIBLE);
            }

        }

        if (eduStatus != null) {

            if (eduStatus.equalsIgnoreCase("No")) {

                education_detail_rl.setVisibility(View.GONE);

            } else {

                education_detail_rl.setVisibility(View.VISIBLE);
            }

        }

        employer_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        education_detail_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        education_status_rl.setOnClickListener(this);
        criminal_bg_Rg.setOnCheckedChangeListener(this);
        mainll.setOnTouchListener(this);
        employee_status_rl.setOnTouchListener(this);


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

            case R.id.education_status_rl:

                ((MainActivity) getActivity()).replaceFragment(new EducationStatusFragment(), true, KeyClass.FRAGMENT_EDUCATION_STATUS,
                        KeyClass.FRAGMENT_EDUCATION_STATUS);

                break;

            case R.id.employee_status_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeStatusFragment(), true, KeyClass.FRAGMENT_EMPLOYEE_STATUS,
                        KeyClass.FRAGMENT_EMPLOYEE_STATUS);

                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup.getId() == R.id.criminal_bg_Rg) {
            int radioId = criminal_bg_Rg.getCheckedRadioButtonId();
            radioButton = radioGroup.findViewById(radioId);

            policeVarification = radioButton.getText().toString();

            if (policeVarification.equalsIgnoreCase("no")) {

                criminal_status = false;
            } else {

                criminal_status = true;
            }

        }
    }


    public void savepolicevarification() {

        JsonObject object = new JsonObject();
        object.addProperty(Constant.EmployeePoliceVarification, criminal_status);

//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add(Constant.data, object);

        JsonObject data = new JsonObject();
        data.add(Constant.data, object);


        employeeDetailsViewModel.saveEmployeeDetail(data,getActivity());


    }

    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        if (criminal_status) {

            ((MainActivity) getActivity()).replaceFragment(new CriminalDetailFragment(), true, KeyClass.FRAGMENT_CRIMINAL_DETAILS,
                    KeyClass.FRAGMENT_CRIMINAL_DETAILS);

        } else {

            ((MainActivity) getActivity()).replaceFragment(new PreviewFragment(), true, KeyClass.FRAGMENT_PREVIEW,
                    KeyClass.FRAGMENT_PREVIEW);

        }

        PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.CriminalBgStatus, String.valueOf(criminal_status));

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.mainll:

                Utility.hideKeyboard(view);

                break;
        }
        return false;
    }
}