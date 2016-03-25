package com.example.sword.phonemanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sword on 2016/3/25.
 */
public class MyTextView extends TextView{


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return   true; //super.isFocused();
    }
}
