package com.mgz.nongzitong.nongzitong.network.request;

import com.mgz.nongzitong.nongzitong.activity.RegisterActivity;

/**
 * Created by john on 2017/7/6.
 */

public class RegisterRequest {

    private String phoneNum;
    private int userRole;
    private String password;
    private String codeNum;

    public RegisterRequest() {

    }
    public RegisterRequest(String phoneNum, int userRole, String password, String codeNum) {
        this.phoneNum = phoneNum;
        this.userRole = userRole;
        this.password = password;
        this.codeNum = codeNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}
