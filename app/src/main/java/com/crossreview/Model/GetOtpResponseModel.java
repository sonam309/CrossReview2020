package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOtpResponseModel extends ClsResultStateResponseModel {

    @SerializedName("data")
    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
