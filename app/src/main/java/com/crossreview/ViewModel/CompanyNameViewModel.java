package com.crossreview.ViewModel;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyNameViewModel extends ViewModel {

    public MutableLiveData<CompanyNameModel>companyName= new MutableLiveData<>();


    public void ComNamefun(String CompanyName, ProgressBar progressBar, Context context){

//        Utility.showLoader();
        progressBar.setVisibility(View.VISIBLE);
            ApiClient.getBaseApiMethods().companyName(CompanyName).enqueue(new Callback<CompanyNameModel>() {
                @Override
                public void onResponse(Call<CompanyNameModel> call, Response<CompanyNameModel> response) {

//                    Utility.hideLoader();
                    progressBar.setVisibility(View.GONE);
                    if(response.isSuccessful()){
                        CompanyNameModel model= response.body();
                        if(response.body()!=null){
                                companyName.postValue(model);
                        }

                    }

                    else {

                        Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CompanyNameModel> call, Throwable t) {

//                    Utility.hideLoader();
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
}
