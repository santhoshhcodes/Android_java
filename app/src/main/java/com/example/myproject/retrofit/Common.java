package com.example.myproject.retrofit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.Window;

import com.example.myproject.R;
import com.example.myproject.SharedPrefrence;
import com.example.myproject.pojo.ResponsePojo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {
    Activity refactivity;
    InterfacePassResponseBackToClass interfacePassResponse;
    InterfacePassResponseBackToClassObj interfacePassResponseobj;
    SharedPrefrence sharedPrefrence;
    Dialog dialog;

    public Common(Activity refactivity1, InterfacePassResponseBackToClass interfacePassResponse){
        this.refactivity=refactivity1;
        this.interfacePassResponse=interfacePassResponse;
        sharedPrefrence=new SharedPrefrence(refactivity);
    }

    public Common(Activity refactivity1, InterfacePassResponseBackToClassObj interfacePassResponse,String type){
        this.refactivity=refactivity1;
        this.interfacePassResponseobj=interfacePassResponse;
        sharedPrefrence=new SharedPrefrence(refactivity);
    }
    private ApiManager connectRetro(String annotationtype, String method){
        String appurl=refactivity.getResources().getString(R.string.baseurl);
        OkHttpClient objclient=new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).connectTimeout(1, TimeUnit.MINUTES).build();
        Retrofit retrofit=new Retrofit.Builder()
                .client(objclient)
                .baseUrl(appurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiManager.class);
    }

    public void CallApiRequest(final String apiname, String calltype, Map<String, String> map){
        Call<ResponseBody> callapi=null;
        if (calltype.equals("GET")){
            System.out.println("response    map    :"+apiname);
            callapi=connectRetro(calltype,apiname).call_postget(apiname);
        }else{
            if (map!=null){
                System.out.println("response    map    :"+map.toString());
                System.out.println("response    map_replace    :"+map.toString().replace("=", ":"));
                callapi=connectRetro(calltype,apiname).call_post(apiname,map.toString().replace("=", ":"));
            }else{
                callapi=connectRetro(calltype,apiname).call_post(apiname);
            }
        }

        callapi.enqueue(new Callback<ResponseBody>() {
            JSONObject data;
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("response  before    :"+response.body());
                if(response.isSuccessful()) {
                    try {
                        System.out.println("response    :" + response.body().toString());
                        interfacePassResponse.apiCallBackOverRideMethod(response, data, apiname, 0, "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("failiure");

                HideLoad();

            }
        });
    }

    public void CallApiRequestObj(final String apiname, String calltype, Map<String, String> map){
        Call<Object> callapi=null;
        if (calltype.equals("GET")){
            System.out.println("response    map    :"+apiname);
            callapi=connectRetro(calltype,apiname).call_postgetobj(apiname);
        }else{
            if (map!=null){
                System.out.println("response    map    :"+map.toString());
                System.out.println("response    map_replace    :"+map.toString().replace("=", ":"));
                callapi=connectRetro(calltype,apiname).getdata(apiname,map);
            }else{
                callapi=connectRetro(calltype,apiname).call_postobj(apiname);
            }
        }

        callapi.enqueue(new Callback<Object>() {
            JSONObject data;
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                System.out.println("response  before    :"+response.body());
                if(response.isSuccessful()) {
                    try {
                        System.out.println("response    :" + response.body().toString());
                        interfacePassResponseobj.apiCallBackOverRideMethodObj(response, data, apiname, 0, "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                System.out.println("failiure");
                HideLoad();

            }
        });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) refactivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    public void ShowLoad(Boolean cancelable) {
        dialog = new Dialog(refactivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (cancelable) {
            dialog.setCancelable(false);
        } else {
            dialog.setCancelable(false);
        }
        dialog.setContentView(R.layout.loading);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        dialog.show();
    }

    public void HideLoad() {
        if (dialog != null && !refactivity.isFinishing()) {
            dialog.dismiss();
        }
    }
}