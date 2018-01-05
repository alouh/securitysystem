package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeInstalled;
import com.youotech.securitysystem.dao.SeInstalledMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeInstalledService;
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
 * 补丁、软件、漏洞安装详情
 * Created by chenzc on 2017-11-23.
 */
@Service("seInstalledService")
public class SeInstalledServiceImpl implements SeInstalledService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeInstalledServiceImpl.class);

    @Autowired
    private SeInstalledMapper seInstalledMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeInstalled entity) {
        LOGGER.info("【SeInstalledServiceImpl.save】入参" + entity);
        int count = 0;
        try {
            count = seInstalledMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeInstalledServiceImpl.save】新增软件/补丁/漏洞异常信息失败");
            throw new BizException("新增软件/补丁/漏洞异常信息失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeInstalledServiceImpl.save】新增软件/补丁/漏洞异常信息失败");
            throw new BizException("新增软件/补丁/漏洞异常信息失败,请联系管理员");
        }
        return entity.getSiId();

    }

    @Override
    public List<SeInstalled> selectByPrimaryKey(Integer id) {
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
    public Boolean update(SeInstalled entity) {
        return null;
    }

    @Override
    public List<SeInstalled> findEntityByParam(SeInstalled entity) {
        LOGGER.info("【SeInstalledServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeInstalled> seInstalledList = seInstalledMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seInstalledList)) {
            LOGGER.info("【SeInstalledServiceImpl.findEntityByParam软件/补丁/漏洞异常信息为空！】");
            return new ArrayList<SeInstalled>();
        }
        return seInstalledList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SeInstalled> findByParamPage(SeInstalled entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", limit);
        map.put("siType", entity.getSiType());
        map.put("siSname", entity.getSiSname());
        map.put("sdId", entity.getSdId());
        List<SeInstalled> seInstalledList = seInstalledMapper.findByParamPage(map);
        List<SeInstalled> seInstalledList1 = seInstalledMapper.findByParamFuzzy(entity);
        Pager<SeInstalled> seInstalledPager = new Pager<SeInstalled>(start, limit, seInstalledList, seInstalledList1.size());
        return seInstalledPager;
    }

    @Override
    public List<SeInstalled> findByParamFuzzy(SeInstalled entity) {
        List<SeInstalled> seInstalledList = seInstalledMapper.findByParamFuzzy(entity);
        return seInstalledList;
    }

    @Override
    public List<SeInstalled> selectAllVRV(int start, int increase) {
        return seInstalledMapper.selectAllVRV(start, increase);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public int autoInsert(List<SeInstalled> seInstalleds) {
        LOGGER.info("[SeInstalledServiceImpl.autoInsert自动插入]入参" + seInstalleds);
        int i = seInstalledMapper.autoInsert(seInstalleds);
        if (i == 0) {
            LOGGER.info("[SeInstalledServiceImpl.autoInsert自动插入信息为空]");
        }
        return i;
    }

    @Override
    public SeInstalled insteadDeviceID(SeInstalled seInstalled) {
        return seInstalledMapper.insteadDeviceID(seInstalled);
    }

    @Override
    public int selectCount() {
        return seInstalledMapper.selectCount();
    }

    @Override
    public List<String> selectBySdId(int sdId) {
        LOGGER.info("[SeInstalledServiceImpl.selectBySdId]" + sdId);
        List<String> list = seInstalledMapper.selectBySdId(sdId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
    @Override
	public int deleteInstalled(){
    	return seInstalledMapper.deleteInstalled();
    }
}
