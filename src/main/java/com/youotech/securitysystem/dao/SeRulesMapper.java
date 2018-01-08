package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeRules;

import java.util.List;
import java.util.Map;

public interface SeRulesMapper {
    int deleteByPrimaryKey(Integer srId);

    int insert(SeRules record);

    int insertSelective(SeRules record);

    SeRules selectByPrimaryKey(Integer srId);

    int updateByPrimaryKeySelective(SeRules record);

    int updateByPrimaryKey(SeRules record);

    List<SeRules> findByParam(SeRules entity);

    List<SeRules> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

}