package com.example.sword.phonemanager.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sword on 2016/3/30.
 */
public class NumberLocationDao {

    public static String getNumberLocation(String number,Context context){
        String result = "";

        if(number.matches("1[3,7,8,5]\\d{9}")){
            String subnum = number.substring(0,7);

            SQLiteDatabase db = SQLiteDatabase.openDatabase("data/data/" + context.getPackageName() + "/location.db", null, 0);

            Cursor cursor = db.rawQuery("select city,cardtype from address_tb where _id=(select outkey from numinfo where mobileprefix = "+subnum+")",null);

            while (cursor.moveToNext()){
                int city = cursor.getColumnIndex("city");
                int cardtype = cursor.getColumnIndex("cardtype");

                String citystring = cursor.getString(city);
                String cardtypestring = cursor.getString(cardtype);
                result = cardtypestring;
            }
        }

        return  result;
    }
}
