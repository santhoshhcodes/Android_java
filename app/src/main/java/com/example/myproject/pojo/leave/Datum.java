
package com.example.myproject.pojo.leave;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {
    @SerializedName("Admin_Desc")
    @Expose
    private String adminDesc;
    @SerializedName("Auto_ID")
    @Expose
    private String autoID;
    @SerializedName("CompCode")
    @Expose
    private String compCode;
    @SerializedName("EmpName")
    @Expose
    private String empName;
    @SerializedName("EmpNo")
    @Expose
    private String empNo;
    @SerializedName("ExistingCode")
    @Expose
    private String existingCode;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("LeaveDesc")
    @Expose
    private String leaveDesc;
    @SerializedName("LeaveStatus")
    @Expose
    private String leaveStatus;
    @SerializedName("LeaveType")
    @Expose
    private String leaveType;
    @SerializedName("LocCode")
    @Expose
    private String locCode;
    @SerializedName("Machine_Encrypt")
    @Expose
    private String machineEncrypt;
    @SerializedName("Machine_No")
    @Expose
    private String machineNo;
    @SerializedName("Photo_Path")
    @Expose
    private String photoPath;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("TotalDays")
    @Expose
    private String totalDays;
    @SerializedName("approve_level")
    @Expose
    private String approveLevel;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;

    public String getAdminDesc() {
        return adminDesc;
    }

    public void setAdminDesc(String adminDesc) {
        this.adminDesc = adminDesc;
    }

    public String getAutoID() {
        return autoID;
    }

    public void setAutoID(String autoID) {
        this.autoID = autoID;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getExistingCode() {
        return existingCode;
    }

    public void setExistingCode(String existingCode) {
        this.existingCode = existingCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLeaveDesc() {
        return leaveDesc;
    }

    public void setLeaveDesc(String leaveDesc) {
        this.leaveDesc = leaveDesc;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getMachineEncrypt() {
        return machineEncrypt;
    }

    public void setMachineEncrypt(String machineEncrypt) {
        this.machineEncrypt = machineEncrypt;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}