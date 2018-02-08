package com.youotech.usbmonitor.controller;

import com.youotech.usbmonitor.bo.SMPlatform;
import com.youotech.usbmonitor.exception.BizException;
import com.youotech.usbmonitor.service.SMPlatformService;
import com.youotech.usbmonitor.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SMPlatformController.class);

    /**
     * 展示短信平台号码
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("showSMPlatform")
    @ResponseBody
    public Pager<SMPlatform> showPhoneNumber(HttpServletRequest request) throws BizException {
        Integer pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageIndex - 1) * pageSize;
        int end = pageSize;
        SMPlatform sMPlatform = new SMPlatform();

        return sMPlatformService.findByParamPage(sMPlatform, start, end);
    }

    /**
     * 添加短信平台号码
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("addPhoneNumber")
    @ResponseBody
    public Map<String, Object> addPhoneNumber(HttpServletRequest request) throws BizException {

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
     * 修改短信平台号码
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("getUpdateDate")
    @ResponseBody
    public Map<String, Object> getUpdateDate(HttpServletRequest request) throws BizException {

        Map<String, Object> map = new HashMap<>();
        try {
            String idStr = request.getParameter("selectedId");
            int idInt = Integer.valueOf(idStr);
            SMPlatform smPlatform = sMPlatformService.selectByPrimaryKey(idInt);

            map.put("success", true);
            map.put("number", smPlatform.getPn_Number() );
        }catch (Exception e){
            LOGGER.error("获取编辑数据失败");
            map.put("success",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    /**
     * 修改短信平台号码
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("updatePhoneNumber")
    @ResponseBody
    public Map<String, Object> updatePhoneNumber(HttpServletRequest request) throws BizException {
        SMPlatform sMPlatform = new SMPlatform();
        String idStr = request.getParameter("selectedId");
        String pn_Number = request.getParameter("pn_Number");
        int idInt = Integer.valueOf(idStr);
        sMPlatform.setPn_Id(idInt);
        sMPlatform.setPn_Number(pn_Number);
        sMPlatformService.update(sMPlatform);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 批量刪除短信平台号码
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
