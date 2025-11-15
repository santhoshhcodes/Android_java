package com.example.myproject;

import android.app.Activity;
import android.content.Context;

public class SharedPrefrence {
    android.content.SharedPreferences shared_pref;
    android.content.SharedPreferences.Editor shared_editor;
    Context activity;
    public static final String LOGINCHECK = "logincheck";
    public static final String COMPANY_CODE = "company_code";
    public static final String LOCATION_CODE = "location_code";
    public static final String SALESVISIBLE = "SalesVisible";
    public static final String EXIT_CODE = "exit_code";
    public static final String MACHINEID_ENCRYPT = "machine_id_encrypt";
    public static final String MACHINE_ID = "machine_id";
    public static final String USERTYPE = "user_type";
    public static final String EMPDETAILS = "emp_details";
    public static final String IMAGE_PATH = "imgpath";
    public static final String LOGIN_TYPE = "login_type";
    public static final String VERSION_CODE = "version_code";
    public static final String EMP_ID = "emp_id";
    public static final String PUNCH_CHECKED = "punch_checked";
    public static final String EMPNAME = "empname";
    public static final String WEEKOFF = "weekoff";
    public static final String PERMISSION_HOUR = "permission_hour";
    public static final String AVAILABLE_PERMISSION = "available_permission";
    public static final String TOTAL_PERMISSION = "total_permission";
    public static final String TAKEN_PERMISSION = "taken_permission";
    public static final String AVAILABLE_LEAVES = "available_leaves";
    public static final String TOTAL_LEAVES = "total_leave";
    public static final String TAKEN_LEAVES = "taken_leave";
    public static final String ELELIGIBLE = "el_eligible";
    public static final String APPROVE_LEVEL = "approve_level";
    public static final String ZONE = "zone";
    public static final String DESIGNATION = "Designation";


    public SharedPrefrence(Activity ac) {
        activity = ac;
        if (activity != null) {
            shared_pref = activity.getSharedPreferences("Prefence_values", Context.MODE_PRIVATE);
            shared_editor = shared_pref.edit();
        }
    }

    public void save(String key, String value) {
        shared_editor.putString(key, value);
        shared_editor.commit();
    }

    public void Remove(String key) {
        shared_editor.remove(key);
        shared_editor.apply();
    }

    public String getvalue(String key) {
        return shared_pref.getString(key, null);
    }
}