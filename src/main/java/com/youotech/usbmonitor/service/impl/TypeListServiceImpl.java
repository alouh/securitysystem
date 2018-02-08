package com.youotech.usbmonitor.service.impl;

import com.youotech.usbmonitor.bo.TypeList;
import com.youotech.usbmonitor.dao.TypeListMapper;
import com.youotech.usbmonitor.exception.BizException;
import com.youotech.usbmonitor.service.TypeListService;
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
 * 台账管理
 * Created by HanJiafeng on 2018-01-12.
 */
@Service("typeListService")
public class TypeListServiceImpl implements TypeListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeListServiceImpl.class);

    @Autowired
    private TypeListMapper typeListMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(TypeList entity) {
        LOGGER.info("[TypeListServiceImpl.save]入参" + entity);
        int count;
        if (entity.getTl_Type().equals("")){
            LOGGER.error("[TypeListServiceImpl.save]新增类型失败");
            throw new BizException("设备类型不能为空，请重新输入");
        }
        if (entity.getTl_Path().equals("")){
            LOGGER.error("[TypeListServiceImpl.save]新增类型失败");
            throw new BizException("注册表路径不能为空，请重新输入");
        }

        int repetitionCount = typeListMapper.findRepetitionByParam(entity);
        if (repetitionCount != 0){

            LOGGER.error("新增类型失败");
            throw new BizException("统一操作系统下USB设备类型或者注册表路径不能重复，请检查您的输入");
        }
        /*List<TypeList> typeListList = typeListMapper.findByParam(new TypeList());
        for (TypeList typeList : typeListList)
        {
            String type = typeList.getTl_Type();
            String newType = entity.getTl_Type();
            if (newType.equals(type)){
                LOGGER.error("[TypeListServiceImpl.save]新增设备失败");
                throw new BizException("设备类型已存在，请重新输入");
            }
            String path = typeList.getTl_Path();
            String newPath = entity.getTl_Path();
            if (newPath.equals(path)){
                LOGGER.error("[TypeListServiceImpl.save]新增设备失败");
                throw new BizException("注册表路径已存在,请重新输入");
            }
        }*/
        try {
            count = typeListMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("[TypeListServiceImpl.save]新增设备失败");
            throw new BizException("新增设备失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("[TypeListServiceImpl.save]新增设备失败");
            throw new BizException("新增设备失败,请联系管理员");
        }
        return entity.getTl_Id();
    }

    @Override
    public TypeList selectByPrimaryKey(Integer id) {

        LOGGER.info("传入参数:" + id);

        return typeListMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByIds(List<Integer> ids) {
        LOGGER.info("[TypeListServiceImpl.deleteByIds][批量更改设备入参]" + ids);
        if (ids.size() == 0) {
            LOGGER.error("[TypeListServiceImpl.deleteByIds][传入设备ID不能为空！]");
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(TypeList entity) {
        
        LOGGER.info("[TypeListServiceImpl.update]入参" + entity);
        int count;
        try {
            count = typeListMapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            LOGGER.error("[TypeListServiceImpl.update]新增设备失败");
            throw new BizException("更新USB设备类型失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("[TypeListServiceImpl.update]新增设备失败");
            throw new BizException("更新USB设备类型失败,请联系管理员");
        }
        return true;
    }

    @Override
    public List<TypeList> findEntityByParam(TypeList entity) {
        LOGGER.info("[TypeListServiceImpl.findEntityByParam条件查询动态信息]入参" + entity);
        List<TypeList> typeListList = typeListMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(typeListList)) {
            LOGGER.info("[TypeListServiceImpl.findEntityByParam台账信息为空！]");
            return new ArrayList<TypeList>();
        }
        return typeListList;
    }

    @Override
    public List<TypeList> findEntityByParamFuzzy(TypeList entity) {
        LOGGER.info("[TypeListServiceImpl.findEntityByParamFuzzy]入参" + entity);
        List<TypeList> typeListList = typeListMapper.findEntityByParamFuzzy(entity);
        if (CollectionUtils.isEmpty(typeListList)) {
            LOGGER.info("[TypeListServiceImpl.findEntityByParamFuzzy！]");
            return new ArrayList<TypeList>();
        }
        return typeListList;
    }

    @Override
    public Pager<TypeList> findByParamPage(TypeList entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("end", limit);
        map.put("tl_Type", entity.getTl_Type());
        map.put("tl_Path", entity.getTl_Path());
        map.put("tl_Allow", entity.getTl_Allow());
        List<TypeList> typeListList = typeListMapper.findEntityByParamFuzzy(entity);
        List<TypeList> typeListList1 = typeListMapper.findByParamPage(map);
        return new Pager<>(start, limit, typeListList1, typeListList.size());
    }

    @Override
    public Boolean updateByIds(List<Integer> ids) {
        LOGGER.info("[TypeListServiceImpl.updateByIds][批量更改设备入参]" + ids);
        if (ids.size() == 0) {
            LOGGER.error("[TypeListServiceImpl.updateByIds][传入设备ID不能为空！]");
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
