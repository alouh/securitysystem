package com.youotech.securitysystem.bo;

import java.util.Date;

public class SePort {
    private Integer spId;

    private Integer sdId;

    private String spType;

    private Integer spPort;

    private Date spDate;

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSpType() {
        return spType;
    }

    public void setSpType(String spType) {
        this.spType = spType == null ? null : spType.trim();
    }

    public Integer getSpPort() {
        return spPort;
    }

    public void setSpPort(Integer spPort) {
        this.spPort = spPort;
    }

    public Date getSpDate() {
        return spDate;
    }

    public void setSpDate(Date spDate) {
        this.spDate = spDate;
    }
}