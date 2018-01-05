package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeServer;

import java.util.List;
import java.util.Map;

public interface SeServerMapper {
    int deleteByPrimaryKey(Integer svId);

    int insert(SeServer record);

    int insertSelective(SeServer record);

    SeServer selectByPrimaryKey(Integer svId);

    int updateByPrimaryKeySelective(SeServer record);

    int updateByPrimaryKey(SeServer record);

    List<SeServer> findByParam(SeServer entity);

    List<SeServer> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);
}