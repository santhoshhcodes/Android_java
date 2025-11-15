package com.example.myproject.retrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public interface InterfacePassResponseBackToClassObj {
    void apiCallBackOverRideMethodObj(Response<Object> response, JSONObject objResponse, String method, int position, String success) throws JSONException, IOException;
}