package com.crossreview.Fragment.EmployeeDetail;

import android.app.DatePickerDialog;
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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.BasicClass;
import com.crossreview.Fragment.EmployerInformationFragment;
import com.crossreview.Interface.awsUploadCallback;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Model.StateCityModel;
import com.crossreview.Model.StateSpinnerModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.FilePath;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.crossreview.Utilites.Constant.EmployeeProfilePic;


public class EmployeeDetailsFragment extends BasicClass implements View.OnClickListener, View.OnTouchListener, Observer<ClsSaveEmployeeDetailModel>, TextView.OnEditorActionListener, awsUploadCallback, AdapterView.OnItemSelectedListener, TextWatcher {

    final Calendar calendar = Calendar.getInstance();
    int mYear = (calendar.get(Calendar.YEAR));
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    private View mview;
    private Context mctx;
    private RelativeLayout txt_contact_deatil_rl, txt_other_detail_rl;
    private TextView txt_heading, tv_txt_doj, tv_txt_interview_date, tv_txt_dob, document_title;
    private String heading;
    private LinearLayout emp_contact_detail, mainll, emp_other_detail, txt_doj_ll, txt_interview_date_ll, txt_employee_id_ll,
            emp_detail_mainll, txt_dob_ll;
    private CardView next_btn;
    private EditText txt_name_et, txt_fathers_name_et, txt_gender_et, txt_address1_et, txt_address2_et, txt_state_et, txt_city_et,
            txt_zipcode_et, txt_employee_id_et;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private String name, fathers_name, dob, address1, address2, state, city, zipcode, doj, doi, emp_id, data, profileUrl;
    private Date dates, date;
    private ImageView profile_iv;
    private Boolean selectdate = false, Dob = false;
    private ScrollView scrollview;
    private Spinner txt_gender_spinner;
    private String[] genstr = {"Select Gender", "Male", "Female", "Trangender"};
    private String Strgen = "", stateStr;
    private ArrayList<StateSpinnerModel> stateModel;
    private ArrayList<String> cityModel;
    private TextView txt_name_tv_error, txt_fathers_name_tv_error, txt_gender_tv_error, txt_dob_tv_error, txt_address_tv_error,
            txt_state_tv_error, txt_city_tv_error, txt_zipcode_tv_error, txt_doj_tv_error, txt_doi_tv_error, txt_empId_tv_error,
            txt_profile_tv_error;
    private ArrayAdapter adapter;
    private boolean flag_api = true;
    private String savepicUrl = "";


    public static EmployeeDetailsFragment newInstance() {
        return new EmployeeDetailsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            data = getArguments().getString(KeyClass.Data);
        }


        viewModelsetup();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {

            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_employee_details, container, false);


        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parseJson();
        bindView();
        viewSetup();
        getDataFromPrefrence();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == START_ACTIVITY_CAMERA_CODE && resultCode == -1) {

            if (path_of_pic != null) {

//               Utility.uploadImageAwsToServer(pathOfPic,this);

                Utility.uploadAndSetProfileImage(path_of_pic, profile_iv, this);


            }


        } else if (requestCode == START_ACTIVITY_GALLERY_CODE && resultCode == -1) {
            Uri selectedImage = data.getData();
            path_of_pic = FilePath.getPath(getActivity(), selectedImage);

            Utility.uploadImageAwsToServer(path_of_pic, this);

            try {
                profile_iv.setImageURI(Uri.parse(path_of_pic));
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

    public void parseJson() {

        stateModel = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("states.json"));
            JSONArray m_jArry = obj.getJSONArray("states");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                stateModel.add(jo_inside.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        cityModel = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("cities.json"));
            JSONArray m_jArry = obj.getJSONArray("cities");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                cityModel.add(jo_inside.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void bindView() {


        txt_contact_deatil_rl = mview.findViewById(R.id.txt_contact_deatil_rl);
        txt_other_detail_rl = mview.findViewById(R.id.txt_other_detail_rl);

        emp_contact_detail = mview.findViewById(R.id.emp_contact_detail);
        emp_other_detail = mview.findViewById(R.id.emp_other_detail);

        txt_heading = mview.findViewById(R.id.txt_heading);
        document_title = mview.findViewById(R.id.document_title);

        next_btn = mview.findViewById(R.id.next_btn);
//        uploadBtn = mview.findViewById(R.id.uploadBtn);

        txt_name_et = mview.findViewById(R.id.txt_name_et);
        txt_fathers_name_et = mview.findViewById(R.id.txt_fathers_name_et);
        txt_gender_et = mview.findViewById(R.id.txt_gender_et);
        tv_txt_dob = mview.findViewById(R.id.tv_txt_dob);
        txt_address1_et = mview.findViewById(R.id.txt_address1_et);
        txt_address2_et = mview.findViewById(R.id.txt_address2_et);
        txt_state_et = mview.findViewById(R.id.txt_state_et);
        txt_city_et = mview.findViewById(R.id.txt_city_et);
        txt_zipcode_et = mview.findViewById(R.id.txt_zipcode_et);
        tv_txt_doj = mview.findViewById(R.id.tv_txt_doj);
        tv_txt_interview_date = mview.findViewById(R.id.tv_txt_interview_date);
        txt_employee_id_et = mview.findViewById(R.id.txt_employee_id_et);

        txt_doj_ll = mview.findViewById(R.id.txt_doj_ll);
        txt_interview_date_ll = mview.findViewById(R.id.txt_interview_date_ll);
        txt_employee_id_ll = mview.findViewById(R.id.txt_employee_id_ll);
        emp_detail_mainll = mview.findViewById(R.id.emp_detail_mainll);
        mainll = mview.findViewById(R.id.mainll);
        txt_dob_ll = mview.findViewById(R.id.txt_dob_ll);

        scrollview = mview.findViewById(R.id.scrollview);
        profile_iv = mview.findViewById(R.id.profile_iv);

        //TextView
        txt_name_tv_error = mview.findViewById(R.id.txt_name_tv_error);
        txt_fathers_name_tv_error = mview.findViewById(R.id.txt_fathers_name_tv_error);
        txt_gender_tv_error = mview.findViewById(R.id.txt_gender_tv_error);
        txt_dob_tv_error = mview.findViewById(R.id.txt_dob_tv_error);
        txt_address_tv_error = mview.findViewById(R.id.txt_address_tv_error);
        txt_state_tv_error = mview.findViewById(R.id.txt_state_tv_error);
        txt_city_tv_error = mview.findViewById(R.id.txt_city_tv_error);
        txt_zipcode_tv_error = mview.findViewById(R.id.txt_zipcode_tv_error);
        txt_doj_tv_error = mview.findViewById(R.id.txt_doj_tv_error);
        txt_doi_tv_error = mview.findViewById(R.id.txt_doi_tv_error);
        txt_empId_tv_error = mview.findViewById(R.id.txt_empId_tv_error);
        txt_profile_tv_error = mview.findViewById(R.id.txt_profile_tv_error);


        txt_gender_spinner = mview.findViewById(R.id.txt_gender_spinner);
//        txt_state_spinner = mview.findViewById(R.id.txt_state_spinner);
//        txt_city_spinner = mview.findViewById(R.id.txt_city_spinner);


    }

    private void viewModelsetup() {

        employeeDetailsViewModel = new ViewModelProvider(this).get(EmployeeDetailsViewModel.class);
        employeeDetailsViewModel.EmployeeDetails.observe(this, this);

    }

    private void viewSetup() {

        spinnerAdapter();


        //getString
        heading = txt_heading.getText().toString();


        txt_contact_deatil_rl.setOnClickListener(this);
        txt_other_detail_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
//        uploadBtn.setOnClickListener(this);
        txt_doj_ll.setOnClickListener(this);
        txt_interview_date_ll.setOnClickListener(this);
        txt_dob_ll.setOnClickListener(this);
        profile_iv.setOnClickListener(this);

        txt_name_et.addTextChangedListener(this);
        txt_fathers_name_et.addTextChangedListener(this);
        txt_address1_et.addTextChangedListener(this);
        txt_state_et.addTextChangedListener(this);
        txt_city_et.addTextChangedListener(this);
        txt_zipcode_et.addTextChangedListener(this);
        txt_employee_id_et.addTextChangedListener(this);

        emp_detail_mainll.setOnTouchListener(this);
        mainll.setOnTouchListener(this);

        txt_employee_id_et.setOnEditorActionListener(this);

        txt_gender_spinner.setOnItemSelectedListener(this);

        txt_zipcode_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 6) {
                    AddressfromZipcoded(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void getDataFromPrefrence() {


        String jsonData = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployeeDetails);
        if (jsonData != null && !jsonData.equals("")) {

            try {
                JSONObject object = new JSONObject(jsonData);
                JSONObject data = object.getJSONObject("data");

                savepicUrl = data.getString(Constant.EmployeeProfilePic);
                String compareValue = data.getString(Constant.EmployeeGender);

                if (compareValue != null) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    txt_gender_spinner.setSelection(spinnerPosition);
                }
                txt_name_et.setText(data.getString(Constant.EmployeeName));
                txt_fathers_name_et.setText(data.getString(Constant.EmployeeFathersName));
                tv_txt_dob.setText(data.getString(Constant.EmployeeDob));
                txt_address1_et.setText(data.getString(Constant.EmployeeAddress));
                txt_zipcode_et.setText(data.getString(Constant.EmployeeZip));
                txt_state_et.setText(data.getString(Constant.EmployeeState));
                txt_city_et.setText(data.getString(Constant.Employeecity));
                tv_txt_doj.setText(data.getString(Constant.EmployeeDoj));
                tv_txt_interview_date.setText(data.getString(Constant.EmployeeDoi));
                txt_employee_id_et.setText(data.getString(Constant.EmployeeId));



                Glide.with(mctx)
                        .load(data.getString(Constant.EmployeeProfilePic))
                        .placeholder(Utility.getCircleProgress())
                        .into(profile_iv);


//                }


            } catch (JSONException e) {
                e.printStackTrace();

                Log.e("Profile pic errror", e.getMessage());
            }

//            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmployeeDetails, "");

        }

    }

    private void AddressfromZipcoded(String zip) {
        ApiClient.getBaseApiMethods().zipcode("http://postalpincode.in/api/pincode/" + zip).enqueue(new Callback<StateCityModel>() {
            @Override
            public void onResponse(Call<StateCityModel> call, Response<StateCityModel> response) {

                if (response.isSuccessful()) {

                    StateCityModel model = response.body();

                    if (model != null) {
                        if (model.getPostOffice() != null && model.getPostOffice().length > 0) {
                            txt_state_et.setText(model.getPostOffice()[0].getState());
                            txt_city_et.setText(model.getPostOffice()[0].getDistrict());
                        }

                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<StateCityModel> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void spinnerAdapter() {

        //ArrayAdapter1
        adapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, genstr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Adaptersetup
        txt_gender_spinner.setAdapter(adapter);


    }


    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.txt_contact_deatil_rl:

                if (emp_contact_detail.getVisibility() == View.VISIBLE) {

                    emp_contact_detail.setVisibility(View.GONE);

                } else {

                    emp_contact_detail.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.txt_other_detail_rl:

                if (emp_other_detail.getVisibility() == View.VISIBLE) {

                    emp_other_detail.setVisibility(View.GONE);

                } else {

                    emp_other_detail.setVisibility(View.VISIBLE);

                }

                break;

            case R.id.next_btn:

                saveEmployeeDetail();

                break;

//            case R.id.uploadBtn:
            case R.id.profile_iv:

                checkPermission(false);

                break;

            case R.id.txt_doj_ll:

                openDatePickerDialogDoj();


                break;

            case R.id.txt_interview_date_ll:

                openDatePickerDialogDoi();


                break;
            case R.id.txt_dob_ll:

                openDatePickerDialogDob();

                break;
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.emp_detail_mainll:
            case R.id.mainll:

                Utility.hideKeyboard(view);
                txt_name_et.clearFocus();
                txt_fathers_name_et.clearFocus();
                txt_gender_et.clearFocus();
//                txt_dob_et.clearFocus();
                txt_address1_et.clearFocus();
                txt_address2_et.clearFocus();
                txt_state_et.clearFocus();
                txt_city_et.clearFocus();
                txt_zipcode_et.clearFocus();
//                txt_doj_ll.clearFocus();

                break;
        }
        return false;
    }


    @Override
    public void onChanged(ClsSaveEmployeeDetailModel clsSaveEmployeeDetailModel) {

        ((MainActivity) getActivity()).replaceFragment(new EmployeeStatusFragment(), true, KeyClass.FRAGMENT_EMPLOYEE_STATUS,
                KeyClass.FRAGMENT_EMPLOYEE_STATUS);

    }

    public void saveEmployeeDetail() {

        name = txt_name_et.getText().toString();
        fathers_name = txt_fathers_name_et.getText().toString();
        dob = tv_txt_dob.getText().toString();
        address1 = txt_address1_et.getText().toString();
        address2 = txt_address2_et.getText().toString();
        state = txt_state_et.getText().toString();
        city = txt_city_et.getText().toString();
        zipcode = txt_zipcode_et.getText().toString();
        doj = tv_txt_doj.getText().toString();
        doi = tv_txt_interview_date.getText().toString();
        emp_id = txt_employee_id_et.getText().toString();

        flag_api = true;

        if (name.isEmpty()) {

            txt_name_tv_error.setVisibility(View.VISIBLE);
            txt_name_et.requestFocus();
            flag_api = false;

        } else {

            txt_name_et.clearFocus();
        }

        if (fathers_name.isEmpty()) {

            txt_fathers_name_tv_error.setVisibility(View.VISIBLE);
            txt_fathers_name_et.requestFocus();
            flag_api = false;


        } else {

            txt_fathers_name_et.clearFocus();
        }
        if (Strgen.equalsIgnoreCase("Select Gender")) {

            txt_gender_tv_error.setVisibility(View.VISIBLE);
            flag_api = false;


        } else {

            txt_gender_tv_error.setVisibility(View.GONE);
        }
        if (dob.isEmpty()) {

            txt_dob_tv_error.setVisibility(View.VISIBLE);
            flag_api = false;


        } else {

            txt_dob_tv_error.setVisibility(View.GONE);

        }
        if (address1.isEmpty()) {

            txt_address_tv_error.setVisibility(View.VISIBLE);
            txt_address1_et.requestFocus();
            flag_api = false;


        } else {

            txt_address1_et.clearFocus();
        }
        if (zipcode.isEmpty() || zipcode.length() < 5) {

            txt_zipcode_tv_error.setVisibility(View.VISIBLE);
            txt_zipcode_et.requestFocus();
            flag_api = false;


        } else {

            txt_zipcode_et.clearFocus();
        }

        if (state.isEmpty()) {

            txt_state_tv_error.setVisibility(View.VISIBLE);
            txt_state_et.requestFocus();
            flag_api = false;


        } else {

            txt_state_et.clearFocus();
        }

        if (city.isEmpty()) {

            txt_city_tv_error.setVisibility(View.VISIBLE);
            txt_city_et.requestFocus();
            flag_api = false;


        } else {

            txt_city_et.clearFocus();
        }

        if (doj.isEmpty()) {

            txt_doj_tv_error.setVisibility(View.VISIBLE);
            flag_api = false;


        } else {

            txt_doj_tv_error.setVisibility(View.GONE);

        }
        if (doi.isEmpty()) {

            txt_doi_tv_error.setVisibility(View.VISIBLE);
            flag_api = false;


        } else {

            txt_doi_tv_error.setVisibility(View.GONE);

        }

        if (emp_id.isEmpty()) {

            txt_empId_tv_error.setVisibility(View.VISIBLE);
            txt_employee_id_et.requestFocus();
            flag_api = false;

        } else {

            txt_employee_id_et.clearFocus();
        }
        if ((path_of_pic.equals("")) && savepicUrl.equals("")) {


            txt_profile_tv_error.setVisibility(View.VISIBLE);
            flag_api = false;

            return;

        } else {

            txt_profile_tv_error.setVisibility(View.GONE);

        }

        if (flag_api) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Constant.EmployeeName, name);
            jsonObject.addProperty(Constant.EmployeeFathersName, fathers_name);
            jsonObject.addProperty(Constant.EmployeeGender, Strgen);
            jsonObject.addProperty(Constant.EmployeeDob, dob);
            jsonObject.addProperty(Constant.EmployeeAddress, address1);
            jsonObject.addProperty(Constant.Employeecity, city);
            jsonObject.addProperty(Constant.EmployeeState, state);
            jsonObject.addProperty(Constant.EmployeeZip, zipcode);
            jsonObject.addProperty(Constant.EmployeeDoj, doj);
            jsonObject.addProperty(Constant.EmployeeDoi, doi);
            jsonObject.addProperty(Constant.EmployeeId, emp_id);
            jsonObject.addProperty(EmployeeProfilePic, profileUrl);


            JsonObject data = new JsonObject();
            data.add(Constant.data, jsonObject);


            employeeDetailsViewModel.saveEmployeeDetail(data,getActivity());

            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.EmployeeDetails, data.toString());

            flag_api = true;
        }

        
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        switch (textView.getId()) {

            case R.id.txt_employee_id_et:

//                onClick(next_btn);

                break;

        }
        return false;
    }


    private void openDatePickerDialogDoj() {
        // Get Current Date
        final Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

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

                        date = calendarSelected.getTime();

                        tv_txt_doj.setText(Utility.getStringFromDate(date, KeyClass.DATE_MM_dd_yyyy));
                        tv_txt_interview_date.setText("");
                        txt_doj_tv_error.setVisibility(View.GONE);

                    }
                }, mYear, mMonth, mDay);
        Calendar Doj = Calendar.getInstance();
        Doj.set(Calendar.MONTH, mMonth + 3);
        Doj.set(Calendar.DAY_OF_MONTH, mDay);
        Doj.set(Calendar.YEAR, mYear - 60);
        datePickerDialog.getDatePicker().setMinDate(Doj.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());


        datePickerDialog.show();


    }

    private void openDatePickerDialogDoi() {
        // Get Current Date
        final Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

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

                        date = calendarSelected.getTime();
//
                        tv_txt_interview_date.setText(Utility.getStringFromDate(date, KeyClass.DATE_MM_dd_yyyy));
                        txt_doi_tv_error.setVisibility(View.GONE);

                    }
                }, mYear, mMonth, mDay);

        Calendar Doi = Calendar.getInstance();
        Doi.set(Calendar.MONTH, mMonth + 3);
        Doi.set(Calendar.DAY_OF_MONTH, mDay);
        Doi.set(Calendar.YEAR, mYear - 60);
        datePickerDialog.getDatePicker().setMinDate(Doi.getTimeInMillis());
        long maxDte = (tempDate.getTimeInMillis() - (1000 * 60 * 60 * 24 * 1));
        datePickerDialog.getDatePicker().setMaxDate(maxDte);


        datePickerDialog.show();


    }

    Calendar tempDate = Calendar.getInstance();


    private void openDatePickerDialogDob() {
        // Get Current Date
//        final Calendar calendar = Calendar.getInstance();
////        if (dates != null) {
////            calendar.setTime(dates);
////
////        }
//
//         int mYear = (calendar.get(Calendar.YEAR));
//        int mMonth = calendar.get(Calendar.MONTH);
//        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar calendarSelected = Calendar.getInstance();

                        calendarSelected.set(year, monthOfYear, dayOfMonth);

                        dates = calendarSelected.getTime();

                        tv_txt_dob.setText(Utility.getStringFromDate(dates, KeyClass.DATE_MM_dd_yyyy));

                        txt_dob_tv_error.setVisibility(View.GONE);
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;


                    }
                }, mYear, mMonth, mDay);
        Calendar Dob = Calendar.getInstance();
        Dob.set(Calendar.MONTH, mMonth);
        Dob.set(Calendar.DAY_OF_MONTH, mDay);
        Dob.set(Calendar.YEAR, mYear);

        long currentDate = System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 6575);
//        datePickerDialog.getDatePicker().setMinDate(Dob.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(currentDate);
//        datePickerDialog.getDatePicker().setMaxDate(Dob.getTimeInMillis());


        datePickerDialog.show();


    }


    private final void focusOnView(View view) {
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.scrollTo(10, view.getBottom());
            }
        });
    }

    @Override
    public void afterAwsUpload(String filename) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EmployeeProfilePic, filename);

        JsonObject date = new JsonObject();
        date.add(Constant.data, jsonObject);


        profileUrl = filename;

        txt_profile_tv_error.setVisibility(View.GONE);

//        employeeDetailsViewModel.saveEmployeeDetail(date);


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            case R.id.txt_gender_spinner:

                Strgen = txt_gender_spinner.getSelectedItem().toString();
                txt_gender_tv_error.setVisibility(View.GONE);

                break;

            case R.id.txt_state_spinner:

//                stateStr = txt_state_spinner.getSelectedItem().toString();

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (txt_name_et.getText().length() > 0) {

            txt_name_tv_error.setVisibility(View.GONE);
        }
        if (txt_fathers_name_et.getText().length() > 0) {

            txt_fathers_name_tv_error.setVisibility(View.GONE);
        }
        if (txt_address1_et.getText().length() > 0) {

            txt_address_tv_error.setVisibility(View.GONE);
        }
        if (txt_state_et.getText().length() > 0) {

            txt_state_tv_error.setVisibility(View.GONE);

        }
        if (txt_city_et.getText().length() > 0) {

            txt_city_tv_error.setVisibility(View.GONE);
        }
        if (txt_zipcode_et.getText().length() > 0) {

            txt_zipcode_tv_error.setVisibility(View.GONE);

        }
        if (txt_employee_id_et.getText().length() > 0) {

            txt_empId_tv_error.setVisibility(View.GONE);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}