package com.crossreview.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployerDataViewModel extends ViewModel {


    public MutableLiveData<ClsEmployerResponseModel>EmployerData= new MutableLiveData<>();

    public void saveEmpData(String emprName, String emprEmail, String emprContct,String orgId,String emprDesignation){

        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("Employer_Email",emprEmail);
        jsonObject.addProperty("Employer_Name",emprName);
        jsonObject.addProperty("Employer_Contact",emprContct);
        jsonObject.addProperty("Organization_Id",orgId);
        jsonObject.addProperty("Employer_Desigination",emprDesignation);

        JsonObject data= new JsonObject();
        data.add("data",jsonObject);



        ApiClient.getBaseApiMethods().employerData(data).enqueue(new Callback<ClsEmployerResponseModel>() {
            @Override
            public void onResponse(Call<ClsEmployerResponseModel> call, Response<ClsEmployerResponseModel> response) {

                if(response.isSuccessful()){
                    ClsEmployerResponseModel model= response.body();
                    if(response.body()!=null){

                        EmployerData.postValue(model);

                       // PrefrenceShared.getInstance().getPreferenceData().setValue(KeyClass.TOKEN,model.getData().getAccessToken());
                    }

                }
            }

            @Override
            public void onFailure(Call<ClsEmployerResponseModel> call, Throwable t) {
                Log.e("okokokokok",t.getMessage());
            }
        });


    }





}
