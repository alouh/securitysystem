package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.TypeList;
import com.youotech.securitysystem.bo.SeUser;
import com.youotech.securitysystem.dao.SeUserMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeUserService;
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
 * 用户管理
 * Created by chenzc on 2017-11-16.
 */
@Service
public class SeUserServiceImpl implements SeUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeUserServiceImpl.class);

    @Autowired
    private SeUserMapper seUserMapper;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeUser entity) {
        LOGGER.info("【SeUserServiceImpl.save】入参" + entity);
        int count = 0;
        SeUser seUser = new SeUser();
        seUser.setUserName(entity.getUserName());
        List<SeUser> seUserList = seUserMapper.findByParam(seUser);
        if (seUserList.size() > 0) {
            LOGGER.error("【SeUserServiceImpl.save】新增用户失败");
            throw new BizException("用户名已存在，请重新输入");
        }
        try {
            count = seUserMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeUserServiceImpl.save】新增用户失败");
            throw new BizException("新增人员失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeUserServiceImpl.save】新增用户失败");
            throw new BizException("新增人员失败,请联系管理员");
        }
        return entity.getUserId();

    }

    public List<SeUser> selectByPrimaryKey(Integer id) {
        return null;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean deleteByIds(List<Integer> ids) {
        LOGGER.info("【SeUserServiceImpl.deleteByIds】【批量删除人员入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【SeUserServiceImpl.deleteByIds】【传入人员ID不能为空！】");
            return false;
        }
        try {
            seUserMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量删除人员失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除人员删除失败，请联系管理员。");
        }
        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(SeUser entity) {
        LOGGER.info("【SeUserServiceImpl.update】入参" + entity);
        if (null == entity.getUserId()) {
            LOGGER.error("【SeUserServiceImpl.update】人员id不能为空");
            throw new BizException("修改失败！请联系管理员");
        }
        Integer count = 0;
        try {
            count = seUserMapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeUserServiceImpl.update】修改失败");
            throw new BizException("修改失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeUserServiceImpl.update】修改失败");
            throw new BizException("修改失败！请联系管理员");
        }


        return true;
    }

    public List<SeUser> findEntityByParam(SeUser entity) {
        LOGGER.info("【SeUserServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeUser> seUserList = seUserMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seUserList)) {
            LOGGER.info("【SeUserServiceImpl.findEntityByParam新闻信息为空！】");
            return new ArrayList<SeUser>();
        }
        return seUserList;
    }

    @Override
    public List<TypeList> findEntityByParamFuzzy(TypeList entity) {
        return null;
    }

    public Pager<SeUser> findByParamPage(SeUser entity, Integer start, Integer end) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", end);
        List<SeUser> seUserList = seUserMapper.findByParam(entity);
        List<SeUser> seUserList1 = seUserMapper.findByParamPage(map);
        Pager<SeUser> seUserPager = new Pager<SeUser>(start, end, seUserList1, seUserList.size());
        return seUserPager;
    }
}
