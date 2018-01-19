package com.youotech.usbmonitor.dao;

import com.youotech.usbmonitor.bo.SeUser;

import java.util.List;
import java.util.Map;

public interface SeUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SeUser record);

    int insertSelective(SeUser record);

    SeUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SeUser record);

    int updateByPrimaryKey(SeUser record);

    List<SeUser> findByParam(SeUser entity);

    List<SeUser> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

}