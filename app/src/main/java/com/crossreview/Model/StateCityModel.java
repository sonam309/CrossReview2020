package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateCityModel {
    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("PostOffice")
    @Expose
    private PostOffice[] PostOffice;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public StateCityModel.PostOffice[] getPostOffice() {
        return PostOffice;
    }

    public void setPostOffice(StateCityModel.PostOffice[] postOffice) {
        PostOffice = postOffice;
    }

    public class PostOffice{
        @SerializedName("State")
        @Expose
        private String State;

        @SerializedName("District")
        @Expose
        private String District;

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String district) {
            District = district;
        }
    }
}
