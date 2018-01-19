package com.youotech.usbmonitor.dao;

import com.youotech.usbmonitor.bo.IllegalDevice;

import java.util.List;
import java.util.Map;

public interface IllegalDeviceMapper {
    int deleteByPrimaryKey(Integer srId);

    int insert(IllegalDevice record);

    int insertSelective(IllegalDevice record);

    IllegalDevice selectByPrimaryKey(Integer srId);

    int updateByPrimaryKeySelective(IllegalDevice record);

    int updateByPrimaryKey(IllegalDevice record);

    List<IllegalDevice> findByParam(IllegalDevice entity);

    List<IllegalDevice> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

}