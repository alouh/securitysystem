package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.TypeList;
import com.youotech.securitysystem.bo.SMPlatform;
import com.youotech.securitysystem.dao.SMPlatformMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SMPlatformService;
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
@Service("sMPlatformService")
public class SMPlatformServiceImpl implements SMPlatformService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMPlatformServiceImpl.class);

    @Autowired
    private SMPlatformMapper sMPlatformMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SMPlatform entity) {
        LOGGER.info("【SMPlatformServiceImpl.save】入参" + entity);

        List<SMPlatform> sMPlatforms = sMPlatformMapper.findByParamFuzzy(new SMPlatform());
        for (SMPlatform sMPlatform : sMPlatforms){
            String phoneNum = sMPlatform.getSiSname();
            if (entity.getSiSname().equals(phoneNum)){
                LOGGER.error("【TypeListServiceImpl.save】新增设备失败");
                throw new BizException("号码已存在，请重新输入");
            }
        }
        int count = 0;
        try {
            count = sMPlatformMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SMPlatformServiceImpl.save】新增软件/补丁/漏洞异常信息失败");
            throw new BizException("新增软件/补丁/漏洞异常信息失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SMPlatformServiceImpl.save】新增软件/补丁/漏洞异常信息失败");
            throw new BizException("新增软件/补丁/漏洞异常信息失败,请联系管理员");
        }
        return entity.getSiId();

    }

    @Override
    public List<SMPlatform> selectByPrimaryKey(Integer id) {
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

        LOGGER.info("【SMPlatformServiceImpl.deleteByIds】【批量更改设备入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【SMPlatformServiceImpl.deleteByIds】【传入设备ID不能为空！】");
            return false;
        }
        try {
            sMPlatformMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SMPlatformServiceImpl.deleteByIds--批量更改设备失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除设备删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(SMPlatform entity) {
        return null;
    }

    @Override
    public List<SMPlatform> findEntityByParam(SMPlatform entity) {
        LOGGER.info("【SMPlatformServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SMPlatform> sMPlatformList = sMPlatformMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(sMPlatformList)) {
            LOGGER.info("【SMPlatformServiceImpl.findEntityByParam软件/补丁/漏洞异常信息为空！】");
            return new ArrayList<SMPlatform>();
        }
        return sMPlatformList;
    }

    @Override
    public List<TypeList> findEntityByParamFuzzy(TypeList entity) {
        return null;
    }

    @Override
    public Pager<SMPlatform> findByParamPage(SMPlatform entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", limit);
        map.put("siSname", entity.getSiSname());
        List<SMPlatform> sMPlatformList = sMPlatformMapper.findByParamPage(map);
        List<SMPlatform> sMPlatformList1 = sMPlatformMapper.findByParamFuzzy(entity);
        Pager<SMPlatform> sMPlatformPager = new Pager<SMPlatform>(start, limit, sMPlatformList, sMPlatformList1.size());
        return sMPlatformPager;
    }

    @Override
    public List<SMPlatform> findByParamFuzzy(SMPlatform entity) {
        List<SMPlatform> sMPlatformList = sMPlatformMapper.findByParamFuzzy(entity);
        return sMPlatformList;
    }

    @Override
    public List<SMPlatform> selectAllVRV(int start, int increase) {
        return sMPlatformMapper.selectAllVRV(start, increase);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public int autoInsert(List<SMPlatform> sMPlatforms) {
        LOGGER.info("[SMPlatformServiceImpl.autoInsert自动插入]入参" + sMPlatforms);
        int i = sMPlatformMapper.autoInsert(sMPlatforms);
        if (i == 0) {
            LOGGER.info("[SMPlatformServiceImpl.autoInsert自动插入信息为空]");
        }
        return i;
    }

    @Override
    public SMPlatform insteadDeviceID(SMPlatform sMPlatform) {
        return sMPlatformMapper.insteadDeviceID(sMPlatform);
    }

    @Override
    public int selectCount() {
        return sMPlatformMapper.selectCount();
    }

    /*@Override
    public List<String> selectBySdId(int sdId) {
        LOGGER.info("[SMPlatformServiceImpl.selectBySdId]" + sdId);
        List<String> list = sMPlatformMapper.selectBySdId(sdId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }*/
    @Override
	public int deleteInstalled(){
    	return sMPlatformMapper.deleteInstalled();
    }
}
