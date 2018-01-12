package com.youotech.securitysystem.bo;

import java.util.Date;

public class SMPlatform {
    private Integer siId;

    private String siSname;

    private Date siDate;

    public Integer getSiId() {
        return siId;
    }

    public void setSiId(Integer siId) {
        this.siId = siId;
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
}