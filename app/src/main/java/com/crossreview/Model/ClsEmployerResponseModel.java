package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsEmployerResponseModel extends ClsResultStateResponseModel {

    @SerializedName("data")
    @Expose
    private Data data;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("rates")
        @Expose
        private Rates rates;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Rates getRates() {
            return rates;
        }

        public void setRates(Rates rates) {
            this.rates = rates;
        }

    }

    public class Rates {

        @SerializedName("last_employement")
        @Expose
        private Integer lastEmployement;
        @SerializedName("other_last_employement")
        @Expose
        private Integer otherLastEmployement;
        @SerializedName("high_school")
        @Expose
        private Integer highSchool;
        @SerializedName("intermediate")
        @Expose
        private Integer intermediate;
        @SerializedName("graduates")
        @Expose
        private Integer graduates;
        @SerializedName("masters")
        @Expose
        private Integer masters;
        @SerializedName("police_verification")
        @Expose
        private Integer policeVerification;


        public Integer getLastEmployement() {
            return lastEmployement;
        }

        public void setLastEmployement(Integer lastEmployement) {
            this.lastEmployement = lastEmployement;
        }

        public Integer getOtherLastEmployement() {
            return otherLastEmployement;
        }

        public void setOtherLastEmployement(Integer otherLastEmployement) {
            this.otherLastEmployement = otherLastEmployement;
        }

        public Integer getHighSchool() {
            return highSchool;
        }

        public void setHighSchool(Integer highSchool) {
            this.highSchool = highSchool;
        }

        public Integer getIntermediate() {
            return intermediate;
        }

        public void setIntermediate(Integer intermediate) {
            this.intermediate = intermediate;
        }

        public Integer getGraduates() {
            return graduates;
        }

        public void setGraduates(Integer graduates) {
            this.graduates = graduates;
        }

        public Integer getMasters() {
            return masters;
        }

        public void setMasters(Integer masters) {
            this.masters = masters;
        }

        public Integer getPoliceVerification() {
            return policeVerification;
        }

        public void setPoliceVerification(Integer policeVerification) {
            this.policeVerification = policeVerification;
        }
    }

}
