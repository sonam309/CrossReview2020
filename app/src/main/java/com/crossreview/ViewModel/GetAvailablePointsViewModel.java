package com.crossreview.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.GetSelfDetailsModel;
import com.crossreview.Model.LoginResponseModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;

import java.security.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAvailablePointsViewModel extends ViewModel {



    public MutableLiveData<GetSelfDetailsModel> getPoints= new MutableLiveData<>();

    String token= PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);

    public void getAvailPoints(){

        ApiClient.getBaseApiMethods().selfDetails(token).enqueue(new Callback<GetSelfDetailsModel>() {
            @Override
            public void onResponse(Call<GetSelfDetailsModel> call, Response<GetSelfDetailsModel> response) {

                if(response.isSuccessful()){

                    GetSelfDetailsModel model= new GetSelfDetailsModel();
                    if (response.body()!=null){

                        getPoints.postValue(model);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetSelfDetailsModel> call, Throwable t) {

            }
        });


    }


}
