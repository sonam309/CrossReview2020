package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreviewInfoModel extends ClsResultStateResponseModel {

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

        @SerializedName("Employer_Id")
        @Expose
        private String employerId;
        @SerializedName("Employer_Email")
        @Expose
        private String employerEmail;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("Employee_Id")
        @Expose
        private Object employeeId;
        @SerializedName("Employee_Name")
        @Expose
        private Object employeeName;
        @SerializedName("Employee_Gender")
        @Expose
        private Object employeeGender;
        @SerializedName("Employee_Address")
        @Expose
        private Object employeeAddress;
        @SerializedName("Employee_City")
        @Expose
        private Object employeeCity;
        @SerializedName("Employee_State")
        @Expose
        private Object employeeState;
        @SerializedName("Employee_Pin_Code")
        @Expose
        private Object employeePinCode;
        @SerializedName("Employe_Profile_Pic")
        @Expose
        private Object employeProfilePic;
        @SerializedName("Employee_DOB")
        @Expose
        private Object employeeDOB;
        @SerializedName("Employee_Father_Name")
        @Expose
        private Object employeeFatherName;
        @SerializedName("Current_Work_Location")
        @Expose
        private Object currentWorkLocation;
        @SerializedName("Current_Designation_Detail")
        @Expose
        private Object currentDesignationDetail;
        @SerializedName("Police_verification")
        @Expose
        private Boolean policeVerification;
        @SerializedName("Education_Verification")
        @Expose
        private Boolean educationVerification;
        @SerializedName("requested_by")
        @Expose
        private Object requestedBy;
        @SerializedName("requested_to")
        @Expose
        private Object requestedTo;
        @SerializedName("Experience")
        @Expose
        private Boolean experience;
        @SerializedName("varyfied_by_Employer_Id")
        @Expose
        private Object varyfiedByEmployerId;
        @SerializedName("varyfied_by_Employer_Email")
        @Expose
        private Object varyfiedByEmployerEmail;
        @SerializedName("DOJ")
        @Expose
        private Object dOJ;
        @SerializedName("DOI")
        @Expose
        private Object dOI;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("Education")
        @Expose
        private List<Object> education = null;
        @SerializedName("Experiences")
        @Expose
        private List<Object> experiences = null;
        @SerializedName("PoliceVerifications")
        @Expose
        private List<Object> policeVerifications = null;
        @SerializedName("Addresses")
        @Expose
        private List<Object> addresses = null;
        @SerializedName("Relatives")
        @Expose
        private List<Object> relatives = null;
        @SerializedName("auth_token")
        @Expose
        private String authToken;
        @SerializedName("Employer")
        @Expose
        private Employer employer;


        public class Employer {

            @SerializedName("Employer_Name")
            @Expose
            private String employerName;
            @SerializedName("Employer_Email")
            @Expose
            private String employerEmail;
            @SerializedName("Employer_Contact")
            @Expose
            private String employerContact;
            @SerializedName("Organization_Name")
            @Expose
            private String organizationName;
            @SerializedName("Organization_Address")
            @Expose
            private String organizationAddress;


            public String getEmployerName() {
                return employerName;
            }

            public void setEmployerName(String employerName) {
                this.employerName = employerName;
            }

            public String getEmployerEmail() {
                return employerEmail;
            }

            public void setEmployerEmail(String employerEmail) {
                this.employerEmail = employerEmail;
            }

            public String getEmployerContact() {
                return employerContact;
            }

            public void setEmployerContact(String employerContact) {
                this.employerContact = employerContact;
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
        }


        public String getEmployerId() {
            return employerId;
        }

        public void setEmployerId(String employerId) {
            this.employerId = employerId;
        }

        public String getEmployerEmail() {
            return employerEmail;
        }

        public void setEmployerEmail(String employerEmail) {
            this.employerEmail = employerEmail;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public Object getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Object employeeId) {
            this.employeeId = employeeId;
        }

        public Object getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(Object employeeName) {
            this.employeeName = employeeName;
        }

        public Object getEmployeeGender() {
            return employeeGender;
        }

        public void setEmployeeGender(Object employeeGender) {
            this.employeeGender = employeeGender;
        }

        public Object getEmployeeAddress() {
            return employeeAddress;
        }

        public void setEmployeeAddress(Object employeeAddress) {
            this.employeeAddress = employeeAddress;
        }

        public Object getEmployeeCity() {
            return employeeCity;
        }

        public void setEmployeeCity(Object employeeCity) {
            this.employeeCity = employeeCity;
        }

        public Object getEmployeeState() {
            return employeeState;
        }

        public void setEmployeeState(Object employeeState) {
            this.employeeState = employeeState;
        }

        public Object getEmployeePinCode() {
            return employeePinCode;
        }

        public void setEmployeePinCode(Object employeePinCode) {
            this.employeePinCode = employeePinCode;
        }

        public Object getEmployeProfilePic() {
            return employeProfilePic;
        }

        public void setEmployeProfilePic(Object employeProfilePic) {
            this.employeProfilePic = employeProfilePic;
        }

        public Object getEmployeeDOB() {
            return employeeDOB;
        }

        public void setEmployeeDOB(Object employeeDOB) {
            this.employeeDOB = employeeDOB;
        }

        public Object getEmployeeFatherName() {
            return employeeFatherName;
        }

        public void setEmployeeFatherName(Object employeeFatherName) {
            this.employeeFatherName = employeeFatherName;
        }

        public Object getCurrentWorkLocation() {
            return currentWorkLocation;
        }

        public void setCurrentWorkLocation(Object currentWorkLocation) {
            this.currentWorkLocation = currentWorkLocation;
        }

        public Object getCurrentDesignationDetail() {
            return currentDesignationDetail;
        }

        public void setCurrentDesignationDetail(Object currentDesignationDetail) {
            this.currentDesignationDetail = currentDesignationDetail;
        }

        public Boolean getPoliceVerification() {
            return policeVerification;
        }

        public void setPoliceVerification(Boolean policeVerification) {
            this.policeVerification = policeVerification;
        }

        public Boolean getEducationVerification() {
            return educationVerification;
        }

        public void setEducationVerification(Boolean educationVerification) {
            this.educationVerification = educationVerification;
        }

        public Object getRequestedBy() {
            return requestedBy;
        }

        public void setRequestedBy(Object requestedBy) {
            this.requestedBy = requestedBy;
        }

        public Object getRequestedTo() {
            return requestedTo;
        }

        public void setRequestedTo(Object requestedTo) {
            this.requestedTo = requestedTo;
        }

        public Boolean getExperience() {
            return experience;
        }

        public void setExperience(Boolean experience) {
            this.experience = experience;
        }

        public Object getVaryfiedByEmployerId() {
            return varyfiedByEmployerId;
        }

        public void setVaryfiedByEmployerId(Object varyfiedByEmployerId) {
            this.varyfiedByEmployerId = varyfiedByEmployerId;
        }

        public Object getVaryfiedByEmployerEmail() {
            return varyfiedByEmployerEmail;
        }

        public void setVaryfiedByEmployerEmail(Object varyfiedByEmployerEmail) {
            this.varyfiedByEmployerEmail = varyfiedByEmployerEmail;
        }

        public Object getdOJ() {
            return dOJ;
        }

        public void setdOJ(Object dOJ) {
            this.dOJ = dOJ;
        }

        public Object getdOI() {
            return dOI;
        }

        public void setdOI(Object dOI) {
            this.dOI = dOI;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<Object> getEducation() {
            return education;
        }

        public void setEducation(List<Object> education) {
            this.education = education;
        }

        public List<Object> getExperiences() {
            return experiences;
        }

        public void setExperiences(List<Object> experiences) {
            this.experiences = experiences;
        }

        public List<Object> getPoliceVerifications() {
            return policeVerifications;
        }

        public void setPoliceVerifications(List<Object> policeVerifications) {
            this.policeVerifications = policeVerifications;
        }

        public List<Object> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Object> addresses) {
            this.addresses = addresses;
        }

        public List<Object> getRelatives() {
            return relatives;
        }

        public void setRelatives(List<Object> relatives) {
            this.relatives = relatives;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public Employer getEmployer() {
            return employer;
        }

        public void setEmployer(Employer employer) {
            this.employer = employer;
        }
    }

}





