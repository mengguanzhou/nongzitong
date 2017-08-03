package com.mgz.nongzitong.nongzitong.network.request;

/**
 * Created by john on 2017/7/7.
 */

public class LoginRequest {

    private String phoneNum;
    private String password;
    private int type;

    public LoginRequest() {

    }
    public LoginRequest(String phoneNum, String password, int type) {
        this.phoneNum = phoneNum;
        this.password = password;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
