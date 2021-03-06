package com.crossreview.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crossreview.Activity.MainActivity;
import com.crossreview.Adapters.AddressRecyclerAdapter;
import com.crossreview.Adapters.EducationDetailsRecyclerAdapter;
import com.crossreview.Adapters.EmployerDetailsRecyclerAdapter;
import com.crossreview.Adapters.PoliceVarificationRecyclerAdapter;
import com.crossreview.Fragment.Authentication.LoginFragment;
import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.EmployeeDetailsViewModel;
import com.crossreview.ViewModel.PreviewInfoViewModel;

import java.util.ArrayList;
import java.util.List;


public class PreviewFragment extends Fragment implements View.OnClickListener, Observer<PreviewInfoModel> {

    private View mview;
    private Context mctx;
    private TextView txt_back_btn, txt_emp_name, txt_emp_contact, txt_emp_email, txt_emp_dob, txt_emp_gender, txt_emp_address;
    private TextView txt_company_name, txt_company_address, txt_company_state, company_hr_name, company_hr_email, company_hr_contact;
    private CardView makePayment_btn, submit_btn;
    private PreviewInfoViewModel previewInfoViewModel;
    private RecyclerView employedetaill_recyclerview, education_details_recyclerview, personalDetails_recyclerview, address_RecyclerView;
    private ImageView profile_image;
    private EmployerDetailsRecyclerAdapter employerDetailsRecyclerAdapter;
    private List<PreviewInfoModel> previewInfoModelList;
    private EducationDetailsRecyclerAdapter educationDetailsRecyclerAdapter;
    private PoliceVarificationRecyclerAdapter policeVarificationRecyclerAdapter;
    private AddressRecyclerAdapter addressRecyclerAdapter;
    private LinearLayout employmentDetail_ll, education_detail_ll, txt_police_varification_ll;

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
            mview = inflater.inflate(R.layout.fragment_preview, container, false);


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

        previewInfoViewModel.PreviewDetail(getActivity());


    }

    private void bindView() {

        txt_back_btn = mview.findViewById(R.id.txt_back_btn);
        makePayment_btn = mview.findViewById(R.id.makePayment_btn);
        submit_btn = mview.findViewById(R.id.submit_btn);

        employedetaill_recyclerview = mview.findViewById(R.id.employedetaill_recyclerview);
        education_details_recyclerview = mview.findViewById(R.id.education_details_recyclerview);
        personalDetails_recyclerview = mview.findViewById(R.id.personalDetails_recyclerview);
        address_RecyclerView = mview.findViewById(R.id.address_RecyclerView);

        profile_image = mview.findViewById(R.id.profile_image);

        txt_emp_name = mview.findViewById(R.id.txt_emp_name);
        txt_emp_contact = mview.findViewById(R.id.txt_emp_contact);
        txt_emp_email = mview.findViewById(R.id.txt_emp_email);
        txt_emp_dob = mview.findViewById(R.id.txt_emp_dob);
        txt_emp_gender = mview.findViewById(R.id.txt_emp_gender);
        txt_emp_address = mview.findViewById(R.id.txt_emp_address);


        txt_company_name = mview.findViewById(R.id.txt_company_name);
        txt_company_address = mview.findViewById(R.id.txt_company_address);
        txt_company_state = mview.findViewById(R.id.txt_company_state);
        company_hr_name = mview.findViewById(R.id.company_hr_name);
        company_hr_email = mview.findViewById(R.id.company_hr_email);
        company_hr_contact = mview.findViewById(R.id.company_hr_contact);


        employmentDetail_ll = mview.findViewById(R.id.employmentDetail_ll);
        education_detail_ll = mview.findViewById(R.id.education_detail_ll);
        txt_police_varification_ll = mview.findViewById(R.id.txt_police_varification_ll);


    }

    private void viewModelSetup() {

        previewInfoViewModel = new ViewModelProvider(this).get(PreviewInfoViewModel.class);
        previewInfoViewModel.previewInfo.observe(this, this);


    }

    private void viewSetup() {

        txt_back_btn.setOnClickListener(this);
        makePayment_btn.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        employedetaill_recyclerview.setLayoutManager(new LinearLayoutManager(mctx));
        education_details_recyclerview.setLayoutManager(new LinearLayoutManager(mctx));
        personalDetails_recyclerview.setLayoutManager(new LinearLayoutManager(mctx));

        String eduChildCount = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EduChildCount);
        String empChildCount = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmpChildCount);

//        if (eduChildCount.equalsIgnoreCase("0") && empChildCount.equalsIgnoreCase("0")) {
//
//            submit_btn.setVisibility(View.VISIBLE);
//            makePayment_btn.setVisibility(View.GONE);
//
//        } else {
//
//            makePayment_btn.setVisibility(View.VISIBLE);
//            submit_btn.setVisibility(View.GONE);
//
//        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txt_back_btn:

                getActivity().onBackPressed();

                break;

            case R.id.makePayment_btn:
                String token = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.AUTH_TOKEN);

                if (token != null && token != "") {

                    ((MainActivity) getActivity()).replaceFragment(new CheckOutFragment(), true, KeyClass.FRAGMENT_CHECKOUT,
                            KeyClass.FRAGMENT_CHECKOUT);


                } else {

                    ((MainActivity) getActivity()).replaceFragment(new LoginFragment(), false, KeyClass.FRAGMENT_LOGIN,
                            KeyClass.FRAGMENT_LOGIN);
                }


                break;

            case R.id.submit_btn:

                ((MainActivity) getActivity()).replaceFragment(new ThankYouFragment(), true, KeyClass.FRAGMENT_THANK_YOU, KeyClass.FRAGMENT_THANK_YOU);

                break;
        }

    }

    @Override
    public void onChanged(PreviewInfoModel previewInfoModel) {

        if (previewInfoModel != null && previewInfoModel.getData() != null) {


            //employee details
            txt_emp_name.setText(previewInfoModel.getData().getEmployeeName());
            txt_emp_dob.setText(previewInfoModel.getData().getEmployeeDOB());
            txt_emp_gender.setText(previewInfoModel.getData().getEmployeeGender());
            txt_emp_address.setText(previewInfoModel.getData().getEmployeeAddress());

            String fathersName = previewInfoModel.getData().getEmployeeFatherName();
            PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.FatherName, fathersName);

            Glide.with(getContext()).load(Uri.parse(previewInfoModel.getData().getEmployeProfilePic()))
                    .placeholder(Utility.getCircleProgress())
                    .into(profile_image);


            //employer details
            txt_company_name.setText(previewInfoModel.getData().getEmployer().getOrganizationName());
            txt_company_address.setText(previewInfoModel.getData().getEmployer().getOrganizationAddress());
//            txt_company_state.setText(previewInfoModel.getData().getEmployer()());
            company_hr_name.setText(previewInfoModel.getData().getEmployer().getEmployerName());
            company_hr_email.setText(previewInfoModel.getData().getEmployer().getEmployerEmail());
            company_hr_contact.setText(previewInfoModel.getData().getEmployer().getEmployerContact());


            if (previewInfoModel.getData().getExperiences().size() != 0 && previewInfoModel.getData().getExperiences() != null) {
                employerDetailsRecyclerAdapter = new EmployerDetailsRecyclerAdapter(mctx, previewInfoModel.getData().getExperiences());
                employmentDetail_ll.setVisibility(View.VISIBLE);
            } else {
                employmentDetail_ll.setVisibility(View.GONE);
            }
            if (previewInfoModel.getData().getEducation().size() != 0 && previewInfoModel.getData().getEducation() != null) {
                educationDetailsRecyclerAdapter = new EducationDetailsRecyclerAdapter(mctx, previewInfoModel.getData().getEducation());
                education_detail_ll.setVisibility(View.VISIBLE);
            } else {

                education_detail_ll.setVisibility(View.GONE);
            }
            if (previewInfoModel.getData().getPoliceVerifications() != null && previewInfoModel.getData().getPoliceVerifications().size() != 0) {
                policeVarificationRecyclerAdapter = new PoliceVarificationRecyclerAdapter(previewInfoModel.getData().getPoliceVerifications(), mctx);
                txt_police_varification_ll.setVisibility(View.VISIBLE);
            } else {

                txt_police_varification_ll.setVisibility(View.GONE);
            }
            addressRecyclerAdapter = new AddressRecyclerAdapter(previewInfoModel.getData().getAddresses(), mctx);


            employedetaill_recyclerview.setAdapter(employerDetailsRecyclerAdapter);
            education_details_recyclerview.setAdapter(educationDetailsRecyclerAdapter);
            personalDetails_recyclerview.setAdapter(policeVarificationRecyclerAdapter);


            if ((previewInfoModel.getData().getExperiences().size() == 0) && (previewInfoModel.getData().getEducation().size() == 0) && (previewInfoModel.getData().getPoliceVerifications().size() == 0)) {

                submit_btn.setVisibility(View.VISIBLE);
                makePayment_btn.setVisibility(View.GONE);


            } else {

                submit_btn.setVisibility(View.GONE);
                makePayment_btn.setVisibility(View.VISIBLE);
            }


        }

    }


}