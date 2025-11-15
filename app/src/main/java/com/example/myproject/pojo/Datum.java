package com.example.myproject.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("DeptName")
    @Expose
    private Object deptName;
    @SerializedName("DesigCode")
    @Expose
    private String desigCode;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Host_IP")
    @Expose
    private String hostIP;
    @SerializedName("Host_Name")
    @Expose
    private String hostName;
    @SerializedName("Lcode")
    @Expose
    private String lcode;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Scan_Type")
    @Expose
    private String scanType;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("profileImg")
    @Expose
    private String profileImg;

    public Object getDeptName() {
        return deptName;
    }

    public void setDeptName(Object deptName) {
        this.deptName = deptName;
    }

    public String getDesigCode() {
        return desigCode;
    }

    public void setDesigCode(String desigCode) {
        this.desigCode = desigCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

}