package com.mgz.nongzitong.nongzitong.constant;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by john on 2017/7/5.
 */

public class Constants {
    public static String BaseUrl = "http://172.16.22.1:8080";
    public static String SECRET = "19950629";
    public static String APPKEY = "888";
    public static String sn = "";

    public static String getSN(Context context) {
        if (sn.equals("")) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            sn = tm.getSimSerialNumber() == null ? "" : tm.getSimSerialNumber();
        }

        return sn;
    }
}
