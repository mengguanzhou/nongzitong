package com.mgz.nongzitong.nongzitong.basictool;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgz.nongzitong.nongzitong.utils.Logger;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by john on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getRecoureId());
        initView();
        initData();
        setOnClickListner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initOnStart();
        resetView();
    }

    public abstract int getRecoureId();

    public abstract void initView();

    public abstract void setOnClickListner();

    public void initData() {}

    public void resetView() {}

    public void initOnStart() {}

    protected void quickSetClickListener(View[] views, View.OnClickListener listener) {
        for (int i = 0; i < views.length; i++) {
            Logger.e("hi", "view " + i);
            views[i].setOnClickListener(listener);
        }
    }

}
