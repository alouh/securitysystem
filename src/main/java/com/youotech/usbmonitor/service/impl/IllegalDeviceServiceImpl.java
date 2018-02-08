package com.youotech.usbmonitor.service.impl;

import com.youotech.usbmonitor.bo.TypeList;
import com.youotech.usbmonitor.bo.IllegalDevice;
import com.youotech.usbmonitor.dao.IllegalDeviceMapper;
import com.youotech.usbmonitor.exception.BizException;
import com.youotech.usbmonitor.service.IllegalDeviceService;
import com.youotech.usbmonitor.utils.Pager;
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
 * 安全审计规则
 * Created by chenzc on 2017-11-23.
 */
@Service("illegalDeviceService")
public class IllegalDeviceServiceImpl implements IllegalDeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IllegalDeviceServiceImpl.class);

    @Autowired
    private IllegalDeviceMapper illegalDeviceMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(IllegalDevice entity) {
        LOGGER.info("【IllegalDeviceServiceImpl.save】入参" + entity);
        int count;
        try {
            count = illegalDeviceMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【IllegalDeviceServiceImpl.save】新增安全审计规则失败");
            throw new BizException("新增安全审计规则失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【IllegalDeviceServiceImpl.save】新增安全审计规则失败");
            throw new BizException("新增安全审计规则失败,请联系管理员");
        }
        return entity.getId_Id();
    }

    @Override
    public IllegalDevice selectByPrimaryKey(Integer id) {
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
        LOGGER.info("【IllegalDeviceServiceImpl.deleteByIds】【批量删除审计规则入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【IllegalDeviceServiceImpl.deleteByIds】【传入ID不能为空！】");
            return false;
        }
        try {
            illegalDeviceMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量删除审计规则失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除审计规则删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(IllegalDevice entity) {
        LOGGER.info("【IllegalDeviceServiceImpl.update】【修改审计规则入参】" + entity);
        Integer count = 0;
        try {
            count = illegalDeviceMapper.updateByPrimaryKey(entity);
        } catch (Exception e) {
            throw new BizException("审计规则更新失败，请联系管理员");
        }
        if (count != 1) {
            throw new BizException("审计规则更新失败，请联系管理员");
        }
        return true;
    }

    @Override
    public List<IllegalDevice> findEntityByParam(IllegalDevice entity) {
        LOGGER.info("【IllegalDeviceServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<IllegalDevice> illegalDeviceList = illegalDeviceMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(illegalDeviceList)) {
            LOGGER.info("【IllegalDeviceServiceImpl.findEntityByParam安全审计规则信息为空！】");
            return new ArrayList<>();
        }
        return illegalDeviceList;
    }

    @Override
    public List<TypeList> findEntityByParamFuzzy(TypeList entity) {
        return null;
    }

    @Override
    public Pager<IllegalDevice> findByParamPage(IllegalDevice entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<>();

        map.put("start", start);
        map.put("end", limit);
        map.put("id_Type", entity.getId_Type());
        map.put("id_Ip", entity.getId_Ip());
        map.put("id_Mac", entity.getId_Mac());
        map.put("id_UsrName", entity.getId_UsrName());
        map.put("id_HostName", entity.getId_HostName());

        List<IllegalDevice> illegalDeviceList1=illegalDeviceMapper.findByParam(entity);
        List<IllegalDevice> illegalDeviceList = illegalDeviceMapper.findByParamPage(map);

        return new Pager<>(start, limit, illegalDeviceList, illegalDeviceList1.size());
    }
}
