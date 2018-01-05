package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeDevice;

import java.util.List;
import java.util.Map;

public interface SeDeviceMapper {
    int deleteByPrimaryKey(Integer sdId);

    int insert(SeDevice record);

    int insertSelective(SeDevice record);

    SeDevice selectByPrimaryKey(Integer sdId);

    int updateByPrimaryKeySelective(SeDevice record);

    int updateByPrimaryKey(SeDevice record);

    List<SeDevice> findByParam(SeDevice entity);

    List<SeDevice> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    List<SeDevice> findEntityByParamFuzzy(SeDevice entity);

    int  updateByIds(List<Integer> ids);

	List<SeDevice> initDeviceList();
}