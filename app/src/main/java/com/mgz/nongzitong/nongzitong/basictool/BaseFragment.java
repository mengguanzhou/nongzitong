package com.mgz.nongzitong.nongzitong.basictool;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by john on 2017/7/5.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container,
                false);
        initView(view);

        setOnClickListner();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    /**
     * 返回布局文件Id
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化视图
     */
    protected abstract void initView(View view);

    /**
     * 设置监听
     */
    protected void setOnClickListner() {

    }

    protected void quickFindTextView(TextView[] views, int[] viewIds, View view) {
        for (int i = 0; i < views.length; i++) {
            views[i] = (TextView) view.findViewById(viewIds[i]);
        }
    };

    protected void quickFindEditText(EditText[] views, int[] viewIds, View view) {
        for (int i = 0; i < views.length; i++) {
            views[i] = (EditText) view.findViewById(viewIds[i]);
        }
    };

    protected void quickFindButton(Button[] views, int[] viewIds, View view) {
        for (int i = 0; i < views.length; i++) {
            views[i] = (Button) view.findViewById(viewIds[i]);
        }
    };

    protected void quickFindImageView(ImageView[] views, int[] viewIds, View view) {
        for (int i = 0; i < views.length; i++) {
            views[i] = (ImageView) view.findViewById(viewIds[i]);
        }
    };

    protected void quickSetClickListener(View[] views, Context context) {
        for (int i = 0; i < views.length; i++) {
            views[i].setOnClickListener((View.OnClickListener) context);
        }
    }


}
