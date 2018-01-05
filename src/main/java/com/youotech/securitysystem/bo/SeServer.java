package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeServer {
    private Integer svId;

    private Integer sdId;

    private Integer svTelnet;

    private Integer svSsh;

    private Integer svHttp;

    private Date svDate;

    private String sdName;

    private String sdIp;

    private String sdUser;

    public Integer getSvId() {
        return svId;
    }

    public void setSvId(Integer svId) {
        this.svId = svId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public Integer getSvTelnet() {
        return svTelnet;
    }

    public void setSvTelnet(Integer svTelnet) {
        this.svTelnet = svTelnet;
    }

    public Integer getSvSsh() {
        return svSsh;
    }

    public void setSvSsh(Integer svSsh) {
        this.svSsh = svSsh;
    }

    public Integer getSvHttp() {
        return svHttp;
    }

    public void setSvHttp(Integer svHttp) {
        this.svHttp = svHttp;
    }

    public Date getSvDate() {
        return svDate;
    }

    public void setSvDate(Date svDate) {
        this.svDate = svDate;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public String getSdIp() {
        return sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp;
    }

    public String getSdUser() {
        return sdUser;
    }

    public void setSdUser(String sdUser) {
        this.sdUser = sdUser;
    }
}