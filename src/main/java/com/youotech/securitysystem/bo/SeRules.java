package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeRules {
    private Integer srId;

    private String sdType;

    private String sdOs;

    private String sdOstype;

    private Integer srType;

    private String srRname;

    private Integer srRules;

    private Date srDate;

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
    }

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType == null ? null : sdType.trim();
    }

    public String getSdOs() {
        return sdOs;
    }

    public void setSdOs(String sdOs) {
        this.sdOs = sdOs == null ? null : sdOs.trim();
    }

    public String getSdOstype() {
        return sdOstype;
    }

    public void setSdOstype(String sdOstype) {
        this.sdOstype = sdOstype == null ? null : sdOstype.trim();
    }

    public Integer getSrType() {
        return srType;
    }

    public void setSrType(Integer srType) {
        this.srType = srType;
    }

    public String getSrRname() {
        return srRname;
    }

    public void setSrRname(String srRname) {
        this.srRname = srRname == null ? null : srRname.trim();
    }

    public Integer getSrRules() {
        return srRules;
    }

    public void setSrRules(Integer srRules) {
        this.srRules = srRules;
    }

    public Date getSrDate() {
        return srDate;
    }

    public void setSrDate(Date srDate) {
        this.srDate = srDate;
    }

    @Override
    public String toString() {
        return "SeRules(srId:" + srId + ",sdType:" + sdType + ",sdOs:" + sdOs + ",sdOstype:" + sdOstype + ",srType:" + srType + ",srRname:" + srRname + ",srRules:" + srRules + ",srDate:" + srDate + ")";
    }
}