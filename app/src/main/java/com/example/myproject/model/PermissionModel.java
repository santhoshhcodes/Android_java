package com.example.myproject.model;

public class PermissionModel {
    String name, time, reason, status;

    public  PermissionModel (String name, String time, String reason, String status){
        this.name = name;
        this.time = time;
        this.reason = reason;
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
