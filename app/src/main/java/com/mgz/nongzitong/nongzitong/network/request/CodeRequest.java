package com.mgz.nongzitong.nongzitong.network.request;

/**
 * Created by john on 2017/7/6.
 */

public class CodeRequest {

    private String phoneNum;
    private int type;

    public CodeRequest() {

    }
    public CodeRequest(String phoneNum, int type) {
        this.phoneNum = phoneNum;
        this.type = type;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
