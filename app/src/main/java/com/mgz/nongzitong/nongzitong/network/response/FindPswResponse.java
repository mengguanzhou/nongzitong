package com.mgz.nongzitong.nongzitong.network.response;

/**
 * Created by john on 2017/7/10.
 */

public class FindPswResponse {

    private String phoneNum;
    private int success;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
