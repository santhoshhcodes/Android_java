package com.example.myproject.retrofit;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiManager  {
    @POST("{path}")
    Call<ResponseBody> call_post(@Path("path") String method);

    @FormUrlEncoded
    @POST("{path}")
    Call<ResponseBody> call_post(@Path("path") String method, @FieldMap Map<String, String> map);

    @GET("{path}")
    Call<ResponseBody> call_postget(@Path("path") String method);

    @Headers("Content-Type: application/json")
    @POST("{path}")
    Call<ResponseBody> call_post(@Path("path") String method, @Query("param") String str2);

    @POST("payment_details")
    Call<Object> getUser(@Body Map<String, String> body);

    @POST("{path}")
    Call<Object> getdata(@Path("path") String method, @Body Map<String, String> body);

    @GET("{path}")
    Call<Object> call_postgetobj(@Path("path") String method);

    @POST("{path}")
    Call<Object> call_postobj(@Path("path") String method);
}