package com.crossreview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoliceVarificataionDetailsModel extends ClsResultStateResponseModel {

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
        @SerializedName("adharCard_number")
        @Expose
        private Integer adharCardNumber;
        @SerializedName("DLC_number")
        @Expose
        private String dLCNumber;
        @SerializedName("voterCard_number")
        @Expose
        private String voterCardNumber;
        @SerializedName("passportId_number")
        @Expose
        private String passportIdNumber;
        @SerializedName("document")
        @Expose
        private List<Document> document = null;
        @SerializedName("address")
        @Expose
        private Address address;
        @SerializedName("relatives")
        @Expose
        private List<Relative> relatives = null;


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

        public Integer getAdharCardNumber() {
            return adharCardNumber;
        }

        public void setAdharCardNumber(Integer adharCardNumber) {
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

        public List<Document> getDocument() {
            return document;
        }

        public void setDocument(List<Document> document) {
            this.document = document;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<Relative> getRelatives() {
            return relatives;
        }

        public void setRelatives(List<Relative> relatives) {
            this.relatives = relatives;
        }
    }

    public class Relative {

        @SerializedName("relative_name")
        @Expose
        private String relativeName;
        @SerializedName("relative_address")
        @Expose
        private String relativeAddress;
        @SerializedName("relative_contact")
        @Expose
        private Integer relativeContact;

        public String getRelativeName() {
            return relativeName;
        }

        public void setRelativeName(String relativeName) {
            this.relativeName = relativeName;
        }

        public String getRelativeAddress() {
            return relativeAddress;
        }

        public void setRelativeAddress(String relativeAddress) {
            this.relativeAddress = relativeAddress;
        }

        public Integer getRelativeContact() {
            return relativeContact;
        }

        public void setRelativeContact(Integer relativeContact) {
            this.relativeContact = relativeContact;
        }
    }

    public class Document {

        @SerializedName("Document_Name")
        @Expose
        private String documentName;
        @SerializedName("Document_Type")
        @Expose
        private String documentType;
        @SerializedName("Document_URL")
        @Expose
        private String documentURL;


        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentURL() {
            return documentURL;
        }

        public void setDocumentURL(String documentURL) {
            this.documentURL = documentURL;
        }
    }


    public class Address {

        @SerializedName("permanent_address")
        @Expose
        private PermanentAddress permanentAddress;
        @SerializedName("local_address")
        @Expose
        private LocalAddress localAddress;

        public PermanentAddress getPermanentAddress() {
            return permanentAddress;
        }

        public void setPermanentAddress(PermanentAddress permanentAddress) {
            this.permanentAddress = permanentAddress;
        }

        public LocalAddress getLocalAddress() {
            return localAddress;
        }

        public void setLocalAddress(LocalAddress localAddress) {
            this.localAddress = localAddress;
        }
    }


    public class LocalAddress {

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


        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getPostOffice() {
            return postOffice;
        }

        public void setPostOffice(String postOffice) {
            this.postOffice = postOffice;
        }

        public String getPoliceStation() {
            return policeStation;
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


    public class PermanentAddress {

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



        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getPostOffice() {
            return postOffice;
        }

        public void setPostOffice(String postOffice) {
            this.postOffice = postOffice;
        }

        public String getPoliceStation() {
            return policeStation;
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

}
