package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeResult;
import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.datasource.DataSource;
import com.youotech.securitysystem.utils.Pager;

import java.util.Date;
import java.util.List;

/**
 * 安全分析结果详细
 * Created by chenzc on 2017-11-23.
 */
public interface SeResultService extends CommerService<SeResult> {
    List <SeResult> findDetailStatistics(String sdType, Integer srType, Date stDate, String sdIp, String srRname);

    List <SeResult> findAllLoop(String sdIp, String sdType, Date stDate);

    List <SeResult> showDetailStatistics(String sdType, Integer srType, Date stDate, Integer sdIp);

    Pager<SeResult> findDetailStatisticsPager(String sdType, Integer srType, Date stDate, String sdIp, String srRname, Integer start, Integer end);

    Pager<SeResult> findAllLoopPager(String sdIp, String sdType, Date stDate, Integer start, Integer end);

    Pager<SeResult> showDetailStatisticsPager(String sdType, Integer srType, Date stDate, Integer sdIp, Integer start, Integer end);

    /**
     * 批量插入
     * @param seResultList
     * @return
     */
    @DataSource("mysql")
    int batchInsert(List<SeResult> seResultList);

	/**
	 * 统计结果表中的数据
	 * @return
	 */
	@DataSource("mysql")
	List<SeStatistics> statistics();
}
