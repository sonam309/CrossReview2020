package com.crossreview.Fragment.CriminalDetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationDetailFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Fragment.PreviewFragment;
import com.crossreview.Model.PoliceVarificataionDetailsModel;
import com.crossreview.Model.StateCityModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.ViewModel.PoliceVarificataionsViewModel;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CriminalDetailFragment extends Fragment implements View.OnClickListener, Observer<PoliceVarificataionDetailsModel>, View.OnTouchListener, AdapterView.OnItemSelectedListener, TextWatcher {

    private View mview;
    private Context mctx;
    private RelativeLayout employee_Detail_rl, employment_Detail_rl, education_detail_rl, txt_address_varification_rl, txt_identity_proof_rl, txt_personal_details_rl;
    private EditText txt_mothers_name_et, txt_birth_place_et, txt_language_et;
    private LinearLayout expand_address_ll, expand_id_prood_ll, expand_personal_details_ll;
    private EditText txt_aadhar_num_et, txt_Dl_num_et, txt_voter_id_et, txt_passport_num_et, txt_name_of_local_F_R_et, txt_address_et, txt_height_et,
            txt_weight_et, txt_Complexion_et, txt_identification_mark_et;

    private EditText txt_permanent_address_et, txt_city_et, txt_post_office_et,
            txt_police_station_et, txt_district_et, txt_state_et, txt_pincode_et, txt_Local_address_et, txt_local_city_et, txt_local_post_office_et,
            txt_local_police_station_et, txt_local_district_et, txt_local_state_et, txt_local_pincode_et;
    private CardView next_btn;
    private ImageView uparrow_txtadd, downarrow_txtadd, downarrow_txtproof, uparrow_txtproof, downarrow_txtpersonal, uparrow_txtpersonal;
    private PoliceVarificataionsViewModel policeVarificataionsViewModel;
    private LinearLayout mainll, data_ll;
    private RelativeLayout height_rl, weight_rl;
    private Spinner txt_POB_spinner, txt_language_spinner, txt_height_spinner, txt_weight_spinner;
    private String birthPlaceStr, language, heightstr, weightstr;
    private CheckBox checkbox_address;
    private TextView txt_PoB_tv_error, txt_pincode_tv, txt_post_office_tv, txt_police_station_tv, txt_district_tv, txt_state_tv, txt_local_address_tv_error,
            txt_local_city_tv, txt_local_post_office_tv, txt_local_police_station_tv, txt_local_district_tv, txt_local_state_tv, txt_mothers_name_tv_error,
            txt_language_tv_error, txt_address_tv_error, txt_city_tv, txt_local_pincode_tv, txt_aadhar_num_tv_error, txt_relative_tv_error,
            txt_relative_add_tv_error, txt_height_tv, txt_weight_tv, txt_complexion_tv_error, txt_identification_mark_tv_error;
    private int count = 0;
    String str = "";
    int strOldlen = 0;
    private ArrayAdapter<String> pobAdapter, languageAdapter, heightAdapter, weightAdapter;

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
            mview = inflater.inflate(R.layout.fragment_criminal_detail_complete, container, false);
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

        employee_Detail_rl = mview.findViewById(R.id.employee_Detail_rl);
        employment_Detail_rl = mview.findViewById(R.id.employment_Detail_rl);
        education_detail_rl = mview.findViewById(R.id.education_detail_rl);
        txt_address_varification_rl = mview.findViewById(R.id.txt_address_varification_rl);
        txt_identity_proof_rl = mview.findViewById(R.id.txt_identity_proof_rl);
        txt_personal_details_rl = mview.findViewById(R.id.txt_personal_details_rl);
        mainll = mview.findViewById(R.id.mainll);
        data_ll = mview.findViewById(R.id.data_ll);

        txt_mothers_name_et = mview.findViewById(R.id.txt_mothers_name_et);
        txt_birth_place_et = mview.findViewById(R.id.txt_birth_place_et);
        txt_language_et = mview.findViewById(R.id.txt_language_et);
        txt_aadhar_num_et = mview.findViewById(R.id.txt_aadhar_num_et);
        txt_Dl_num_et = mview.findViewById(R.id.txt_Dl_num_et);
        txt_voter_id_et = mview.findViewById(R.id.txt_voter_id_et);
        txt_passport_num_et = mview.findViewById(R.id.txt_passport_num_et);
        txt_name_of_local_F_R_et = mview.findViewById(R.id.txt_name_of_local_F_R_et);
        txt_address_et = mview.findViewById(R.id.txt_address_et);
        txt_height_et = mview.findViewById(R.id.txt_height_et);
        txt_weight_et = mview.findViewById(R.id.txt_weight_et);
        txt_Complexion_et = mview.findViewById(R.id.txt_Complexion_et);
        txt_identification_mark_et = mview.findViewById(R.id.txt_identification_mark_et);

        //relative layout
        height_rl = mview.findViewById(R.id.height_rl);
        weight_rl = mview.findViewById(R.id.weight_rl);


        txt_permanent_address_et = mview.findViewById(R.id.txt_permanent_address_et);
        txt_city_et = mview.findViewById(R.id.txt_city_et);
        txt_post_office_et = mview.findViewById(R.id.txt_post_office_et);
        txt_police_station_et = mview.findViewById(R.id.txt_police_station_et);
        txt_district_et = mview.findViewById(R.id.txt_district_et);
        txt_state_et = mview.findViewById(R.id.txt_state_et);
        txt_pincode_et = mview.findViewById(R.id.txt_pincode_et);
        txt_Local_address_et = mview.findViewById(R.id.txt_Local_address_et);
        txt_local_city_et = mview.findViewById(R.id.txt_local_city_et);
        txt_local_post_office_et = mview.findViewById(R.id.txt_local_post_office_et);
        txt_local_police_station_et = mview.findViewById(R.id.txt_local_police_station_et);
        txt_local_district_et = mview.findViewById(R.id.txt_local_district_et);
        txt_local_state_et = mview.findViewById(R.id.txt_local_state_et);
        txt_local_pincode_et = mview.findViewById(R.id.txt_local_pincode_et);

        expand_address_ll = mview.findViewById(R.id.expand_address_ll);
        expand_id_prood_ll = mview.findViewById(R.id.expand_id_prood_ll);
        expand_personal_details_ll = mview.findViewById(R.id.expand_personal_details_ll);

        next_btn = mview.findViewById(R.id.next_btn);

        //check box
        checkbox_address = mview.findViewById(R.id.checkbox_address);

        uparrow_txtadd = mview.findViewById(R.id.uparrow_txtadd);
        downarrow_txtadd = mview.findViewById(R.id.downarrow_txtadd);
        downarrow_txtproof = mview.findViewById(R.id.downarrow_txtproof);
        uparrow_txtproof = mview.findViewById(R.id.uparrow_txtproof);
        downarrow_txtpersonal = mview.findViewById(R.id.downarrow_txtpersonal);
        uparrow_txtpersonal = mview.findViewById(R.id.uparrow_txtpersonal);

        //Error TextView

        txt_PoB_tv_error = mview.findViewById(R.id.txt_PoB_tv_error);
        txt_pincode_tv = mview.findViewById(R.id.txt_pincode_tv);
        txt_post_office_tv = mview.findViewById(R.id.txt_post_office_tv);
        txt_police_station_tv = mview.findViewById(R.id.txt_police_station_tv);
        txt_district_tv = mview.findViewById(R.id.txt_district_tv);
        txt_state_tv = mview.findViewById(R.id.txt_state_tv);
        txt_city_tv = mview.findViewById(R.id.txt_city_tv);
        txt_local_address_tv_error = mview.findViewById(R.id.txt_local_address_tv_error);
        txt_local_city_tv = mview.findViewById(R.id.txt_local_city_tv);
        txt_local_post_office_tv = mview.findViewById(R.id.txt_local_post_office_tv);
        txt_local_police_station_tv = mview.findViewById(R.id.txt_local_police_station_tv);
        txt_local_district_tv = mview.findViewById(R.id.txt_local_district_tv);
        txt_local_state_tv = mview.findViewById(R.id.txt_local_state_tv);
        txt_mothers_name_tv_error = mview.findViewById(R.id.txt_mothers_name_tv_error);
        txt_language_tv_error = mview.findViewById(R.id.txt_language_tv_error);
        txt_address_tv_error = mview.findViewById(R.id.txt_address_tv_error);
        txt_local_pincode_tv = mview.findViewById(R.id.txt_local_pincode_tv);
        txt_aadhar_num_tv_error = mview.findViewById(R.id.txt_aadhar_num_tv_error);
        txt_relative_tv_error = mview.findViewById(R.id.txt_relative_tv_error);
        txt_relative_add_tv_error = mview.findViewById(R.id.txt_relative_add_tv_error);
        txt_height_tv = mview.findViewById(R.id.txt_height_tv);
        txt_weight_tv = mview.findViewById(R.id.txt_weight_tv);
        txt_complexion_tv_error = mview.findViewById(R.id.txt_complexion_tv_error);
        txt_identification_mark_tv_error = mview.findViewById(R.id.txt_identification_mark_tv_error);


        //Spinner
        txt_POB_spinner = mview.findViewById(R.id.txt_POB_spinner);
        txt_language_spinner = mview.findViewById(R.id.txt_language_spinner);
        txt_height_spinner = mview.findViewById(R.id.txt_height_spinner);
        txt_weight_spinner = mview.findViewById(R.id.txt_weight_spinner);

    }

    private void viewModelSetup() {

        policeVarificataionsViewModel = new ViewModelProvider(this).get(PoliceVarificataionsViewModel.class);
        policeVarificataionsViewModel.policevarificataion.observe(this, this);

    }

    private void viewSetup() {


        if (PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmployeeStatus).equalsIgnoreCase("true")) {

            employment_Detail_rl.setVisibility(View.VISIBLE);

        } else {

            employment_Detail_rl.setVisibility(View.GONE);

        }

        if (PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EducationStatus).equalsIgnoreCase("Yes")) {

            education_detail_rl.setVisibility(View.VISIBLE);

        } else {

            education_detail_rl.setVisibility(View.GONE);
        }

        employee_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        education_detail_rl.setOnClickListener(this);
        txt_address_varification_rl.setOnClickListener(this);
        txt_identity_proof_rl.setOnClickListener(this);
        txt_personal_details_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        mainll.setOnTouchListener(this);
        data_ll.setOnTouchListener(this);

        txt_POB_spinner.setOnItemSelectedListener(this);
        txt_language_spinner.setOnItemSelectedListener(this);
        txt_height_spinner.setOnItemSelectedListener(this);
        txt_weight_spinner.setOnItemSelectedListener(this);

        txt_mothers_name_et.addTextChangedListener(this);
        txt_permanent_address_et.addTextChangedListener(this);
        txt_pincode_et.addTextChangedListener(this);
        txt_post_office_et.addTextChangedListener(this);
        txt_police_station_et.addTextChangedListener(this);
        txt_district_et.addTextChangedListener(this);
        txt_state_et.addTextChangedListener(this);
        txt_city_et.addTextChangedListener(this);
        txt_Local_address_et.addTextChangedListener(this);
        txt_local_pincode_et.addTextChangedListener(this);
        txt_local_post_office_et.addTextChangedListener(this);
        txt_local_police_station_et.addTextChangedListener(this);
        txt_local_district_et.addTextChangedListener(this);
        txt_local_state_et.addTextChangedListener(this);
        txt_local_city_et.addTextChangedListener(this);
        txt_aadhar_num_et.addTextChangedListener(this);
        txt_name_of_local_F_R_et.addTextChangedListener(this);
        txt_address_et.addTextChangedListener(this);
        txt_Complexion_et.addTextChangedListener(this);
        txt_identification_mark_et.addTextChangedListener(this);

        setData();

        spinnerAdapterSetup();
        setupHeightSpinner();
        setupWeightSpinner();
        getDatafromPrefrence();


    }

    public void getDatafromPrefrence() {

        String jsonData = (PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.CriminalDetails));

        if (jsonData != null && !jsonData.equals("")) {

            try {
                JSONObject object = new JSONObject(jsonData);
                JSONObject data = object.getJSONObject("data");


                txt_mothers_name_et.setText(data.getString(Constant.MothersName));


                String com_PlaceofBirth = (data.getString(Constant.PlaceOfBirth));
                if (com_PlaceofBirth != null) {

                    int POB = pobAdapter.getPosition(com_PlaceofBirth);
                    txt_POB_spinner.setSelection(POB);
                }

                String com_Lnguage = (data.getString(Constant.LanguageSpoken));
                if (com_Lnguage != null) {

                    int Lan = languageAdapter.getPosition(com_Lnguage);
                    txt_language_spinner.setSelection(Lan);
                }


                JSONObject address = data.getJSONObject("address");
                JSONObject permanentadd = address.getJSONObject("permanent_address");


                txt_permanent_address_et.setText(permanentadd.getString(Constant.PermanentAddress));
                txt_pincode_et.setText(permanentadd.getString(Constant.Zipcode));
                txt_post_office_et.setText(permanentadd.getString(Constant.PostOffice));
                txt_police_station_et.setText(permanentadd.getString(Constant.PoliceStation));
                txt_district_et.setText(permanentadd.getString(Constant.District));
                txt_state_et.setText(permanentadd.getString(Constant.State));
                txt_city_et.setText(permanentadd.getString(Constant.City));

                // check local and permanent add are same

                JSONObject localadd = address.getJSONObject("local_address");


                txt_Local_address_et.setText(localadd.getString(Constant.LocalAddress));
                txt_local_pincode_et.setText(localadd.getString(Constant.Zipcode));
                txt_local_post_office_et.setText(localadd.getString(Constant.PostOffice));
                txt_local_police_station_et.setText(localadd.getString(Constant.PoliceStation));
                txt_local_district_et.setText(localadd.getString(Constant.District));
                txt_local_state_et.setText(localadd.getString(Constant.State));
                txt_local_city_et.setText(localadd.getString(Constant.City));


                txt_aadhar_num_et.setText(data.getString(Constant.AadharNumber));
                txt_Dl_num_et.setText(data.getString(Constant.DrivingliecenceNum));
                txt_voter_id_et.setText(data.getString(Constant.VotarId));
                txt_passport_num_et.setText(data.getString(Constant.PassportNum));

                JSONArray relative = data.getJSONArray("relatives");

                for (int i = 0; i < relative.length(); i++) {

                    JSONObject rel = relative.getJSONObject(i);

                    txt_name_of_local_F_R_et.setText(rel.getString(Constant.Relative_name));
                    txt_address_et.setText(rel.getString(Constant.Relative_address));

                }

                String com_height = (data.getString(Constant.Height));

                if (com_height != null) {

                    int heightspnr = heightAdapter.getPosition(com_height);
                    txt_height_spinner.setSelection(heightspnr);

                }

                String com_weight = (data.getString(Constant.Weight));
                if (com_weight != null) {

                    int weightSpnr = weightAdapter.getPosition(com_weight);
                    txt_weight_spinner.setSelection(weightSpnr);
                }

                txt_Complexion_et.setText(data.getString(Constant.Complexion));
                txt_identification_mark_et.setText(data.getString(Constant.IdentificationMark));

                if (PrefrenceShared.getInstance().getPreferenceData().getValueBooleanFromKey(Constant.Same_address)) {

                    checkbox_address.setChecked(true);

                } else {
                    checkbox_address.setChecked(false);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.CriminalDetails,"");


    }


    public void setData() {

        checkbox_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    PrefrenceShared.getInstance().getPreferenceData().setValueBoolean(Constant.Same_address, b);

                    txt_Local_address_et.setText(txt_permanent_address_et.getText().toString());
                    txt_local_pincode_et.setText(txt_pincode_et.getText().toString());
                    txt_local_post_office_et.setText(txt_post_office_et.getText().toString());
                    txt_local_police_station_et.setText(txt_police_station_et.getText().toString());
                    txt_local_district_et.setText(txt_district_et.getText().toString());
                    txt_local_state_et.setText(txt_state_et.getText().toString());
                    txt_local_city_et.setText(txt_city_et.getText().toString());


                } else {

                    txt_Local_address_et.setText("");
                    txt_local_pincode_et.setText("");
                    txt_local_post_office_et.setText("");
                    txt_local_police_station_et.setText("");
                    txt_local_district_et.setText("");
                    txt_local_state_et.setText("");
                    txt_local_city_et.setText("");

                }
            }
        });


        txt_pincode_et.addTextChangedListener(new TextWatcher() {
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

        txt_local_pincode_et.addTextChangedListener(new TextWatcher() {
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


        txt_aadhar_num_et.addTextChangedListener(new TextWatcher() {

            private static final char space = ' ';

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                str = txt_aadhar_num_et.getText().toString();
                int strLen = str.length();


                if (strOldlen < strLen) {

                    if (strLen > 0) {
                        if (strLen == 4 || strLen == 9) {

                            str = str + " ";

                            txt_aadhar_num_et.setText(str);
                            txt_aadhar_num_et.setSelection(txt_aadhar_num_et.getText().length());

                        } else {

                            if (strLen == 5) {
                                if (!str.contains(" ")) {
                                    String tempStr = str.substring(0, strLen - 1);
                                    tempStr += " " + str.substring(strLen - 1, strLen);
                                    txt_aadhar_num_et.setText(tempStr);
                                    txt_aadhar_num_et.setSelection(txt_aadhar_num_et.getText().length());
                                }
                            }
                            if (strLen == 10) {
                                if (str.lastIndexOf(" ") != 9) {
                                    String tempStr = str.substring(0, strLen - 1);
                                    tempStr += " " + str.substring(strLen - 1, strLen);
                                    txt_aadhar_num_et.setText(tempStr);
                                    txt_aadhar_num_et.setSelection(txt_aadhar_num_et.getText().length());
                                }
                            }
                            strOldlen = strLen;
                        }
                    } else {
                        return;
                    }

                } else {
                    strOldlen = strLen;


//                    Log.i("MainActivity ","keyDel is Pressed ::: strLen : "+strLen+"\n old Str Len : "+strOldlen);


                }
            }
        });


    }

    private void spinnerAdapterSetup() {

        ArrayList<String> POB = getData("IndianStates.json");
        pobAdapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, POB);
        txt_POB_spinner.setAdapter(pobAdapter);

        ArrayList<String> language = getLanguage("language.json");
        languageAdapter = new ArrayAdapter<String>(mctx, android.R.layout.simple_spinner_item, language);
        txt_language_spinner.setAdapter(languageAdapter);


    }


    private void setupHeightSpinner() {
        String[] heightArr = new String[186];
        heightArr[0] = Constant.Select_height;
        for (int i = 121; i < 306; i++) {
            heightArr[i - 120] = i + "cm";
            //        +" ("+Util.convertHeightToInches(i)+")"
            heightArr[i - 120] = i + "cm" + " "
                    + " (" + Utility.convertHeightToInches(i) + ")";
        }
        heightAdapter = new ArrayAdapter<>
                (MainActivity.context, android.R.layout.simple_spinner_item,
                        heightArr);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        txt_height_spinner.setAdapter(heightAdapter);
    }


    private void setupWeightSpinner() {
        String[] weightArr = new String[498];
        int count = 0;
        weightArr[0] = Constant.Select_weight;
        for (int i = 1; i < 270; i++) {
            if (i % 2 != 0) {
                weightArr[i] = (i - count) + "kg" + " " + " (" + Utility.convertWeightToLbs(i - count) + "lbs)";
                count++;
            } else {
                weightArr[i] = (i / 2 + 0.5) + "kg" + " " + " (" + Utility.convertWeightToLbs(i / 2 + 0.5) + "lbs)";
            }

        }
        for (int i = 271; i <= 498; i++) {
            weightArr[i - 1] = i - 135 + "kg" + " " + " (" + Utility.convertWeightToLbs(i - 135) + "lbs)";
        }
        weightAdapter = new ArrayAdapter<>
                (mctx, android.R.layout.simple_spinner_item,
                        weightArr);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        txt_weight_spinner.setAdapter(weightAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.employee_Detail_rl:

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

            case R.id.txt_address_varification_rl:

                if (expand_address_ll.getVisibility() == View.VISIBLE) {

                    expand_address_ll.setVisibility(View.GONE);
                    downarrow_txtadd.setVisibility(View.GONE);
                    uparrow_txtadd.setVisibility(View.VISIBLE);
                } else {

                    expand_address_ll.setVisibility(View.VISIBLE);
                    downarrow_txtadd.setVisibility(View.VISIBLE);
                    uparrow_txtadd.setVisibility(View.GONE);
                }

                break;

            case R.id.txt_identity_proof_rl:

                if (expand_id_prood_ll.getVisibility() == View.VISIBLE) {

                    expand_id_prood_ll.setVisibility(View.GONE);
                    downarrow_txtproof.setVisibility(View.GONE);
                    uparrow_txtproof.setVisibility(View.VISIBLE);
                } else {

                    expand_id_prood_ll.setVisibility(View.VISIBLE);
                    downarrow_txtproof.setVisibility(View.VISIBLE);
                    uparrow_txtproof.setVisibility(View.GONE);
                }

                break;

            case R.id.txt_personal_details_rl:

                if (expand_personal_details_ll.getVisibility() == View.VISIBLE) {

                    expand_personal_details_ll.setVisibility(View.GONE);
                    downarrow_txtpersonal.setVisibility(View.GONE);
                    uparrow_txtpersonal.setVisibility(View.VISIBLE);
                } else {

                    expand_personal_details_ll.setVisibility(View.VISIBLE);
                    uparrow_txtpersonal.setVisibility(View.GONE);
                    downarrow_txtpersonal.setVisibility(View.VISIBLE);

                }
                break;

            case R.id.next_btn:

                saveCriminalBgDetails();


                break;
        }

    }


    public void saveCriminalBgDetails() {


        if (txt_mothers_name_et.getText().toString().isEmpty()) {

            if (txt_POB_spinner.getSelectedItem().toString().equalsIgnoreCase("select place of birth")) {

                if (txt_language_spinner.getSelectedItem().toString().equalsIgnoreCase("--select--")) {

                    if (txt_permanent_address_et.getText().toString().isEmpty()) {

                        if (txt_pincode_et.getText().toString().isEmpty() && txt_pincode_et.getText().toString().length() < 5) {

                            if (txt_post_office_et.getText().toString().isEmpty()) {

                                if (txt_police_station_et.getText().toString().isEmpty()) {

                                    if (txt_district_et.getText().toString().isEmpty()) {

                                        if (txt_state_et.getText().toString().isEmpty()) {

                                            if (txt_city_et.getText().toString().isEmpty()) {

                                                if (txt_Local_address_et.getText().toString().isEmpty()) {

                                                    if (txt_local_pincode_et.getText().toString().isEmpty()) {

                                                        if (txt_local_post_office_et.getText().toString().isEmpty()) {

                                                            if (txt_local_police_station_et.getText().toString().isEmpty()) {

                                                                if (txt_local_district_et.getText().toString().isEmpty()) {

                                                                    if (txt_local_state_et.getText().toString().isEmpty()) {

                                                                        if (txt_local_city_et.getText().toString().isEmpty()) {

                                                                            if (txt_aadhar_num_et.getText().toString().isEmpty()) {

                                                                                if (txt_name_of_local_F_R_et.getText().toString().isEmpty()) {

                                                                                    if (txt_address_et.getText().toString().isEmpty()) {

                                                                                        if (txt_height_spinner.getSelectedItem().toString().equalsIgnoreCase("0 cm")) {

                                                                                            if (txt_weight_spinner.getSelectedItem().toString().equalsIgnoreCase("0 kg")) {

                                                                                                if (txt_Complexion_et.getText().toString().isEmpty()) {

                                                                                                    if (txt_identification_mark_et.getText().toString().isEmpty()) {

                                                                                                        txt_identification_mark_tv_error.setVisibility(View.VISIBLE);
                                                                                                    }

                                                                                                    txt_complexion_tv_error.setVisibility(View.VISIBLE);

                                                                                                }

                                                                                                txt_weight_tv.setTextColor(getResources().getColor(R.color.error_red));
                                                                                                weight_rl.setBackground(getResources().getDrawable(R.drawable.error_bg));

                                                                                            }

                                                                                            txt_height_tv.setTextColor(getResources().getColor(R.color.error_red));
                                                                                            height_rl.setBackground(getResources().getDrawable(R.drawable.error_bg));

                                                                                        }

                                                                                        txt_relative_add_tv_error.setVisibility(View.VISIBLE);
                                                                                    }

                                                                                    txt_relative_tv_error.setVisibility(View.VISIBLE);
                                                                                }

                                                                                txt_aadhar_num_tv_error.setVisibility(View.VISIBLE);
                                                                            }

                                                                            Utility.setError(txt_local_city_tv, txt_local_city_et, mctx);

                                                                        } else {

                                                                            Utility.setError(txt_local_city_tv, txt_local_city_et, mctx);

                                                                        }
                                                                        Utility.setError(txt_local_state_tv, txt_local_state_et, mctx);
                                                                    } else {

                                                                        Utility.removeError(txt_local_state_tv, txt_local_state_et, mctx);


                                                                    }

                                                                    Utility.setError(txt_local_district_tv, txt_local_district_et, mctx);

                                                                } else {

                                                                    Utility.removeError(txt_local_district_tv, txt_local_district_et, mctx);


                                                                }
                                                                Utility.setError(txt_local_police_station_tv, txt_local_police_station_et, mctx);

                                                            } else {

                                                                Utility.setError(txt_local_police_station_tv, txt_local_post_office_et, mctx);

                                                            }

                                                            Utility.setError(txt_local_post_office_tv, txt_local_post_office_et, mctx);

                                                        } else {

                                                            Utility.removeError(txt_local_post_office_tv, txt_local_post_office_et, mctx);


                                                        }

                                                        Utility.setError(txt_local_pincode_tv, txt_local_pincode_et, mctx);

                                                    } else {

                                                        Utility.removeError(txt_local_pincode_tv, txt_local_pincode_et, mctx);


                                                    }

                                                    txt_local_address_tv_error.setVisibility(View.VISIBLE);

                                                }

                                                Utility.setError(txt_city_tv, txt_city_et, mctx);

                                            } else {

                                                Utility.removeError(txt_city_tv, txt_city_et, mctx);

                                            }

                                            Utility.setError(txt_state_tv, txt_state_et, mctx);

                                        } else {

                                            Utility.removeError(txt_state_tv, txt_state_et, mctx);


                                        }

                                        Utility.setError(txt_district_tv, txt_district_et, mctx);

                                    } else {

                                        Utility.removeError(txt_district_tv, txt_district_et, mctx);

                                    }

                                    Utility.setError(txt_police_station_tv, txt_police_station_et, mctx);

                                } else {

                                    Utility.removeError(txt_police_station_tv, txt_police_station_et, mctx);

                                }

                                Utility.setError(txt_post_office_tv, txt_post_office_et, mctx);

                            } else {

                                Utility.removeError(txt_post_office_tv, txt_post_office_et, mctx);


                            }

                            Utility.setError(txt_pincode_tv, txt_pincode_et, mctx);

                        } else {

                            Utility.removeError(txt_pincode_tv, txt_pincode_et, mctx);

                        }

                        txt_address_tv_error.setVisibility(View.VISIBLE);
                        txt_permanent_address_et.requestFocus();
                    } else {

                        txt_address_tv_error.clearFocus();
                    }

                    txt_language_tv_error.setVisibility(View.VISIBLE);
                }

                txt_PoB_tv_error.setVisibility(View.VISIBLE);

            }

            txt_mothers_name_tv_error.setVisibility(View.VISIBLE);
            txt_mothers_name_et.requestFocus();

        } else {


            txt_mothers_name_et.clearFocus();

            //json Array for add relative info
            JsonArray relative = new JsonArray();

            // for (int i=0;i<2;i++) {
            //for add relatiev infi items
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Constant.Relative_name, txt_name_of_local_F_R_et.getText().toString());
            jsonObject.addProperty(Constant.Relative_address, txt_address_et.getText().toString());
            jsonObject.addProperty(Constant.Relative_contact, 0);

            //add relative object in json array
            relative.add(jsonObject);
            //  }


            //json object to add perament address details
            JsonObject permanemt = new JsonObject();
            permanemt.addProperty(Constant.Address_type, Constant.permanenet);
            permanemt.addProperty(Constant.PermanentAddress, txt_permanent_address_et.getText().toString());
            permanemt.addProperty(Constant.City, txt_city_et.getText().toString());
            permanemt.addProperty(Constant.PostOffice, txt_post_office_et.getText().toString());
            permanemt.addProperty(Constant.PoliceStation, txt_police_station_et.getText().toString());
            permanemt.addProperty(Constant.District, txt_district_et.getText().toString());
            permanemt.addProperty(Constant.State, txt_state_et.getText().toString());
            permanemt.addProperty(Constant.Zipcode, txt_pincode_et.getText().toString());


            //json object to add local address details
            JsonObject local = new JsonObject();
            local.addProperty(Constant.Address_type, Constant.local);
            local.addProperty(Constant.LocalAddress, txt_Local_address_et.getText().toString());
            local.addProperty(Constant.City, txt_local_city_et.getText().toString());
            local.addProperty(Constant.PostOffice, txt_local_post_office_et.getText().toString());
            local.addProperty(Constant.PoliceStation, txt_local_police_station_et.getText().toString());
            local.addProperty(Constant.District, txt_local_district_et.getText().toString());
            local.addProperty(Constant.State, txt_local_state_et.getText().toString());
            local.addProperty(Constant.Zipcode, txt_local_pincode_et.getText().toString());


            //json object to add combine address in one json object whic is address
            JsonObject address = new JsonObject();
            address.add(Constant.permanent_address, permanemt);
            address.add(Constant.local_address, local);

            //json array to add documents details
            JsonArray document = new JsonArray();
//        for (int i=0;i<2;i++) {
//            //json object for adding componets in a json object for adding data in document json array
////            JsonObject object = new JsonObject();
//////            object.addProperty(Constant.Document_Name, "Document name");
//////            object.addProperty(Constant.Document_Type, "Document type");
//////            object.addProperty(Constant.Document_URL, "Document url");
////
////
////            document.add(object);
//        }
//

            //json object to add all info of data in data json object
            JsonObject details = new JsonObject();
            details.addProperty(Constant.MothersName, txt_mothers_name_et.getText().toString());
            details.addProperty(Constant.PlaceOfBirth, birthPlaceStr);
            details.addProperty(Constant.LanguageSpoken, language);
            details.addProperty(Constant.Height, heightstr);
            details.addProperty(Constant.Weight, weightstr);
            details.addProperty(Constant.Complexion, txt_Complexion_et.getText().toString());
            details.addProperty(Constant.IdentificationMark, txt_identification_mark_et.getText().toString());
            details.addProperty(Constant.AadharNumber, txt_aadhar_num_et.getText().toString());
            details.addProperty(Constant.DrivingliecenceNum, txt_Dl_num_et.getText().toString());
            details.addProperty(Constant.VotarId, txt_voter_id_et.getText().toString());
            details.addProperty(Constant.PassportNum, txt_passport_num_et.getText().toString());
            details.add(Constant.UploadDocument, document);
            details.add(Constant.address, address);
            details.add(Constant.relatives, relative);


            //data json object to bind all data in single json array for sending data in api
            JsonObject data = new JsonObject();
            data.add(Constant.data, details);

            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.SaveData, data.toString());

            policeVarificataionsViewModel.saveCriminalBgDetails(data);

            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.CriminalDetails, data.toString());

        }

    }

    @Override
    public void onChanged(PoliceVarificataionDetailsModel policeVarificataionDetailsModel) {


        ((MainActivity) getActivity()).replaceFragment(new PreviewFragment(), true, KeyClass.FRAGMENT_PREVIEW,
                KeyClass.FRAGMENT_PREVIEW);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (view.getId()) {

            case R.id.mainll:
            case R.id.data_ll:

                Utility.hideKeyboard(view);

                break;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {

            case R.id.txt_POB_spinner:

                if (i == 0) {
//                    txt_PoB_tv_error.setVisibility(View.VISIBLE);
                } else {
                    birthPlaceStr = txt_POB_spinner.getSelectedItem().toString();
                    txt_PoB_tv_error.setVisibility(View.GONE);
                }

                break;

            case R.id.txt_language_spinner:

                if (i == 0) {


                } else {

                    language = txt_language_spinner.getSelectedItem().toString();
                    txt_language_tv_error.setVisibility(View.GONE);

                }


                break;

            case R.id.txt_height_spinner:

                if (i == 0) {


                } else {

                    heightstr = txt_height_spinner.getSelectedItem().toString();
                    txt_height_tv.setTextColor(getResources().getColor(R.color.black));
                    height_rl.setBackground(getResources().getDrawable(R.drawable.square_white_bg));

                }

                break;

            case R.id.txt_weight_spinner:

                if (i != 0) {

                    txt_weight_tv.setTextColor(getResources().getColor(R.color.black));
                    weight_rl.setBackground(getResources().getDrawable(R.drawable.square_white_bg));
                    weightstr = txt_weight_spinner.getSelectedItem().toString();
                }


                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private ArrayList<String> getData(String fileName) {
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
                    cList.add(jsonArray.getJSONObject(i).getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    private ArrayList<String> getLanguage(String fileName) {
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
                    cList.add(jsonArray.getJSONObject(i).getString("Language"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (txt_mothers_name_et.getText().toString().length() > 0) {

            txt_mothers_name_tv_error.setVisibility(View.GONE);
        }
        if (txt_permanent_address_et.getText().toString().length() > 0) {

            txt_address_tv_error.setVisibility(View.GONE);

        }
        if (txt_pincode_et.getText().toString().length() > 0) {

            Utility.removeError(txt_pincode_tv, txt_pincode_et, mctx);

        }
        if (txt_post_office_et.getText().toString().length() > 0) {

            Utility.removeError(txt_post_office_tv, txt_post_office_et, mctx);

        }
        if (txt_police_station_et.getText().toString().length() > 0) {

            Utility.removeError(txt_police_station_tv, txt_police_station_et, mctx);

        }
        if (txt_district_et.getText().toString().length() > 0) {

            Utility.removeError(txt_district_tv, txt_district_et, mctx);

        }
        if (txt_state_et.getText().toString().length() > 0) {

            Utility.removeError(txt_state_tv, txt_state_et, mctx);

        }
        if (txt_city_et.getText().toString().length() > 0) {

            Utility.removeError(txt_city_tv, txt_city_et, mctx);

        }
        if (txt_Local_address_et.getText().toString().length() > 0) {

            txt_local_address_tv_error.setVisibility(View.GONE);

        }
        if (txt_local_pincode_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_pincode_tv, txt_local_pincode_et, mctx);

        }
        if (txt_local_post_office_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_post_office_tv, txt_local_post_office_et, mctx);

        }
        if (txt_local_police_station_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_police_station_tv, txt_local_police_station_et, mctx);

        }
        if (txt_local_district_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_district_tv, txt_local_district_et, mctx);

        }
        if (txt_local_state_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_state_tv, txt_local_state_et, mctx);

        }
        if (txt_local_city_et.getText().toString().length() > 0) {

            Utility.removeError(txt_local_city_tv, txt_local_city_et, mctx);

        }
        if (txt_aadhar_num_et.getText().toString().length() > 0) {

            txt_aadhar_num_tv_error.setVisibility(View.GONE);

        }
        if (txt_name_of_local_F_R_et.getText().toString().length() > 0) {

            txt_relative_tv_error.setVisibility(View.GONE);

        }
        if (txt_address_et.getText().toString().length() > 0) {

            txt_relative_add_tv_error.setVisibility(View.GONE);
        }
        if (txt_Complexion_et.getText().toString().length() > 0) {

            txt_complexion_tv_error.setVisibility(View.GONE);
        }
        if (txt_identification_mark_et.getText().toString().length() > 0) {

            txt_identification_mark_tv_error.setVisibility(View.GONE);

        }


    }

    @Override
    public void afterTextChanged(Editable editable) {


    }


    private void AddressfromZipcoded(String zip) {
        ApiClient.getBaseApiMethods().zipcode("http://postalpincode.in/api/pincode/" + zip).enqueue(new Callback<StateCityModel>() {
            @Override
            public void onResponse(Call<StateCityModel> call, Response<StateCityModel> response) {

                if (response.isSuccessful()) {

                    StateCityModel model = response.body();

                    if (model != null) {
                        if (model.getPostOffice() != null && model.getPostOffice().length > 0) {
                            txt_district_et.setText(model.getPostOffice()[0].getDistrict());
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
}