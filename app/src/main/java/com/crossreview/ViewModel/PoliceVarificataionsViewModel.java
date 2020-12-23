package com.crossreview.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Model.PoliceVarificataionDetailsModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoliceVarificataionsViewModel extends ViewModel {

    public MutableLiveData<PoliceVarificataionDetailsModel> policevarificataion= new MutableLiveData<>();


    public void saveCriminalBgDetails(JsonObject jsonObject){


        String token = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);

        Utility.showLoader();
        ApiClient.getBaseApiMethods().policeVarificataions(token,jsonObject).enqueue(new Callback<PoliceVarificataionDetailsModel>() {
            @Override
            public void onResponse(Call<PoliceVarificataionDetailsModel> call, Response<PoliceVarificataionDetailsModel> response) {

                Utility.hideLoader();
                if(response.isSuccessful()){

                    PoliceVarificataionDetailsModel model=response.body();
                    if(response.body()!=null){


                        policevarificataion.postValue(model);

                    }
                }

            }

            @Override
            public void onFailure(Call<PoliceVarificataionDetailsModel> call, Throwable t) {
                Utility.hideLoader();
                Log.e("pppppppppp",t.getMessage());
            }
        });



    }


}
