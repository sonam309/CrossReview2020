package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOtpRequestModel {

    @SerializedName("Employer_Email")
    @Expose
    private String employerEmail;

    public GetOtpRequestModel(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }
}
