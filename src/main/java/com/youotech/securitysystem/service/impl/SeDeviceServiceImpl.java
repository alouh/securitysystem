package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.dao.SeDeviceMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeDeviceService;
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
 * 台账管理
 * Created by chenzc on 2017-11-23.
 */
@Service("seDeviceService")
public class SeDeviceServiceImpl implements SeDeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeDeviceServiceImpl.class);

    @Autowired
    private SeDeviceMapper seDeviceMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeDevice entity) {
        LOGGER.info("【SeDeviceServiceImpl.save】入参" + entity);
        int count = 0;
        List<SeDevice> seDeviceList = seDeviceMapper.findByParam(new SeDevice());
        if (entity.getSdType().equals("")){
            LOGGER.error("【SeDeviceServiceImpl.save】新增设备失败");
            throw new BizException("设备类型不能为空，请重新输入");
        }
        for (SeDevice seDevice : seDeviceList)
        {
            String type = seDevice.getSdType();
            String newType = entity.getSdType();
            if (newType.equals(type)){
                LOGGER.error("【SeDeviceServiceImpl.save】新增设备失败");
                throw new BizException("设备类型已存在，请重新输入");
            }
        }
        try {
            count = seDeviceMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeDeviceServiceImpl.save】新增设备失败");
            throw new BizException("新增设备失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeDeviceServiceImpl.save】新增设备失败");
            throw new BizException("新增设备失败,请联系管理员");
        }
        return entity.getSdId();
    }

    @Override
    public List<SeDevice> selectByPrimaryKey(Integer id) {
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
        LOGGER.info("【SeDeviceServiceImpl.deleteByIds】【批量更改设备入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【SeDeviceServiceImpl.deleteByIds】【传入设备ID不能为空！】");
            return false;
        }
        try {
            seDeviceMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量更改设备失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除设备删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(SeDevice entity) {
        return null;
    }

    @Override
    public List<SeDevice> findEntityByParam(SeDevice entity) {
        LOGGER.info("【SeDeviceServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeDevice> seDeviceList = seDeviceMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seDeviceList)) {
            LOGGER.info("【SeDeviceServiceImpl.findEntityByParam台账信息为空！】");
            return new ArrayList<SeDevice>();
        }
        return seDeviceList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        LOGGER.info("【SeDeviceServiceImpl.findEntityByParamFuzzy】入参" + entity);
        List<SeDevice> seDeviceList = seDeviceMapper.findEntityByParamFuzzy(entity);
        if (CollectionUtils.isEmpty(seDeviceList)) {
            LOGGER.info("【SeDeviceServiceImpl.findEntityByParamFuzzy！】");
            return new ArrayList<SeDevice>();
        }
        return seDeviceList;
    }

    @Override
    public Pager<SeDevice> findByParamPage(SeDevice entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", limit);
        map.put("sdType", entity.getSdType());
        List<SeDevice> seDeviceList = seDeviceMapper.findEntityByParamFuzzy(entity);
        List<SeDevice> seDeviceList1 = seDeviceMapper.findByParamPage(map);
        Pager<SeDevice> seUserPager = new Pager<SeDevice>(start, limit, seDeviceList1, seDeviceList.size());
        return seUserPager;
    }

    @Override
    public Boolean updateByIds(List<Integer> ids) {
        LOGGER.info("【SeDeviceServiceImpl.updateByIds】【批量更改设备入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【SeDeviceServiceImpl.updateByIds】【传入设备ID不能为空！】");
            return false;
        }
        try {
            seDeviceMapper.updateByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量更改设备失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除设备删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    public List<SeDevice> initDeviceList() {
        List<SeDevice> deviceList = seDeviceMapper.initDeviceList();
        if (deviceList.isEmpty()) {
            LOGGER.info("[SeDeviceServiceImpl.initDeviceList查询失败]");
            return new ArrayList<>();
        }
        return deviceList;
    }
}
