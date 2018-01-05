package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.bo.StatisticsCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeStatisticsMapper {
    int deleteByPrimaryKey(Integer ssId);

	/**
	 * 插入统计结果
	 * @param record
	 * @return
	 */
	int insert(SeStatistics record);

    int insertSelective(SeStatistics record);

    SeStatistics selectByPrimaryKey(Integer ssId);

    int updateByPrimaryKeySelective(SeStatistics record);

    int updateByPrimaryKey(SeStatistics record);

    List<SeStatistics> findByParam(SeStatistics entity);

    List<SeStatistics> findByParamPage(Map<String, Object> map);

    List<SeStatistics> findEntityByParamPlus(@Param("srType") Integer srType, @Param("sdType") String sdType, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<StatisticsCount> findreversalByParam(@Param("sdType") String sdType, @Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("start")Integer start,@Param("end")Integer end);


    int deleteByIds(List<Integer> userIds);
}