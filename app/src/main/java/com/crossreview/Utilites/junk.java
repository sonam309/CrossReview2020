package com.crossreview.Utilites;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.EmployeeDetail.EmployementDetailsFragment;
import com.crossreview.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class junk {


    //    public void setSpinnerAdapter() {
//
//        ArrayList<String> designationArrayList = new ArrayList<>();
//        ArrayList<String> jobRoleArrayList = new ArrayList<>();
//
//        if (saveEmployeeDetailModel.getData() != null) {
//
//
//            for (int i = 0; i < saveEmployeeDetailModel.getData().getExperience().size(); i++) {
//
//                designationArrayList.add(saveEmployeeDetailModel.getData().getExperience().get(i).getEmployeeDesignation());
//                jobRoleArrayList.add(saveEmployeeDetailModel.getData().getExperience().get(i).getJobRole());
//
//            }
//
//            txt_designation_et.setVisibility(View.GONE);
//            txt_job_role_tv.setVisibility(View.GONE);
//
//        }
//
//        txt_designation_et.setVisibility(View.VISIBLE);
//        txt_job_role_tv.setVisibility(View.VISIBLE);

//        ArrayList<String> companynameSpinner = new ArrayList<>();
//        if (companyNameModel != null) {
//
//            for (int i = 0; i < companyNameModel.getData().size(); i++) {
//
//                companynameSpinner.add(companyNameModel.getData().get(i).getOrganizationName());
//            }
//        }
//        ArrayAdapter companyNameAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, designationArrayList);
//        companyNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        ArrayAdapter designationAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, designationArrayList);
//        designationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        ArrayAdapter jobRoleAdapter = new ArrayAdapter(mctx, android.R.layout.simple_spinner_item, jobRoleArrayList);
//        jobRoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//        designation_spinner.setAdapter(designationAdapter);
//        job_role_spinner.setAdapter(jobRoleAdapter);
//    }


//    public void converJson() {
//        if (PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.Data) != null) {
//
//            JSONObject object = new JSONObject();
//            try {
//
//                if (data != null) {
//                    object = new JSONObject(data);
//                    object.putOpt(Constant.EmployeeExperience, empStatus);
//
//                    EmployementDetailsFragment employementDetailsFragment = new EmployementDetailsFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(KeyClass.Data, object.toString());
//                    employementDetailsFragment.setArguments(bundle);
//
//                    PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.Data, object.toString());
//
//
//
//                    ((MainActivity) getActivity()).replaceFragment(employementDetailsFragment, true, KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS,
//                            KeyClass.FRAGMENT_EMPLOYEMENT_DETAILS);
//
//                }
//            } catch (JSONException e) {
//                //e.printStackTrace();
//            }
//
//        }
//    }





    //
//    public static JsonObject getJsonApi(){
//
//        JsonObject jsonObject= new JsonObject();
//        jsonObject.addProperty("Employer_Email",emprEmail);
//        jsonObject.addProperty("Employer_Name",emprName);
//        jsonObject.addProperty("Employer_Contact",emprContct);
//        jsonObject.addProperty("Organization_Id",orgId);
//        jsonObject.addProperty("Employer_Desigination",emprDesignation);
//
//        JsonObject data= new JsonObject();
//        data.add("data",jsonObject);
//        return data;
//    }


//    JsonArray jsonArray = new JsonArray();
//    JsonObject data = new JsonObject();
//            for (int j = 0; j < addEmploymentDetailView.getChildCount(); j++) {
//
//        View view2 = addEmploymentDetailView.getChildAt(j);
//        TextView textView = view2.findViewById(R.id.txt_reason_of_leaving_et);
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("ok", textView.getText().toString());
//        jsonArray.add(jsonObject);
//
//
//    }
//    JsonObject jsonObject = new JsonObject();
//            jsonObject.add(Constant.experience, jsonArray);
//
//            data.add(Constant.data, jsonObject);
//
//            employeeDetailsViewModel.saveEmployeeDetail(data);
}




//-------


//
//        if (empName.isEmpty()) {
//
//        txt_name_tv_error.setVisibility(View.VISIBLE);
//        txt_name_et.requestFocus();
//
//
//        if (empEmail.isEmpty()) {
//
//        if (empContact.isEmpty()) {
//        if (Utility.isValidMobile(empContact)) {
//
//        txt_phone_tv_error.setVisibility(View.VISIBLE);
//        txt_phone_et.requestFocus();
//
//        if (empOrgName == null) {
//
//
////            if (empOrgName.isEmpty()) {
//
//        txt_org_tv_error.setVisibility(View.VISIBLE);
//        txt_orgName_Autocomplete.requestFocus();
//
//        if (empDesignation.isEmpty()) {
//
//        txt_designation_tv_error.setVisibility(View.VISIBLE);
//        txt_designation_et.requestFocus();
//
//        if (checkbox.isChecked()) {
//
//        checkbox_error.setVisibility(View.GONE);
//        return;
//
//        } else {
//
//        checkbox_error.setVisibility(View.VISIBLE);
//        }
//        return;
//        } else {
//        txt_designation_tv_error.setVisibility(View.GONE);
//        txt_designation_et.clearFocus();
//        }
//
//        return;
////            }
//        } else {
//        txt_org_tv_error.setVisibility(View.GONE);
//        txt_orgName_Autocomplete.clearFocus();
//        }
//
//
//        } else {
//        txt_phone_tv_error.setVisibility(View.GONE);
//        txt_phone_et.clearFocus();
//        }
//
//        txt_phone_tv_error.setVisibility(View.VISIBLE);
//        txt_phone_et.requestFocus();
//        return;
//        } else {
//
//        txt_phone_tv_error.setVisibility(View.VISIBLE);
//        txt_phone_et.clearFocus();
//        }
//
//        if (!(Utility.isValidMail(empEmail))) {
//
//        txt_email_tv_error.setVisibility(View.VISIBLE);
//        txt_email_et.requestFocus();
//
//
//
//
//        } else {
//
//        txt_email_tv_error.setVisibility(View.GONE);
//        txt_email_et.clearFocus();
//        }
//
//        txt_email_tv_error.setVisibility(View.VISIBLE);
//        txt_email_et.requestFocus();
//        return;
//
//        } else {
//
//        txt_email_et.clearFocus();
//        txt_email_tv_error.setVisibility(View.GONE);
//
//        }
//
//        return;
//        } else {
//        txt_name_tv_error.setVisibility(View.GONE);
//        txt_name_et.clearFocus();
//        }






//        if (name.isEmpty()) {
//
//            Toast.makeText(mctx, "enter name", Toast.LENGTH_LONG).show();
//            txt_name_et.requestFocus();
//            return;
//        } else {
//
//            txt_name_et.clearFocus();
//        }
//        if (fathers_name.isEmpty()) {
//
//            Toast.makeText(mctx, "enter fathers name", Toast.LENGTH_LONG).show();
//            txt_fathers_name_et.requestFocus();
//            return;
//
//        } else {
//
//            txt_fathers_name_et.clearFocus();
//        }
//        if (Strgen.equalsIgnoreCase(String.valueOf(R.string.selectgender))) {
//
//            Toast.makeText(mctx, "please Select gender", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (dob.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter DOB", Toast.LENGTH_LONG).show();
////            focusOnView(tv_txt_dob);
//            onClick(txt_dob_ll);
//            return;
//
//        } else {
////            tv_txt_dob.clearFocus();
//        }
//        if (address1.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter address1", Toast.LENGTH_LONG).show();
//            txt_address1_et.requestFocus();
//            return;
//
//        } else {
//            txt_address1_et.clearFocus();
//        }
//        if (address2.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter address2", Toast.LENGTH_LONG).show();
//            txt_address2_et.requestFocus();
//            return;
//
//        } else {
//            txt_address2_et.clearFocus();
//        }
//        if (state.isEmpty()) {
//            Toast.makeText(mctx, "please enter state", Toast.LENGTH_LONG).show();
//            txt_state_et.requestFocus();
//            return;
//
//        } else {
//
//            txt_state_et.clearFocus();
//        }
//        if (city.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter city", Toast.LENGTH_LONG).show();
//            txt_city_et.requestFocus();
//            return;
//
//        } else {
//
//            txt_city_et.clearFocus();
//        }
//        if (zipcode.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter zipcode", Toast.LENGTH_LONG).show();
//            txt_zipcode_et.requestFocus();
//            return;
//
//        } else {
//
//            txt_zipcode_et.clearFocus();
//        }
//        if (doj.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter Date of joining", Toast.LENGTH_LONG).show();
////            focusOnView(tv_txt_doj);
//            onClick(txt_doj_ll);
//
//            return;
//
//        } else {
////            tv_txt_doj.clearFocus();
//        }
//        if (doi.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter Date of interview", Toast.LENGTH_LONG).show();
////            focusOnView(tv_txt_interview_date);
//            onClick(txt_interview_date_ll);
//
//            return;
//
//        } else {
//
//        }
//        if (emp_id.isEmpty()) {
//
//            Toast.makeText(mctx, "please enter Employee ID", Toast.LENGTH_LONG).show();
//            txt_employee_id_et.requestFocus();
//            return;
//
//        } else {
//            txt_employee_id_et.clearFocus();
//        }
//        if (path_of_pic == null && !(path_of_pic == "")) {
//
//            Toast.makeText(mctx, "Please select Profile Pictue", Toast.LENGTH_SHORT).show();
//        }


//        JsonArray experience = new JsonArray();


//        if (joining_date.getText().toString().isEmpty()) {
//
//
//            if (relieving_date.getText().toString().isEmpty()) {
//
//
//                if (txt_designation_et.getText().toString().isEmpty()) {
//
//
//                    if (txt_job_role_et.getText().toString().isEmpty()) {
//
//                        if (OrgName == null) {
//
//                            txt_org_name_tv_error.setVisibility(View.VISIBLE);
//
//                        } else {
//
//                            txt_org_name_tv_error.setVisibility(View.GONE);
//
//                        }
//
//                        txt_job_role_et.requestFocus();
//                        Utility.showKeyboard(getActivity());
//                        txt_jobRole_tv_error.setVisibility(View.VISIBLE);
//
//
//                    } else {
//
//                        txt_job_role_et.clearFocus();
//                        Utility.hideKeyboard(getActivity());
//                    }
//
//                    txt_designation_et.requestFocus();
//                    Utility.showKeyboard(getActivity());
//                    txt_designataion_tv_error.setVisibility(View.VISIBLE);
//
//                } else {
//
//                    txt_designation_et.clearFocus();
//                    Utility.hideKeyboard(getActivity());
//                }
//
//
//                txt_dor_tv_error.setVisibility(View.VISIBLE);
//            }
//
//            txt_doj_tv_error.setVisibility(View.VISIBLE);
//        }
//
//
//        if (reason_of_leaving_et.getText().toString().isEmpty()) {
//
//            reason_of_leaving_et.requestFocus();
//            Utility.showKeyboard(getActivity());
//            Toast.makeText(mctx, "Please fill the Reason of Leaving ", Toast.LENGTH_LONG).show();
//
//        } else {
//            reason_of_leaving_et.clearFocus();
//            Utility.hideKeyboard(getActivity());
//        }
//        if (stringCtcLac.equals("0") && stringCtcThousand.equals("0")) {
//
//            Toast.makeText(mctx, "Please select Remuneration", Toast.LENGTH_SHORT).show();
//
//        }