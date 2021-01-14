package com.crossreview.ViewModel;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    public MutableLiveData<InstitutionNameModel> instituteName = new MutableLiveData<>();


    public void InstituteNamefun(String InstituteName, ProgressBar progressBar, Context context) {


        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getBaseApiMethods().institution(InstituteName).enqueue(new Callback<InstitutionNameModel>() {
            @Override
            public void onResponse(Call<InstitutionNameModel> call, Response<InstitutionNameModel> response) {

                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    InstitutionNameModel model = response.body();
                    if (response.body() != null) {

                        instituteName.postValue(model);
                    }

                }else {

                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InstitutionNameModel> call, Throwable t) {

                Utility.hideLoader();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });
    }
}
