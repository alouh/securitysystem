package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SMPlatform;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SMPlatformService;
import com.youotech.securitysystem.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 软件/补丁/漏洞安装明细
 * Created by HanJiafeng on 2017-11-27.
 */
@Controller
@RequestMapping("sMPlatform")
public class SMPlatformController {
    @Autowired
    private SMPlatformService sMPlatformService;

    /**
     * 展示软件/补丁/漏洞安装明细
     *
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("showSMPlatform")
    @ResponseBody
    public Pager<SMPlatform> showInstalled(HttpServletRequest request) throws BizException {
        Integer pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageIndex - 1) * pageSize;
        int end = pageSize;
        SMPlatform sMPlatform = new SMPlatform();

        return sMPlatformService.findByParamPage(sMPlatform, start, end);
    }

    /**
     * 手动维护 添加设备
     *
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("addPhoneNumber")
    @ResponseBody
    public Map<String, Object> addDevice(HttpServletRequest request) throws BizException {
        SMPlatform sMPlatform = new SMPlatform();
        String pn_Number = request.getParameter("pn_Number");
        sMPlatform.setPn_Number(pn_Number);
        sMPlatform.setPn_Date(new Date());
        sMPlatformService.save(sMPlatform);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 批量刪除设备
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("delDeviceByIds")
    @ResponseBody
    public Map<String, Object> delDeviceByIds(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<>();
        String sdId = request.getParameter("pn_Ids");
        String[] sdIds = sdId.split(",");
        Integer[] intTemp = new Integer[sdIds.length];
        for (int i = 0; i < sdIds.length; i++) {
            intTemp[i] = Integer.parseInt(sdIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        sMPlatformService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }
}
