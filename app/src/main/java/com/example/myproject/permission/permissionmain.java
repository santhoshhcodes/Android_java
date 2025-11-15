package com.example.myproject.permission;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.MyAdapter_permission;
import com.example.myproject.R;
import com.example.myproject.model.PermissionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class permissionmain extends AppCompatActivity {

    RecyclerView recyclerView;

    MyAdapter_permission myAdapterPermission;

   FloatingActionButton floatingActionButtonid;


    List<PermissionModel> permissionModelList = new ArrayList<>() ;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_permission);
        recyclerView = findViewById(R.id.recyle_permission);
        floatingActionButtonid = findViewById(R.id.fab_addPermission);

        toolbar = findViewById(R.id.permissionToolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> onBackPressed());



        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        permissionModelList.add(new PermissionModel("Nikhil", "2clk to 3clk", "buy new laptop", "pending"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterPermission = new MyAdapter_permission(permissionModelList);
        recyclerView.setAdapter(myAdapterPermission);

        floatingActionButtonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(permissionmain.this, Add_permission.class);
                startActivity(i);
            }
        });
    }
}