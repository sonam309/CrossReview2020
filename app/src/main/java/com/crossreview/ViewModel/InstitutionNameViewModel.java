package com.crossreview.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Model.InstitutionNameModel;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstitutionNameViewModel extends ViewModel {

    public MutableLiveData<InstitutionNameModel> instituteName= new MutableLiveData<>();


    public void InstituteNamefun(String InstituteName){


        Utility.showLoader();
        ApiClient.getBaseApiMethods().institution(InstituteName).enqueue(new Callback<InstitutionNameModel>() {
            @Override
            public void onResponse(Call<InstitutionNameModel> call, Response<InstitutionNameModel> response) {

                Utility.hideLoader();

                if(response.isSuccessful()){
                    InstitutionNameModel model= response.body();
                    if(response.body()!=null){

                        instituteName.postValue(model);
                    }

                }
            }

            @Override
            public void onFailure(Call<InstitutionNameModel> call, Throwable t) {

                Utility.hideLoader();

            }
        });
    }
}
