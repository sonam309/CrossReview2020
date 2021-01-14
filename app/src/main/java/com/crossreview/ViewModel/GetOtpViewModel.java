package com.crossreview.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.GetOtpRequestModel;
import com.crossreview.Model.GetOtpResponseModel;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetOtpViewModel extends ViewModel {


    public MutableLiveData<GetOtpResponseModel> getOtp= new MutableLiveData<>();


    public void getOtpfun(String email, Context context){

        Utility.showLoader();
        ApiClient.getBaseApiMethods().getOtp(new GetOtpRequestModel(email)).enqueue(new Callback<GetOtpResponseModel>() {
            @Override

            public void onResponse(Call<GetOtpResponseModel> call, Response<GetOtpResponseModel> response) {

                Utility.hideLoader();

                if(response.isSuccessful()){
                    GetOtpResponseModel model= response.body();

                    if(response.body()!=null){
                        getOtp.postValue(model);
                    }

                }
                else {

                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<GetOtpResponseModel> call, Throwable t) {

//                Toast.makeText(context, "Please enter valid emial", Toast.LENGTH_SHORT).show();
                Utility.hideLoader();

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}
