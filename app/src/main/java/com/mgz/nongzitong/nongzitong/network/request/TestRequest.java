package com.mgz.nongzitong.nongzitong.network.request;

import java.util.Date;

/**
 * Created by john on 2017/7/5.
 */

public class TestRequest {
    private String userName;
    private String password;
    private int userRole;
    private Date createDate;

    public TestRequest () {

    }

    public TestRequest (String userName, String password, int userRole, Date createDate) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
