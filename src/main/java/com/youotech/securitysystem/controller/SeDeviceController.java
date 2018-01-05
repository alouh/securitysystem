package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeDeviceService;
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
 * 台账管理
 * Created by chenzc on 2017-11-23.
 */
@Controller
@RequestMapping("seDevice")
public class SeDeviceController {
    @Autowired
    private SeDeviceService seDeviceService;

    /**
     * 展示台账管理列表
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showSeDevice")
    @ResponseBody
    public Pager<SeDevice> showSeDevice(HttpServletRequest request) throws BizException,IOException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String sdIp = URLDecoder.decode(request.getParameter("sdIp"));
        String sdType = URLDecoder.decode(request.getParameter("sdType"));
        String sdType1 = request.getParameter("sdType1");
        String sdUser = URLDecoder.decode(request.getParameter("sdUser"));
        SeDevice seDevice = new SeDevice();
        seDevice.setSdRemarks(0);//有效的
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        if (StringUtils.isNotBlank(sdIp)) {
            seDevice.setSdIp(URLDecoder.decode(request.getParameter("sdIp"), "UTF-8"));
        }
        if (StringUtils.isNotBlank(sdType1)) {
            seDevice.setSdType(URLDecoder.decode(request.getParameter("sdType"), "UTF-8"));
        }
        if (StringUtils.isNotBlank(sdUser)) {
            seDevice.setSdUser(URLDecoder.decode(request.getParameter("sdUser"), "UTF-8"));
        }
        Pager<SeDevice> seUserPager = seDeviceService.findByParamPage(seDevice, start, end);
        return seUserPager;
    }

    /**
     * 手动维护 添加设备
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("addDevice")
    @ResponseBody
    public Map<String, Object> addDevice(HttpServletRequest request) throws BizException {
        SeDevice seDevice = new SeDevice();
        String sdName = request.getParameter("sdName");
        String sdType = request.getParameter("sdType");
        String sdOs = request.getParameter("sdOs");
        String sdOsType = request.getParameter("sdOsType");
        String sdMac = request.getParameter("sdMac");
        String sdDept = request.getParameter("sdDept");
        String sdUser = request.getParameter("sdUser");
        String sdIp = request.getParameter("sdIp");
        seDevice.setSdRemarks(0);
        seDevice.setSdUser(sdUser);
        seDevice.setSdType(sdType);
        seDevice.setSdIp(sdIp);
        seDevice.setSdName(sdName);
        seDevice.setSdDept(sdDept);
        seDevice.setSdSource("手动维护");
        seDevice.setSdDate(new Date());
        if (StringUtils.isNotBlank(sdOs)) {
            seDevice.setSdOs(sdOs);
        }else {
            seDevice.setSdOs("无");
        }
        if (StringUtils.isNotBlank(sdOsType)) {
            seDevice.setSdOstype(sdOsType);
        }else {
            seDevice.setSdOstype("无");
        }
        if (StringUtils.isNotBlank(sdMac)) {
            seDevice.setSdMac(sdMac);
        }else {
            seDevice.setSdMac("00-00-00-00-00");
        }
        seDeviceService.save(seDevice);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    /**
     * 批量刪除设备（伪删除）
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("delDeviceByIds")
    @ResponseBody
    public Map<String, Object> delDeviceByIds(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        String sdId = request.getParameter("sdIds");
        String[] sdIds = sdId.split(",");
        Integer[] intTemp = new Integer[sdIds.length];
        for (int i = 0; i < sdIds.length; i++) {
            intTemp[i] = Integer.parseInt(sdIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        seDeviceService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }

}