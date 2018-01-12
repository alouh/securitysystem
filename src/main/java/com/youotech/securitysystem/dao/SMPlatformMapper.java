package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SMPlatform;

import java.util.List;
import java.util.Map;

public interface SMPlatformMapper {
    int deleteByPrimaryKey(Integer siId);

    int insert(SMPlatform record);

    int insertSelective(SMPlatform record);

    SMPlatform selectByPrimaryKey(Integer siId);

    int updateByPrimaryKeySelective(SMPlatform record);

    int updateByPrimaryKey(SMPlatform record);

    List<SMPlatform> findByParam(SMPlatform entity);

    List<SMPlatform> findByParamFuzzy(SMPlatform entity);

    List<SMPlatform> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    List<SMPlatform> selectAllVRV(int start, int increase);

    int autoInsert(List<SMPlatform> sMPlatforms);

    SMPlatform insteadDeviceID(SMPlatform sMPlatform);

    int selectCount();

    List<String> selectBySdId(int sdId);

    int deleteInstalled();
}