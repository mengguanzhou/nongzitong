package com.mgz.nongzitong.nongzitong.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgz.nongzitong.nongzitong.MainActivity;
import com.mgz.nongzitong.nongzitong.R;
import com.mgz.nongzitong.nongzitong.activity.LoginActivity;
import com.mgz.nongzitong.nongzitong.activity.RegisterActivity;
import com.mgz.nongzitong.nongzitong.basictool.BaseActivity;
import com.mgz.nongzitong.nongzitong.basictool.BaseFragment;
import com.mgz.nongzitong.nongzitong.network.HttpStringResponseHandler;
import com.mgz.nongzitong.nongzitong.network.request.LogoutRequest;
import com.mgz.nongzitong.nongzitong.network.response.LoginResponse;
import com.mgz.nongzitong.nongzitong.network.response.LogoutResponse;
import com.mgz.nongzitong.nongzitong.utils.MyOKHttp;
import com.mgz.nongzitong.nongzitong.utils.StorageUtil;

/**
 * Created by john on 2017/7/6.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{

    private TextView userName;
    private TextView exit;
    private Boolean isLogin;
    private changeState stateL;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        userName = (TextView) view.findViewById(R.id.tv_username);
        exit = (TextView) view.findViewById(R.id.btn_exit);
        isLogin = StorageUtil.getLoginState();
        if (!isLogin) {
            exit.setVisibility(View.GONE);
        } else  {
            exit.setVisibility(View.VISIBLE);
        }
        stateL = (changeState) getActivity();
    }

    @Override
    protected void setOnClickListner() {
        userName.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isLogin) {
            exit.setVisibility(View.GONE);
        } else  {
            exit.setVisibility(View.VISIBLE);
        }
        if (v == userName) {
            if (isLogin) {
                exitEvent();
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        } else if (v == exit) {
            exitEvent();
        }
    }

    private void exitEvent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("退出登录");
        builder.setMessage("确定要退出该账户吗？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {    //退出登录操作
                LogoutRequest logoutRequest = new LogoutRequest(StorageUtil.getUserId(), 1);
                MyOKHttp.postToBase("logout", logoutRequest, new HttpStringResponseHandler<LogoutResponse>(getActivity().getApplicationContext(),
                        LogoutResponse.class, true) {
                    @Override
                    public void onSuccess(LogoutResponse response) {
                        super.onSuccess(response);
                        if (response.getSuccess() == 1) {
                            exit.setVisibility(View.GONE);
                            isLogin = false;
                            StorageUtil.clearStorage();
                            stateL.changeLoginState(false);
                        }
                    }
                });
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public interface changeState{
        public abstract void changeLoginState(boolean state);
    }
}
