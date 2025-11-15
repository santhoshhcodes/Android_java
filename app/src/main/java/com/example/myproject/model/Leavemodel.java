package com.example.myproject.model;

public class Leavemodel {

    String name, time, reason, status;

    public Leavemodel(String name, String time, String reason, String status){
        this.name = name;
        this.time = time;
        this.reason = reason;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}
