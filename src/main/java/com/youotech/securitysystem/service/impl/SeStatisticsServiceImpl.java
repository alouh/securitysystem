package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.bo.StatisticsCount;
import com.youotech.securitysystem.dao.SeStatisticsMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeStatisticsService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安全分析结果统计
 * Created by chenzc on 2017-11-23.
 */
@Service
public class SeStatisticsServiceImpl implements SeStatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeStatisticsServiceImpl.class);

    @Autowired
    private SeStatisticsMapper seStatisticsMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeStatistics entity) {
        LOGGER.info("【SeStatisticsServiceImpl.save】入参" + entity);
        int count = 0;
        try {
            count = seStatisticsMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeStatisticsServiceImpl.save】新增安全分析结果统计失败");
            throw new BizException("新增安全分析结果统计失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeStatisticsServiceImpl.save】新增安全分析结果统计失败");
            throw new BizException("新增安全分析结果统计失败,请联系管理员");
        }
        return entity.getSsId();
    }

    @Override
    public List<SeStatistics> selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByIds(List<Integer> ids) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(SeStatistics entity) {
        return null;
    }

    @Override
    public List<SeStatistics> findEntityByParam(SeStatistics entity) {
        LOGGER.info("【SeStatisticsServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeStatistics> seStatisticsList = seStatisticsMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seStatisticsList)) {
            LOGGER.info("【SeResultServiceImpl.findEntityByParam安全分析结果统计信息为空！】");
            return new ArrayList<SeStatistics>();
        }
        return seStatisticsList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SeStatistics> findByParamPage(SeStatistics entity, Integer start, Integer limit) {
        return null;
    }

    @Override
    public List<SeStatistics> findEntityByParamPlus(Integer srType, String sdType, Date startDate, Date endDate) {
        List<SeStatistics> seStatisticsList = seStatisticsMapper.findEntityByParamPlus(srType, sdType, startDate, endDate);
        return seStatisticsList;
    }

    @Override
    public Pager<StatisticsCount> findreversalByParamPager(String sdType, Date startDate, Date endDate, Integer start, Integer end) {
        List<StatisticsCount> statisticsCountList = seStatisticsMapper.findreversalByParam(sdType, startDate, endDate, start, end);
        Pager<StatisticsCount> statisticsCountPager = new Pager<StatisticsCount>(start, end, statisticsCountList, statisticsCountList.size());
        return statisticsCountPager;
    }
	/**
	 * 插入统计结果
	 * @param record
	 * @return
	 */
	@Override
	public int insert(SeStatistics record) {
		LOGGER.info("{SeStatisticsServiceImpl.insert]" + record);
		return seStatisticsMapper.insert(record);
	}
}
