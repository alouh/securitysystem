package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.bo.StatisticsCount;
import com.youotech.securitysystem.datasource.DataSource;
import com.youotech.securitysystem.utils.Pager;

import java.util.Date;
import java.util.List;

/**
 * 安全分析结果统计
 * Created by chenzc on 2017-11-23.
 */
public interface SeStatisticsService extends CommerService<SeStatistics> {
    /**
     * 根据条件查询
     * @param srType
     * @param startDate
     * @param endDate
     * @return
     */
    List<SeStatistics> findEntityByParamPlus(Integer srType,String sdType, Date startDate, Date endDate);

    /**
     * 根据条件查询数量
     * @param startDate
     * @param endDate
     * @return
     */
    Pager<StatisticsCount> findreversalByParamPager(String sdType, Date startDate, Date endDate, Integer start, Integer end);

	/**
	 * 插入统计结果
	 * @param record
	 * @return
	 */
	@DataSource("mysql")
	int insert(SeStatistics record);
}
