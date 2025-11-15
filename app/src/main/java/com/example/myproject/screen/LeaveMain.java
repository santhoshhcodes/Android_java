package com.example.myproject.screen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.MyAdapter_leave;

import com.example.myproject.R;

import com.example.myproject.pojo.leave.ResponsePojo;
import com.example.myproject.pojo.leave.Datum;
import com.example.myproject.retrofit.Common;

import com.example.myproject.retrofit.InterfacePassResponseBackToClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class LeaveMain extends AppCompatActivity implements InterfacePassResponseBackToClass {

    RecyclerView recyclerView;
    MyAdapter_leave my_adapter;
    List<Datum> leaveList = new ArrayList<>();

    Common common;


    ResponsePojo responsePojo;


    int version_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycleviewcard);

        Toolbar toolbar = findViewById(R.id.leaveDetailsToolId);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        common = new Common(this, this);

        recyclerView = findViewById(R.id.recyle_LeaveDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        call_api_leave_details();

//
//        leavemodelList.add(new Leavemodel("muthu","half day", "visit hospital","pending" ));
//        leavemodelList.add(new Leavemodel("muthu","half day", "visit hospital","pending" ));
//        leavemodelList.add(new Leavemodel("muthu","half day", "visit hospital","pending" ));
//        leavemodelList.add(new Leavemodel("muthu","half day", "visit hospital","pending" ));


    }

    private void call_api_leave_details() {
        if (common.isNetworkConnected()) {
            common.ShowLoad(true);
            Map<String, String> map = new HashMap<>();
            common.CallApiRequest("api/employee_leaveslist/1016/06-2025", "GET", map);

        } else {
            System.out.println("No network connection");
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void apiCallBackOverRideMethod(Response<ResponseBody> response, JSONObject objResponse, String method, int position, String success) throws JSONException, IOException {
        if (response != null) {
            common.HideLoad();
            responsePojo = new Gson().fromJson(response.body().string(),new TypeToken<ResponsePojo>(){}.getType());
            System.out.println("response"+response);
            if (responsePojo.getData() != null) {
                if(responsePojo.getData().size()>0) {
                    leaveList.clear();
                    leaveList.addAll(responsePojo.getData());
                    my_adapter = new MyAdapter_leave(this, leaveList,this::connecting);
                    recyclerView.setAdapter(my_adapter);
                    my_adapter.notifyDataSetChanged();
                }

            }else{
                Toast.makeText(this, responsePojo.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void connecting(int position) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        View view = LayoutInflater.from(this).inflate(R.layout.bottomsheetlayout, null);
        TextView EmpName, EmpNo, Leavetype, fromDate, toDate, TotalDays, gender;

        EmpName = view.findViewById(R.id.EmpNameBS);
        EmpNo = view.findViewById(R.id.EmpNOBS);
        Leavetype = view.findViewById(R.id.LeaveTypeBS);
        fromDate = view.findViewById(R.id.FromDateBS);
        toDate = view.findViewById(R.id.ToDateBS);
        TotalDays = view.findViewById(R.id.TotalDaysBS);
        gender = view.findViewById(R.id.genderBS);

        Datum data = leaveList.get(position);

        EmpName.setText(data.getEmpName());
        EmpNo.setText(data.getEmpNo());
        Leavetype.setText(data.getLeaveType());
        fromDate.setText(data.getFromDate());
        toDate.setText(data.getToDate());
        TotalDays.setText(data.getTotalDays());
        gender.setText(data.getGender());




        dialog.setContentView(view);
        dialog.show();


    }
}