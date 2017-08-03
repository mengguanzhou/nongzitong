package com.mgz.nongzitong.nongzitong.network;

import android.content.Context;

/**
 * Created by john on 2017/7/5.
 */

public class Api {
    private volatile static Api instance;

    private Api(Context mContext) {
    }


    public static Api getInstance(Context mContext) {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api(mContext);
                }
            }
        }
        return instance;
    }

    public void getCodeNum() {

    }
}
