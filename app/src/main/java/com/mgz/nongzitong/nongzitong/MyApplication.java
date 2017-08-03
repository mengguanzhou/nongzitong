package com.mgz.nongzitong.nongzitong;

import android.app.Application;

import com.mgz.nongzitong.nongzitong.utils.StorageUtil;

/**
 * Created by john on 2017/7/7.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StorageUtil.init(getApplicationContext());
    }
}
