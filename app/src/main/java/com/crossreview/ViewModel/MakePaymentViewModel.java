package com.crossreview.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.ClsResultStateResponseModel;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonObject;

import java.security.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakePaymentViewModel extends ViewModel {


    public MutableLiveData<ClsResultStateResponseModel> makePayment = new MutableLiveData<>();

    String afterLogin = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.AUTH_TOKEN);

    String afterregister = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);

    public void makePayment(JsonObject jsonObject, Context context) {

        ApiClient.getBaseApiMethods().makePayment(KeyClass.BEARER_TOCKEN + afterLogin, afterregister, jsonObject).enqueue(new Callback<ClsResultStateResponseModel>() {
            @Override
            public void onResponse(Call<ClsResultStateResponseModel> call, Response<ClsResultStateResponseModel> response) {

                if (response.isSuccessful()) {

                    ClsResultStateResponseModel model = response.body();

                    makePayment.postValue(model);
                } else {

//                    Toast.makeText(context, "filed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsResultStateResponseModel> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
