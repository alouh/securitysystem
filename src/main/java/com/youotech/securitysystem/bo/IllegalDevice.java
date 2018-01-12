package com.youotech.securitysystem.bo;

import java.util.Date;

public class IllegalDevice {

    /*private Integer srId;

    private String sdType;

    private String sdOs;

    private String sdOstype;

    private String srType;

    private String srRname;

    private Integer srRules;

    private Date srDate;

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
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

    public String getSrType() {
        return srType;
    }

    public void setSrType(String srType) {
        this.srType = srType;
    }

    public String getSrRname() {
        return srRname;
    }

    public void setSrRname(String srRname) {
        this.srRname = srRname == null ? null : srRname.trim();
    }

    public Integer getSrRules() {
        return srRules;
    }

    public void setSrRules(Integer srRules) {
        this.srRules = srRules;
    }

    public Date getSrDate() {
        return srDate;
    }

    public void setSrDate(Date srDate) {
        this.srDate = srDate;
    }

    @Override
    public String toString() {
        return "IllegalDevice(srId:" + srId + ",sdType:" + sdType + ",sdOs:" + sdOs + ",sdOstype:" + sdOstype + ",srType:" + srType + ",srRname:" + srRname + ",srRules:" + srRules + ",srDate:" + srDate + ")";
    }*/

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