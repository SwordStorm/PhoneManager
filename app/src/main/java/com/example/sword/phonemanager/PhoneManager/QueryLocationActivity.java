package com.example.sword.phonemanager.PhoneManager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import com.example.sword.phonemanager.R;

public class QueryLocationActivity extends ActionBarActivity {
    private static final String TAG = "QueryLocationActivity";
    private EditText et_querylocation_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_location);
    }
}
