package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeRules;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeRulesService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 安全审计规则
 * Created by chenzc on 2017-11-27.
 */
@Controller
@RequestMapping("seRules")
public class SeRulesController {

    @Autowired
    private SeRulesService seRulesService;

    /**
     * 展示安全审计规则
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showRules")
    @ResponseBody
    public Pager<SeRules> showRules(HttpServletRequest request) throws BizException,IOException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        String srType = request.getParameter("srType");
        String sdType = URLDecoder.decode(request.getParameter("sdType"));
        String sdType1 = URLDecoder.decode(request.getParameter("sdType1"));
        SeRules seRules = new SeRules();
        if (StringUtils.isNotBlank(srType)) {
            seRules.setSrType(Integer.valueOf(srType));
        }
        if (StringUtils.isNotBlank(sdType1)) {
            seRules.setSdType(URLDecoder.decode(request.getParameter("sdType"), "UTF-8"));
        }
        Pager<SeRules> seRulesPager = seRulesService.findByParamPage(seRules, start, end);
        return seRulesPager;
    }

    /**
     * 新增审计规则
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("addRules")
    @ResponseBody
    public Map<String, Object> addRules(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        String sdType = request.getParameter("sdType");
        String sdOsType = request.getParameter("sdOsType");
        String sdOs = request.getParameter("sdOs");
        String srType = request.getParameter("srType");
        String srRules = request.getParameter("srRules");
        String srRname = request.getParameter("srRname");

        SeRules seRules = new SeRules();
        seRules.setSdType(sdType);
        seRules.setSdOstype(sdOsType);

        if (StringUtils.isNotBlank(srType)) {
            seRules.setSrType(Integer.valueOf(srType));
        }
        if (StringUtils.isNotBlank(srRules)) {
            seRules.setSrRules(Integer.valueOf(srRules));
        }
        if (StringUtils.isNotBlank(sdOs)) {
            seRules.setSdOs(sdOs);
        } else {
            seRules.setSdOs("无");
        }
        if (StringUtils.isNotBlank(sdOsType)) {
            seRules.setSdOstype(sdOsType);
        } else {
            seRules.setSdOstype("无");
        }
        seRules.setSrRname(srRname);
        seRules.setSrDate(new Date());
        seRulesService.save(seRules);
        map.put("success", true);
        return map;
    }

    /**
     * 修改审计规则
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("updateRules")
    @ResponseBody
    public Map<String, Object> updateRules(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        String srId = request.getParameter("srId");
        String sdType = request.getParameter("sdType");
        String sdOsType = request.getParameter("sdOsType");
        String sdOs = request.getParameter("sdOs");
        String srType = request.getParameter("srType");
        String srRules = request.getParameter("srRules");
        String srRname = request.getParameter("srRname");

        SeRules seRules = new SeRules();
        seRules.setSdType(sdType);
        seRules.setSdOstype(sdOsType);
        seRules.setSdOs(sdOs);
        if (StringUtils.isNotBlank(srId)) {
            seRules.setSrId(Integer.valueOf(srId));
        }
        if (StringUtils.isNotBlank(srType)) {
            seRules.setSrType(Integer.valueOf(srType));
        }
        if (StringUtils.isNotBlank(srRules)) {
            seRules.setSrRules(Integer.valueOf(srRules));
        }
        seRules.setSrRname(srRname);
        seRules.setSrDate(new Date());
        seRulesService.update(seRules);
        map.put("success", true);
        return map;
    }

    /**
     * 批量刪除
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("delRulesByIds")
    @ResponseBody
    public Map<String, Object> delRulesByIds(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        String srId = request.getParameter("srIds");
        String[] srIds = srId.split(",");
        Integer[] intTemp = new Integer[srIds.length];
        for (int i = 0; i < srIds.length; i++) {
            intTemp[i] = Integer.parseInt(srIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        seRulesService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }
}
