package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSelfDetailsModel extends ClsResultStateResponseModel {

    private String status;
    @SerializedName("data")
    @Expose
    private Data data;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("Employer_Name")
        @Expose
        private String employerName;
        @SerializedName("Organization_Id")
        @Expose
        private String organizationId;
        @SerializedName("Employer_Desigination")
        @Expose
        private String employerDesigination;
        @SerializedName("Employer_Id")
        @Expose
        private String employerId;
        @SerializedName("Employer_Contact")
        @Expose
        private String employerContact;
        @SerializedName("Employer_Email")
        @Expose
        private String employerEmail;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("Organization_Name")
        @Expose
        private String organizationName;
        @SerializedName("Organization_Address")
        @Expose
        private String organizationAddress;
        @SerializedName("Organization_gst_no")
        @Expose
        private Object organizationGstNo;


        public String getEmployerName() {
            return employerName;
        }

        public void setEmployerName(String employerName) {
            this.employerName = employerName;
        }

        public String getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public String getEmployerDesigination() {
            return employerDesigination;
        }

        public void setEmployerDesigination(String employerDesigination) {
            this.employerDesigination = employerDesigination;
        }

        public String getEmployerId() {
            return employerId;
        }

        public void setEmployerId(String employerId) {
            this.employerId = employerId;
        }

        public String getEmployerContact() {
            return employerContact;
        }

        public void setEmployerContact(String employerContact) {
            this.employerContact = employerContact;
        }

        public String getEmployerEmail() {
            return employerEmail;
        }

        public void setEmployerEmail(String employerEmail) {
            this.employerEmail = employerEmail;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getOrganizationAddress() {
            return organizationAddress;
        }

        public void setOrganizationAddress(String organizationAddress) {
            this.organizationAddress = organizationAddress;
        }

        public Object getOrganizationGstNo() {
            return organizationGstNo;
        }

        public void setOrganizationGstNo(Object organizationGstNo) {
            this.organizationGstNo = organizationGstNo;
        }
    }

}
