package com.youotech.securitysystem.bo;

import java.util.Date;

public class TypeList {
    private Integer sdId;

    private String sdType;

    private Date sdDate;

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType == null ? null : sdType.trim();
    }

    public Date getSdDate() {
        return sdDate;
    }

    public void setSdDate(Date sdDate) {
        this.sdDate = sdDate;
    }
}