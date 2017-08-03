package com.mgz.nongzitong.nongzitong.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mgz.nongzitong.nongzitong.MainActivity;
import com.mgz.nongzitong.nongzitong.R;
import com.mgz.nongzitong.nongzitong.basictool.BaseActivity;
import com.mgz.nongzitong.nongzitong.network.HttpStringResponseHandler;
import com.mgz.nongzitong.nongzitong.network.request.CodeRequest;
import com.mgz.nongzitong.nongzitong.network.request.RegisterRequest;
import com.mgz.nongzitong.nongzitong.network.response.CodeResponse;
import com.mgz.nongzitong.nongzitong.network.response.RegisterResponse;
import com.mgz.nongzitong.nongzitong.utils.Logger;
import com.mgz.nongzitong.nongzitong.utils.MyOKHttp;
import com.mgz.nongzitong.nongzitong.utils.StorageUtil;

/**
 * Created by john on 2017/7/6.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText mobileNum;
    private EditText code;
    private TextView codeTimer;
    private EditText password;
    private EditText passwordConfirm;
    private CheckBox agreePro;
    private Button confirm;
    private CodeCount codeCount = new CodeCount(60000, 1000);

    @Override
    public int getRecoureId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mobileNum = (EditText) findViewById(R.id.et_mobile_num);
        code = (EditText) findViewById(R.id.et_code);
        codeTimer = (TextView) findViewById(R.id.tv_timer);
        password = (EditText) findViewById(R.id.et_password);
        passwordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        agreePro = (CheckBox) findViewById(R.id.cb_agree);
        confirm = (Button) findViewById(R.id.btn_confirm);
    }

    @Override
    public void setOnClickListner() {
        codeTimer.setOnClickListener(this);
        confirm.setOnClickListener(this);
        agreePro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == codeTimer) {   //请求验证码
            String mobilephoneNum = mobileNum.getText().toString().trim();
            if (mobilephoneNum.length() == 11) {
                codeTimer.setEnabled(false);
                codeCount.start();
                requireCode(mobilephoneNum);
            } else {
                Toast.makeText(getApplicationContext(), "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            }
        } else if (v == confirm) {  //完成注册
            Logger.e("confirm", "press");
            if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
                Toast.makeText(getApplicationContext(), "两次输入的密码不一致，请确认", Toast.LENGTH_LONG).show();
            } else {
                String phoneNum = mobileNum.getText().toString().trim();
                String codeNum = code.getText().toString().trim();
                String password = passwordConfirm.getText().toString().trim();
                registerConfirm(phoneNum, codeNum, password);
            }
        } else if (v == agreePro) {
            Logger.e("agree", "press");
            if (agreePro.isChecked()) {
                confirm.setEnabled(true);
                confirm.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                confirm.setEnabled(false);
                confirm.setBackgroundColor(getResources().getColor(R.color.login_text_color));
            }
        }
    }

    private void requireCode(String phoneNum) {
        CodeRequest codeRequest = new CodeRequest(phoneNum, 1);
        MyOKHttp.postToBase("requireCode", codeRequest, new HttpStringResponseHandler<CodeResponse>(getApplicationContext(), CodeResponse.class, true) {
            @Override
            public void onSuccess(CodeResponse response) {
                super.onSuccess(response);
                if (response.getSuccess() == 1) {
                    code.setText(response.getCodeNum());
                } else if (response.getSuccess() == -1) {
                    Toast.makeText(getApplicationContext(), "您操作过于频繁，请稍候", Toast.LENGTH_LONG).show();
                    codeTimer.setEnabled(false);
                    codeCount.start();
                }
            }
        });
    }

    private void registerConfirm(String phoneNum, String codeNum, String password) {
        final RegisterRequest registerRequest = new RegisterRequest(phoneNum, 1, password, codeNum);
        MyOKHttp.postToBase("register", registerRequest, new HttpStringResponseHandler<RegisterResponse>(getApplicationContext(), RegisterResponse.class, true) {
            @Override
            public void onSuccess(RegisterResponse response) {
                super.onSuccess(response);
                if (response.getSuccess() == 1) {   //注册完成
                    StorageUtil.safeUserInfo(registerRequest.getPhoneNum(), registerRequest.getPassword(), response.getUserId());
                    StorageUtil.safeState(true);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public class CodeCount extends CountDownTimer {

        public CodeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            codeTimer.setText("重新发送");
            codeTimer.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeTimer.setText(millisUntilFinished / 1000 + "S");
        }

    }
}
