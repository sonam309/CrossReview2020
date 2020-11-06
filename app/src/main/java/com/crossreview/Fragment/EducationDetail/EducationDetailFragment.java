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
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.CompanyNameViewModel;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.ViewModel.InstitutionNameViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

public class EducationDetailFragment extends BasicClass implements View.OnClickListener, awsUploadCallback, View.OnTouchListener, TextView.OnEditorActionListener, Observer<ClsSaveEmployeeDetailModel> {

    private View mview;
    private Context mctx;
    private RelativeLayout txt_add_education_rl, employer_Detail_rl, employment_Detail_rl, txt_university_rl;
    private LinearLayout education_dynamic_view, univercity_detail_ll, upload_doc_ll, addEducationTile, mainll, education_detail_ll;
    private CardView next_btn;
    private EditText txt_education_et, txt_course_et, txt_specialization_et, txt_passout_year_et, txt_grade_et;
    private RadioGroup course_type_rg;
    private RadioButton radioButton;
    private String courseType;
    private ImageView doc_file, iv_delete;
    private TextView uploadBtn, doc_name, txt_university_tv;
    private Handler handler = new Handler();
    private InstitutionNameViewModel institutionNameViewModel;
    private ArrayAdapter<InstitutionNameModel.data> institutearrayAdapter;
    private String instituteName;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private Boolean to_varify = true;

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


        education_dynamic_view.addView(view);

    }


    public void getIds(View view) {

        //editText
        txt_education_et = view.findViewById(R.id.txt_education_et);
        txt_course_et = view.findViewById(R.id.txt_course_et);
        txt_specialization_et = view.findViewById(R.id.txt_specialization_et);
        txt_passout_year_et = view.findViewById(R.id.txt_passout_year_et);
        txt_grade_et = view.findViewById(R.id.txt_grade_et);

        //Relative layout
        txt_university_rl = view.findViewById(R.id.txt_university_rl);


        //Linear layout
        univercity_detail_ll = view.findViewById(R.id.univercity_detail_ll);
        upload_doc_ll = view.findViewById(R.id.upload_doc_ll);
        education_detail_ll = view.findViewById(R.id.education_detail_ll);


        //Radio Group
        course_type_rg = view.findViewById(R.id.course_type_rg);

        //TextView
        uploadBtn = view.findViewById(R.id.uploadBtn);
        txt_university_tv = view.findViewById(R.id.txt_university_tv);


        //AutoTextComplete

        AutoCompleteTextView txt_university_et = view.findViewById(R.id.txt_university_et);

        final InstitutionNameModel[] model = new InstitutionNameModel[1];
        txt_university_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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


        //radio button
        course_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int radioId = course_type_rg.getCheckedRadioButtonId();

                radioButton = (RadioButton) view.findViewById(radioId);

                courseType = radioButton.getText().toString();

            }
        });


    }


    public void uploadDocument() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.upload_document, null);

        upload_doc_ll.addView(view);


        doc_file = view.findViewById(R.id.doc_file);
        doc_name = view.findViewById(R.id.doc_name);

    }


    @Override
    public void afterAwsUpload(String filename) {

        doc_file.setVisibility(View.VISIBLE);
        doc_name.setVisibility(View.VISIBLE);
        doc_name.setText(filename);
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

        EditText educations = view2.findViewById(R.id.txt_education_et);
        EditText course = view2.findViewById(R.id.txt_course_et);
        EditText specialization = view2.findViewById(R.id.txt_specialization_et);
        AutoCompleteTextView university = view2.findViewById(R.id.txt_university_et);
        EditText passingYear = view2.findViewById(R.id.txt_passout_year_et);
        EditText grade = view2.findViewById(R.id.txt_grade_et);

        if (educations.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Educations", Toast.LENGTH_SHORT).show();
            txt_education_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            txt_education_et.clearFocus();
            Utility.hideKeyboard(getActivity());

        }
        if (course.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please Fill Course", Toast.LENGTH_SHORT).show();
            txt_course_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            txt_course_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (specialization.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please Fill Specialization", Toast.LENGTH_SHORT).show();
            txt_specialization_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            txt_specialization_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (university.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Institution name", Toast.LENGTH_SHORT).show();
            university.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            university.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (passingYear.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Pass Out year", Toast.LENGTH_SHORT).show();
            txt_passout_year_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            txt_passout_year_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }
        if (grade.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill grade", Toast.LENGTH_SHORT).show();
            txt_grade_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        } else {

            txt_grade_et.clearFocus();
            Utility.hideKeyboard(getActivity());
        }


        view2.setVisibility(View.GONE);
        addDynamicEducataionLayour();
        addEducationTileView();


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


            EditText txt_education_et = view1.findViewById(R.id.txt_education_et);
            EditText txt_course_et = view1.findViewById(R.id.txt_course_et);
            EditText txt_specialization_et = view1.findViewById(R.id.txt_specialization_et);
            TextView txt_university_tv = view1.findViewById(R.id.txt_university_tv);
            RadioGroup course_type_rg = view1.findViewById(R.id.course_type_rg);
            EditText txt_passout_year_et = view1.findViewById(R.id.txt_passout_year_et);
            EditText txt_grade_et = view1.findViewById(R.id.txt_grade_et);

            String instituteName = txt_university_tv.getText().toString();
            String courseType = course_type_rg.toString();


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


            if (txt_education_et.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill Education Type", Toast.LENGTH_SHORT).show();
                txt_education_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_education_et.clearFocus();
                Utility.hideKeyboard(view1);

            }
            if (txt_course_et.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill course", Toast.LENGTH_SHORT).show();
                txt_course_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_course_et.clearFocus();
                Utility.hideKeyboard(view1);

            }
            if (txt_specialization_et.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill specialization", Toast.LENGTH_SHORT).show();
                txt_specialization_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_specialization_et.clearFocus();
                Utility.hideKeyboard(view1);

            }
            if (txt_university_tv.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "please select Institution", Toast.LENGTH_SHORT).show();
                return;
            }
            if (txt_passout_year_et.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill Passout year", Toast.LENGTH_SHORT).show();
                txt_passout_year_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_passout_year_et.clearFocus();
                Utility.hideKeyboard(view1);
            }
            if (txt_grade_et.getText().toString().isEmpty()) {

                Toast.makeText(mctx, "Please fill  grade", Toast.LENGTH_SHORT).show();
                txt_grade_et.requestFocus();
                Utility.showKeyboard(getActivity());
                return;
            } else {

                txt_grade_et.clearFocus();
                Utility.hideKeyboard(view1);
            }


            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Constant.Education_type, txt_education_et.getText().toString());
            jsonObject.addProperty(Constant.Course, txt_course_et.getText().toString());
            jsonObject.addProperty(Constant.Specialization, txt_specialization_et.getText().toString());
            jsonObject.addProperty(Constant.InstitutionName, instituteName);
            jsonObject.addProperty(Constant.CourseType, courseType);
            jsonObject.addProperty(Constant.PassOutYear, txt_passout_year_et.getText().toString());
            jsonObject.addProperty(Constant.Grade, txt_grade_et.getText().toString());
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


    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        ((MainActivity) getActivity()).replaceFragment(new CriminalBackgroundStatusFragment(), true, KeyClass.FRAGMENT_CRIMINAL_STATUS,
                KeyClass.FRAGMENT_CRIMINAL_STATUS);

    }

}