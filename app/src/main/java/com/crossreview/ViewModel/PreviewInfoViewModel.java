package com.crossreview.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.Utilites.Utility;
import com.crossreview.network.ApiClient;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewInfoViewModel extends ViewModel {


    public MutableLiveData<PreviewInfoModel> previewInfo = new MutableLiveData<>();


    public void PreviewDetail(Context context) {

        String token = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.TOKEN);

        Utility.showLoader();
        ApiClient.getBaseApiMethods().previewInfo(token).enqueue(new Callback<PreviewInfoModel>() {
            @Override
            public void onResponse(Call<PreviewInfoModel> call, Response<PreviewInfoModel> response) {

                Utility.hideLoader();
                if (response.isSuccessful()) {
                    PreviewInfoModel model = response.body();
                    if (response.body() != null) {

                        previewInfo.postValue(model);
                    }

                } else {

                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<PreviewInfoModel> call, Throwable t) {

                Utility.hideLoader();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
