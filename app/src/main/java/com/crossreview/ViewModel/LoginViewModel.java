package com.crossreview.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.GetOtpResponseModel;
import com.crossreview.Model.LoginRequestModel;
import com.crossreview.Model.LoginResponseModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.network.ApiClient;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginResponseModel> login= new MutableLiveData<>();


    public void login(String email, String otp, Context context){

        ApiClient.getBaseApiMethods().logIn(new LoginRequestModel(email,otp)).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                if(response.isSuccessful()){

                    LoginResponseModel model= new LoginResponseModel();
                    if(response.body()!=null){

                        login.postValue(model);

                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                Toast.makeText(context, "Please enter a valid Otp", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
