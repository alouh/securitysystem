package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeServer;
import com.youotech.securitysystem.dao.SeServerMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeServerService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络设备服务开放详情
 * Created by chenzc on 2017-11-23.
 */
@Service("seServerService")
public class SeServerServiceImpl implements SeServerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeServerServiceImpl.class);

    @Autowired
    private SeServerMapper seServerMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeServer entity) {
        LOGGER.info("【SeServerServiceImpl.save】入参" + entity);
        int count = 0;
        try {
            count = seServerMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeServerServiceImpl.save】新增网络设备服务开放失败");
            throw new BizException("新增网络设备服务开放失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeServerServiceImpl.save】新增网络设备服务开放失败");
            throw new BizException("新增网络设备服务开放失败,请联系管理员");
        }
        return entity.getSvId();
    }

    @Override
    public List<SeServer> selectByPrimaryKey(Integer id) {
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
    public Boolean update(SeServer entity) {
        return null;
    }

    @Override
    public List<SeServer> findEntityByParam(SeServer entity) {
        LOGGER.info("【SeServerServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeServer> seServerList = seServerMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seServerList)) {
            LOGGER.info("【SeResultServiceImpl.findEntityByParam网络设备服务开放信息为空！】");
            return new ArrayList<SeServer>();
        }
        return seServerList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SeServer> findByParamPage(SeServer entity, Integer start, Integer limit) {
        return null;
    }

    @Override
    public Pager<SeServer> findByParamPagePlus(SeServer entity, Integer start, Integer limit,String svDates) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("end", limit);
        map.put("svDate", entity.getSvDate());
        map.put("svDates", svDates);//分页查询入参
        List<SeServer> seServerList=seServerMapper.findByParam(entity);
        List<SeServer> seDeviceList1 = seServerMapper.findByParamPage(map);
        return new Pager<>(start, limit, seDeviceList1, seServerList.size());
    }

	@Override
	public int insert(SeServer record) {
    	LOGGER.info("[SeServerServiceImpl.insert入参]" + record);
    	int i = seServerMapper.insert(record);
    	if (i == 0){
    		LOGGER.info("[SeServerServiceImpl.insert插入失败]");
	    }
		return i;
	}
}
