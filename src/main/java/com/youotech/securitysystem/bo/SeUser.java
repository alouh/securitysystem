package com.youotech.securitysystem.bo;

import java.util.Date;

/**
 * Created by Administrator on 2017-11-16.
 */
public class    SeUser {
    private Integer userId;

    private String userName;

    private String realName;

    private Integer sex;

    private Date creatTime;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return userId + "," + userName + "," + realName + "," + sex + "," + creatTime + "," + passWord;
    }
}
