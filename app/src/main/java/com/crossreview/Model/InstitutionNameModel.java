package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstitutionNameModel extends ClsResultStateResponseModel {

    @SerializedName("data")
    @Expose
    private List<data> data = null;

    public List<InstitutionNameModel.data> getData() {
        return data;
    }

    public void setData(List<InstitutionNameModel.data> data) {
        this.data = data;
    }

    public class data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("university_name")
        @Expose
        private String universityName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUniversityName() {
            return universityName;
        }

        public void setUniversityName(String universityName) {
            this.universityName = universityName;
        }

        @Override
        public String toString() {
            return universityName;
        }
    }


}
