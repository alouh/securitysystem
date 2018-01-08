package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeServer {
    private Integer svId;

    private Date svDate;

    private String sdIp;

    public Integer getSvId() {
        return svId;
    }

    public void setSvId(Integer svId) {
        this.svId = svId;
    }

    public Date getSvDate() {
        return svDate;
    }

    public void setSvDate(Date svDate) {
        this.svDate = svDate;
    }

    public String getSdIp() {
        return sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp;
    }
}