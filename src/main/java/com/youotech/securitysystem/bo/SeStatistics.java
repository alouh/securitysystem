package com.youotech.securitysystem.bo;

import java.util.Date;

public class SeStatistics {
    private Integer ssId;

    private Integer srType;

    private Integer ssCount;

    private Date ssDate;

    private String sdType;

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

    public Integer getSrType() {
        return srType;
    }

    public void setSrType(Integer srType) {
        this.srType = srType;
    }

    public Integer getSsCount() {
        return ssCount;
    }

    public void setSsCount(Integer ssCount) {
        this.ssCount = ssCount;
    }

    public Date getSsDate() {
        return ssDate;
    }

    public void setSsDate(Date ssDate) {
        this.ssDate = ssDate;
    }

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
    }

	@Override
	public String toString() {
		return "SeStatistics(ssId:" + ssId + "srType:" + srType + "ssCount:" + ssCount + "ssDate:" + ssDate + "sdType:" + sdType + ")";
	}
}