package com.mgz.nongzitong.nongzitong.network;

import android.content.Context;

import com.google.gson.Gson;
import com.mgz.nongzitong.nongzitong.utils.Logger;
import com.mgz.nongzitong.nongzitong.utils.ProgressDialog;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by john on 2017/7/5.
 */

public abstract class HttpStringResponseHandler<T> extends StringCallback {
    private static final String TAG = "HttpStringResponseHandler";
    private Context context;
    private Class<T> cls;
    private boolean isNeedParse;

    /**
     * 不需要解析时使用
     *
     * @param context
     */
    public HttpStringResponseHandler(Context context) {
        this.context = context;
    }

    /**
     * 需要解析时,使用
     *
     * @param context
     * @param cls
     * @param isNeedParse
     */
    public HttpStringResponseHandler(Context context, Class<T> cls, boolean isNeedParse) {
        this.context = context;
        this.cls = cls;
        this.isNeedParse = isNeedParse;
    }

    @Override
    public void onBefore(Request request, int id) {
//        if (isNeedProgress)
//            ProgressDialog.getInstance().startProgressDialog(context, "加载中");
    }

    @Override
    public void onAfter(int id) {
//        if (isNeedProgress)
//            ProgressDialog.getInstance().stopProgressDialog();

    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Logger.e("onError", "网络访问异常，请检查网络！");
        e.printStackTrace();
        onFaile("网络访问异常，请检查网络！");
//        if (isNeedProgress)
//            ProgressDialog.getInstance().stopProgressDialog();
    }

    @Override
    public void onResponse(String response, int id) {
        Logger.e("onResponse", response + "====");
//        if (isNeedProgress)
//            ProgressDialog.getInstance().stopProgressDialog();

        if (isNeedParse) {
            try {
                Logger.d(TAG, "response = "+response);
                Gson gson = new Gson();
                T result = gson.fromJson(response, cls);
                onSuccess(result);
            } catch (Exception e) {
                onFaile("数据错误！");
                Logger.e("onResponse", e.getMessage());
                e.printStackTrace();
            }

        } else {
            onSuccessWithNoparse(response);
        }


    }

    @Override
    public void inProgress(float progress, long total, int id) {

    }

    /**
     * 需要解析时的回调
     *
     * @param t
     */
    public void onSuccess(T t) {
    }


    /**
     * 不需要解析时的回调
     *
     * @param result
     */
    public void onSuccessWithNoparse(String result) {
    }

    /**
     * 失败的回调
     *
     * @param res
     */
    public void onFaile(String res) {
    }
}
