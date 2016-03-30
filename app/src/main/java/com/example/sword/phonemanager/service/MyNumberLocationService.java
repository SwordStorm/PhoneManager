package com.example.sword.phonemanager.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sword.phonemanager.R;
import com.example.sword.phonemanager.application.MyApplication;
import com.example.sword.phonemanager.dao.NumberLocationDao;

/**
 * Created by Sword on 2016/3/30.
 */
public class MyNumberLocationService extends Service {

    private static final String TAG ="MyNumberLocationService" ;
    private WindowManager mWM;
    private View v;


    public MyNumberLocationService(){
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Noy yet implemented");
    }

    @Override
    public void onCreate() {
        TelephonyManager telmar = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telmar.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

        Log.i(TAG,"onCreate");
        super.onCreate();
    }
    class MyPhoneStateListener extends PhoneStateListener{

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    hideLocationView();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    String location = NumberLocationDao.getNumberLocation(incomingNumber, MyNumberLocationService.this);
                    Log.i(TAG,location);
                    showLocationView(location);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
            }
        }
    }

    private  void showLocationView(String location){
        LayoutInflater inflate = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflate.inflate(R.layout.mynumberlocation,null);
        v.setBackgroundResource(R.drawable.call_locate_blue);

        TextView message = (TextView) v.findViewById(R.id.message);
        message.setText(location);
        mWM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;

        params.gravity = Gravity.LEFT|Gravity.TOP;

        params.x = MyApplication.configsp.getInt("toastx",200);
        params.y = MyApplication.configsp.getInt("toasty",300)+50;


        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mWM.addView(v,params);

    }

    private void hideLocationView(){
        if(mWM!=null){
            mWM.removeView(v);

        }
    }


}
