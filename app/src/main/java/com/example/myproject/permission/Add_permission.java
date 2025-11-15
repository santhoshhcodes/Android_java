package com.example.myproject.permission;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myproject.R;

import java.util.Calendar;
import java.util.Locale;

public class Add_permission extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private String[] LeaveTitle = {
            "--- Select title ---",
            "Half Day",
            "Medical Leave",
            "Full Day",
    };

    Spinner LeaveSpinner ;



    EditText fromDate, toDate, fromTime, toTime, Description , TotalHrs, TotalDays;

    int year, month, day, mHour, mMinute;

    Button applyBtn;

    CardView FromDateCard, ToDateCard, FromTimeCard, ToTimeCard;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_permission);

        toolbar = findViewById(R.id.addPermission_toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //---------------SPINNEER-----------
        LeaveSpinner = findViewById(R.id.Permission_title);
        LeaveSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,

                LeaveTitle
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LeaveSpinner.setAdapter(ad);

        //-------------FromDate-----------

        fromDate = findViewById(R.id.from_dateEdit);
        FromDateCard = findViewById(R.id.from_datecard);

        final Calendar fromD  = Calendar.getInstance();
        year = fromD.get(Calendar.YEAR);
        month = fromD.get(Calendar.MONTH);
        day = fromD.get(Calendar.DATE);

        FromDateCard.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialog_fromDate();
            }
        });

        //------------TO_DATE--------------

        toDate = findViewById(R.id.to_dateEdit);
        ToDateCard = findViewById(R.id.to_datecard);

        final Calendar toD = Calendar.getInstance();
        year = toD.get(Calendar.YEAR);
        month = toD.get(Calendar.MONTH);
        day = toD.get(Calendar.DATE);

        ToDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog_toDate();
            }
        });
        //--------------FromTime-------------
        fromTime = findViewById(R.id.from_timeEdit);
        FromTimeCard = findViewById(R.id.from_timecard);

        final Calendar fromT = Calendar.getInstance();
        mHour = fromT.get(Calendar.HOUR_OF_DAY);
        mMinute = fromT.get(Calendar.MINUTE);

        FromTimeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFromTimepickerDialog();
            }
        });

        //---------------ToTime-----------

        toTime = findViewById(R.id.to_TimeEdit);
        ToTimeCard = findViewById(R.id.to_timecard);

        final  Calendar toT = Calendar.getInstance();
        mHour = toT.get(Calendar.HOUR_OF_DAY);
        mMinute = toT.get(Calendar.MINUTE);

        ToTimeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToTimepickerDialog();
            }
        });


        //------------TotalDays-----------
        TotalDays = findViewById(R.id.totalDays);





        //------------TotalHours---------
        TotalHrs = findViewById(R.id.totalHrs);

        Description = findViewById(R.id.per_description);

        //----------Apply button----
        applyBtn = findViewById(R.id.per_applybtn);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromDateStr = fromDate.getText().toString().trim();
                String toDateStr = toDate.getText().toString().trim();
                String fromTimeStr = fromTime.getText().toString().trim();
                String toTimeStr = toTime.getText().toString().trim();
                String selectedItem = LeaveSpinner.getSelectedItem().toString();
                if (selectedItem.equals("--- Select title ---")){
                    Toast.makeText(Add_permission.this, "Please select the Leave title", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(fromDate.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add_permission.this,"Please select the from Date", Toast.LENGTH_SHORT).show();;
                    return;
                }

                if(toDate.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add_permission.this, "Please select to Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(fromTime.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add_permission.this, "Please Select From Time", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(toTime.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add_permission.this, "Please Select To time", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Description.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add_permission.this,"Please Enter the Discription", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    java.text.SimpleDateFormat sdfDate = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    java.util.Date startDate = sdfDate.parse(fromDateStr);
                    java.util.Date endDate = sdfDate.parse(toDateStr);

                    if (startDate != null && endDate != null) {
                        long diffInMillis = endDate.getTime() - startDate.getTime();
                        if (diffInMillis < 0) {
                            Toast.makeText(Add_permission.this, " To Date should be after From Date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Add_permission.this, "Invalid Date Format", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("hh:mm a", Locale.getDefault());
                    java.util.Date startTime = sdfTime.parse(fromTimeStr);
                    java.util.Date endTime = sdfTime.parse(toTimeStr);

                    if (startTime != null && endTime != null) {
                        long diffInMillis = endTime.getTime() - startTime.getTime();
                        if (diffInMillis < 0) {
                            Toast.makeText(Add_permission.this, " To Time should be after From Time", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Add_permission.this, "Invalid Time Format", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(Add_permission.this, "Permission Applied Successfully", Toast.LENGTH_LONG).show();

            }
        });




    }
    private void calculateTotalHours(){
        try{
            String fromTimeStr = fromTime.getText().toString().trim();
            String toTimeStr = toTime.getText().toString().trim();

            if(fromTimeStr.isEmpty() || toTimeStr.isEmpty()){
                TotalHrs.setText("");
                return;
            }

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a", Locale.getDefault());
            java.util.Date startTime = sdf.parse(fromTimeStr);
            java.util.Date endTime = sdf.parse(toTimeStr);

            if(startTime == null || endTime == null ){
                TotalHrs.setText("");
                return;
            }

            long diffinMills = endTime.getTime() - startTime.getTime();
            double hours = diffinMills /(1000.0 *60*60);

            if(hours <0){
                TotalHrs.setText("0");
                Toast.makeText(this, "To time should be after from Time", Toast.LENGTH_SHORT).show();
                return;
            }
            String formatedHrs = String.format(Locale.getDefault(), "%.2f", hours);
            TotalHrs.setText(formatedHrs);

        }catch (Exception e){
            e.printStackTrace();
            TotalHrs.setText("");
            Toast.makeText(this, "Error calculating hours", Toast.LENGTH_SHORT).show();

        }
    }




    //TotalDays
    private  void  calculateTotalDays(){
        try{
            String fromDateStr = fromDate.getText().toString().trim();
            String toDateStr = toDate.getText().toString().trim();

            if(fromDateStr.isEmpty() || toDateStr.isEmpty()){
                TotalDays.setText("");
                return;
            }

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            java.util.Date startDate = sdf.parse(fromDateStr);
            java.util.Date endDate = sdf.parse(toDateStr);

            if(startDate == null || endDate == null){
                TotalDays.setText("");
                return;
            }

            long diffInMillis = endDate.getTime() - startDate.getTime();
            long dayDiff = (diffInMillis /(1000L * 60 * 60 * 24)) ;

            if(dayDiff  < 0){
                TotalDays.setText("0");
                Toast.makeText(this, "To Date Should be after From Date ", Toast.LENGTH_SHORT).show();
                return;
            }
            TotalDays.setText(String.valueOf(dayDiff));

        }catch (Exception e){
            e.printStackTrace();
            TotalDays.setText("");
            Toast.makeText(this, "Error Calculating days", Toast.LENGTH_SHORT).show();
        }
    }

    //ToTimeDialog
    private void showToTimepickerDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                         mMinute= minute;
                        updateToTimeInEditText();
                    }
                }, mHour, mMinute, false
        );
        timePickerDialog.show();
    }
    //UpdateToTime
    private void updateToTimeInEditText() {
        String Format;
        int displayhrs = mHour;
        if(mHour == 0){
            displayhrs = 12;
            Format ="AM";
        }else if(mHour == 12){
            Format = "PM";
        }else if(mHour > 12){
            displayhrs = mHour - 12;
            Format = "PM";
        }else{
            Format = "AM";
        }
        String time = String.format(Locale.getDefault(),"%02d:%02d %s", displayhrs, mMinute, Format);
        toTime.setText(time);
        calculateTotalHours();
    }
    //FromTimeDialog
    private void showFromTimepickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                        updateFromTimeInEditText();
                    }
                }, mHour, mMinute, false
        );
        timePickerDialog.show();
    }
    //UpdateFromTime
    private void updateFromTimeInEditText() {
        String format;
        int displayHours = mHour;

        if (mHour ==  0){
            displayHours = 12;
            format = "AM";

        }else if(mHour == 12){
            format = "PM";

        }else if(mHour > 12){
            displayHours = mHour - 12;
            format = "PM";
        }else {
            format = "AM";
        }

        String time = String.format(Locale.getDefault(), "%02d:%02d %s", displayHours, mMinute, format);
        fromTime.setText(time);
        calculateTotalHours();

    }
    // ToDate
    private void showDatePickerDialog_toDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                year  = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                String selectedDate = day + "/" + (month +1) + "/" + year;
                toDate.setText(selectedDate);
                calculateTotalDays();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                year,
                day,
                month
        );
        datePickerDialog.show();
    }
    // FromDate
    private void showDatePickerDialog_fromDate() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth , int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;


                String selectedDate = day + "/" + (month +1) + "/" + year;
                fromDate.setText(selectedDate);
                calculateTotalDays();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                year,
                month,
                day
        );
        datePickerDialog.show();
    }
    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), LeaveTitle[position], Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}