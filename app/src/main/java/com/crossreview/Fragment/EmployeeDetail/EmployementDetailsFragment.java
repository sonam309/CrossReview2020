package com.crossreview.Fragment.EmployeeDetail;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.BasicClass;
import com.crossreview.Fragment.DocumentFragment;
import com.crossreview.Fragment.EducationDetail.EducationStatusFragment;
import com.crossreview.Interface.awsUploadCallback;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.DownloadPdfAndShowInImageView;
import com.crossreview.Utilites.FilePath;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.CompanyNameViewModel;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class EmployementDetailsFragment extends BasicClass implements View.OnClickListener, View.OnTouchListener, Observer<ClsSaveEmployeeDetailModel>, AdapterView.OnItemSelectedListener, awsUploadCallback, TextWatcher {
    private View mview;
    private Context mctx;
    private RelativeLayout employer_Detail_rl, txt_org_deatil_rl, date_of_joining_rl, date_of_relieving_rl, employee_Detail_rl,
            txt_reporting_person_rl, txt_upload_doc_rl, txt_add_employement_rl;
    private LinearLayout rl_org_deatil, emploeyment_mainll, repoting_person_rl, txt_upload_ll;

    private TextView txt_joining_date, txt_relieving_date, view_title, uploadBtn, doc_name, autotextcomplte;
    private CardView next_btn;
    private String data, stringCtcLac, stringCtcThousand, reportingPersonaName, reportingPersonaDesignation, orgNameId, OrgName;
    private LinearLayout addEmploymentDetailView, addEmploymentDetailTile, mainll, upload_doc;
    private ImageView iv_delete, doc_file, delete_iv;
    private Date dates;
    private Spinner ctc_in_lac_spinner, ctc_in_thous_spinner;
    private Boolean selectdate = false, to_varify = true;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private ClsSaveEmployeeDetailModel saveEmployeeDetailModel;
    private CompanyNameModel companyNameModel;
    private CompanyNameViewModel companyNameViewModel;
    private EditText txt_job_role_et, txt_designation_et, txt_reporting_person_et, txt_reporting_person_designataion_et, txt_reason_of_leaving_et;
    private Handler handler = new Handler();
    private ArrayAdapter<CompanyNameModel.Data> companyNameModelArrayAdapter;
    private ProgressDialog progressDialog;
    private TextView txt_doj_tv_error, txt_dor_tv_error, txt_designataion_tv_error, txt_jobRole_tv_error, txt_org_name_tv_error,
            txt_reason_of_leaving_tv_error, txt_ctc_tv_error, txt_reporting_person_tv_error, txt_reporting_person_designataion_tv_error,
            txt_upload_tv_error;
    private int count = 1;
    private ProgressBar progressLoading;
    private ArrayAdapter ctcInLacAdapter, ctcInthousAdapter;
    private String picUrl = "";
    private String temp = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

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
            mview = inflater.inflate(R.layout.fragment_employement_details, container, false);

            bindView();
            viewSetup();
        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void bindView() {


        emploeyment_mainll = mview.findViewById(R.id.emploeyment_mainll);
        addEmploymentDetailView = mview.findViewById(R.id.addEmploymentDetailView);
        addEmploymentDetailTile = mview.findViewById(R.id.addEmploymentDetailTile);
        txt_add_employement_rl = mview.findViewById(R.id.txt_add_employement_rl);
        mainll = mview.findViewById(R.id.mainll);
        next_btn = mview.findViewById(R.id.next_btn);
        employee_Detail_rl = mview.findViewById(R.id.employee_Detail_rl);


    }

    private void viewModelSetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);


        saveEmployeeDetailModel = new ClsSaveEmployeeDetailModel();

    }

    private void viewSetup() {

        progressDialog = new ProgressDialog(mctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);


        txt_add_employement_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        employee_Detail_rl.setOnClickListener(this);

        emploeyment_mainll.setOnTouchListener(this);
        mainll.setOnTouchListener(this);


        addEmployementView();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.employee_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), true, KeyClass.FRAGMENT_EMPLOYEE_DETAIL,
                        KeyClass.FRAGMENT_EMPLOYEE_DETAIL);

                break;


            case R.id.date_of_joining_rl:


                openDatePickerDialogDoj();

                break;

            case R.id.date_of_relieving_rl:


                openDatePickerDialogDor();

                break;

            case R.id.txt_org_deatil_rl:

                if (rl_org_deatil.getVisibility() == View.VISIBLE) {

                    rl_org_deatil.setVisibility(View.GONE);
                } else {

                    rl_org_deatil.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.txt_reporting_person_rl:

                if (repoting_person_rl.getVisibility() == View.VISIBLE) {

                    repoting_person_rl.setVisibility(View.GONE);
                } else {

                    repoting_person_rl.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.txt_upload_doc_rl:


                if (txt_upload_ll.getVisibility() == View.VISIBLE) {

                    txt_upload_ll.setVisibility(View.GONE);
                } else {

                    txt_upload_ll.setVisibility(View.VISIBLE);
                }

                break;


            case R.id.txt_add_employement_rl:

                addViewValidation();

                break;

            case R.id.next_btn:

                setSaveEmployeeDetail();

//                ((MainActivity) getActivity()).replaceFragment(new EducationStatusFragment(), true, KeyClass.FRAGMENT_EDUCATION_DETAIL,
//                        KeyClass.FRAGMENT_EDUCATION_DETAIL);

                break;

            case R.id.uploadBtn:

                checkPermission(true);

                break;


        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.emploeyment_mainll:
            case R.id.mainll:

                Utility.hideKeyboard(view);

                break;
        }
        return false;
    }


    public void addEmployementView() {


        LayoutInflater inflater = (LayoutInflater) mctx.getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.employement_detail_layout, null);


        txt_org_deatil_rl = view.findViewById(R.id.txt_org_deatil_rl);
        txt_reporting_person_rl = view.findViewById(R.id.txt_reporting_person_rl);
        txt_upload_doc_rl = view.findViewById(R.id.txt_upload_doc_rl);
        date_of_joining_rl = view.findViewById(R.id.date_of_joining_rl);
        date_of_relieving_rl = view.findViewById(R.id.date_of_relieving_rl);
        autotextcomplte = view.findViewById(R.id.autotextcomplte);


        rl_org_deatil = view.findViewById(R.id.rl_org_deatil);
        repoting_person_rl = view.findViewById(R.id.repoting_person_rl);
        txt_upload_ll = view.findViewById(R.id.txt_upload_ll);
        upload_doc = view.findViewById(R.id.upload_doc);

        txt_joining_date = view.findViewById(R.id.txt_joining_date);
        txt_relieving_date = view.findViewById(R.id.txt_relieving_date);
        txt_designation_et = view.findViewById(R.id.txt_designation_et);
        txt_job_role_et = view.findViewById(R.id.txt_job_role_et);
        view_title = view.findViewById(R.id.view_title);
        txt_reporting_person_et = view.findViewById(R.id.txt_reporting_person_et);
        txt_reporting_person_designataion_et = view.findViewById(R.id.txt_reporting_person_designataion_et);
        txt_reason_of_leaving_et = view.findViewById(R.id.txt_reason_of_leaving_et);
        uploadBtn = view.findViewById(R.id.uploadBtn);

        ctc_in_lac_spinner = view.findViewById(R.id.ctc_in_lac_spinner);
        ctc_in_thous_spinner = view.findViewById(R.id.ctc_in_thous_spinner);

        //Error TextView

        txt_doj_tv_error = view.findViewById(R.id.txt_doj_tv_error);
        txt_dor_tv_error = view.findViewById(R.id.txt_dor_tv_error);
        txt_designataion_tv_error = view.findViewById(R.id.txt_designataion_tv_error);
        txt_jobRole_tv_error = view.findViewById(R.id.txt_jobRole_tv_error);
        txt_org_name_tv_error = view.findViewById(R.id.txt_org_name_tv_error);
        txt_reason_of_leaving_tv_error = view.findViewById(R.id.txt_reason_of_leaving_tv_error);
        txt_ctc_tv_error = view.findViewById(R.id.txt_ctc_tv_error);
        txt_reporting_person_tv_error = view.findViewById(R.id.txt_reporting_person_tv_error);
        txt_reporting_person_designataion_tv_error = view.findViewById(R.id.txt_reporting_person_designataion_tv_error);
        txt_upload_tv_error = view.findViewById(R.id.txt_upload_tv_error);


        progressLoading = view.findViewById(R.id.progressLoading);


        AutoCompleteTextView txt_orgName_Autocomplete = view.findViewById(R.id.txt_orgName_Autocomplete);


        viewOnclick();
        setSpinnerAdapter();
        getDataFromPrefrence();


        final String[] model = new String[1];
        txt_orgName_Autocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                temp = s.toString().trim();


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (txt_orgName_Autocomplete.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    return;
                }


                final ArrayAdapter<CompanyNameModel>[] adapter = new ArrayAdapter[]{new ArrayAdapter<>(MainActivity.context, R.layout.autocomplte_textview_layout, new ArrayList<CompanyNameModel>())};

//             txt_orgName_Autocomplete.setThreshold(1);
                txt_orgName_Autocomplete.setAdapter(adapter[0]);
//                model[0] = null;
//                if (model[0] == null) {
//                    setTimer(txt_orgName_Autocomplete);
//                } else {
//                    txt_orgName_Autocomplete.setAdapter(adapter[0]);
//                }

                if (model[0] == null || !model[0].equalsIgnoreCase(temp)) {
                    setTimer(txt_orgName_Autocomplete);
                } else {
                    txt_orgName_Autocomplete.setAdapter(adapter[0]);
                }

            }
        });


        uploadDocuments();


        addEmploymentDetailView.addView(view);

        companyNameViewModel = new ViewModelProvider(this).get(CompanyNameViewModel.class);
        companyNameViewModel.companyName.observe(getViewLifecycleOwner(), new Observer<CompanyNameModel>() {
            @Override
            public void onChanged(CompanyNameModel companyNameModel) {
                companyNameModelArrayAdapter = new ArrayAdapter<>(MainActivity.context, R.layout.autocomplte_textview_layout, companyNameModel.getData());
                txt_orgName_Autocomplete.setAdapter(companyNameModelArrayAdapter);
                txt_orgName_Autocomplete.showDropDown();
//                txt_orgName_Autocomplete.setText(OrgName);


            }
        });
        txt_orgName_Autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CompanyNameModel.Data data = (CompanyNameModel.Data) companyNameModelArrayAdapter.getItem(i);


                model[0] = data.getOrganizationName();

                orgNameId = data.getOrganizationId();
                OrgName = data.getOrganizationName();

                txt_org_name_tv_error.setVisibility(View.GONE);
                autotextcomplte.setText(orgNameId);


            }
        });
    }


    private void getDataFromPrefrence() {

        String jsonData = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployementDetails);

        if (jsonData != null) {


            try {
                JSONObject object = new JSONObject(jsonData);
                JSONObject data = object.getJSONObject("data");
                JSONArray array = data.getJSONArray("experience");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject experienceData = array.getJSONObject(i);

                    txt_joining_date.setText(experienceData.getString(Constant.Employer_D_O_Joining));
                    txt_relieving_date.setText(experienceData.getString(Constant.Employer_D_O_Relieving));
                    txt_designation_et.setText(experienceData.getString(Constant.Employer_Designataion));
                    txt_job_role_et.setText(experienceData.getString(Constant.Employer_Job_role));
                    autotextcomplte.setText(experienceData.getString(Constant.Emp_org_name));

                    txt_reason_of_leaving_et.setText(experienceData.getString(Constant.Employer_Reason_Of_Leaving));

                    // spinner Employer_ctc_lac
                    String compareValue_lac = (experienceData.getString(Constant.Employer_ctc_lac));
//
                    if (compareValue_lac != null) {
                        int spinnerPosition = ctcInLacAdapter.getPosition(compareValue_lac);
                        ctc_in_lac_spinner.setSelection(spinnerPosition);
                    }
                    //spinner Employer_ctc_thous

                    String compareValue_thous = (experienceData.getString(Constant.Employer_ctc_thous));

                    if (compareValue_thous != null) {
                        int spinnerPosition = ctcInthousAdapter.getPosition(compareValue_thous);
                        ctc_in_thous_spinner.setSelection(spinnerPosition);
                    }


                    txt_reporting_person_et.setText(experienceData.getString(Constant.Employer_Reporting_Persona_name));
                    txt_reporting_person_designataion_et.setText(experienceData.getString(Constant.Employer_Reporting_person_designantion));


//                    experienceData.add(Constant.UploadDocument, document);

                    JSONArray doc = experienceData.getJSONArray("document");
                    for (int j = 0; j < doc.length(); j++) {

                        JSONObject document = doc.getJSONObject(j);

//                        JSONArray doc_name= document.getJSONArray("Document_URL");
//                        for(int k=0;k<doc_name.length();k++){
//
//                            JSONObject url=doc_name.getJSONObject(k);
//
////                            Glide.with(mctx)
////                                    .load(url)
////                                    .into(doc_file);
//
//                        }


//                        Glide.with(getContext())
//                                .load(document.getString(Constant.Document_URL))
//                                .into(doc_file);


                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    private void viewOnclick() {

        txt_reporting_person_rl.setOnClickListener(this);
        txt_upload_doc_rl.setOnClickListener(this);
        txt_org_deatil_rl.setOnClickListener(this);
        date_of_joining_rl.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);

        date_of_relieving_rl.setOnClickListener(this);

        ctc_in_lac_spinner.setOnItemSelectedListener(this);
        ctc_in_thous_spinner.setOnItemSelectedListener(this);

        txt_designation_et.addTextChangedListener(this);
        txt_job_role_et.addTextChangedListener(this);
        txt_reason_of_leaving_et.addTextChangedListener(this);
        txt_reporting_person_et.addTextChangedListener(this);
        txt_reporting_person_designataion_et.addTextChangedListener(this);

        reportingPersonaName = txt_reporting_person_et.getText().toString();
        reportingPersonaDesignation = txt_reporting_person_designataion_et.getText().toString();


    }

    public void addTile() {

        LayoutInflater layoutInflater = (LayoutInflater) mctx.getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View layoutview = layoutInflater.inflate(R.layout.add_tile, null);


        TextView txt_tile_name = layoutview.findViewById(R.id.txt_tile_name);
        employer_Detail_rl = layoutview.findViewById(R.id.employer_Detail_rl);
        iv_delete = layoutview.findViewById(R.id.iv_delete);

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addEmploymentDetailTile.removeView(layoutview);

            }
        });


        txt_tile_name.setText("Employement Details" + " " + (addEmploymentDetailTile.getChildCount() + 1));


        layoutview.setTag(addEmploymentDetailTile.getChildCount());

        // show delete icon from 2nd tile
        if (addEmploymentDetailTile.getChildCount() < 1) {
            iv_delete.setVisibility(View.GONE);
        } else {

            iv_delete.setVisibility(View.VISIBLE);

            view_title.setVisibility(View.VISIBLE);
            view_title.setText(getResources().getString(R.string.employement_details) + " " + addEmploymentDetailView.getChildCount());
        }


        addEmploymentDetailTile.addView(layoutview);


        //open edit tile on tile click
        layoutview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (Integer) layoutview.getTag();

                for (int i = 0; i < addEmploymentDetailTile.getChildCount(); i++) {

                    View view1 = addEmploymentDetailView.getChildAt(i);
                    view1.setVisibility(View.GONE);

                }

                view_title.setVisibility(View.VISIBLE);
                view_title.setText(getResources().getString(R.string.employement_details) + " " + (position + 1));
                //addEmploymentDetailView.getChildAt(position).setVisibility(View.VISIBLE);


            }
        });


    }

    public void uploadDocuments() {

        LayoutInflater layoutInflater = (LayoutInflater) mctx.getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View uploadDoc = layoutInflater.inflate(R.layout.upload_document, null);

        upload_doc.addView(uploadDoc);

        doc_file = uploadDoc.findViewById(R.id.doc_file);
        delete_iv = uploadDoc.findViewById(R.id.delete_iv);
        doc_name = uploadDoc.findViewById(R.id.doc_name);

        delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload_doc.removeView(uploadDoc);

            }
        });

        doc_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (path_of_pic.contains("pdf")) {

//
                    DocumentFragment documentFragment = new DocumentFragment();
                    Bundle args = new Bundle();
                    args.putString(KeyClass.DOCUMENT_URL, picUrl);
                    documentFragment.setArguments(args);

                    ((MainActivity) getActivity()).replaceFragment(documentFragment, true, KeyClass.FRAGMENT_Document, KeyClass.FRAGMENT_Document);


                }

            }
        });


    }


    private void openDatePickerDialogDoj() {
        // Get Current Date
        final Calendar calendar = Calendar.getInstance();
        if (dates != null) {
            calendar.setTime(dates);

        }

        int mYear = (calendar.get(Calendar.YEAR));
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar calendarSelected = Calendar.getInstance();

                        calendarSelected.set(year, monthOfYear, dayOfMonth);


                        tempDate.setTimeInMillis(calendarSelected.getTimeInMillis());
                        dates = calendarSelected.getTime();


                        txt_joining_date.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));
                        txt_doj_tv_error.setVisibility(View.GONE);


                    }
                }, mYear, mMonth, mDay);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.MONTH, mMonth + 3);
        calendar1.set(Calendar.DAY_OF_MONTH, mDay);
        calendar1.set(Calendar.YEAR, mYear - 60);
        datePickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());


        datePickerDialog.show();


    }

    private void openDatePickerDialogDor() {
        // Get Current Date
        final Calendar calendar = Calendar.getInstance();
        if (dates != null) {
            calendar.setTime(dates);

        }

        int mYear = (calendar.get(Calendar.YEAR));
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar calendarSelected = Calendar.getInstance();

                        calendarSelected.set(year, monthOfYear, dayOfMonth);

                        dates = calendarSelected.getTime();


                        txt_relieving_date.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));
                        txt_dor_tv_error.setVisibility(View.GONE);


                    }
                }, mYear, mMonth, mDay);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.MONTH, mMonth + 3);
        calendar1.set(Calendar.DAY_OF_MONTH, mDay);
        calendar1.set(Calendar.YEAR, mYear - 60);
        long minDate = (tempDate.getTimeInMillis());
        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());


        datePickerDialog.show();


    }

    Calendar tempDate = Calendar.getInstance();


    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        ((MainActivity) getActivity()).replaceFragment(new EducationStatusFragment(), true, KeyClass.FRAGMENT_EDUCATION_DETAIL,
                KeyClass.FRAGMENT_EDUCATION_DETAIL);


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            case R.id.ctc_in_lac_spinner:

                stringCtcLac = ctc_in_lac_spinner.getSelectedItem().toString();
//                Toast.makeText(mctx, stringCtcLac, Toast.LENGTH_SHORT).show();
                txt_ctc_tv_error.setVisibility(View.GONE);

                break;

            case R.id.ctc_in_thous_spinner:


                stringCtcThousand = ctc_in_thous_spinner.getSelectedItem().toString();
//                Toast.makeText(mctx, stringCtcThousand, Toast.LENGTH_SHORT).show();
                txt_ctc_tv_error.setVisibility(View.GONE);

                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void addViewValidation() {

        View view = addEmploymentDetailView.getChildAt(addEmploymentDetailView.getChildCount() - 1);

        TextView joining_date = view.findViewById(R.id.txt_joining_date);
        TextView relieving_date = view.findViewById(R.id.txt_relieving_date);
        EditText reason_of_leaving_et = view.findViewById(R.id.txt_reason_of_leaving_et);
        EditText txt_designation_et = view.findViewById(R.id.txt_designation_et);
        EditText txt_job_role_et = view.findViewById(R.id.txt_job_role_et);
        EditText txt_reporting_person_et = view.findViewById(R.id.txt_reporting_person_et);
        EditText txt_reporting_person_designataion_et = view.findViewById(R.id.txt_reporting_person_designataion_et);


        if (joining_date.getText().toString().isEmpty()) {


            if (relieving_date.getText().toString().isEmpty()) {


                if (txt_designation_et.getText().toString().isEmpty()) {


                    if (txt_job_role_et.getText().toString().isEmpty()) {


                        if (orgNameId == null) {


                            if (reason_of_leaving_et.getText().toString().isEmpty()) {


                                if ((stringCtcLac.equalsIgnoreCase("0 Lac")) && (stringCtcThousand.equalsIgnoreCase("0 thousand"))) {


                                    if (txt_reporting_person_et.getText().toString().isEmpty()) {


                                        if (txt_reporting_person_designataion_et.getText().toString().isEmpty()) {


                                            if (doc_file != null) {

                                                txt_upload_tv_error.setVisibility(View.VISIBLE);

                                            } else {

                                                txt_upload_tv_error.setVisibility(View.GONE);

                                            }

                                            txt_reporting_person_designataion_tv_error.setVisibility(View.VISIBLE);
                                            txt_reporting_person_designataion_et.requestFocus();


                                        } else {

                                            txt_reporting_person_designataion_et.clearFocus();
                                        }

                                        txt_reporting_person_tv_error.setVisibility(View.VISIBLE);
                                        txt_reporting_person_et.requestFocus();


                                    } else {

                                        txt_reporting_person_et.clearFocus();
                                    }

                                    txt_ctc_tv_error.setVisibility(View.VISIBLE);

                                } else {

                                    txt_ctc_tv_error.setVisibility(View.GONE);
                                }


                                txt_reason_of_leaving_tv_error.setVisibility(View.VISIBLE);
                                txt_reason_of_leaving_et.requestFocus();


                            } else {

                                txt_reason_of_leaving_et.clearFocus();

                            }

                            txt_org_name_tv_error.setVisibility(View.VISIBLE);

                        }

                        txt_jobRole_tv_error.setVisibility(View.VISIBLE);
                        txt_job_role_et.requestFocus();


                    } else {

                        txt_job_role_et.clearFocus();

                    }

                    txt_designataion_tv_error.setVisibility(View.VISIBLE);
                    txt_designation_et.requestFocus();

                } else {

                    txt_designation_et.clearFocus();

                }

                txt_dor_tv_error.setVisibility(View.VISIBLE);

            }

            txt_doj_tv_error.setVisibility(View.VISIBLE);

        } else {


            view.setVisibility(View.GONE);
            addEmployementView();
            addTile();
        }


    }

    public void setSpinnerAdapter() {


        ArrayList<String> lacCtc = new ArrayList<>();
        ArrayList<String> thousCtc = new ArrayList<>();
        for (int i = 0; i < 101; ) {

            if (i < 51) {

                lacCtc.add(i + " " + "Lac");
                i++;
            } else {
                lacCtc.add((i + 4) + "+" + " " + "Lac");
                i += 5;
            }

        }

        for (int j = 0; j < 96; ) {

            thousCtc.add((j) + " " + "thousand");
            j += 5;
        }


        ArrayList<String> ctcInLac = new ArrayList<>();
        ArrayList<String> ctcInthous = new ArrayList<>();

        if (saveEmployeeDetailModel.getData() != null) {

            for (int i = 0; i < saveEmployeeDetailModel.getData().getExperience().size(); i++) {

                ctcInLac.add(saveEmployeeDetailModel.getData().getExperience().get(i).getLastCTCLac());
                ctcInthous.add(saveEmployeeDetailModel.getData().getExperience().get(i).getLastCTCThousand());

            }


        }
        ctcInLacAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, lacCtc);
        ctcInLacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ctcInthousAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, thousCtc);
        ctcInthousAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ctc_in_lac_spinner.setAdapter(ctcInLacAdapter);
        ctc_in_thous_spinner.setAdapter(ctcInthousAdapter);


    }


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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == START_ACTIVITY_CAMERA_CODE && resultCode == -1) {

            if (path_of_pic != null) {

                if (path_of_pic.contains("pdf")) {

                    new DownloadPdfAndShowInImageView(getActivity(), "document", doc_file).execute(path_of_pic, "document");

                }
//               Utility.uploadImageAwsToServer(pathOfPic,this);

                Utility.uploadAndSetProfileImage(path_of_pic, doc_file, this);


            }


        } else if (requestCode == START_ACTIVITY_GALLERY_CODE && resultCode == -1) {
            String pathOfPic = "";
            pathOfPic = Utility.getPathOfSelectedImage(data.getData());
            Uri selectedImage = data.getData();
            pathOfPic = FilePath.getPath(getActivity(), selectedImage);

            Utility.uploadImageAwsToServer(pathOfPic, this);
            if (pathOfPic.contains("pdf")) {


                new DownloadPdfAndShowInImageView(getActivity(), "document", doc_file).execute(pathOfPic, "document");
            } else {

//            try {f hyvnn inh  h
                doc_file.setImageURI(Uri.parse(pathOfPic));
            }


//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraPermission && readExternalFile) {

                        openCameraGalleryDialog(false);
                    } else {
//                        Util.showSnackbar(MainActivity.context, signUpDialog == null ? MainActivity.mFragmentContainer : signUpDialog.main_layouyt, getString(R.string.photoPermission));

                        Toast.makeText(mctx, "Permission rejected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    @Override
    public void afterAwsUpload(String filename) {


        doc_file.setVisibility(View.VISIBLE);
        doc_name.setVisibility(View.VISIBLE);
        delete_iv.setVisibility(View.VISIBLE);
//        doc_name.setText("file" + count++);

        txt_upload_tv_error.setVisibility(View.GONE);

        if (filename.contains("pdf")) {


            picUrl = filename;

            doc_file.setImageResource(R.drawable.pdf);

        }
        uploadDocuments();

    }

    public void setSaveEmployeeDetail() {

        JsonArray jsonArray = new JsonArray();

        for (int j = 0; j < addEmploymentDetailView.getChildCount(); j++) {


            View view2 = addEmploymentDetailView.getChildAt(j);

            TextView doj = view2.findViewById(R.id.txt_joining_date);
            TextView dor = view2.findViewById(R.id.txt_relieving_date);
            EditText designataion = view2.findViewById(R.id.txt_designation_et);
            EditText jobRole = view2.findViewById(R.id.txt_job_role_et);
            AutoCompleteTextView orgName = view2.findViewById(R.id.txt_orgName_Autocomplete);
            EditText reasonOfLeaving = view2.findViewById(R.id.txt_reason_of_leaving_et);
            EditText reportingPersonName = view2.findViewById(R.id.txt_reporting_person_et);
            EditText reportingPersonDesignation = view2.findViewById(R.id.txt_reporting_person_designataion_et);
            TextView orgId = view2.findViewById(R.id.autotextcomplte);
            Spinner ctcLac = view2.findViewById(R.id.ctc_in_lac_spinner);
            Spinner ctcthos = view2.findViewById(R.id.ctc_in_thous_spinner);
            TextView doc_name = view2.findViewById(R.id.doc_name);

            TextView txt_doj_tv_error = view2.findViewById(R.id.txt_doj_tv_error);
            TextView txt_dor_tv_error = view2.findViewById(R.id.txt_dor_tv_error);
            TextView txt_designataion_tv_error = view2.findViewById(R.id.txt_designataion_tv_error);
            TextView txt_jobRole_tv_error = view2.findViewById(R.id.txt_jobRole_tv_error);
            TextView txt_org_name_tv_error = view2.findViewById(R.id.txt_org_name_tv_error);
            TextView txt_reason_of_leaving_tv_error = view2.findViewById(R.id.txt_reason_of_leaving_tv_error);
            TextView txt_ctc_tv_error = view2.findViewById(R.id.txt_ctc_tv_error);
            TextView txt_reporting_person_tv_error = view2.findViewById(R.id.txt_reporting_person_tv_error);
            TextView txt_reporting_person_designataion_tv_error = view2.findViewById(R.id.txt_reporting_person_designataion_tv_error);
            TextView txt_upload_tv_error = view2.findViewById(R.id.txt_upload_tv_error);

            String organizationId = orgId.getText().toString();

            String lac = ctcLac.getSelectedItem().toString();
            String thou = ctcthos.getSelectedItem().toString();
            String fileName = "";
//            ArrayList<String> docFile = new ArrayList<String>();

            JsonArray document = new JsonArray();

            for (int i = 0; i < upload_doc.getChildCount(); i++) {

                fileName = doc_name.getText().toString();
//                docFile.add(fileName);

                JsonObject object = new JsonObject();
                object.addProperty(Constant.Document_Type, "Image");
                object.addProperty(Constant.Document_Name, fileName);
                object.addProperty(Constant.Document_URL, path_of_pic);

                document.add(object);

            }


            if (doj.getText().toString().isEmpty()) {


                if (dor.getText().toString().isEmpty()) {


                    if (designataion.getText().toString().isEmpty()) {


                        if (jobRole.getText().toString().isEmpty()) {


                            if (orgName.getText().toString().isEmpty()) {


                                if (reasonOfLeaving.getText().toString().isEmpty()) {


                                    if ((ctcLac.getSelectedItem().toString().equalsIgnoreCase("0 Lac")) && (ctcthos.getSelectedItem().toString().equalsIgnoreCase("0 thousand"))) {


                                        if (reportingPersonName.getText().toString().isEmpty()) {


                                            if (reportingPersonDesignation.getText().toString().isEmpty()) {


                                                if (path_of_pic == null && path_of_pic != "") {

                                                    txt_upload_tv_error.setVisibility(View.VISIBLE);

                                                } else {

                                                    txt_upload_tv_error.setVisibility(View.GONE);

                                                }

                                                txt_reporting_person_designataion_tv_error.setVisibility(View.VISIBLE);
                                                txt_reporting_person_designataion_et.requestFocus();


                                            } else {

                                                reportingPersonDesignation.clearFocus();
                                            }

                                            txt_reporting_person_tv_error.setVisibility(View.VISIBLE);
                                            txt_reporting_person_et.requestFocus();


                                        } else {

                                            txt_reporting_person_et.clearFocus();
                                        }

                                        txt_ctc_tv_error.setVisibility(View.VISIBLE);

                                    } else {

                                        txt_ctc_tv_error.setVisibility(View.GONE);
                                    }


                                    txt_reason_of_leaving_tv_error.setVisibility(View.VISIBLE);
                                    txt_reason_of_leaving_et.requestFocus();


                                } else {

                                    txt_reason_of_leaving_et.clearFocus();

                                }

                                txt_org_name_tv_error.setVisibility(View.VISIBLE);

                            }

                            txt_jobRole_tv_error.setVisibility(View.VISIBLE);
                            txt_job_role_et.requestFocus();


                        } else {

                            txt_job_role_et.clearFocus();

                        }

                        txt_designataion_tv_error.setVisibility(View.VISIBLE);
                        txt_designation_et.requestFocus();

                    } else {

                        txt_designation_et.clearFocus();

                    }

                    txt_dor_tv_error.setVisibility(View.VISIBLE);

                }

                txt_doj_tv_error.setVisibility(View.VISIBLE);

            } else {

                String childCount = String.valueOf(j);
                PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmpChildCount, childCount);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(Constant.Employer_D_O_Joining, doj.getText().toString());
                jsonObject.addProperty(Constant.Employer_D_O_Relieving, dor.getText().toString());
                jsonObject.addProperty(Constant.Employer_Designataion, designataion.getText().toString());
                jsonObject.addProperty(Constant.Employer_Job_role, jobRole.getText().toString());
                jsonObject.addProperty(Constant.Employer_Organizataion_id, organizationId);
                jsonObject.addProperty(Constant.Emp_org_name, OrgName);
                jsonObject.addProperty(Constant.Employer_Reason_Of_Leaving, reasonOfLeaving.getText().toString());
                jsonObject.addProperty(Constant.Employer_ctc_lac, lac);
                jsonObject.addProperty(Constant.Employer_ctc_thous, thou);
                jsonObject.addProperty(Constant.Employer_Reporting_Persona_name, reportingPersonName.getText().toString());
                jsonObject.addProperty(Constant.Employer_Reporting_person_designantion, reportingPersonDesignation.getText().toString());
                jsonObject.addProperty(Constant.to_varify, to_varify);
                jsonObject.add(Constant.UploadDocument, document);
                jsonArray.add(jsonObject);
            }

            JsonObject experience = new JsonObject();
            experience.add(Constant.experience, jsonArray);

            JsonObject data = new JsonObject();
            data.add(Constant.data, experience);

            employeeDetailsViewModel.saveEmployeeDetail(data);


            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmployementDetails, data.toString());

        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (txt_designation_et.getText().toString().length() > 0) {

            txt_designataion_tv_error.setVisibility(View.GONE);
        }
        if (txt_job_role_et.getText().toString().length() > 0) {

            txt_jobRole_tv_error.setVisibility(View.GONE);

        }
        if (txt_reason_of_leaving_et.getText().toString().length() > 0) {

            txt_reason_of_leaving_tv_error.setVisibility(View.GONE);

        }
        if (txt_reporting_person_et.getText().toString().length() > 0) {

            txt_reporting_person_tv_error.setVisibility(View.GONE);

        }
        if (txt_reporting_person_designataion_et.getText().toString().length() > 0) {

            txt_reporting_person_designataion_tv_error.setVisibility(View.GONE);
        }


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}