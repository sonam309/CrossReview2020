package com.crossreview.network;

import com.crossreview.Model.ClsEmployerResponseModel;
import com.crossreview.Model.ClsSaveEmployeeDetailModel;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.Model.GetOtpRequestModel;
import com.crossreview.Model.GetOtpResponseModel;
import com.crossreview.Model.GetSelfDetailsModel;
import com.crossreview.Model.InstitutionNameModel;
import com.crossreview.Model.LoginRequestModel;
import com.crossreview.Model.LoginResponseModel;
import com.crossreview.Model.PoliceVarificataionDetailsModel;
import com.crossreview.Model.PreviewInfoModel;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiMethods {
    @GET("get-organization-list/{input}")
    Call<CompanyNameModel> companyName(@Path(value = "input", encoded = true) String input);

    @POST("save-Employer")
    Call<ClsEmployerResponseModel> employerData(@Body JsonObject object);

    @PUT("save-employee")
    Call<ClsSaveEmployeeDetailModel> employeeDetail(@Header("auth_token") String bearerToken, @Body JsonObject object);

    @GET("get-all-university/{input}")
    Call<InstitutionNameModel> institution(@Path(value = "input", encoded = true) String input);


    @PUT("create-police-verification")
    Call<PoliceVarificataionDetailsModel> policeVarificataions(@Header("auth_token") String bearerToken, @Body JsonObject object);

    @GET("get-userbyid")
    Call<PreviewInfoModel> previewInfo(@Header("auth_token") String bearerToken);

    @POST("get-otp")
    Call<GetOtpResponseModel>getOtp(@Body GetOtpRequestModel getOtpRequestModel);

    @POST("login")
    Call<LoginResponseModel>logIn(@Body LoginRequestModel loginRequestModel);

    @GET("employer-get-self-details")
    Call<GetSelfDetailsModel>selfDetails(@Header("auth_token")String bearerToken);

//    @PUT("make-payment")
//    Call<>makePayment(@Header("Authorization") String bearerToke, @Header("auth_token") String bearerToken,@Body  )

}
