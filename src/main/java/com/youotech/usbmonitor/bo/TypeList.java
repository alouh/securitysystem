package com.youotech.usbmonitor.bo;

import java.util.Date;

public class TypeList {

    private Integer tl_Id;//索引ID
    private String tl_Type;//USB设备类型
    private String tl_Path;//对应注册表中的路径
    private String tl_Allow;//设备类型是否可用
    private Date tl_Date;//添加日期

    public Integer getTl_Id() {
        return tl_Id;
    }

    public String getTl_Allow() {
        return tl_Allow;
    }

    public Date getTl_Date() {
        return tl_Date;
    }

    public String getTl_Path() {
        return tl_Path;
    }

    public String getTl_Type() {
        return tl_Type;
    }

    public void setTl_Allow(String tl_Allow) {
        this.tl_Allow = tl_Allow;
    }

    public void setTl_Date(Date tl_Date) {
        this.tl_Date = tl_Date;
    }

    public void setTl_Id(Integer tl_Id) {
        this.tl_Id = tl_Id;
    }

    public void setTl_Path(String tl_Path) {
        this.tl_Path = tl_Path;
    }

    public void setTl_Type(String tl_Type) {
        this.tl_Type = tl_Type;
    }
}