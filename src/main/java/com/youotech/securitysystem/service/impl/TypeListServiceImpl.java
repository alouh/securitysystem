package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.TypeList;
import com.youotech.securitysystem.dao.TypeListMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.TypeListService;
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
@Service("typeListService")
public class TypeListServiceImpl implements TypeListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeListServiceImpl.class);

    @Autowired
    private TypeListMapper typeListMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(TypeList entity) {
        LOGGER.info("【TypeListServiceImpl.save】入参" + entity);
        int count = 0;
        List<TypeList> typeListList = typeListMapper.findByParam(new TypeList());
        if (entity.getSdType().equals("")){
            LOGGER.error("【TypeListServiceImpl.save】新增设备失败");
            throw new BizException("设备类型不能为空，请重新输入");
        }
        for (TypeList typeList : typeListList)
        {
            String type = typeList.getSdType();
            String newType = entity.getSdType();
            if (newType.equals(type)){
                LOGGER.error("【TypeListServiceImpl.save】新增设备失败");
                throw new BizException("设备类型已存在，请重新输入");
            }
        }
        try {
            count = typeListMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【TypeListServiceImpl.save】新增设备失败");
            throw new BizException("新增设备失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【TypeListServiceImpl.save】新增设备失败");
            throw new BizException("新增设备失败,请联系管理员");
        }
        return entity.getSdId();
    }

    @Override
    public List<TypeList> selectByPrimaryKey(Integer id) {
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
        LOGGER.info("【TypeListServiceImpl.deleteByIds】【批量更改设备入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【TypeListServiceImpl.deleteByIds】【传入设备ID不能为空！】");
            return false;
        }
        try {
            typeListMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量更改设备失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除设备删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(TypeList entity) {
        return null;
    }

    @Override
    public List<TypeList> findEntityByParam(TypeList entity) {
        LOGGER.info("【TypeListServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<TypeList> typeListList = typeListMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(typeListList)) {
            LOGGER.info("【TypeListServiceImpl.findEntityByParam台账信息为空！】");
            return new ArrayList<TypeList>();
        }
        return typeListList;
    }

    @Override
    public List<TypeList> findEntityByParamFuzzy(TypeList entity) {
        LOGGER.info("【TypeListServiceImpl.findEntityByParamFuzzy】入参" + entity);
        List<TypeList> typeListList = typeListMapper.findEntityByParamFuzzy(entity);
        if (CollectionUtils.isEmpty(typeListList)) {
            LOGGER.info("【TypeListServiceImpl.findEntityByParamFuzzy！】");
            return new ArrayList<TypeList>();
        }
        return typeListList;
    }

    @Override
    public Pager<TypeList> findByParamPage(TypeList entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", limit);
        map.put("sdType", entity.getSdType());
        List<TypeList> typeListList = typeListMapper.findEntityByParamFuzzy(entity);
        List<TypeList> typeListList1 = typeListMapper.findByParamPage(map);
        Pager<TypeList> seUserPager = new Pager<TypeList>(start, limit, typeListList1, typeListList.size());
        return seUserPager;
    }

    @Override
    public Boolean updateByIds(List<Integer> ids) {
        LOGGER.info("【TypeListServiceImpl.updateByIds】【批量更改设备入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【TypeListServiceImpl.updateByIds】【传入设备ID不能为空！】");
            return false;
        }
        try {
            typeListMapper.updateByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量更改设备失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除设备删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    public List<TypeList> initDeviceList() {
        List<TypeList> deviceList = typeListMapper.initDeviceList();
        if (deviceList.isEmpty()) {
            LOGGER.info("[TypeListServiceImpl.initDeviceList查询失败]");
            return new ArrayList<>();
        }
        return deviceList;
    }
}
