package com.etps.etps.models;


public class DisplayData {

    private String providerID;
    private String providerName;
    private String providerDescription;
    private String campusID;
    private String campusName;
    private String programID;
    private String programName;
    private String programDescription;
    private String etpID;
    private String status;

    public DisplayData(String providerID, String providerName, String providerDescription, String campusID, String campusName, String programID, String programName, String programDescription, String etpID, String status) {
        this.providerID = providerID;
        this.providerName = providerName;
        this.providerDescription = providerDescription;
        this.campusID = campusID;
        this.campusName = campusName;
        this.programID = programID;
        this.programName = programName;
        this.programDescription = programDescription;
        this.etpID = etpID;
        this.status = status;
    }

    public DisplayData(Object[] objArr) {
        int count = 0;
        for (Object obj : objArr) {
            switch (count) {
                case 0:
                    this.providerID = obj.toString();
                    count++;
                    break;
                case 1:
                    this.providerName = obj.toString();
                    count++;
                    break;
                case 2:
                    this.providerDescription = obj.toString();
                    count++;
                    break;
                case 3:
                    this.campusID = obj.toString();
                    count++;
                    break;
                case 4:
                    this.campusName = obj.toString();
                    count++;
                    break;
                case 5:
                    this.programID = obj.toString();
                    count++;
                    break;
                case 6:
                    this.programName = obj.toString();
                    count++;
                    break;
                case 7:
                    this.programDescription = obj.toString();
                    count++;
                    break;
                case 8:
                    this.etpID = obj.toString();
                    count++;
                    break;
                case 9:
                    this.status = obj.toString();
                    count++;
                    break;
            }
        }

    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String getCampusID() {
        return campusID;
    }

    public void setCampusID(String campusID) {
        this.campusID = campusID;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getEtpID() {
        return etpID;
    }

    public void setEtpID(String etpID) {
        this.etpID = etpID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
