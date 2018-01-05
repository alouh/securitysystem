package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeDevice {
    private Integer sdId;

    private String sdName;

    private String sdType;

    private String sdOs;

    private String sdOstype;

    private String sdIp;

    private String sdMac;

    private String sdUser;

    private String sdSource;

    private Date sdDate;

    private String sdDept;

    private Integer sdRemarks;

    public Integer getSdRemarks() {
        return sdRemarks;
    }

    public void setSdRemarks(Integer sdRemarks) {
        this.sdRemarks = sdRemarks;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName == null ? null : sdName.trim();
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

    public String getSdIp() {
        return sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp == null ? null : sdIp.trim();
    }

    public String getSdMac() {
        return sdMac;
    }

    public void setSdMac(String sdMac) {
        this.sdMac = sdMac == null ? null : sdMac.trim();
    }

    public String getSdUser() {
        return sdUser;
    }

    public void setSdUser(String sdUser) {
        this.sdUser = sdUser == null ? null : sdUser.trim();
    }

    public String getSdSource() {
        return sdSource;
    }

    public void setSdSource(String sdSource) {
        this.sdSource = sdSource == null ? null : sdSource.trim();
    }

    public Date getSdDate() {
        return sdDate;
    }

    public void setSdDate(Date sdDate) {
        this.sdDate = sdDate;
    }

    public String getSdDept() {
        return sdDept;
    }

    public void setSdDept(String sdDept) {
        this.sdDept = sdDept == null ? null : sdDept.trim();
    }

	@Override
	public String toString() {
		return "SeDevice(sdId:" + sdId + ",sdName:" + sdName + ",sdType:" + sdType + ",sdOs:" + sdOs + ",sdOstype:" + sdOstype + ",sdIp:" + sdIp + ",sdMac:" + sdMac + ",sdUser:" + sdUser + ",sdSource:" + sdSource + ",sdDate:" + sdDate + ",sdDept:" + sdDept + ",sdRemarks:" + sdRemarks + ",)";
	}
}