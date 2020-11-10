package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    @SerializedName("Employer_Email")
    @Expose
    private String employerEmail;
    @SerializedName("otp")
    @Expose
    private String otp;

    public LoginRequestModel(String employerEmail, String otp) {
        this.employerEmail = employerEmail;
        this.otp = otp;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
