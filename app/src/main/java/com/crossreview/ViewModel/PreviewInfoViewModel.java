package com.crossreview.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewInfoViewModel extends ViewModel {


     public MutableLiveData<PreviewInfoModel> previewInfo = new MutableLiveData<>();


    public void PreviewDetail(){

//        String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJFbXBsb3llcl9JZCI6IkVNUC02ODMwNy0yMzM2NyIsIkVtcGxveWVyX0VtYWlsIjoic29uYS5tYWdpY3NAZ21haWwuY28iLCJPcmdhbml6YXRpb25fSWQiOiJvcmc0MzA1IiwiRW1wbG95ZWVfQmFzaWNfSWQiOiIwMTc4MDY1NzM5IiwiaWF0IjoxNjA0NDkwMzc2LCJleHAiOjE2MDQ1NzY3NzZ9.yDrbZTTqSEjiL0XmpXxk5BKnitPPJ7axQB1uTK_wgak";
        String token=  PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);

        ApiClient.getBaseApiMethods().previewInfo(token).enqueue(new Callback<PreviewInfoModel>() {
            @Override
            public void onResponse(Call<PreviewInfoModel> call, Response<PreviewInfoModel> response) {

                if(response.isSuccessful()){
                    PreviewInfoModel model= response.body();
                    if(response.body()!=null){

                        previewInfo.postValue(model);
                    }

                }



            }

            @Override
            public void onFailure(Call<PreviewInfoModel> call, Throwable t) {

            }
        });
    }
}
