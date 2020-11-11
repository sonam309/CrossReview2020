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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.BasicClass;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.internal.Util;


public class EmployementDetailsFragment extends BasicClass implements View.OnClickListener, View.OnTouchListener, Observer<ClsSaveEmployeeDetailModel>, AdapterView.OnItemSelectedListener, awsUploadCallback {
    private View mview;
    private Context mctx;
    private RelativeLayout employer_Detail_rl, txt_org_deatil_rl, date_of_joining_rl, date_of_relieving_rl, employee_Detail_rl,
            txt_reporting_person_rl, txt_upload_doc_rl, txt_add_employement_rl;
    private LinearLayout rl_org_deatil, emploeyment_mainll, repoting_person_rl, txt_upload_ll;

    private TextView txt_joining_date, txt_relieving_date, view_title, uploadBtn, doc_name, autotextcomplte;
    private CardView next_btn;
    private String data, stringCtcLac, stringCtcThousand, reportingPersonaName, reportingPersonaDesignation, orgNameId, OrgName;
    private LinearLayout addEmploymentDetailView, addEmploymentDetailTile, mainll, upload_doc;
    private ImageView iv_delete, doc_file;
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

                selectdate = true;
                openDatePickerDialog();

                break;

            case R.id.date_of_relieving_rl:

                selectdate = false;
                openDatePickerDialog();

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
        AutoCompleteTextView txt_orgName_Autocomplete = view.findViewById(R.id.txt_orgName_Autocomplete);
        viewOnclick();
        setSpinnerAdapter();
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
                final ArrayAdapter<CompanyNameModel>[] adapter = new ArrayAdapter[]{new ArrayAdapter<>(MainActivity.context, R.layout.autocomplte_textview_layout, new ArrayList<CompanyNameModel>())};
                txt_orgName_Autocomplete.setAdapter(adapter[0]);
                model[0] = null;
                if (model[0] == null) {
                    setTimer(txt_orgName_Autocomplete);
                } else {
                    txt_orgName_Autocomplete.setAdapter(adapter[0]);
                }
            }
        });
        uploadDocuments();


        addEmploymentDetailView.addView(view);

        companyNameViewModel = new ViewModelProvider(this).get(CompanyNameViewModel.class);
        companyNameViewModel.companyName.observe(this, new Observer<CompanyNameModel>() {
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

                orgNameId = data.getOrganizationId();
                OrgName = data.getOrganizationName();

                autotextcomplte.setText(orgNameId);

            }
        });
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
        doc_name = uploadDoc.findViewById(R.id.doc_name);


    }


    private void openDatePickerDialog() {
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
//                        if (calendarSelected.getTime().after(new Date())) {
//
//                            return;
//                        }
                        dates = calendarSelected.getTime();
//                        if (Dob == true) {
//
//                            tv_txt_dob.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));
//                            Dob = false;
//                        }

                        if (selectdate == true) {
                            txt_joining_date.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));
                            selectdate = false;
                        } else {

                            txt_relieving_date.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));
                            selectdate = true;
                        }

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

                break;

            case R.id.ctc_in_thous_spinner:


                stringCtcThousand = ctc_in_thous_spinner.getSelectedItem().toString();
//                Toast.makeText(mctx, stringCtcThousand, Toast.LENGTH_SHORT).show();

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


        if (joining_date.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please select Date of Joining", Toast.LENGTH_LONG).show();
            return;
        }
        if (relieving_date.getText().toString().isEmpty()) {


            Toast.makeText(mctx, "Please select Date of relieving", Toast.LENGTH_LONG).show();
            return;
        }
        if (txt_designation_et.getText().toString().isEmpty()) {

            txt_designation_et.requestFocus();
            Utility.showKeyboard(getActivity());
            Toast.makeText(mctx, "Please enter Designantion", Toast.LENGTH_SHORT).show();
            return;
        } else {

            txt_designation_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (txt_job_role_et.getText().toString().isEmpty()) {

            txt_job_role_et.requestFocus();
            Utility.showKeyboard(getActivity());
            Toast.makeText(mctx, "Please enter Job Role", Toast.LENGTH_SHORT).show();
            return;

        } else {

            txt_job_role_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (reason_of_leaving_et.getText().toString().isEmpty()) {

            reason_of_leaving_et.requestFocus();
            Utility.showKeyboard(getActivity());
            Toast.makeText(mctx, "Please fill the Reason of Leaving ", Toast.LENGTH_LONG).show();
            return;
        } else {
            reason_of_leaving_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (stringCtcLac.equals("0") && stringCtcThousand.equals("0")) {

            Toast.makeText(mctx, "Please select Remuneration", Toast.LENGTH_SHORT).show();

        }


        view.setVisibility(View.GONE);
        addEmployementView();
        addTile();


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
        ArrayAdapter ctcInLacAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, lacCtc);
        ctcInLacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter ctcInthousAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, thousCtc);
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

                    companyNameViewModel.ComNamefun(autoCompleteTextView.getText().toString());


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
        doc_name.setText(filename);
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
            String organizationId = orgId.getText().toString();

            String lac = ctcLac.getSelectedItem().toString();
            String thou = ctcthos.getSelectedItem().toString();
            String fileName = "";
            ArrayList<String> docFile = new ArrayList<String>();
            JsonArray document = new JsonArray();
            for (int i = 0; i < upload_doc.getChildCount(); i++) {

                fileName = doc_name.getText().toString();
                docFile.add(fileName);

                JsonObject object = new JsonObject();
                object.addProperty(Constant.Document_Type, "Image");
                object.addProperty(Constant.Document_Name, fileName);
                object.addProperty(Constant.Document_URL, docFile.toString());

                document.add(object);

            }


            if (doj.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please select date Of Joining", Toast.LENGTH_SHORT).show();
                return;

            }
            if (dor.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "please select date Of Relieving", Toast.LENGTH_SHORT).show();
                return;
            }
            if (designataion.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill Designanation", Toast.LENGTH_SHORT).show();
                txt_designation_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_designation_et.clearFocus();
                Utility.hideKeyboard(view2);

            }
            if (jobRole.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill Job Role", Toast.LENGTH_SHORT).show();
                txt_job_role_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;

            } else {

                txt_job_role_et.clearFocus();
                Utility.hideKeyboard(view2);

            }
            if (orgName.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please select Organization", Toast.LENGTH_SHORT).show();

            }
            if (reasonOfLeaving.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "please fill Reason of leaving", Toast.LENGTH_SHORT).show();
                txt_reason_of_leaving_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;

            } else {

                txt_reason_of_leaving_et.clearFocus();
                Utility.hideKeyboard(view2);

            }
            if ((ctcLac.getSelectedItem().toString() == "0 Lac") || (ctcthos.getSelectedItem().toString() == "0 thousand")) {

                Toast.makeText(mctx, "Please select ctc", Toast.LENGTH_SHORT).show();
                return;

            }

            if (reportingPersonName.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please Fill Reporting Person Name", Toast.LENGTH_SHORT).show();
                txt_reporting_person_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;

            } else {

                txt_reporting_person_et.clearFocus();
                Utility.hideKeyboard(view2);
            }
            if (reportingPersonDesignation.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please Fill Reporting person Designanation", Toast.LENGTH_SHORT).show();
                txt_reporting_person_designataion_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;

            } else {

                reportingPersonDesignation.clearFocus();
                Utility.hideKeyboard(view2);
            }
            if (docFile.isEmpty()) {

                Toast.makeText(mctx, "Please Choose Documents", Toast.LENGTH_SHORT).show();
                return;
            }

            String childCount= String.valueOf(j);
            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmpChildCount,childCount);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Constant.Employer_D_O_Joining, doj.getText().toString());
            jsonObject.addProperty(Constant.Employer_D_O_Relieving, dor.getText().toString());
            jsonObject.addProperty(Constant.Employer_Designataion, designataion.getText().toString());
            jsonObject.addProperty(Constant.Employer_Job_role, jobRole.getText().toString());
            jsonObject.addProperty(Constant.Employer_Organizataion_id, organizationId);
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


    }


}