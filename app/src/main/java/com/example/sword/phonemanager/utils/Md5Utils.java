package com.example.sword.phonemanager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sword on 2016/4/4.
 */
public class Md5Utils {
    public  static String  getMd5Digest(String password){

        String afterencyp ="";
        try {
            MessageDigest md= MessageDigest.getInstance("md5");

            byte[] digest = md.digest(password.getBytes());

            StringBuffer result= new StringBuffer();//变长的string

            for (byte b : digest) {
                int ret = b&0xFF;

                String hexstring = Integer.toHexString(ret);
                //System.out.println( hexstring);

                if (hexstring.length()==1) {
                    result.append("0");
                }
                result.append(hexstring);

            }
            //System.out.println(result);
            afterencyp=result.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return afterencyp;
    }
}
