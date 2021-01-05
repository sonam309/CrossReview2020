package com.crossreview.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CompanyNameModel extends ClsResultStateResponseModel{
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("Organization_Name")
        @Expose
        private String organizationName;
        @SerializedName("Organization_Id")
        @Expose
        private String organizationId;
        @SerializedName("Organization_Email")
        @Expose
        private String organizationEmail;
        @SerializedName("Organization_Address")
        @Expose
        private String organizationAddress;

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public String getOrganizationEmail() {
            return organizationEmail;
        }

        public void setOrganizationEmail(String organizationEmail) {
            this.organizationEmail = organizationEmail;
        }

        public String getOrganizationAddress() {
            return organizationAddress;
        }

        public void setOrganizationAddress(String organizationAddress) {
            this.organizationAddress = organizationAddress;
        }

        @NonNull
        @Override
        public String toString() {
            return organizationName;
        }
    }
    }

