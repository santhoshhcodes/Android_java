package com.example.myproject.screen;

import android.os.Bundle;
import android.view.View; 
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.MyAdapter_AddScore;
import com.example.myproject.R;
import com.example.myproject.model.StMark;

import java.util.ArrayList;
import java.util.List;

public class Exam_mark extends AppCompatActivity {

    EditText StName, tamilScore, engScore, mathsScore, sciScore, socialScore;
    CardView addBtn;

    MyAdapter_AddScore myAdapterAddScore;

    List<StMark> stMarkList = new ArrayList<>();

    RecyclerView recyclerView;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exam_mark);

        StName = findViewById(R.id.stName);
        tamilScore = findViewById(R.id.tamilScore);
        engScore = findViewById(R.id.englishScore);
        mathsScore = findViewById(R.id.mathsScore);
        sciScore = findViewById(R.id.scienceScore);
        socialScore = findViewById(R.id.socialScore);


        addBtn = findViewById(R.id.addButton);

        toolbar = findViewById(R.id.toolbarExam);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());


        recyclerView = findViewById(R.id.scoreRecylce);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        myAdapterAddScore = new MyAdapter_AddScore(stMarkList, this);

        recyclerView.setAdapter(myAdapterAddScore);




        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stName = StName.getText().toString().trim();
                String tamil = tamilScore.getText().toString().trim();
                String eng = engScore.getText().toString().trim();
                String math = mathsScore.getText().toString().trim();
                String sci = sciScore.getText().toString().trim();
                String social = socialScore.getText().toString().trim();

                if (stName.isEmpty() || tamil.isEmpty() || eng.isEmpty() || math.isEmpty() || sci.isEmpty() || social.isEmpty()) {
                    Toast.makeText(Exam_mark.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int total = Integer.parseInt(tamil) + Integer.parseInt(eng) + Integer.parseInt(math) + Integer.parseInt(sci) + Integer.parseInt(social);

                StMark newMark = new StMark(
                        String.valueOf(stMarkList.size() + 1),
                        stName,
                        tamil,
                        eng,
                        math,
                        sci,
                        social,
                        String.valueOf(total),
                        "✏️",
                        "🗑️"

                );

                stMarkList.add(newMark);
                myAdapterAddScore.notifyDataSetChanged();

                Toast.makeText(Exam_mark.this, "Score Added", Toast.LENGTH_SHORT).show();

                StName.setText("");
                tamilScore.setText("");
                engScore.setText("");
                mathsScore.setText("");
                sciScore.setText("");
                socialScore.setText("");
            }
        });


    }
}