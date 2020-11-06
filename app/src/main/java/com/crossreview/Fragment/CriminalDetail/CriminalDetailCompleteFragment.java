package com.crossreview.Fragment.CriminalDetail;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EducationDetail.EducationDetailFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployeeDetailsFragment;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.Fragment.PreviewFragment;
import com.crossreview.Model.PoliceVarificataionDetailsModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.ViewModel.PoliceVarificataionsViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class CriminalDetailCompleteFragment extends Fragment implements View.OnClickListener, Observer<PoliceVarificataionDetailsModel> {

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

        uparrow_txtadd = mview.findViewById(R.id.uparrow_txtadd);
        downarrow_txtadd = mview.findViewById(R.id.downarrow_txtadd);
        downarrow_txtproof = mview.findViewById(R.id.downarrow_txtproof);
        uparrow_txtproof = mview.findViewById(R.id.uparrow_txtproof);
        downarrow_txtpersonal = mview.findViewById(R.id.downarrow_txtpersonal);
        uparrow_txtpersonal = mview.findViewById(R.id.uparrow_txtpersonal);


    }

    private void viewModelSetup() {

        policeVarificataionsViewModel = new ViewModelProvider(this).get(PoliceVarificataionsViewModel.class);
        policeVarificataionsViewModel.policevarificataion.observe(this, this);

    }

    private void viewSetup() {

        employee_Detail_rl.setOnClickListener(this);
        employment_Detail_rl.setOnClickListener(this);
        education_detail_rl.setOnClickListener(this);
        txt_address_varification_rl.setOnClickListener(this);
        txt_identity_proof_rl.setOnClickListener(this);
        txt_personal_details_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);

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

            Toast.makeText(mctx, "Please fill mothers name", Toast.LENGTH_LONG).show();
            txt_mothers_name_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_birth_place_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Place of Birth", Toast.LENGTH_LONG).show();
            txt_birth_place_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }

        if (txt_permanent_address_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Permanenet Address", Toast.LENGTH_LONG).show();
            txt_permanent_address_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }

        if (txt_city_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill city", Toast.LENGTH_LONG).show();
            txt_city_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_post_office_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Post Office", Toast.LENGTH_LONG).show();
            txt_post_office_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_police_station_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Police Station", Toast.LENGTH_LONG).show();
            txt_police_station_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_district_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill District", Toast.LENGTH_LONG).show();
            txt_district_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_state_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill State", Toast.LENGTH_LONG).show();
            txt_state_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_pincode_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Zipcode", Toast.LENGTH_LONG).show();
            txt_pincode_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_Local_address_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address", Toast.LENGTH_LONG).show();
            txt_Local_address_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_city_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address city", Toast.LENGTH_LONG).show();
            txt_local_city_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_post_office_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address Post office", Toast.LENGTH_LONG).show();
            txt_local_post_office_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_police_station_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address Police station", Toast.LENGTH_LONG).show();
            txt_local_police_station_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_district_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address District", Toast.LENGTH_LONG).show();
            txt_local_district_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_state_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address state", Toast.LENGTH_LONG).show();
            txt_local_state_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_local_pincode_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Local Address pin code", Toast.LENGTH_LONG).show();
            txt_local_pincode_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_aadhar_num_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Aadhar number", Toast.LENGTH_LONG).show();
            txt_aadhar_num_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_height_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Height", Toast.LENGTH_LONG).show();
            txt_height_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_weight_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Weight", Toast.LENGTH_LONG).show();
            txt_weight_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_Complexion_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill Complexion", Toast.LENGTH_LONG).show();
            txt_Complexion_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }
        if (txt_identification_mark_et.getText().toString().isEmpty()) {

            Toast.makeText(mctx, "Please fill identifiacation mark", Toast.LENGTH_LONG).show();
            txt_identification_mark_et.requestFocus();
            Utility.showKeyboard(getActivity());
            return;
        }

        Utility.hideKeyboard(getActivity());

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
        details.addProperty(Constant.PlaceOfBirth, txt_birth_place_et.getText().toString());
        details.addProperty(Constant.LanguageSpoken, txt_language_et.getText().toString());
        details.addProperty(Constant.Height, txt_height_et.getText().toString());
        details.addProperty(Constant.Weight, txt_weight_et.getText().toString());
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

        policeVarificataionsViewModel.saveCriminalBgDetails(data);


    }

    @Override
    public void onChanged(PoliceVarificataionDetailsModel policeVarificataionDetailsModel) {

        ((MainActivity) getActivity()).replaceFragment(new PreviewFragment(), true, KeyClass.FRAGMENT_PREVIEW,
                KeyClass.FRAGMENT_PREVIEW);

    }
}