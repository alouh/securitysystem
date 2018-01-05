package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeResult {
    private Integer stId;

    private Integer sdId;

    private Integer srType;

    private String stReason;

    private Date stDate;

    private String sdType;

    private String srRname;

    private Integer srRules;


    private String sdName;

    private String sdOs;

    private String sdOsType;

    private String sdUser;

    private String sdIp;

    private String sdMac;

    private Integer loopholeCount;

    private Integer softwareCount;

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public Integer getSrType() {
        return srType;
    }

    public void setSrType(Integer srType) {
        this.srType = srType;
    }

    public String getStReason() {
        return stReason;
    }

    public void setStReason(String stReason) {
        this.stReason = stReason == null ? null : stReason.trim();
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
    }


    public String getSrRname() {
        return srRname;
    }

    public void setSrRname(String srRname) {
        this.srRname = srRname;
    }

    public Integer getSrRules() {
        return srRules;
    }

    public void setSrRules(Integer srRules) {
        this.srRules = srRules;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public String getSdOs() {
        return sdOs;
    }

    public void setSdOs(String sdOs) {
        this.sdOs = sdOs;
    }

    public String getSdOsType() {
        return sdOsType;
    }

    public void setSdOsType(String sdOsType) {
        this.sdOsType = sdOsType;
    }

    public String getSdUser() {
        return sdUser;
    }

    public void setSdUser(String sdUser) {
        this.sdUser = sdUser;
    }

    public String getSdIp() {
        return sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp;
    }

    public String getSdMac() {
        return sdMac;
    }

    public void setSdMac(String sdMac) {
        this.sdMac = sdMac;
    }

    public Integer getLoopholeCount() {
        return loopholeCount;
    }

    public void setLoopholeCount(Integer loopholeCount) {
        this.loopholeCount = loopholeCount;
    }

    public Integer getSoftwareCount() {
        return softwareCount;
    }

    public void setSoftwareCount(Integer softwareCount) {
        this.softwareCount = softwareCount;
    }
}