package com.example.myproject.screen;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.MyAdapter_onDuty;
import com.example.myproject.R;
import com.example.myproject.model.OnDutyModel;

import java.util.ArrayList;
import java.util.List;

public class OnDutymain extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter_onDuty myAdapterOnDuty;
    List<OnDutyModel> onDutyList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_duty);

        toolbar = findViewById(R.id.onDutyToolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        onDutyList.add(new OnDutyModel("Sanvi", "9AM - 3pm", "RecycleView", "onDuty"));
        onDutyList.add(new OnDutyModel("Sanvi", "9AM - 3pm", "RecycleView", "onDuty"));
        onDutyList.add(new OnDutyModel("Sanvi", "9AM - 3pm", "RecycleView", "onDuty"));
        onDutyList.add(new OnDutyModel("Sanvi", "9AM - 3pm", "RecycleView", "onDuty"));
        onDutyList.add(new OnDutyModel("Sanvi", "9AM - 3pm", "RecycleView", "onDuty"));

        recyclerView = findViewById(R.id.RecycleonDuty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterOnDuty = new MyAdapter_onDuty(onDutyList);
        recyclerView.setAdapter(myAdapterOnDuty);
    }
}