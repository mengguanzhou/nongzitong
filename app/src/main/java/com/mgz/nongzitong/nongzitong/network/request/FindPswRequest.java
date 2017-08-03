package com.mgz.nongzitong.nongzitong.network.request;

/**
 * Created by john on 2017/7/10.
 */

public class FindPswRequest {

    private String phoneNum;
    private String codeNum;
    private String password;

    public FindPswRequest() {}
    public FindPswRequest(String phoneNum, String codeNum, String password) {
        this.phoneNum = phoneNum;
        this.codeNum = codeNum;
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
