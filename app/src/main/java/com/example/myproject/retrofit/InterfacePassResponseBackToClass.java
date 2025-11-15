package com.example.myproject.retrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface InterfacePassResponseBackToClass {
    void apiCallBackOverRideMethod(Response<ResponseBody> response, JSONObject objResponse, String method, int position, String success) throws JSONException, IOException;
}
