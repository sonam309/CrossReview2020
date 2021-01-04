package com.crossreview.Fragment;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
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

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.ArrayList;


public class EmployerInformationFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, Observer<CompanyNameModel>, CompoundButton.OnCheckedChangeListener, TextWatcher {
    private View mview;
    private Context mctx;
    private LinearLayout mainll, llmain;
    private TextView countinue_btn;
    private MaterialCheckBox checkbox;
    private EditText txt_designation_et, txt_phone_et, txt_email_et, txt_name_et;
    private String heading;
    private AutoCompleteTextView txt_orgName_Autocomplete;
    private CompanyNameViewModel companyNameViewModel;
    private ArrayAdapter<CompanyNameModel.orgList> companyNameModelArrayAdapter;
    private Handler handler = new Handler();
    private EmployerDataViewModel saveEmployerDataViewModel;
    private String empName, empEmail, empContact, empOrgName, empDesignation, orgNameId, token;
    private ProgressDialog progressDialog;
    private TextView txt_name_tv_error, txt_email_tv_error, txt_phone_tv_error, txt_org_tv_error, txt_designation_tv_error, checkbox_error;
    private ProgressBar progressLoading;

    public EmployerInformationFragment() {


    }

    public static EmployerInformationFragment newInstance() {

        return new EmployerInformationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            heading = getArguments().getString(KeyClass.Heading);

        }
        viewModelSetup();

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
        viewSetup();

    }

    private void bindView() {

        //LinearLayout
        mainll = mview.findViewById(R.id.mainll);
        llmain = mview.findViewById(R.id.llmain);

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

        //TextView
        txt_name_tv_error = mview.findViewById(R.id.txt_name_tv_error);
        txt_email_tv_error = mview.findViewById(R.id.txt_email_tv_error);
        txt_phone_tv_error = mview.findViewById(R.id.txt_phone_tv_error);
        txt_org_tv_error = mview.findViewById(R.id.txt_org_tv_error);
        txt_designation_tv_error = mview.findViewById(R.id.txt_designation_tv_error);
        checkbox_error = mview.findViewById(R.id.checkbox_error);

        progressLoading = mview.findViewById(R.id.progressLoading);


    }

    private void viewModelSetup() {

        companyNameViewModel = new ViewModelProvider(this).get(CompanyNameViewModel.class);
        companyNameViewModel.companyName.observe(this, this);
        saveEmployerDataViewModel = new ViewModelProvider(this).get(EmployerDataViewModel.class);
        saveEmployerDataViewModel.EmployerData.observe(this, employerResponseModelObserver);


    }

    private void viewSetup() {

        String jsonData = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployerInformation);

        if (jsonData != null) {

            try {
                JSONObject object = new JSONObject(jsonData);

                if (object != null && !object.equals("")) {

                    txt_name_et.setText(object.getString("Employer_Email"));
                    txt_email_et.setText(object.getString("Employer_Name"));
                    txt_phone_et.setText("Employer_Contact");
                    txt_orgName_Autocomplete.setText("Organization_Id");
                    txt_designation_et.setText("Employer_Desigination");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        progressDialog = new ProgressDialog(mctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        checkbox.setOnCheckedChangeListener(this);


        txt_name_et.addTextChangedListener(this);
        txt_email_et.addTextChangedListener(this);
        txt_phone_et.addTextChangedListener(this);
        txt_orgName_Autocomplete.addTextChangedListener(this);
        txt_designation_et.addTextChangedListener(this);


        countinue_btn.setOnClickListener(this);
        mainll.setOnTouchListener(this);
        llmain.setOnTouchListener(this);
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
            case R.id.llmain:

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


        companyNameModelArrayAdapter = new ArrayAdapter<>(MainActivity.context, R.layout.support_simple_spinner_dropdown_item, companyNameModel.getData().getOrgList());
        txt_orgName_Autocomplete.setAdapter(companyNameModelArrayAdapter);
        txt_orgName_Autocomplete.showDropDown();


    }

    private final Observer<ClsEmployerResponseModel> employerResponseModelObserver = new Observer<ClsEmployerResponseModel>() {
        @Override
        public void onChanged(ClsEmployerResponseModel clsEmployerResponseModel) {

            //setData

            token = clsEmployerResponseModel.getData().getAccessToken();
            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.TOKEN, token);


            ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), false, KeyClass.FRAGMENT_EMPLOYEE_DETAIL,
                    KeyClass.FRAGMENT_EMPLOYEE_DETAIL);


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

                    companyNameViewModel.ComNamefun(autoCompleteTextView.getText().toString(), progressLoading);
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

            if (empEmail.isEmpty()) {

                if (empContact.isEmpty()) {

                    if (empOrgName == null) {


                        if (empDesignation.isEmpty()) {


                            if (checkbox.isChecked()) {

                                checkbox_error.setVisibility(View.GONE);


                            } else {

                                checkbox_error.setVisibility(View.VISIBLE);
                            }

                            txt_designation_tv_error.setVisibility(View.VISIBLE);
                            txt_designation_et.requestFocus();

                        } else {

//                            txt_designation_tv_error.setVisibility(View.GONE);
                            txt_designation_et.clearFocus();
                        }

                        txt_org_tv_error.setVisibility(View.VISIBLE);
                        txt_orgName_Autocomplete.requestFocus();


                    } else {

                        txt_orgName_Autocomplete.clearFocus();
//                        txt_org_tv_error.setVisibility(View.GONE);
                    }

                    txt_phone_tv_error.setVisibility(View.VISIBLE);
                    txt_phone_et.requestFocus();

                } else {

                    txt_phone_et.clearFocus();
//                    txt_phone_tv_error.setVisibility(View.GONE);
                }

                txt_email_tv_error.setVisibility(View.VISIBLE);
                txt_email_et.requestFocus();

            } else {

//                txt_email_tv_error.setVisibility(View.GONE);
                txt_email_et.clearFocus();
            }

            txt_name_tv_error.setVisibility(View.VISIBLE);
            txt_name_et.requestFocus();


        } else {


            txt_name_et.clearFocus();


            saveEmployerDataViewModel.saveEmpData(empName, empEmail, empContact, orgNameId, empDesignation, getActivity());

        }


    }

    public void onadapterItemClick() {

        txt_orgName_Autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CompanyNameModel.orgList data = companyNameModelArrayAdapter.getItem(i);

                orgNameId = data.getOrganizationId();
                empOrgName = data.getOrganizationName();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b) {

            txt_name_et.clearFocus();
            txt_email_et.clearFocus();
            txt_phone_et.clearFocus();
            txt_orgName_Autocomplete.clearFocus();
            txt_designation_et.clearFocus();

            checkbox_error.setVisibility(View.GONE);
        } else {

            checkbox_error.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (txt_name_et.getText().length() > 0) {

            txt_name_tv_error.setVisibility(View.GONE);

        }
        if (txt_email_et.getText().length() > 0) {

            txt_email_tv_error.setVisibility(View.GONE);

        }
        if (txt_phone_et.getText().length() > 0) {

            txt_phone_tv_error.setVisibility(View.GONE);

        }
        if (empOrgName != null) {

            txt_org_tv_error.setVisibility(View.GONE);

        }
        if (txt_designation_et.getText().length() > 0) {

            txt_designation_tv_error.setVisibility(View.GONE);

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {


    }
}