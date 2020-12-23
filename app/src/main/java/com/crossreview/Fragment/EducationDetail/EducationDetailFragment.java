package com.crossreview.Fragment.EducationDetail;

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
import android.os.Trace;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.BasicClass;
import com.crossreview.Fragment.CriminalDetail.CriminalBackgroundStatusFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Interface.awsUploadCallback;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Model.InstitutionNameModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.DownloadPdfAndShowInImageView;
import com.crossreview.Utilites.FilePath;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.CompanyNameViewModel;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.ViewModel.InstitutionNameViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;

public class EducationDetailFragment extends BasicClass implements View.OnClickListener, awsUploadCallback, View.OnTouchListener, TextView.OnEditorActionListener, Observer<ClsSaveEmployeeDetailModel>, AdapterView.OnItemSelectedListener {

    private View mview;
    private Context mctx;
    private RelativeLayout txt_add_education_rl, employer_Detail_rl, employment_Detail_rl, txt_university_rl;
    private LinearLayout education_dynamic_view, univercity_detail_ll, upload_doc_ll, addEducationTile, mainll, education_detail_ll,
            marks_ll;
    private CardView next_btn;
    private EditText txt_education_et, txt_course_et, txt_specialization_et, txt_passout_year_et, txt_grade_et, txt_Marks_et;
    private RadioGroup course_type_rg;
    private RadioButton radioButton;
    private String courseType="Full Time";
    private ImageView doc_file, iv_delete, delete_iv;
    private TextView uploadBtn, doc_name, txt_university_tv;
    private Handler handler = new Handler();
    private InstitutionNameViewModel institutionNameViewModel;
    private ArrayAdapter<InstitutionNameModel.data> institutearrayAdapter;
    private String instituteName;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private Boolean to_varify = true;
    private int count = 1;
    private TextView txt_education_tv_error, txt_course_tv_error, txt_specilizataion_tv_error, txt_university_tv_error, txt_course_type_tv_error,
            txt_passoutyear_tv_error, txt_grade_tv_error, txt_upload_doc_tv_error, txt_Marks_tv_error;
    private Spinner txt_education_spinner, txt_course_spinner, txt_passout_year_spinner, txt_grade_spinner;
    private String eduStr[] = {"--Select--", " PHD", " Masters", " Graduations", " 12th", " 10th"};
    private String courseStr[] = {"--Select--", ""};
    private String gradeStr[] = {"--Select--", "CGPA", "Percentage"};
    private String educationStr, strCourse, strPassoutYear, strGrade;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModelSetup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {

            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_education_detail, container, false);


        }
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindView();
        viewSetup();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void bindView() {

        employer_Detail_rl = mview.findViewById(R.id.employer_Detail_rl);
        employment_Detail_rl = mview.findViewById(R.id.employment_Detail_rl);
        txt_add_education_rl = mview.findViewById(R.id.txt_add_education_rl);
        education_dynamic_view = mview.findViewById(R.id.education_dynamic_view);
        addEducationTile = mview.findViewById(R.id.addEducationTile);
        mainll = mview.findViewById(R.id.mainll);

        next_btn = mview.findViewById(R.id.next_btn);


    }

    private void viewModelSetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);


    }

    private void viewSetup() {

        employer_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        addEducationTile.setOnClickListener(this);
        txt_add_education_rl.setOnClickListener(this);


        mainll.setOnTouchListener(this);

        addDynamicEducataionLayour();


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.employer_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployeeDetailsFragment(), true,
                        KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS, KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS);

                break;

            case R.id.employment_Detail_rl:

                ((MainActivity) getActivity()).replaceFragment(new EmployementDetailsFragment(), true, KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS,
                        KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS);

                break;

            case R.id.txt_add_education_rl:

                addTileValidataion();

                break;


            case R.id.txt_university_rl:

                if (univercity_detail_ll.getVisibility() == View.VISIBLE) {

                    univercity_detail_ll.setVisibility(View.GONE);

                } else {

                    univercity_detail_ll.setVisibility(View.VISIBLE);

                }

                break;

            case R.id.uploadBtn:

                checkPermission(true);

                break;

            case R.id.addEducationTile:

                break;

            case R.id.next_btn:

                setSaveEducataionDetails();
//                ((MainActivity) getActivity()).replaceFragment(new CriminalBackgroundStatusFragment(), true, KeyClass.FRAGMENT_CRIMINAL_STATUS,
//                        KeyClass.FRAGMENT_CRIMINAL_STATUS);

                break;
        }

    }


    public void addDynamicEducataionLayour() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.education_detail_layout, null);

        getIds(view);
        onClicks(view);
        uploadDocument();
        spinnerAdapterSetup();


        education_dynamic_view.addView(view);

    }


    public void getIds(View view) {

        //editText
        txt_education_et = view.findViewById(R.id.txt_education_et);
        txt_course_et = view.findViewById(R.id.txt_course_et);
        txt_specialization_et = view.findViewById(R.id.txt_specialization_et);
        txt_passout_year_et = view.findViewById(R.id.txt_passout_year_et);
        txt_grade_et = view.findViewById(R.id.txt_grade_et);
        txt_Marks_et = view.findViewById(R.id.txt_Marks_et);

        //Relative layout
        txt_university_rl = view.findViewById(R.id.txt_university_rl);


        //Linear layout
        univercity_detail_ll = view.findViewById(R.id.univercity_detail_ll);
        upload_doc_ll = view.findViewById(R.id.upload_doc_ll);
        education_detail_ll = view.findViewById(R.id.education_detail_ll);
        marks_ll = view.findViewById(R.id.marks_ll);


        //Radio Group
        course_type_rg = view.findViewById(R.id.course_type_rg);

        //TextView
        uploadBtn = view.findViewById(R.id.uploadBtn);
        txt_university_tv = view.findViewById(R.id.txt_university_tv);

        //error TextView
        txt_education_tv_error = view.findViewById(R.id.txt_education_tv_error);
        txt_course_tv_error = view.findViewById(R.id.txt_course_tv_error);
        txt_specilizataion_tv_error = view.findViewById(R.id.txt_specilizataion_tv_error);
        txt_university_tv_error = view.findViewById(R.id.txt_university_tv_error);
        txt_course_type_tv_error = view.findViewById(R.id.txt_course_type_tv_error);
        txt_passoutyear_tv_error = view.findViewById(R.id.txt_passoutyear_tv_error);
        txt_grade_tv_error = view.findViewById(R.id.txt_grade_tv_error);
        txt_upload_doc_tv_error = view.findViewById(R.id.txt_upload_doc_tv_error);
        txt_Marks_tv_error = view.findViewById(R.id.txt_Marks_tv_error);

        //spinner

        txt_education_spinner = view.findViewById(R.id.txt_education_spinner);
        txt_course_spinner = view.findViewById(R.id.txt_course_spinner);
        txt_passout_year_spinner = view.findViewById(R.id.txt_passout_year_spinner);
        txt_grade_spinner = view.findViewById(R.id.txt_grade_spinner);


        //AutoTextComplete

        AutoCompleteTextView txt_university_et = view.findViewById(R.id.txt_university_et);

        final InstitutionNameModel[] model = new InstitutionNameModel[1];
        txt_university_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                txt_university_tv_error.setVisibility(View.GONE);


            }

            @Override
            public void afterTextChanged(Editable editable) {

                final ArrayAdapter<InstitutionNameModel>[] adapter = new ArrayAdapter[]{new ArrayAdapter<>(MainActivity.context, R.layout.autocomplte_textview_layout, new ArrayList<InstitutionNameModel>())};
                txt_university_et.setAdapter(adapter[0]);
                model[0] = null;
                if (model[0] == null) {
                    setTimer(txt_university_et);
                } else {
                    txt_university_et.setAdapter(adapter[0]);
                }

            }
        });

        modelSetup(txt_university_et);


    }

    public void modelSetup(AutoCompleteTextView autoCompleteTextView) {

        institutionNameViewModel = new ViewModelProvider(this).get(InstitutionNameViewModel.class);
        institutionNameViewModel.instituteName.observe(this, new Observer<InstitutionNameModel>() {
            @Override
            public void onChanged(InstitutionNameModel institutionNameModel) {

                institutearrayAdapter = new ArrayAdapter<>(MainActivity.context, R.layout.autocomplte_textview_layout, institutionNameModel.getData());
                autoCompleteTextView.setAdapter(institutearrayAdapter);
                autoCompleteTextView.showDropDown();

            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                InstitutionNameModel.data data = institutearrayAdapter.getItem(i);

                instituteName = data.getUniversityName();

                txt_university_tv.setText(instituteName);


            }
        });
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

                    institutionNameViewModel.InstituteNamefun(autoCompleteTextView.getText().toString());
                }

            }, 1000);
        }
    }

    public void onClicks(View view) {

        txt_university_rl.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        education_detail_ll.setOnTouchListener(this);

        txt_education_spinner.setOnItemSelectedListener(this);
        txt_course_spinner.setOnItemSelectedListener(this);
        txt_passout_year_spinner.setOnItemSelectedListener(this);
        txt_grade_spinner.setOnItemSelectedListener(this);


        //radio button
        course_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int radioId = course_type_rg.getCheckedRadioButtonId();

                radioButton = (RadioButton) view.findViewById(radioId);

                courseType = radioButton.getText().toString();

                txt_course_type_tv_error.setVisibility(View.GONE);

            }
        });

        txt_specialization_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (txt_specialization_et.getText().toString().length() > 0) {

                    txt_specilizataion_tv_error.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txt_Marks_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (txt_Marks_et.getText().toString().length() > 0) {

                    txt_Marks_tv_error.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void spinnerAdapterSetup() {

        //ArrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, eduStr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Adaptersetup
        txt_education_spinner.setAdapter(adapter);


        ArrayList<String> years = new ArrayList<String>();
        years.add("--Select--");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1980; i--) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> Yearadapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, years);

        txt_passout_year_spinner.setAdapter(Yearadapter);

        ArrayAdapter grade = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, gradeStr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        txt_grade_spinner.setAdapter(grade);


    }

    public void uploadDocument() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View uploadDoc = inflater.inflate(R.layout.upload_document, null);

        upload_doc_ll.addView(uploadDoc);


        doc_file = uploadDoc.findViewById(R.id.doc_file);
        doc_name = uploadDoc.findViewById(R.id.doc_name);
        delete_iv = uploadDoc.findViewById(R.id.delete_iv);

        delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload_doc_ll.removeView(uploadDoc);

            }
        });

    }


    @Override
    public void afterAwsUpload(String filename) {

        doc_file.setVisibility(View.VISIBLE);
        doc_name.setVisibility(View.VISIBLE);
        delete_iv.setVisibility(View.VISIBLE);
        doc_name.setText("file" + count++);
        txt_upload_doc_tv_error.setVisibility(View.GONE);

        uploadDocument();

    }

    public void addEducationTileView() {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View addEdu = layoutInflater.inflate(R.layout.add_tile, null);

        addEdu.setTag(addEducationTile.getChildCount());


        iv_delete = addEdu.findViewById(R.id.iv_delete);


        if (addEducationTile.getChildCount() < 1) {

            iv_delete.setVisibility(View.GONE);
        } else {

            iv_delete.setVisibility(View.VISIBLE);
        }

        addEducationTile.addView(addEdu);

    }

    public void addTileValidataion() {

        View view2 = education_dynamic_view.getChildAt(education_dynamic_view.getChildCount() - 1);

        EditText specialization = view2.findViewById(R.id.txt_specialization_et);
        AutoCompleteTextView university = view2.findViewById(R.id.txt_university_et);


        if (educationStr.equalsIgnoreCase("--Select--")) {


            if (strCourse.equalsIgnoreCase("--Select--")) {


                if (specialization.getText().toString().isEmpty()) {


                    if (university.getText().toString() != null) {

                        if (strPassoutYear.equalsIgnoreCase("--Select--")) {

                            if (strGrade.equalsIgnoreCase("--Select--")) {

                                if (photoFile == null) {

                                    txt_upload_doc_tv_error.setVisibility(View.VISIBLE);
                                }

                                txt_grade_tv_error.setVisibility(View.VISIBLE);

                            } else {

                                if (txt_Marks_et.getText().toString().isEmpty()) {


                                    txt_Marks_tv_error.setVisibility(View.VISIBLE);
                                    txt_Marks_et.requestFocus();


                                } else {

                                    txt_Marks_et.clearFocus();
                                }


                            }

                            txt_passoutyear_tv_error.setVisibility(View.VISIBLE);

                        }

                        txt_university_tv_error.setVisibility(View.VISIBLE);
                        university.requestFocus();

                    } else {
                        txt_university_tv_error.setVisibility(View.GONE);
                        university.clearFocus();
                    }

                    txt_specilizataion_tv_error.setVisibility(View.VISIBLE);
                    txt_specialization_et.requestFocus();

                } else {

                    txt_specialization_et.clearFocus();
                }

                txt_course_tv_error.setVisibility(View.VISIBLE);
            }

            txt_education_tv_error.setVisibility(View.VISIBLE);

        } else {
            view2.setVisibility(View.GONE);
            addDynamicEducataionLayour();
            addEducationTileView();
        }


    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        switch (textView.getId()) {


        }


        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.mainll:
            case R.id.education_detail_ll:

                Utility.hideKeyboard(view);

                break;

        }
        return false;
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
            }
            try {
                doc_file.setImageURI(Uri.parse(pathOfPic));


            } catch (Exception ex) {
                ex.printStackTrace();


            }

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


    public void setSaveEducataionDetails() {

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < education_dynamic_view.getChildCount(); i++) {

            View view1 = education_dynamic_view.getChildAt(i);


            EditText txt_specialization_et = view1.findViewById(R.id.txt_specialization_et);
            EditText txt_Marks_et = view1.findViewById(R.id.txt_Marks_et);
            TextView txt_university_tv = view1.findViewById(R.id.txt_university_tv);
            RadioGroup course_type_rg = view1.findViewById(R.id.course_type_rg);


            String instituteName = txt_university_tv.getText().toString();
//            String courseType = course_type_rg.toString();


            String fileName = "";
            ArrayList<String> docFile = new ArrayList<String>();
            JsonArray document = new JsonArray();

            for (int j = 0; j < upload_doc_ll.getChildCount(); j++) {

                fileName = doc_name.getText().toString();
                docFile.add(fileName);

                JsonObject object = new JsonObject();
                object.addProperty(Constant.Document_Type, "Image");
                object.addProperty(Constant.Document_Name, fileName);
                object.addProperty(Constant.Document_URL, docFile.toString());

                document.add(object);

            }
            if (photoFile == null) {

                if (strGrade.equalsIgnoreCase("--select--")) {

                    if (strPassoutYear.equalsIgnoreCase("--select--")) {

                        if (txt_university_tv.getText().toString().isEmpty()) {

                            if (txt_specialization_et.getText().toString().isEmpty()) {

                                if (strCourse.equalsIgnoreCase("--select--")) {

                                    if (educationStr.equalsIgnoreCase("--Select--")) {



                                        txt_education_tv_error.setVisibility(View.VISIBLE);


                                    }



                                    txt_course_tv_error.setVisibility(View.VISIBLE);
                                }



                                txt_specilizataion_tv_error.setVisibility(View.VISIBLE);
                                txt_specialization_et.requestFocus();
                            } else {

                                txt_specialization_et.clearFocus();
                            }

                            txt_university_tv_error.setVisibility(View.VISIBLE);
                        }


                        txt_passoutyear_tv_error.setVisibility(View.VISIBLE);

                    }

                    txt_grade_tv_error.setVisibility(View.VISIBLE);
                }else {

                    if (txt_Marks_et.getText().toString().isEmpty()) {


                        txt_Marks_tv_error.setVisibility(View.VISIBLE);
                        txt_Marks_et.requestFocus();


                    } else {

                        txt_Marks_et.clearFocus();
                    }

                }


                txt_upload_doc_tv_error.setVisibility(View.VISIBLE);
            }

            else {

                String getChildCount = String.valueOf(i);

                PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EduChildCount, getChildCount);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(Constant.Education_type, educationStr);
                jsonObject.addProperty(Constant.Course, strCourse);
                jsonObject.addProperty(Constant.Specialization, txt_specialization_et.getText().toString());
                jsonObject.addProperty(Constant.InstitutionName, instituteName);
                jsonObject.addProperty(Constant.CourseType, courseType);
                jsonObject.addProperty(Constant.PassOutYear, strPassoutYear);
                jsonObject.addProperty(Constant.Grade, strGrade);
                jsonObject.add(Constant.UploadDocument, document);
                jsonObject.addProperty(Constant.to_varify, to_varify);


                jsonArray.add(jsonObject);
            }


            JsonObject experience = new JsonObject();
            experience.add(Constant.education, jsonArray);

            JsonObject data = new JsonObject();
            data.add(Constant.data, experience);

            employeeDetailsViewModel.saveEmployeeDetail(data);
        }


    }


    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        ((MainActivity) getActivity()).replaceFragment(new CriminalBackgroundStatusFragment(), true, KeyClass.FRAGMENT_CRIMINAL_STATUS,
                KeyClass.FRAGMENT_CRIMINAL_STATUS);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            case R.id.txt_education_spinner:

//                Toast.makeText(mctx, i+"", Toast.LENGTH_SHORT).show();
                if (i == 1) {

                    //show phd data

                    ArrayList<String> phd = getPhdData("phd.json");
                    ArrayAdapter<String> phdAdapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, phd);
                    txt_course_spinner.setAdapter(phdAdapter);

                } else if (i == 2) {
                    //show masters data

                    ArrayList<String> masters = getPhdData("master.json");
                    ArrayAdapter<String> mastersAdapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, masters);
                    txt_course_spinner.setAdapter(mastersAdapter);

                } else if (i == 3) {
                    //show graduation data
                    ArrayList<String> graduation = getPhdData("graduation.json");
                    ArrayAdapter<String> graduationAdapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, graduation);
                    txt_course_spinner.setAdapter(graduationAdapter);

                } else {

                    ArrayAdapter<String> course = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, courseStr);
                    txt_course_spinner.setAdapter(course);

                }

                educationStr = txt_education_spinner.getSelectedItem().toString();

                txt_education_tv_error.setVisibility(View.GONE);

                break;

            case R.id.txt_course_spinner:

                strCourse = txt_course_spinner.getSelectedItem().toString();
                txt_course_tv_error.setVisibility(View.GONE);


                break;

            case R.id.txt_passout_year_spinner:

                strPassoutYear = txt_passout_year_spinner.getSelectedItem().toString();
                txt_passoutyear_tv_error.setVisibility(View.GONE);

                break;

            case R.id.txt_grade_spinner:

                if (i == 0) {
                    marks_ll.setVisibility(View.GONE);
                } else {

                    marks_ll.setVisibility(View.VISIBLE);

                }
                strGrade = txt_grade_spinner.getSelectedItem().toString();
                txt_grade_tv_error.setVisibility(View.GONE);

                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private ArrayList<String> getPhdData(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream inputStream = getResources().getAssets().open(fileName);

            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();

            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("CourseName"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }
}