package com.example.sword.phonemanager.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Sword on 2016/4/4.
 */
public class AppCacheInfo {

    String name ;
    Drawable icon;
    long size;

    public AppCacheInfo(String name, long size, Drawable icon) {
        this.name = name;
        this.size = size;
        this.icon = icon;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
