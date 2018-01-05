package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeInstalled {
    private Integer siId;

    private Integer sdId;

    private String siType;

    private String siSname;

    private Date siDate;

    private String sdName;

    private String sdIp;

    private String sdType;

    private String sdOs;

    private String sdOstype;

    private String sdMac;

    private String sdUser;

    private Integer loopholeCount;

    private Integer softwareCount;

    public Integer getSiId() {
        return siId;
    }

    public void setSiId(Integer siId) {
        this.siId = siId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSiType() {
    	return siType;
    }

    public void setSiType(String siType) {
        this.siType = siType == null ? null : siType.trim();
    }

    public String getSiSname() {
        return siSname;
    }

    public void setSiSname(String siSname) {
        this.siSname = siSname == null ? null : siSname.trim();
    }

    public Date getSiDate() {
        return siDate;
    }

    public void setSiDate(Date siDate) {
        this.siDate = siDate;
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

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
    }

    public String getSdOs() {
        return sdOs;
    }

    public void setSdOs(String sdOs) {
        this.sdOs = sdOs;
    }

    public String getSdOstype() {
        return sdOstype;
    }

    public void setSdOstype(String sdOstype) {
        this.sdOstype = sdOstype;
    }

    public String getSdMac() {
        return sdMac;
    }

    public void setSdMac(String sdMac) {
        this.sdMac = sdMac;
    }

    public String getSdUser() {
        return sdUser;
    }

    public void setSdUser(String sdUser) {
        this.sdUser = sdUser;
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