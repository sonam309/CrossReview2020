package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreviewInfoModel extends ClsResultStateResponseModel {


    @SerializedName("data")
    @Expose
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{


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
        private String employeePinCode;
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
        @SerializedName("Police_verification")
        @Expose
        private Boolean policeVerification;
        @SerializedName("Education_Verification")
        @Expose
        private Boolean educationVerification;
        @SerializedName("requested_by")
        @Expose
        private String requestedBy;
        @SerializedName("requested_to")
        @Expose
        private String requestedTo;
        @SerializedName("Experience")
        @Expose
        private Boolean experience;
        @SerializedName("varyfied_by_Employer_Id")
        @Expose
        private String varyfiedByEmployerId;
        @SerializedName("varyfied_by_Employer_Email")
        @Expose
        private String varyfiedByEmployerEmail;
        @SerializedName("DOJ")
        @Expose
        private String dOJ;
        @SerializedName("DOI")
        @Expose
        private String dOI;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("Education")
        @Expose
        private List<Education> education = null;
        @SerializedName("Experiences")
        @Expose
        private List<Experience> experiences = null;
        @SerializedName("PoliceVerifications")
        @Expose
        private List<PoliceVerification> policeVerifications = null;
        @SerializedName("Addresses")
        @Expose
        private List<Address> addresses = null;
        @SerializedName("Relatives")
        @Expose
        private List<Relative> relatives = null;
        @SerializedName("auth_token")
        @Expose
        private String authToken;
        @SerializedName("Employer")
        @Expose
        private Employer employer;


        public String getEmployerId() {
            return employerId==null?"":employerId;
        }

        public void setEmployerId(String employerId) {
            this.employerId = employerId;
        }

        public String getEmployerEmail() {
            return employerEmail==null?"":employerEmail;
        }

        public void setEmployerEmail(String employerEmail) {
            this.employerEmail = employerEmail;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId==null?"":employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getEmployeeId() {
            return employeeId==null?"":employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName==null?"":employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeGender() {
            return employeeGender==null?"":employeeGender;
        }

        public void setEmployeeGender(String employeeGender) {
            this.employeeGender = employeeGender;
        }

        public String getEmployeeAddress() {
            return employeeAddress==null?"":employeeAddress;
        }

        public void setEmployeeAddress(String employeeAddress) {
            this.employeeAddress = employeeAddress;
        }

        public String getEmployeeCity() {
            return employeeCity==null?"":employeeCity;
        }

        public void setEmployeeCity(String employeeCity) {
            this.employeeCity = employeeCity;
        }

        public String getEmployeeState() {
            return employeeState==null?"":employeeState;
        }

        public void setEmployeeState(String employeeState) {
            this.employeeState = employeeState;
        }

        public String getEmployeePinCode() {
            return employeePinCode==null?"":employeePinCode;
        }

        public void setEmployeePinCode(String employeePinCode) {
            this.employeePinCode = employeePinCode;
        }

        public String getEmployeProfilePic() {
            return employeProfilePic==null?"":employeProfilePic;
        }

        public void setEmployeProfilePic(String employeProfilePic) {
            this.employeProfilePic = employeProfilePic;
        }

        public String getEmployeeDOB() {
            return employeeDOB==null?"":employeeDOB;
        }

        public void setEmployeeDOB(String employeeDOB) {
            this.employeeDOB = employeeDOB;
        }

        public String getEmployeeFatherName() {
            return employeeFatherName==null?"":employeeFatherName;
        }

        public void setEmployeeFatherName(String employeeFatherName) {
            this.employeeFatherName = employeeFatherName;
        }

        public String getCurrentWorkLocation() {
            return currentWorkLocation==null?"":currentWorkLocation;
        }

        public void setCurrentWorkLocation(String currentWorkLocation) {
            this.currentWorkLocation = currentWorkLocation;
        }

        public String getCurrentDesignationDetail() {
            return currentDesignationDetail==null?"":currentDesignationDetail;
        }

        public void setCurrentDesignationDetail(String currentDesignationDetail) {
            this.currentDesignationDetail = currentDesignationDetail;
        }

        public Boolean getPoliceVerification() {
            return policeVerification==null;
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

        public String getRequestedBy() {
            return requestedBy==null?"":requestedBy;
        }

        public void setRequestedBy(String requestedBy) {
            this.requestedBy = requestedBy;
        }

        public String getRequestedTo() {
            return requestedTo==null?"":requestedTo;
        }

        public void setRequestedTo(String requestedTo) {
            this.requestedTo = requestedTo;
        }

        public Boolean getExperience() {
            return experience;
        }

        public void setExperience(Boolean experience) {
            this.experience = experience;
        }

        public String getVaryfiedByEmployerId() {
            return varyfiedByEmployerId==null?"":varyfiedByEmployerId;
        }

        public void setVaryfiedByEmployerId(String varyfiedByEmployerId) {
            this.varyfiedByEmployerId = varyfiedByEmployerId;
        }

        public String getVaryfiedByEmployerEmail() {
            return varyfiedByEmployerEmail==null?"":varyfiedByEmployerEmail;
        }

        public void setVaryfiedByEmployerEmail(String varyfiedByEmployerEmail) {
            this.varyfiedByEmployerEmail = varyfiedByEmployerEmail;
        }

        public String getdOJ() {
            return dOJ==null?"":dOJ;
        }

        public void setdOJ(String dOJ) {
            this.dOJ = dOJ;
        }

        public String getdOI() {
            return dOI==null?"":dOI;
        }

        public void setdOI(String dOI) {
            this.dOI = dOI;
        }

        public String getStatus() {
            return status==null?"":status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt==null?"":createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<Education> getEducation() {
            return education;
        }

        public void setEducation(List<Education> education) {
            this.education = education;
        }

        public List<Experience> getExperiences() {
            return experiences;
        }

        public void setExperiences(List<Experience> experiences) {
            this.experiences = experiences;
        }

        public List<PoliceVerification> getPoliceVerifications() {
            return policeVerifications;
        }

        public void setPoliceVerifications(List<PoliceVerification> policeVerifications) {
            this.policeVerifications = policeVerifications;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public List<Relative> getRelatives() {
            return relatives;
        }

        public void setRelatives(List<Relative> relatives) {
            this.relatives = relatives;
        }

        public String getAuthToken() {
            return authToken==null?"":authToken;
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


    public class Relative {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("relative_name")
        @Expose
        private String relativeName;
        @SerializedName("relative_address")
        @Expose
        private String relativeAddress;
        @SerializedName("relative_contact")
        @Expose
        private String relativeContact;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId==null?"":employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getRelativeName() {
            return relativeName==null?"":relativeName;
        }

        public void setRelativeName(String relativeName) {
            this.relativeName = relativeName;
        }

        public String getRelativeAddress() {
            return relativeAddress==null?"":relativeAddress;
        }

        public void setRelativeAddress(String relativeAddress) {
            this.relativeAddress = relativeAddress;
        }

        public String getRelativeContact() {
            return relativeContact==null?"":relativeContact;
        }

        public void setRelativeContact(String relativeContact) {
            this.relativeContact = relativeContact;
        }
    }



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
            return employerName==null?"":employerName;
        }

        public void setEmployerName(String employerName) {
            this.employerName = employerName;
        }

        public String getEmployerEmail() {
            return employerEmail==null?"":employerEmail;
        }

        public void setEmployerEmail(String employerEmail) {
            this.employerEmail = employerEmail;
        }

        public String getEmployerContact() {
            return employerContact==null?"":employerContact;
        }

        public void setEmployerContact(String employerContact) {
            this.employerContact = employerContact;
        }

        public String getOrganizationName() {
            return organizationName==null?"":organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getOrganizationAddress() {
            return organizationAddress==null?"":organizationAddress;
        }

        public void setOrganizationAddress(String organizationAddress) {
            this.organizationAddress = organizationAddress;
        }
    }



    public class Address {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("address_type")
        @Expose
        private String addressType;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("village")
        @Expose
        private String village;
        @SerializedName("post_office")
        @Expose
        private String postOffice;
        @SerializedName("police_station")
        @Expose
        private String policeStation;
        @SerializedName("distric")
        @Expose
        private String distric;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("pin_code")
        @Expose
        private Integer pinCode;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId==null?"":employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getAddressType() {
            return addressType==null?"":addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public Object getAddress() {
            return address==null?"":address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVillage() {
            return village==null?"":village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getPostOffice() {
            return postOffice==null?"":postOffice;
        }

        public void setPostOffice(String postOffice) {
            this.postOffice = postOffice;
        }

        public String getPoliceStation() {
            return policeStation==null?"":policeStation;
        }

        public void setPoliceStation(String policeStation) {
            this.policeStation = policeStation;
        }

        public String getDistric() {
            return distric;
        }

        public void setDistric(String distric) {
            this.distric = distric;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getPinCode() {
            return pinCode;
        }

        public void setPinCode(Integer pinCode) {
            this.pinCode = pinCode;
        }
    }



    public class PoliceVerification {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("Mother_Name")
        @Expose
        private String motherName;
        @SerializedName("birth_place")
        @Expose
        private String birthPlace;
        @SerializedName("language_spoken")
        @Expose
        private String languageSpoken;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("weight")
        @Expose
        private Integer weight;
        @SerializedName("complexion")
        @Expose
        private String complexion;
        @SerializedName("identification_marks")
        @Expose
        private String identificationMarks;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("requested_by")
        @Expose
        private String requestedBy;
        @SerializedName("requested_to")
        @Expose
        private String requestedTo;
        @SerializedName("verifier_name")
        @Expose
        private String verifierName;
        @SerializedName("verifier_email")
        @Expose
        private String verifierEmail;
        @SerializedName("verifier_designation")
        @Expose
        private String verifierDesignation;
        @SerializedName("adharCard_number")
        @Expose
        private String adharCardNumber;
        @SerializedName("DLC_number")
        @Expose
        private String dLCNumber;
        @SerializedName("voterCard_number")
        @Expose
        private String voterCardNumber;
        @SerializedName("passportId_number")
        @Expose
        private String passportIdNumber;
        @SerializedName("Documents")
        @Expose
        private List<Object> documents = null;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getLanguageSpoken() {
            return languageSpoken;
        }

        public void setLanguageSpoken(String languageSpoken) {
            this.languageSpoken = languageSpoken;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getComplexion() {
            return complexion;
        }

        public void setComplexion(String complexion) {
            this.complexion = complexion;
        }

        public String getIdentificationMarks() {
            return identificationMarks;
        }

        public void setIdentificationMarks(String identificationMarks) {
            this.identificationMarks = identificationMarks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRequestedBy() {
            return requestedBy;
        }

        public void setRequestedBy(String requestedBy) {
            this.requestedBy = requestedBy;
        }

        public String getRequestedTo() {
            return requestedTo;
        }

        public void setRequestedTo(String requestedTo) {
            this.requestedTo = requestedTo;
        }

        public String getVerifierName() {
            return verifierName;
        }

        public void setVerifierName(String verifierName) {
            this.verifierName = verifierName;
        }

        public String getVerifierEmail() {
            return verifierEmail;
        }

        public void setVerifierEmail(String verifierEmail) {
            this.verifierEmail = verifierEmail;
        }

        public String getVerifierDesignation() {
            return verifierDesignation;
        }

        public void setVerifierDesignation(String verifierDesignation) {
            this.verifierDesignation = verifierDesignation;
        }

        public String getAdharCardNumber() {
            return adharCardNumber;
        }

        public void setAdharCardNumber(String adharCardNumber) {
            this.adharCardNumber = adharCardNumber;
        }

        public String getdLCNumber() {
            return dLCNumber;
        }

        public void setdLCNumber(String dLCNumber) {
            this.dLCNumber = dLCNumber;
        }

        public String getVoterCardNumber() {
            return voterCardNumber;
        }

        public void setVoterCardNumber(String voterCardNumber) {
            this.voterCardNumber = voterCardNumber;
        }

        public String getPassportIdNumber() {
            return passportIdNumber;
        }

        public void setPassportIdNumber(String passportIdNumber) {
            this.passportIdNumber = passportIdNumber;
        }

        public List<Object> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Object> documents) {
            this.documents = documents;
        }
    }



    public class Experience{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
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
        @SerializedName("Reporting_Person_Name")
        @Expose
        private String reportingPersonName;
        @SerializedName("Reporting_Person_Designation")
        @Expose
        private String reportingPersonDesignation;
        @SerializedName("performance_review")
        @Expose
        private String performanceReview;
        @SerializedName("rehire_elegibility")
        @Expose
        private Boolean rehireElegibility;
        @SerializedName("DOJ")
        @Expose
        private String dOJ;
        @SerializedName("DOR")
        @Expose
        private String dOR;
        @SerializedName("leaving_reson")
        @Expose
        private String leavingReson;
        @SerializedName("last_CTC_lac")
        @Expose
        private String lastCTCLac;
        @SerializedName("last_CTC_thousand")
        @Expose
        private String lastCTCThousand;
        @SerializedName("to_verify")
        @Expose
        private Boolean toVerify;
        @SerializedName("verifier_name")
        @Expose
        private String verifierName;
        @SerializedName("verifier_email")
        @Expose
        private String verifierEmail;
        @SerializedName("verifier_designation")
        @Expose
        private String verifierDesignation;
        @SerializedName("Documents")
        @Expose
        private List<Document_> documents = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
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

        public Boolean getRehireElegibility() {
            return rehireElegibility;
        }

        public void setRehireElegibility(Boolean rehireElegibility) {
            this.rehireElegibility = rehireElegibility;
        }

        public String getdOJ() {
            return dOJ;
        }

        public void setdOJ(String dOJ) {
            this.dOJ = dOJ;
        }

        public String getdOR() {
            return dOR;
        }

        public void setdOR(String dOR) {
            this.dOR = dOR;
        }

        public String getLeavingReson() {
            return leavingReson;
        }

        public void setLeavingReson(String leavingReson) {
            this.leavingReson = leavingReson;
        }

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

        public Boolean getToVerify() {
            return toVerify;
        }

        public void setToVerify(Boolean toVerify) {
            this.toVerify = toVerify;
        }

        public String getVerifierName() {
            return verifierName;
        }

        public void setVerifierName(String verifierName) {
            this.verifierName = verifierName;
        }

        public String getVerifierEmail() {
            return verifierEmail;
        }

        public void setVerifierEmail(String verifierEmail) {
            this.verifierEmail = verifierEmail;
        }

        public String getVerifierDesignation() {
            return verifierDesignation;
        }

        public void setVerifierDesignation(String verifierDesignation) {
            this.verifierDesignation = verifierDesignation;
        }

        public List<Document_> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document_> documents) {
            this.documents = documents;
        }
    }



    public class Document_{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("reference_id")
        @Expose
        private String referenceId;
        @SerializedName("Document_Type")
        @Expose
        private String documentType;
        @SerializedName("Document_Name")
        @Expose
        private String documentName;
        @SerializedName("Document_Key")
        @Expose
        private String documentKey;
        @SerializedName("Document_URL")
        @Expose
        private String documentURL;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getReferenceId() {
            return referenceId;
        }

        public void setReferenceId(String referenceId) {
            this.referenceId = referenceId;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public Object getDocumentKey() {
            return documentKey;
        }

        public void setDocumentKey(String documentKey) {
            this.documentKey = documentKey;
        }

        public String getDocumentURL() {
            return documentURL;
        }

        public void setDocumentURL(String documentURL) {
            this.documentURL = documentURL;
        }
    }




    public class Education{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
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
        @SerializedName("grading_system")
        @Expose
        private String gradingSystem;
        @SerializedName("pass_out_year")
        @Expose
        private Integer passOutYear;
        @SerializedName("marks")
        @Expose
        private String marks;
        @SerializedName("specialization")
        @Expose
        private String specialization;
        @SerializedName("verifier_name")
        @Expose
        private String verifierName;
        @SerializedName("verifier_email")
        @Expose
        private String verifierEmail;
        @SerializedName("verifier_designation")
        @Expose
        private String verifierDesignation;
        @SerializedName("Documents")
        @Expose
        private List<Document> documents = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
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

        public String getGradingSystem() {
            return gradingSystem;
        }

        public void setGradingSystem(String gradingSystem) {
            this.gradingSystem = gradingSystem;
        }

        public Integer getPassOutYear() {
            return passOutYear;
        }

        public void setPassOutYear(Integer passOutYear) {
            this.passOutYear = passOutYear;
        }

        public Object getMarks() {
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

        public Object getVerifierName() {
            return verifierName;
        }

        public void setVerifierName(String verifierName) {
            this.verifierName = verifierName;
        }

        public Object getVerifierEmail() {
            return verifierEmail;
        }

        public void setVerifierEmail(String verifierEmail) {
            this.verifierEmail = verifierEmail;
        }

        public Object getVerifierDesignation() {
            return verifierDesignation;
        }

        public void setVerifierDesignation(String verifierDesignation) {
            this.verifierDesignation = verifierDesignation;
        }

        public List<Document> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }
    }



    public class Document{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Employee_Basic_Id")
        @Expose
        private String employeeBasicId;
        @SerializedName("reference_id")
        @Expose
        private String referenceId;
        @SerializedName("Document_Type")
        @Expose
        private String documentType;
        @SerializedName("Document_Name")
        @Expose
        private String documentName;
        @SerializedName("Document_Key")
        @Expose
        private String documentKey;
        @SerializedName("Document_URL")
        @Expose
        private String documentURL;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeBasicId() {
            return employeeBasicId;
        }

        public void setEmployeeBasicId(String employeeBasicId) {
            this.employeeBasicId = employeeBasicId;
        }

        public String getReferenceId() {
            return referenceId;
        }

        public void setReferenceId(String referenceId) {
            this.referenceId = referenceId;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public Object getDocumentKey() {
            return documentKey;
        }

        public void setDocumentKey(String documentKey) {
            this.documentKey = documentKey;
        }

        public String getDocumentURL() {
            return documentURL;
        }

        public void setDocumentURL(String documentURL) {
            this.documentURL = documentURL;
        }
    }

}





