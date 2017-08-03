package com.mgz.nongzitong.nongzitong.network.response;

/**
 * Created by john on 2017/7/6.
 */

public class RegisterResponse {

    private String phoneNum;
    private String userId;
    private int userRole;
    private int success;

    public RegisterResponse() {

    }
    public RegisterResponse(String phoneNum, String userId, int userRole, int success) {
        this.phoneNum = phoneNum;
        this.userId = userId;
        this.userRole = userRole;
        this.success = success;
    }


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

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
