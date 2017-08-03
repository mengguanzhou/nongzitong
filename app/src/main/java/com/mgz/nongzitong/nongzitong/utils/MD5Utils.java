package com.mgz.nongzitong.nongzitong.utils;

import java.security.MessageDigest;

/**
 * Created by john on 2017/7/5.
 */

public class MD5Utils {
    public MD5Utils() {
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception var8) {
            System.out.println(var8.toString());
            var8.printStackTrace();
            return "";
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for(int md5Bytes = 0; md5Bytes < charArray.length; ++md5Bytes) {
            byteArray[md5Bytes] = (byte)charArray[md5Bytes];
        }

        byte[] var9 = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for(int i = 0; i < var9.length; ++i) {
            int val = var9[i] & 255;
            if(val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }
}
