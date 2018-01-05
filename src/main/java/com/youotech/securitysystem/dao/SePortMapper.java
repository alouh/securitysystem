package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SePort;

import java.util.List;
import java.util.Map;

public interface SePortMapper {
    int deleteByPrimaryKey(Integer spId);

    int insert(SePort record);

    int insertSelective(SePort record);

    SePort selectByPrimaryKey(Integer spId);

    int updateByPrimaryKeySelective(SePort record);

    int updateByPrimaryKey(SePort record);

    List<SePort> findByParam(SePort entity);

    List<SePort> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    /**
     * 批量插入
     * @param sePortList
     * @return
     */
    int batchInsert(List<SePort> sePortList);
}