package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeResult;
import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.dao.SeResultMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeResultService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安全分析结果详细
 * Created by chenzc on 2017-11-23.
 */
@Service("seResultService")
public class SeResultServiceImpl implements SeResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeResultServiceImpl.class);

    @Autowired
    private SeResultMapper seResultMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeResult entity) {
        LOGGER.info("【SeResultServiceImpl.save】入参" + entity);
        int count = 0;
        try {
            count = seResultMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeResultServiceImpl.save】新增安全分析结果失败");
            throw new BizException("新增安全分析结果失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeResultServiceImpl.save】新增安全分析结果失败");
            throw new BizException("新增安全分析结果失败,请联系管理员");
        }
        return entity.getStId();
    }

    @Override
    public List<SeResult> selectByPrimaryKey(Integer id) {
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
    public Boolean update(SeResult entity) {
        return null;
    }

    @Override
    public List<SeResult> findEntityByParam(SeResult entity) {
        LOGGER.info("【SeResultServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeResult> seResultList = seResultMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seResultList)) {
            LOGGER.info("【SeResultServiceImpl.findEntityByParam安全分析结果信息为空！】");
            return new ArrayList<SeResult>();
        }
        return seResultList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SeResult> findByParamPage(SeResult entity, Integer start, Integer limit) {
        return null;
    }

    @Override
    public List<SeResult> findDetailStatistics(String sdType, Integer srType, Date stDate, String sdIp, String srRname) {
        List<SeResult> seResultList = seResultMapper.findDetailStatistics(sdType, srType, stDate, sdIp, srRname);
        return seResultList;
    }

    @Override
    public List<SeResult> findAllLoop(String sdIp, String sdType, Date stDate) {
        List<SeResult> seResultList = seResultMapper.findAllLoop(sdIp, sdType, stDate);
        return seResultList;
    }

    @Override
    public List<SeResult> showDetailStatistics(String sdType, Integer srType, Date stDate, Integer sdIp) {
        List<SeResult> seResultList = seResultMapper.showDetailStatistics(sdType,srType,stDate,sdIp);
        return  seResultList;
    }

    @Override
    public Pager<SeResult> findDetailStatisticsPager(String sdType, Integer srType, Date stDate, String sdIp, String srRname, Integer start, Integer end) {
        List<SeResult> seResultList = seResultMapper.findDetailStatisticsPager(sdType, srType, stDate, sdIp, srRname, start, end);
        List<SeResult> seResultList1=seResultMapper.findDetailStatistics(sdType,srType,stDate,sdIp,srRname);
        Pager<SeResult> seResultPager = new Pager<SeResult>(start, end, seResultList, seResultList1.size());
        return seResultPager;
    }

    @Override
    public Pager<SeResult> findAllLoopPager(String sdIp, String sdType, Date stDate, Integer start, Integer end) {
        List<SeResult> seResultList = seResultMapper.findAllLoopPager(sdIp, sdType, stDate, start, end);
        List<SeResult> seResultList1 = seResultMapper.findAllLoop(sdIp, sdType, stDate);
        Pager<SeResult> seResultPager = new Pager<SeResult>(start, end, seResultList, seResultList1.size());
        return seResultPager;
    }

    @Override
    public Pager<SeResult> showDetailStatisticsPager(String sdType, Integer srType, Date stDate, Integer sdId, Integer start, Integer end) {
        List<SeResult> seResultList = seResultMapper.showDetailStatisticsPager(sdType, srType, stDate, sdId, start, end);
        List<SeResult> seResultList1 = seResultMapper.showDetailStatistics(sdType, srType, stDate, sdId);
        Pager<SeResult> seResultPager = new Pager<SeResult>(start, end, seResultList, seResultList1.size());
        return seResultPager;
    }

    @Override
    public int batchInsert(List<SeResult> seResultList) {
        LOGGER.info("[SeResultServiceImpl.batchInsert入参]" + seResultList);
        if (seResultList.size() == 0)
            return 0;
        int i = seResultMapper.batchInsert(seResultList);
        if (i == 0){
            LOGGER.info("[SeResultServiceImpl.batchInsert]插入数量为空");
        }
        return i;
    }

	/**
	 * 统计结果表中的数据
	 * @return
	 */
	@Override
	public List<SeStatistics> statistics() {
		return seResultMapper.statistics();
	}
}
