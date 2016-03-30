package com.example.sword.phonemanager.application;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sword.phonemanager.service.MyNumberLocationService;

/**
 * Created by Sword on 2016/3/25.
 */
public class MyApplication extends Application{


    public static  String SERVER_PATH  ;
    public static  SharedPreferences configsp;
    public static SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();

         SERVER_PATH="http://192.168.3.29/MobileManager";

         configsp=getSharedPreferences("config",MODE_PRIVATE);

        editor = configsp.edit();

        startService(new Intent(this, MyNumberLocationService.class));
    }

    public static void setConfigspValue(String key,String value){
        editor.putString(key, value);
        editor.commit();
    }
    public static void setConfigValue(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(new Intent(this,MyNumberLocationService.class));
    }
}
