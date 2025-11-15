package com.example.myproject.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.SharedPrefrence;
import com.example.myproject.pojo.ResponsePojo;
import com.example.myproject.retrofit.CommonObj;
import com.example.myproject.retrofit.InterfacePassResponseBackToClassObj;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity implements InterfacePassResponseBackToClassObj {

    EditText Username,Password;
    TextView  Forgetpassword;

    CommonObj common;

    Button button;

    SharedPrefrence sharedPrefrence;

    ResponsePojo responsePojo;

    int version_code=1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        Forgetpassword=findViewById(R.id.forgetpassword);
        button=findViewById(R.id.btn);


        common = new CommonObj(this, this,"testing");
        sharedPrefrence = new SharedPrefrence(this);



        button.setOnClickListener(v -> {
            if (!Username.getText().toString().isEmpty()){
                if (!Password.getText().toString().isEmpty()){
                    if(common.isNetworkConnected()) {
                        common.ShowLoad(true);
                        Map<String, String> map = new HashMap<>();
                        map.put("username", Username.getText().toString());
                        System.out.println(Username.getText().toString());
                        map.put("passwords", Password.getText().toString());
                        System.out.println(Password.getText().toString());
                        common.CallApiRequestObj("api/user_login_demo", "POST", map);
                    }else{
                        Toast.makeText(this, "Please Connect Your Network", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Enter Your UserName", Toast.LENGTH_SHORT).show();
            }



        });

    }

    @Override
    public void apiCallBackOverRideMethodObj(Response<Object> response, JSONObject objResponse, String method, int position, String success) throws JSONException, IOException {
        if(response!=null){
            common.HideLoad();
            JSONObject object=new JSONObject(new Gson().toJson(response.body()));
            System.out.println("responseobj"+object);
            responsePojo = new Gson().fromJson(object.toString(),new TypeToken<ResponsePojo>(){}.getType());
            if (responsePojo.getStatus() == true) {
                if (responsePojo.getLockDate().equals("Yes")) {
                    if (Integer.parseInt(responsePojo.getVersionCode())<=version_code) {
                        Toast.makeText(this, responsePojo.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }else{
                Toast.makeText(this, responsePojo.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}