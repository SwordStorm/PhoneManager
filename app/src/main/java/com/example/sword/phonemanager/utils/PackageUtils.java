package com.example.sword.phonemanager.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.sword.phonemanager.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sword on 2016/4/4.
 */
public class PackageUtils {
    public static List<AppInfo> getAllAppInfo(Context ctx){

//        ctx.getSystemService(ctx.ACTIVITY_SERVICE)
        List<AppInfo> appinfolist = new ArrayList<AppInfo>();


      /*  try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        final PackageManager packageManager = ctx.getPackageManager();

        final List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);

        for (ApplicationInfo appinfo: installedApplications) {
            //应用名字，应用图片，应用是否安装在SDcard上
            String packagename = appinfo.packageName;
            final CharSequence lable = appinfo.loadLabel(packageManager);
            final Drawable icon = appinfo.loadIcon(packageManager);
            boolean isSDCARD;
            boolean isSystem;

            if( (appinfo.flags &   appinfo.FLAG_SYSTEM )!=0 ) //=   1 | 4;
            {
                //系统应用
                isSystem=true;
            }else
            {
                //用户自己安装的应用
                isSystem=false;
            }

            if( (appinfo.flags &   appinfo.FLAG_EXTERNAL_STORAGE )!=0 ) //=   1 | 4;
            {
                //SDCARD
                isSDCARD=true;
            }else
            {
                //非SDcard安装
                isSDCARD=false;
            }

            AppInfo appInfo = new AppInfo(lable.toString(),icon,isSDCARD,isSystem,packagename);

            appinfolist.add(appInfo);
        }

        return  appinfolist;
    }
}
