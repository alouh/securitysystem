package com.youotech.usbmonitor.bo;

import java.util.Date;

/**
 * Created by Administrator on 2017-11-16.
 */
public class    SeUser {
    private Integer userId;

    private String userName;

    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
