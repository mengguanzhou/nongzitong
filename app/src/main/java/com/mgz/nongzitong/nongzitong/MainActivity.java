package com.mgz.nongzitong.nongzitong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgz.nongzitong.nongzitong.activity.LoginActivity;
import com.mgz.nongzitong.nongzitong.basictool.BaseActivity;
import com.mgz.nongzitong.nongzitong.fragment.HomeFragment;
import com.mgz.nongzitong.nongzitong.fragment.MyFragment;
import com.mgz.nongzitong.nongzitong.network.HttpStringResponseHandler;
import com.mgz.nongzitong.nongzitong.network.request.LoginRequest;
import com.mgz.nongzitong.nongzitong.network.request.TestRequest;
import com.mgz.nongzitong.nongzitong.network.response.LoginResponse;
import com.mgz.nongzitong.nongzitong.network.response.TestResponse;
import com.mgz.nongzitong.nongzitong.utils.Logger;
import com.mgz.nongzitong.nongzitong.utils.MyOKHttp;
import com.mgz.nongzitong.nongzitong.utils.StorageUtil;


public class MainActivity extends BaseActivity implements View.OnClickListener,MyFragment.changeState{

    private ImageButton home;
    private ImageButton category;
    private ImageButton cart;
    private ImageButton my;
    private MyFragment myFragment;
    private HomeFragment homeFragment;
    private SharedPreferences sp;
    private String phoneNum;
    private String password;
    private String userId;
    private boolean isLogin = false;

    @Override
    public int getRecoureId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        home = (ImageButton) findViewById(R.id.ib_home);
        category = (ImageButton) findViewById(R.id.ib_category);
        cart = (ImageButton) findViewById(R.id.ib_cart);
        my = (ImageButton) findViewById(R.id.ib_my);

        home.setImageResource(R.drawable.home_pressed);
    }

    @Override
    public void initData() {
        phoneNum = StorageUtil.getPhoneNum();
        password = StorageUtil.getPassword();
        userId = StorageUtil.getUserId();
        if (phoneNum.length() < 11) {
            isLogin = false;
            StorageUtil.safeState(isLogin);
        } else {
            LoginRequest loginRequest = new LoginRequest(phoneNum, password, 1);
            MyOKHttp.postToBase("login", loginRequest, new HttpStringResponseHandler<LoginResponse>(getApplicationContext(), LoginResponse.class, true){
                @Override
                public void onSuccess(LoginResponse response) {
                    if (response.getSuccess() == 1) {
                        isLogin = true;
                        StorageUtil.safeState(isLogin);
                    }
                }
            });
        }
    }

    @Override
    public void initOnStart() {
        isLogin = StorageUtil.getLoginState();
        if (isLogin) {
            if (userId.length() <= 1) {
                userId = StorageUtil.getUserId();
            }
        } else {
            if (home != null) {
                hiddenFragment(getSupportFragmentManager().beginTransaction());
                onClick(home);
            }
        }
    }

    @Override
    public void resetView() {
        if (!isLogin) {
            homeButtonEvent(getSupportFragmentManager().beginTransaction());
        }
    }

    @Override
    public void setOnClickListner() {
        home.setOnClickListener(this);
        category.setOnClickListener(this);
        cart.setOnClickListener(this);
        my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resetBg();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hiddenFragment(transaction);
        if (v == home) {
            homeButtonEvent(transaction);
        } else if (v == category) {
            category.setImageResource(R.drawable.category_pressed);
        } else if (v == cart) {
            cart.setImageResource(R.drawable.cart_pressed);
        } else if (v == my) {
            myButtonEvent(transaction);
        }
    }

    private void homeButtonEvent(FragmentTransaction transaction) {    //主页按钮点击事件
        home.setImageResource(R.drawable.home_pressed);

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.main_fragment, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        transaction.commit();
    }

    private void myButtonEvent(final FragmentTransaction transaction) {   //我的按钮点击事件
        my.setImageResource(R.drawable.my_pressed);

        if (myFragment == null) {
            myFragment = new MyFragment();
            transaction.add(R.id.main_fragment, myFragment);
        } else {
            transaction.show(myFragment);
        }
        transaction.commit();
        if (!isLogin) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("请登录");
            builder.setMessage("您尚未登录，需登录方可查看该界面!");
            builder.setPositiveButton("立即登录", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {    //跳转至登录界面
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {    //跳转至主界面
                    MainActivity.this.onClick(home);
                }
            });
            builder.show();
        }
    }

    private void resetBg() {
        home.setImageResource(R.drawable.home_normal);
        category.setImageResource(R.drawable.category_normal);
        cart.setImageResource(R.drawable.cart_normal);
        my.setImageResource(R.drawable.my_normal);
    }

    private void hiddenFragment(FragmentTransaction transaction) {
        if (myFragment != null)
            transaction.hide(myFragment);
        if (homeFragment != null)
            transaction.hide(homeFragment);
    }

    @Override
    public void changeLoginState(boolean state) {
        this.isLogin = state;
        onClick(home);
    }
}
