package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeResult;
import com.youotech.securitysystem.bo.SeStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeResultMapper {
    int deleteByPrimaryKey(Integer stId);

    int insert(SeResult record);

    int insertSelective(SeResult record);

    SeResult selectByPrimaryKey(Integer stId);

    int updateByPrimaryKeySelective(SeResult record);

    int updateByPrimaryKey(SeResult record);

    List<SeResult> findByParam(SeResult entity);

    List<SeResult> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    List<SeResult> findDetailStatistics(@Param("sdType") String sdType, @Param("srType") Integer srType, @Param("stDate") Date stDate, @Param("sdIp") String sdIp, @Param("srRname") String srRname);

    List<SeResult> findAllLoop(@Param("sdIp") String sdIp, @Param("sdType") String sdType, @Param("stDate") Date stDate);

    List<SeResult> showDetailStatistics(@Param("sdType") String sdType, @Param("srType") Integer srType, @Param("stDate") Date stDate, @Param("sdId") Integer sdId);

    List<SeResult> findDetailStatisticsPager(@Param("sdType") String sdType, @Param("srType") Integer srType, @Param("stDate") Date stDate, @Param("sdIp") String sdIp, @Param("srRname") String srRname, @Param("start") Integer start, @Param("end") Integer end);

    List<SeResult> findAllLoopPager(@Param("sdIp") String sdIp, @Param("sdType") String sdType, @Param("stDate") Date stDate, @Param("start") Integer start, @Param("end") Integer end);

    List<SeResult> showDetailStatisticsPager(@Param("sdType") String sdType, @Param("srType") Integer srType, @Param("stDate") Date stDate, @Param("sdId") Integer sdId, @Param("start") Integer start, @Param("end") Integer end);


    /**
     * 批量插入
     * @param seResultList
     * @return
     */
    int batchInsert(List<SeResult> seResultList);

	/**
	 * 统计结果表中的数据
	 * @return
	 */
	List<SeStatistics> statistics();
}