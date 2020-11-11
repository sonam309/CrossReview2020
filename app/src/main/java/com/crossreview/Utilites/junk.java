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
