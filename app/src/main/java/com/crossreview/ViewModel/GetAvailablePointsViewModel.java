package com.crossreview.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.GetSelfDetailsModel;
import com.crossreview.Model.LoginResponseModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;

import java.security.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAvailablePointsViewModel extends ViewModel {


    public MutableLiveData<GetSelfDetailsModel> getPoints = new MutableLiveData<>();


    public void getAvailPoints(Context context) {

        String token = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.AUTH_TOKEN);

        Utility.showLoader();
        ApiClient.getBaseApiMethods().selfDetails(KeyClass.BEARER_TOCKEN + token).enqueue(new Callback<GetSelfDetailsModel>() {
            @Override
            public void onResponse(Call<GetSelfDetailsModel> call, Response<GetSelfDetailsModel> response) {

                Utility.hideLoader();
                if (response.isSuccessful()) {

                    GetSelfDetailsModel model = response.body();
                    if (response.body() != null) {

                        getPoints.postValue(model);
                    }
                }else {

                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<GetSelfDetailsModel> call, Throwable t) {

                Log.e("kkkkkkkkkkkkkkkkkkkkkk", t.getMessage());
                Utility.hideLoader();

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


}
