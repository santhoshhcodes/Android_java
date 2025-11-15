package com.example.myproject.pojo.leave;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponsePojo {
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("leaves_data")
    @Expose
    private Object leavesData;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Object getLeavesData() {
        return leavesData;
    }

    public void setLeavesData(Object leavesData) {
        this.leavesData = leavesData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}