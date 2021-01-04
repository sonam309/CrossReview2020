package com.crossreview.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CompanyNameModel extends ClsResultStateResponseModel{
    @SerializedName("data")
    @Expose
    private Data data = null;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("orgList")
        @Expose
        public List<orgList> orgList=null;

        @SerializedName("Input")
        @Expose
        public String Input;

        public List<CompanyNameModel.orgList> getOrgList() {
            return orgList;
        }

        public void setOrgList(List<CompanyNameModel.orgList> orgList) {
            this.orgList = orgList;
        }

        public String getInput() {
            return Input;
        }

        public void setInput(String input) {
            Input = input;
        }
    }

    public class orgList {

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

