package com.youotech.securitysystem.bo;

import java.util.Date;

/**
 * 统计数量
 * Created by chenzc on 2017-11-29.
 */
public class StatisticsCount {
    private Date scDate;

    private Integer tcpCount;

    private Integer udpCount;

    private Integer sofeWareCount;

    private Integer loopholeCount;

    public Date getScDate() {
        return scDate;
    }

    public void setScDate(Date scDate) {
        this.scDate = scDate;
    }

    public Integer getTcpCount() {
        return tcpCount;
    }

    public void setTcpCount(Integer tcpCount) {
        this.tcpCount = tcpCount;
    }

    public Integer getUdpCount() {
        return udpCount;
    }

    public void setUdpCount(Integer udpCount) {
        this.udpCount = udpCount;
    }

    public Integer getSofeWareCount() {
        return sofeWareCount;
    }

    public void setSofeWareCount(Integer sofeWareCount) {
        this.sofeWareCount = sofeWareCount;
    }

    public Integer getLoopholeCount() {
        return loopholeCount;
    }

    public void setLoopholeCount(Integer loopholeCount) {
        this.loopholeCount = loopholeCount;
    }
}
