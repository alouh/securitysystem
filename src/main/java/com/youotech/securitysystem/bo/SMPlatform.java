package com.youotech.securitysystem.bo;

import java.util.Date;

public class SMPlatform {
    /*private Integer siId;

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
    }*/

    private Integer pn_Id;
    private String pn_Number;
    private Date pn_Date;

    public Date getPn_Date() {
        return pn_Date;
    }

    public Integer getPn_Id() {
        return pn_Id;
    }

    public void setPn_Date(Date pn_Date) {
        this.pn_Date = pn_Date;
    }

    public void setPn_Id(Integer pn_Id) {
        this.pn_Id = pn_Id;
    }

    public String getPn_Number() {
        return pn_Number;
    }

    public void setPn_Number(String pn_Number) {
        this.pn_Number = pn_Number;
    }
}