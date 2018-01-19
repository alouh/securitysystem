package com.youotech.usbmonitor.bo;

import java.util.Date;

public class IllegalDevice {

    private Integer id_Id;//索引id
    private String id_Ip;//终端IP
    private String id_Type;//USB设备类型
    private String id_Mac;//终端Mac
    private String id_UsrName;//终端用户名
    private String id_HostName;//终端主机名
    private Date id_Date;//插入日期

    public void setId_UsrName(String id_UsrName) {
        this.id_UsrName = id_UsrName;
    }

    public void setId_Type(String id_Type) {
        this.id_Type = id_Type;
    }

    public void setId_Mac(String id_Mac) {
        this.id_Mac = id_Mac;
    }

    public void setId_Ip(String id_Ip) {
        this.id_Ip = id_Ip;
    }

    public void setId_Id(int id_Id) {
        this.id_Id = id_Id;
    }

    public void setId_HostName(String id_HostName) {
        this.id_HostName = id_HostName;
    }

    public void setId_Date(Date id_Date) {
        this.id_Date = id_Date;
    }

    public String getId_UsrName() {
        return id_UsrName;
    }

    public String getId_Type() {
        return id_Type;
    }

    public String getId_Mac() {
        return id_Mac;
    }

    public String getId_Ip() {
        return id_Ip;
    }

    public String getId_HostName() {
        return id_HostName;
    }

    public Integer getId_Id() {
        return id_Id;
    }

    public Date getId_Date() {
        return id_Date;
    }

    @Override
    public String toString() {
        return "IllegalDevice(" + id_Id + "," + id_Ip + "," + id_Mac + "," + id_UsrName + "," + id_HostName + "," + id_Type + "," + id_Date + ")";
    }
}