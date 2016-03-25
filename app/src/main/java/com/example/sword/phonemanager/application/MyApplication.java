package com.example.sword.phonemanager.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Sword on 2016/3/25.
 */
public class MyApplication extends Application{


    public static  String SERVER_PATH  ;
    public static  SharedPreferences configsp;
    @Override
    public void onCreate() {
        super.onCreate();

         SERVER_PATH="http://192.168.3.29/MobileManager";

         configsp=getSharedPreferences("config",MODE_PRIVATE);
    }
}
