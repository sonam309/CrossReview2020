package com.crossreview.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.CriminalDetail.CriminalBackgroundStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.CompanyNameViewModel;
import com.crossreview.ViewModel.EmployerDataViewModel;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;


public class EmployerInformationFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, Observer<CompanyNameModel>, CompoundButton.OnCheckedChangeListener {
    private View mview;
    private Context mctx;
    private LinearLayout mainll;
    private TextView countinue_btn;
    private MaterialCheckBox checkbox;
    private EditText txt_designation_et, txt_phone_et, txt_email_et, txt_name_et;
    private String heading;
    private AutoCompleteTextView txt_orgName_Autocomplete;
    private CompanyNameViewModel companyNameViewModel;
    private ArrayAdapter<CompanyNameModel.Data> companyNameModelArrayAdapter;
    private Handler handler = new Handler();
    private EmployerDataViewModel saveEmployerDataViewModel;
    private String empName, empEmail, empContact, empOrgName, empDesignation, orgNameId,token;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            heading = getArguments().getString(KeyClass.Heading);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_employer_information, container, false);
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

        //LinearLayout
        mainll = mview.findViewById(R.id.mainll);

        //Button
        countinue_btn = mview.findViewById(R.id.countinue_btn);

        //Checkbox
        checkbox = mview.findViewById(R.id.checkbox);

        //EditText
        txt_designation_et = mview.findViewById(R.id.txt_designation_et);
        txt_orgName_Autocomplete = mview.findViewById(R.id.orgName_Autocomplete);
        txt_phone_et = mview.findViewById(R.id.txt_phone_et);
        txt_email_et = mview.findViewById(R.id.txt_email_et);
        txt_name_et = mview.findViewById(R.id.txt_name_et);


    }

    private void viewModelSetup() {

        companyNameViewModel = new ViewModelProvider(this).get(CompanyNameViewModel.class);
        companyNameViewModel.companyName.observe(getViewLifecycleOwner(), this);
        saveEmployerDataViewModel = new ViewModelProvider(this).get(EmployerDataViewModel.class);
        saveEmployerDataViewModel.EmployerData.observe(getViewLifecycleOwner(), employerResponseModelObserver);


    }

    private void viewSetup() {



        countinue_btn.setOnClickListener(this);
        mainll.setOnTouchListener(this);
        onadapterItemClick();


        final CompanyNameModel[] model = new CompanyNameModel[1];
        txt_orgName_Autocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final ArrayAdapter<CompanyNameModel>[] adapter = new ArrayAdapter[]{new ArrayAdapter<>(MainActivity.context, R.layout.support_simple_spinner_dropdown_item, new ArrayList<CompanyNameModel>())};
                txt_orgName_Autocomplete.setAdapter(adapter[0]);
                model[0] = null;
                if (model[0] == null) {
                    setTimer(txt_orgName_Autocomplete);
                } else {
                    txt_orgName_Autocomplete.setAdapter(adapter[0]);
                }
            }
        });



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.countinue_btn:

                saveEmployerDetails();

                break;


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.mainll:

                Utility.hideKeyboard(view);

                txt_name_et.clearFocus();
                txt_email_et.clearFocus();
                txt_phone_et.clearFocus();
                txt_orgName_Autocomplete.clearFocus();
                txt_designation_et.clearFocus();


                break;
        }

        return false;
    }

    @Override
    public void onChanged(CompanyNameModel companyNameModel) {

        companyNameModelArrayAdapter = new ArrayAdapter<>(MainActivity.context, R.layout.support_simple_spinner_dropdown_item, companyNameModel.getData());
        txt_orgName_Autocomplete.setAdapter(companyNameModelArrayAdapter);
        txt_orgName_Autocomplete.showDropDown();



    }

    private final Observer<ClsEmployerResponseModel> employerResponseModelObserver = new Observer<ClsEmployerResponseModel>() {
        @Override
        public void onChanged(ClsEmployerResponseModel clsEmployerResponseModel) {

            //setData

            token=clsEmployerResponseModel.getData().getAccessToken();
            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.TOKEN,token);


        }
    };

    public void setTimer(final AutoCompleteTextView autoCompleteTextView) {
        handler.removeMessages(0);
        if (autoCompleteTextView.getText().toString().length() > 0) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            autoCompleteTextView.dismissDropDown();


                        }
                    });

                    companyNameViewModel.ComNamefun(autoCompleteTextView.getText().toString());
                }

            }, 1000);
        }
    }


    public void saveEmployerDetails() {

        empName = txt_name_et.getText().toString();
        empEmail = txt_email_et.getText().toString();
        empContact = txt_phone_et.getText().toString();
        empDesignation = txt_designation_et.getText().toString();

        if (empName.isEmpty()) {

            Toast.makeText(mctx, "Please enter Employer Name", Toast.LENGTH_LONG).show();
            txt_name_et.requestFocus();
            return;
        } else {
            txt_name_et.clearFocus();
        }
        if (empEmail.isEmpty()) {
            if (!(Utility.isValidMail(empEmail))) {

                Toast.makeText(mctx, "Please enter Valid EmailId", Toast.LENGTH_LONG).show();
                txt_email_et.requestFocus();

            } else {

                txt_email_et.clearFocus();
            }

            Toast.makeText(mctx, "Please enter Employer Email", Toast.LENGTH_LONG).show();
            txt_email_et.requestFocus();
            return;

        } else {

            txt_email_et.clearFocus();
        }
        if (empContact.isEmpty()) {
            if (Utility.isValidMobile(empContact)) {

                Toast.makeText(mctx, "Please enter valid Contact Number", Toast.LENGTH_LONG).show();
                txt_phone_et.requestFocus();
            } else {
                txt_phone_et.clearFocus();
            }

            Toast.makeText(mctx, "Please enter Contact Number", Toast.LENGTH_LONG).show();
            txt_phone_et.requestFocus();
            return;
        } else {

            txt_phone_et.clearFocus();
        }
        if (empOrgName.isEmpty()) {

            Toast.makeText(mctx, "Please Select Organzation Name", Toast.LENGTH_SHORT).show();
            txt_orgName_Autocomplete.requestFocus();
            return;
        } else {

            txt_orgName_Autocomplete.clearFocus();
        }
        if (empDesignation.isEmpty()) {

            Toast.makeText(mctx, "Please enter Designation", Toast.LENGTH_LONG).show();
            txt_designation_et.requestFocus();
            return;
        } else {

            txt_designation_et.clearFocus();
        }
        if (!checkbox.isChecked()) {

            Toast.makeText(mctx, "Please accept Terms & conditions", Toast.LENGTH_LONG).show();
            return;

        }


        saveEmployerDataViewModel.saveEmpData(empName, empEmail, empContact, orgNameId, empDesignation);


        ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), false, KeyClass.FRAGMENT_EMPLOYEE_DETAIL,
                KeyClass.FRAGMENT_EMPLOYEE_DETAIL);


    }

    public void onadapterItemClick() {

        txt_orgName_Autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CompanyNameModel.Data data = companyNameModelArrayAdapter.getItem(i);

                orgNameId = data.getOrganizationId();
                empOrgName=data.getOrganizationName();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(b){

            txt_name_et.clearFocus();
            txt_email_et.clearFocus();
            txt_phone_et.clearFocus();
            txt_orgName_Autocomplete.clearFocus();
            txt_designation_et.clearFocus();
        }

    }
}