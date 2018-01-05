package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeRules;
import com.youotech.securitysystem.dao.SeRulesMapper;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeRulesService;
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
 * 安全审计规则
 * Created by chenzc on 2017-11-23.
 */
@Service("seRulesService")
public class SeRulesServiceImpl implements SeRulesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeRulesServiceImpl.class);

    @Autowired
    private SeRulesMapper seRulesMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Integer save(SeRules entity) {
        LOGGER.info("【SeRulesServiceImpl.save】入参" + entity);
        int count = 0;
        try {
            count = seRulesMapper.insertSelective(entity);
        } catch (Exception e) {
            LOGGER.error("【SeRulesServiceImpl.save】新增安全审计规则失败");
            throw new BizException("新增安全审计规则失败！请联系管理员");
        }
        if (count != 1) {
            LOGGER.error("【SeRulesServiceImpl.save】新增安全审计规则失败");
            throw new BizException("新增安全审计规则失败,请联系管理员");
        }
        return entity.getSrId();
    }

    @Override
    public List<SeRules> selectByPrimaryKey(Integer id) {
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
        LOGGER.info("【SeRulesServiceImpl.deleteByIds】【批量删除审计规则入参】" + ids);
        if (ids.size() == 0) {
            LOGGER.error("【SeRulesServiceImpl.deleteByIds】【传入ID不能为空！】");
            return false;
        }
        try {
            seRulesMapper.deleteByIds(ids);
        } catch (Exception e) {
            LOGGER.error("SeUserServiceImpl.deleteByIds--批量删除审计规则失败，请联系管理员。" + e.getCause());
            throw new BizException("批量删除审计规则删除失败，请联系管理员。");
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {BizException.class, Exception.class})
    public Boolean update(SeRules entity) {
        LOGGER.info("【SeRulesServiceImpl.update】【修改审计规则入参】" + entity);
        Integer count = 0;
        try {
            count = seRulesMapper.updateByPrimaryKey(entity);
        } catch (Exception e) {
            throw new BizException("审计规则更新失败，请联系管理员");
        }
        if (count != 1) {
            throw new BizException("审计规则更新失败，请联系管理员");
        }
        return true;
    }

    @Override
    public List<SeRules> findEntityByParam(SeRules entity) {
        LOGGER.info("【SeRulesServiceImpl.findEntityByParam条件查询动态信息】入参" + entity);
        List<SeRules> seRulesList = seRulesMapper.findByParam(entity);
        if (CollectionUtils.isEmpty(seRulesList)) {
            LOGGER.info("【SeResultServiceImpl.findEntityByParam安全审计规则信息为空！】");
            return new ArrayList<SeRules>();
        }
        return seRulesList;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SeRules> findByParamPage(SeRules entity, Integer start, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", limit);
        map.put("srType", entity.getSrType());
        map.put("sdType", entity.getSdType());
        List<SeRules> seRulesList1=seRulesMapper.findByParam(entity);
        List<SeRules> seRulesList = seRulesMapper.findByParamPage(map);
        Pager<SeRules> seRulesPager = new Pager<SeRules>(start, limit, seRulesList, seRulesList1.size());
        return seRulesPager;
    }

	/**
	 * 根据设备信息查找安全审计规则
	 * @return
	 */
	@Override
	public List<SeRules> findByDevice() {
		List<SeRules> seRulesList = seRulesMapper.findByDevice();
		if (CollectionUtils.isEmpty(seRulesList)) {
			LOGGER.info("【SeResultServiceImpl.findEntityByParam安全审计规则信息为空！】");
			return new ArrayList<>();
		}
		return seRulesList;
	}
}
