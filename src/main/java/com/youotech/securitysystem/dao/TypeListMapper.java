package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.TypeList;

import java.util.List;
import java.util.Map;

public interface TypeListMapper {
    int deleteByPrimaryKey(Integer sdId);

    int insert(TypeList record);

    int insertSelective(TypeList record);

    TypeList selectByPrimaryKey(Integer sdId);

    int updateByPrimaryKeySelective(TypeList record);

    int updateByPrimaryKey(TypeList record);

    List<TypeList> findByParam(TypeList entity);

    List<TypeList> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    List<TypeList> findEntityByParamFuzzy(TypeList entity);

    int  updateByIds(List<Integer> ids);

	List<TypeList> initDeviceList();
}