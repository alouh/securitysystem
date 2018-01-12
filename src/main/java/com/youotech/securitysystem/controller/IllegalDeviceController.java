package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.IllegalDevice;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.IllegalDeviceService;
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
@RequestMapping("illegalDevice")
public class IllegalDeviceController {

    @Autowired
    private IllegalDeviceService illegalDeviceService;

    /**
     * 展示安全审计规则
     *
     * @param request 请求
     * @return page
     * @throws BizException
     */
    @RequestMapping("showRules")
    @ResponseBody
    public Pager<IllegalDevice> showRules(HttpServletRequest request) throws BizException,IOException {
        Integer pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageIndex - 1) * pageSize;
        int end = pageSize;
        String srType = request.getParameter("srType");
        String sdType1 = URLDecoder.decode(request.getParameter("sdType1"), "UTF-8");
        IllegalDevice illegalDevice = new IllegalDevice();
        if (StringUtils.isNotBlank(srType)) {
            illegalDevice.setSrType(srType);
        }
        if (StringUtils.isNotBlank(sdType1)) {
            illegalDevice.setSdType(URLDecoder.decode(request.getParameter("sdType"), "UTF-8"));
        }
        return illegalDeviceService.findByParamPage(illegalDevice, start, end);
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

        IllegalDevice illegalDevice = new IllegalDevice();
        illegalDevice.setSdType(sdType);
        illegalDevice.setSdOstype(sdOsType);

        if (StringUtils.isNotBlank(srType)) {
            /*illegalDevice.setSrType(Integer.valueOf(srType));*/
            illegalDevice.setSrType(srType);
        }
        if (StringUtils.isNotBlank(srRules)) {
            illegalDevice.setSrRules(Integer.valueOf(srRules));
        }
        if (StringUtils.isNotBlank(sdOs)) {
            illegalDevice.setSdOs(sdOs);
        } else {
            illegalDevice.setSdOs("无");
        }
        if (StringUtils.isNotBlank(sdOsType)) {
            illegalDevice.setSdOstype(sdOsType);
        } else {
            illegalDevice.setSdOstype("无");
        }
        illegalDevice.setSrRname(srRname);
        illegalDevice.setSrDate(new Date());
        illegalDeviceService.save(illegalDevice);
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

        IllegalDevice illegalDevice = new IllegalDevice();
        illegalDevice.setSdType(sdType);
        illegalDevice.setSdOstype(sdOsType);
        illegalDevice.setSdOs(sdOs);
        if (StringUtils.isNotBlank(srId)) {
            illegalDevice.setSrId(Integer.valueOf(srId));
        }
        if (StringUtils.isNotBlank(srType)) {
            /*illegalDevice.setSrType(Integer.valueOf(srType));*/
            illegalDevice.setSrType(srType);
        }
        if (StringUtils.isNotBlank(srRules)) {
            illegalDevice.setSrRules(Integer.valueOf(srRules));
        }
        illegalDevice.setSrRname(srRname);
        illegalDevice.setSrDate(new Date());
        illegalDeviceService.update(illegalDevice);
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
        illegalDeviceService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }
}
