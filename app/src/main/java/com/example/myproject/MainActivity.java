package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myproject.permission.permissionmain;
import com.example.myproject.screen.Exam_mark;
import com.example.myproject.screen.Expense;
import com.example.myproject.screen.LeaveMain;
import com.example.myproject.screen.MapActivity;
import com.example.myproject.screen.OnDutymain;
import com.example.myproject.screen.ScanActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView Leave, Permission, OnDuty, ExpenseTracker, ExamScore, MapLocation;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    TextView backButton;

    Button  QrScanBtn ;

    NavigationView navigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Leave= findViewById(R.id.leave);
        Permission= findViewById(R.id.permission);
        OnDuty= findViewById(R.id.onDuty);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ExpenseTracker = findViewById(R.id.expenseTrack);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        QrScanBtn = findViewById(R.id.scanBtn);
        ExamScore = findViewById(R.id.examScore);
        toolbar = findViewById(R.id.mainToolbar);
        MapLocation = findViewById(R.id.mapID);

        setSupportActionBar(toolbar);

        QrScanBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(i);
        });

        MapLocation.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);

        });





       //-----Navigation Drawer-----
        View headerView = navigationView.getHeaderView(0);
        backButton = headerView.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        ExpenseTracker.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Expense.class)));

        OnDuty.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, OnDutymain.class)));

        Permission.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, permissionmain.class)));

        Leave.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LeaveMain.class)));


        //-------------------Intent------------
        ExpenseTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Expense.class);
                startActivity(i);
            }
        });
        Leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LeaveMain.class);
                startActivity(i);

            }
        });
        Permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, permissionmain.class);
                startActivity(i);

            }
        });
        OnDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OnDutymain.class);
                startActivity(i);

            }
        });
        ExamScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Exam_mark.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem  menuItem) {
        int id = menuItem.getItemId();


        if(id == R.id.navbarLeave){
            startActivity(new Intent(this, LeaveMain.class));
        }
        else if(id == R.id.navbarPermisssion){
            startActivity(new Intent(this, permissionmain.class));

        }
        else if(id == R.id.navbarOnduty){
            startActivity(new Intent(this ,OnDutymain.class));

        }else if (id == R.id.navbarExpense){
            startActivity(new Intent(this, Expense.class));
        }
        return true;
    }
}