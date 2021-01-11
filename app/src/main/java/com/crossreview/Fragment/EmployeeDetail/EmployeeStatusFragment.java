package com.crossreview.Fragment.EmployeeDetail;

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
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationStatusFragment;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class EmployeeStatusFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Observer<ClsSaveEmployeeDetailModel> {

    private View mview;
    private Context mctx;
    private String heading, data;
    private RelativeLayout employer_Detail_rl;
    private CardView next_btn;
    private RadioGroup emp_status_Rg;
    private RadioButton radioButton;
    private String emp_experience="";
    private Boolean empStatus = true;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private int radioId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            heading = getArguments().getString(KeyClass.Heading);
            data = getArguments().getString(KeyClass.Data);


        }

        viewModelSetup();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_employee_status, container, false);
        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        bindview();
        viewSetup();
    }

    private void bindview() {

        employer_Detail_rl = mview.findViewById(R.id.employer_Detail_rl);

        next_btn = mview.findViewById(R.id.next_btn);

        emp_status_Rg = mview.findViewById(R.id.emp_status_Rg);


    }

    private void viewModelSetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);

    }

    private void viewSetup() {

        employer_Detail_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        emp_status_Rg.setOnCheckedChangeListener(this);


//        boolean rb = PrefrenceShared.getInstance().getPreferenceData().getValueBooleanFromKey(Constant.EmployeeStatus);
//
//        radioId = emp_status_Rg.getCheckedRadioButtonId();
//        RadioButton b=(RadioButton)mview.findViewById(radioId);
//
//
//            if (rb) {
//
//                b.setChecked(true);
//
//            }

        if (PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployeeStatus).equalsIgnoreCase("false")) {

            if(radioButton!=null) {
                radioId = emp_status_Rg.getCheckedRadioButtonId();
        RadioButton b=(RadioButton)mview.findViewById(radioId);

        b.setChecked(true);


            }
        }




        }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.employer_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), false, KeyClass.FRAGMENT_EMPLOYEE_DETAIL,
                        KeyClass.FRAGMENT_EMPLOYEE_DETAIL);

                break;

            case R.id.next_btn:

//                converJson();
                saveEmplyeeExperiece();


                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        radioId = emp_status_Rg.getCheckedRadioButtonId();

        radioButton = mview.findViewById(radioId);

//        Toast.makeText(mctx, radioButton + "", Toast.LENGTH_SHORT).show();

        emp_experience = radioButton.getText().toString();
        if (emp_experience.equalsIgnoreCase("experience")) {

            empStatus = true;

        } else {

            empStatus = false;
        }


    }


    public void saveEmplyeeExperiece() {

        JsonObject object = new JsonObject();
        object.addProperty(Constant.EmployeeExperience, empStatus);

//        JsonObject jsonObject= new JsonObject();
//        jsonObject.add(Constant.data,object);

        JsonObject data = new JsonObject();
        data.add(Constant.data, object);

        employeeDetailsViewModel.saveEmployeeDetail(data);


    }

    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        if (empStatus) {

            ((MainActivity) getActivity()).replaceFragment(new EmployementDetailsFragment(), true, KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS,
                    KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS);


        } else {

            ((MainActivity) getActivity()).replaceFragment(new EducationStatusFragment(), true, KeyClass.FRAGMENT_EDUCATION_STATUS,
                    KeyClass.FRAGMENT_EDUCATION_STATUS);


        }

        PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmployeeStatus, (String.valueOf(empStatus)));


    }
}