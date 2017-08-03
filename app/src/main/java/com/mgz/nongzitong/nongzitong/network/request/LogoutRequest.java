package com.mgz.nongzitong.nongzitong.network.request;

/**
 * Created by john on 2017/7/10.
 */

public class LogoutRequest {

    private String userId;
    private int type;

    public LogoutRequest() {}
    public LogoutRequest(String userId, int type) {
        this.userId = userId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
