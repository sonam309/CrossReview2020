package com.crossreview.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailsViewModel extends ViewModel {
    String data;

    public MutableLiveData<ClsSaveEmployeeDetailModel> EmployeeDetails = new MutableLiveData<>();


    public void saveEmployeeDetail(JsonObject jsonObject) {


            String token = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);


            ApiClient.getBaseApiMethods().employeeDetail(token, jsonObject).enqueue(new Callback<ClsSaveEmployeeDetailModel>() {
                @Override
                public void onResponse(Call<ClsSaveEmployeeDetailModel> call, Response<ClsSaveEmployeeDetailModel> response) {

                    if (response.isSuccessful()) {
                        ClsSaveEmployeeDetailModel model = response.body();
                        if (response.body() != null) {

                            EmployeeDetails.postValue(model);
                        }

                    }
                }

                @Override
                public void onFailure(Call<ClsSaveEmployeeDetailModel> call, Throwable t) {

                }
            });
        }

    }

