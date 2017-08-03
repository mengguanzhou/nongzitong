package com.mgz.nongzitong.nongzitong.activity;

import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mgz.nongzitong.nongzitong.R;
import com.mgz.nongzitong.nongzitong.basictool.BaseActivity;
import com.mgz.nongzitong.nongzitong.network.HttpStringResponseHandler;
import com.mgz.nongzitong.nongzitong.network.request.CodeRequest;
import com.mgz.nongzitong.nongzitong.network.request.FindPswRequest;
import com.mgz.nongzitong.nongzitong.network.response.CodeResponse;
import com.mgz.nongzitong.nongzitong.network.response.FindPswResponse;
import com.mgz.nongzitong.nongzitong.utils.Logger;
import com.mgz.nongzitong.nongzitong.utils.MyOKHttp;

import java.util.ArrayList;

/**
 * Created by john on 2017/7/10.
 */

public class FindPswActivity extends BaseActivity implements View.OnClickListener {

    private EditText newPsw;
    private EditText confirmPsw;
    private EditText phoneNum;
    private EditText codeNum;
    private Button getCode;
    private Button finish;
    private ImageButton back;

    @Override
    public int getRecoureId() {
        return R.layout.activity_findpwd;
    }

    @Override
    public void initView() {
        newPsw = (EditText) findViewById(R.id.et_new_psw);
        confirmPsw = (EditText) findViewById(R.id.et_confirm_psw);
        phoneNum = (EditText) findViewById(R.id.et_phonenum);
        codeNum = (EditText) findViewById(R.id.verify_code);
        getCode = (Button) findViewById(R.id.btn_getcode);
        finish = (Button) findViewById(R.id.btn_finish);
        back = (ImageButton) findViewById(R.id.btn_back);
    }

    @Override
    public void setOnClickListner() {
        View[] views = new View[]{back, getCode, finish};
        quickSetClickListener(views, this);
    }

    @Override
    public void onClick(View v) {
        if (v == getCode) {
            if (phoneNum.getText().toString().length() < 11) {
                Toast.makeText(getApplicationContext(), "请输入11位手机号", Toast.LENGTH_LONG).show();
            } else if (!newPsw.getText().toString().equals(confirmPsw.getText().toString())) {
                Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_LONG).show();
            } else {
                CodeRequest codeRequest = new CodeRequest(phoneNum.getText().toString().trim(), 2);
                MyOKHttp.postToBase("requireCode", codeRequest, new HttpStringResponseHandler<CodeResponse>(getApplicationContext(),
                        CodeResponse.class, true) {
                    @Override
                    public void onSuccess(CodeResponse response) {
                        super.onSuccess(response);
                        if (response.getSuccess() == 1) {
                            codeNum.setText(response.getCodeNum());
                        }
                    }
                });
            }
        } else if (v == finish) {
            FindPswRequest findPswRequest = new FindPswRequest(phoneNum.getText().toString().trim(), codeNum.getText().toString().trim(),
                    newPsw.getText().toString().trim());
            MyOKHttp.postToBase("findPsw", findPswRequest, new HttpStringResponseHandler<FindPswResponse>(getApplicationContext(),
                    FindPswResponse.class, true) {
                @Override
                public void onSuccess(FindPswResponse response) {
                    super.onSuccess(response);
                    if (response.getSuccess() == 1) {
                        Toast.makeText(getApplicationContext(), "修改密码成功", Toast.LENGTH_LONG).show();
                        finish();
                    } else if (response.getSuccess() == -1) {
                        Toast.makeText(getApplicationContext(), "验证码错误，请核对", Toast.LENGTH_LONG).show();
                    } else if (response.getSuccess() == 0) {
                        Toast.makeText(getApplicationContext(), "抱歉，未知错误，请稍候再操作", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (v == back) {
            finish();
        }
    }
}
