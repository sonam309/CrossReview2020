package com.crossreview.Fragment.EducationDetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.CriminalDetail.CriminalBackgroundStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.google.gson.JsonObject;

import java.security.Key;


public class EducationStatusFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Observer<ClsSaveEmployeeDetailModel> {

    private View mview;
    private Context mctx;
    private RelativeLayout employer_Detail_rl, employment_Detail_rl, employee_status_rl;
    private RadioGroup edu_status_Rg;
    private RadioButton radioButton, txt_edu_yes_rb, txt_edu_no_rb;
    private CardView next_btn;
    private String edu_varification;
    private Boolean edu_status = true;
    private EmployeeDetailsViewModel employeeDetailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModelSetup();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_education_status, container, false);
        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindView();
        viewSetup();

    }

    private void bindView() {

        employer_Detail_rl = mview.findViewById(R.id.employer_Detail_rl);
        employment_Detail_rl = mview.findViewById(R.id.employment_Detail_rl);
        employee_status_rl = mview.findViewById(R.id.employee_status_rl);
        txt_edu_yes_rb = mview.findViewById(R.id.txt_edu_yes_rb);
        txt_edu_no_rb = mview.findViewById(R.id.txt_edu_no_rb);


        edu_status_Rg = mview.findViewById(R.id.edu_status_Rg);


        next_btn = mview.findViewById(R.id.next_btn);

    }

    private void viewModelSetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);

    }

    private void viewSetup() {

        employer_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        employee_status_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        edu_status_Rg.setOnCheckedChangeListener(this);

        String eduStatus = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EducationStatus);
        String empStatus=PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployeeStatus);

        if (eduStatus != null) {
            if (eduStatus.equalsIgnoreCase("NO")) {

//            radioButton.setChecked(true);
                txt_edu_no_rb.setChecked(true);
                txt_edu_yes_rb.setChecked(false);


            } else {

                txt_edu_no_rb.setChecked(false);
                txt_edu_yes_rb.setChecked(true);


            }
        }

        if(empStatus!=null){

            if(empStatus.equalsIgnoreCase("Fresher")){

                employment_Detail_rl.setVisibility(View.GONE);


            }else {

                employment_Detail_rl.setVisibility(View.VISIBLE);


            }
        }
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

            case R.id.employee_status_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeStatusFragment(), true, KeyClass.FRAGMENT_EMPLOYEE_STATUS,
                        KeyClass.FRAGMENT_EMPLOYEE_STATUS);

                break;

            case R.id.next_btn:

                saveEduVarification();

                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {


        int radioId = edu_status_Rg.getCheckedRadioButtonId();

        radioButton = mview.findViewById(radioId);

        edu_varification = radioButton.getText().toString();

        if (edu_varification.equalsIgnoreCase("Yes")) {

            edu_status = true;

        } else {

            edu_status = false;
        }

    }


    public void saveEduVarification() {

        JsonObject object = new JsonObject();
        object.addProperty(Constant.EmployeeeducationVarification, edu_status);

//        JsonObject jsonObject= new JsonObject();
//        jsonObject.add(Constant.data,object);

        JsonObject data = new JsonObject();
        data.add(Constant.data, object);

        employeeDetailsViewModel.saveEmployeeDetail(data);


    }

    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        if (edu_status) {

            ((MainActivity) getActivity()).replaceFragment(new EducationDetailFragment(), true, KeyClass.FRAGMENT_EDUCATION_DETAIL,
                    KeyClass.FRAGMENT_EDUCATION_DETAIL);


        } else if (!edu_status) {

            ((MainActivity) getActivity()).replaceFragment(new CriminalBackgroundStatusFragment(), true, KeyClass.FRAGMENT_CRIMINAL_STATUS,
                    KeyClass.FRAGMENT_CRIMINAL_STATUS);
        }

        PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EducationStatus, edu_varification);

    }
}