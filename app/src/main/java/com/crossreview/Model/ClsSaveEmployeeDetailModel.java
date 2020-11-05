package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsSaveEmployeeDetailModel {

    @SerializedName("data")
    @Expose
    private Data_ data;

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }
    public class Data_ {

        @SerializedName("Employee_Id")
        @Expose
        private String employeeId;
        @SerializedName("Employee_Name")
        @Expose
        private String employeeName;
        @SerializedName("Employee_Gender")
        @Expose
        private String employeeGender;
        @SerializedName("Employee_Address")
        @Expose
        private String employeeAddress;
        @SerializedName("Employee_City")
        @Expose
        private String employeeCity;
        @SerializedName("Employee_State")
        @Expose
        private String employeeState;
        @SerializedName("Employee_Pin_Code")
        @Expose
        private Integer employeePinCode;
        @SerializedName("Employe_Profile_Pic")
        @Expose
        private String employeProfilePic;
        @SerializedName("Employee_DOB")
        @Expose
        private String employeeDOB;
        @SerializedName("Employee_Father_Name")
        @Expose
        private String employeeFatherName;
        @SerializedName("Current_Work_Location")
        @Expose
        private String currentWorkLocation;
        @SerializedName("Current_Designation_Detail")
        @Expose
        private String currentDesignationDetail;
        @SerializedName("DOJ")
        @Expose
        private String dOJ;
        @SerializedName("DOI")
        @Expose
        private String dOI;
        @SerializedName("Police_verification")
        @Expose
        private Boolean policeVerification;
        @SerializedName("Education_Verification")
        @Expose
        private Boolean educationVerification;
        @SerializedName("Experienc")
        @Expose
        private Boolean experienc;
        @SerializedName("education")
        @Expose
        private List<Education> education = null;
        @SerializedName("experience")
        @Expose
        private List<Experience> experience = null;

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeGender() {
            return employeeGender;
        }

        public void setEmployeeGender(String employeeGender) {
            this.employeeGender = employeeGender;
        }

        public String getEmployeeAddress() {
            return employeeAddress;
        }

        public void setEmployeeAddress(String employeeAddress) {
            this.employeeAddress = employeeAddress;
        }

        public String getEmployeeCity() {
            return employeeCity;
        }

        public void setEmployeeCity(String employeeCity) {
            this.employeeCity = employeeCity;
        }

        public String getEmployeeState() {
            return employeeState;
        }

        public void setEmployeeState(String employeeState) {
            this.employeeState = employeeState;
        }

        public Integer getEmployeePinCode() {
            return employeePinCode;
        }

        public void setEmployeePinCode(Integer employeePinCode) {
            this.employeePinCode = employeePinCode;
        }

        public String getEmployeProfilePic() {
            return employeProfilePic;
        }

        public void setEmployeProfilePic(String employeProfilePic) {
            this.employeProfilePic = employeProfilePic;
        }

        public String getEmployeeDOB() {
            return employeeDOB;
        }

        public void setEmployeeDOB(String employeeDOB) {
            this.employeeDOB = employeeDOB;
        }

        public String getEmployeeFatherName() {
            return employeeFatherName;
        }

        public void setEmployeeFatherName(String employeeFatherName) {
            this.employeeFatherName = employeeFatherName;
        }

        public String getCurrentWorkLocation() {
            return currentWorkLocation;
        }

        public void setCurrentWorkLocation(String currentWorkLocation) {
            this.currentWorkLocation = currentWorkLocation;
        }

        public String getCurrentDesignationDetail() {
            return currentDesignationDetail;
        }

        public void setCurrentDesignationDetail(String currentDesignationDetail) {
            this.currentDesignationDetail = currentDesignationDetail;
        }

        public String getDOJ() {
            return dOJ;
        }

        public void setDOJ(String dOJ) {
            this.dOJ = dOJ;
        }

        public String getDOI() {
            return dOI;
        }

        public void setDOI(String dOI) {
            this.dOI = dOI;
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

        public Boolean getExperienc() {
            return experienc;
        }

        public void setExperienc(Boolean experienc) {
            this.experienc = experienc;
        }

        public List<Education> getEducation() {
            return education;
        }

        public void setEducation(List<Education> education) {
            this.education = education;
        }

        public List<Experience> getExperience() {
            return experience;
        }

        public void setExperience(List<Experience> experience) {
            this.experience = experience;
        }

    }
    public class Education {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("education_type")
        @Expose
        private String educationType;
        @SerializedName("course")
        @Expose
        private String course;
        @SerializedName("university_name")
        @Expose
        private String universityName;
        @SerializedName("course_type")
        @Expose
        private String courseType;
        @SerializedName("pass_out_year")
        @Expose
        private Integer passOutYear;
        @SerializedName("grading_system")
        @Expose
        private String gradingSystem;
        @SerializedName("marks")
        @Expose
        private String marks;
        @SerializedName("specialization")
        @Expose
        private String specialization;
        @SerializedName("to_verify")
        @Expose
        private Boolean toVerify;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEducationType() {
            return educationType;
        }

        public void setEducationType(String educationType) {
            this.educationType = educationType;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getUniversityName() {
            return universityName;
        }

        public void setUniversityName(String universityName) {
            this.universityName = universityName;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public Integer getPassOutYear() {
            return passOutYear;
        }

        public void setPassOutYear(Integer passOutYear) {
            this.passOutYear = passOutYear;
        }

        public String getGradingSystem() {
            return gradingSystem;
        }

        public void setGradingSystem(String gradingSystem) {
            this.gradingSystem = gradingSystem;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public Boolean getToVerify() {
            return toVerify;
        }

        public void setToVerify(Boolean toVerify) {
            this.toVerify = toVerify;
        }

    }
    public class EmployeeDetails {

        @SerializedName("data")
        @Expose
        private ClsSaveEmployeeDetailModel data;

        public ClsSaveEmployeeDetailModel getData() {
            return data;
        }

        public void setData(ClsSaveEmployeeDetailModel data) {
            this.data = data;
        }

    }
    public class Experience {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Designation")
        @Expose
        private String employeeDesignation;
        @SerializedName("Job_Role")
        @Expose
        private String jobRole;
        @SerializedName("Organization_Id")
        @Expose
        private String organizationId;
        @SerializedName("exit_formalities_compeleted")
        @Expose
        private Boolean exitFormalitiesCompeleted;
        @SerializedName("DOR")
        @Expose
        private String dOR;
        @SerializedName("Reporting_Person_Name")
        @Expose
        private String reportingPersonName;
        @SerializedName("Reporting_Person_Designation")
        @Expose
        private String reportingPersonDesignation;
        @SerializedName("performance_review")
        @Expose
        private String performanceReview;
        @SerializedName("leaving_reson")
        @Expose
        private String leavingReson;
        @SerializedName("last_CTC_lac")
        @Expose
        private String lastCTCLac;
        @SerializedName("last_CTC_thousand")
        @Expose
        private String lastCTCThousand;
        @SerializedName("DOJ")
        @Expose
        private String dOJ;
        @SerializedName("rehire_elegibility")
        @Expose
        private Boolean rehireElegibility;
        @SerializedName("to_verify")
        @Expose
        private Boolean toVerify;


        public String getLastCTCLac() {
            return lastCTCLac;
        }

        public void setLastCTCLac(String lastCTCLac) {
            this.lastCTCLac = lastCTCLac;
        }

        public String getLastCTCThousand() {
            return lastCTCThousand;
        }

        public void setLastCTCThousand(String lastCTCThousand) {
            this.lastCTCThousand = lastCTCThousand;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeDesignation() {
            return employeeDesignation;
        }

        public void setEmployeeDesignation(String employeeDesignation) {
            this.employeeDesignation = employeeDesignation;
        }

        public String getJobRole() {
            return jobRole;
        }

        public void setJobRole(String jobRole) {
            this.jobRole = jobRole;
        }

        public String getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public Boolean getExitFormalitiesCompeleted() {
            return exitFormalitiesCompeleted;
        }

        public void setExitFormalitiesCompeleted(Boolean exitFormalitiesCompeleted) {
            this.exitFormalitiesCompeleted = exitFormalitiesCompeleted;
        }

        public String getDOR() {
            return dOR;
        }

        public void setDOR(String dOR) {
            this.dOR = dOR;
        }

        public String getReportingPersonName() {
            return reportingPersonName;
        }

        public void setReportingPersonName(String reportingPersonName) {
            this.reportingPersonName = reportingPersonName;
        }

        public String getReportingPersonDesignation() {
            return reportingPersonDesignation;
        }

        public void setReportingPersonDesignation(String reportingPersonDesignation) {
            this.reportingPersonDesignation = reportingPersonDesignation;
        }

        public String getPerformanceReview() {
            return performanceReview;
        }

        public void setPerformanceReview(String performanceReview) {
            this.performanceReview = performanceReview;
        }

        public String getLeavingReson() {
            return leavingReson;
        }

        public void setLeavingReson(String leavingReson) {
            this.leavingReson = leavingReson;
        }


        public String getDOJ() {
            return dOJ;
        }

        public void setDOJ(String dOJ) {
            this.dOJ = dOJ;
        }

        public Boolean getRehireElegibility() {
            return rehireElegibility;
        }

        public void setRehireElegibility(Boolean rehireElegibility) {
            this.rehireElegibility = rehireElegibility;
        }

        public Boolean getToVerify() {
            return toVerify;
        }

        public void setToVerify(Boolean toVerify) {
            this.toVerify = toVerify;
        }

    }
}