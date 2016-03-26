package com.example.sword.phonemanager.PhoneManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.sword.phonemanager.application.MyApplication;
import com.example.sword.phonemanager.R;
//设置中心的Activity
public class SettingActivity extends ActionBarActivity {

    private static final String TAG ="SettingActivity" ;
    private CheckBox cb_setting_update;
    private TextView tv_setting_updatestatus;

    private SharedPreferences sp; //使用SharedPreferences来保存键值对信息
    private SharedPreferences.Editor editor;//创建SharedPreferences的编辑器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_setting);//Activity启动时会在自定义控件中去configsp中加载保存的键值对，并设置控件的状态

        sp= MyApplication.configsp;//从MyApplication中取出全局变量SharedPreferences configsp
        editor =sp.edit();

        //final ActionBar actionBar = getActionBar();
        final android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.hide();


    }
}
