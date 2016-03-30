package com.example.sword.phonemanager.PhoneManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.sword.phonemanager.R;

public class AdvanceToolActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_tool);
    }

    public void querylocattion(View v){
        startActivity(new Intent(this,QueryLocationActivity.class));
    }
}
