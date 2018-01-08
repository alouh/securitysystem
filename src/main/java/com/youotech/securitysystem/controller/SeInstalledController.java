package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeInstalled;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeDeviceService;
import com.youotech.securitysystem.service.SeInstalledService;
import com.youotech.securitysystem.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 软件/补丁/漏洞安装明细
 * Created by chenzc on 2017-11-27.
 */
@Controller
@RequestMapping("seInstalled")
public class SeInstalledController {
    @Autowired
    private SeInstalledService seInstalledService;

    @Autowired
    private SeDeviceService seDeviceService;

    /**
     * 展示软件/补丁/漏洞安装明细
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showInstalled")
    @ResponseBody
    public Pager<SeInstalled> showInstalled(HttpServletRequest request) throws BizException,IOException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        /*String siSname = URLDecoder.decode(request.getParameter("siSname"), "UTF-8");*/
        SeInstalled seInstalled = new SeInstalled();
        /*if (StringUtils.isNotBlank(siSname)) {
            seInstalled.setSiSname(siSname);
        }*/

        return seInstalledService.findByParamPage(seInstalled, start, end);
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
        SeInstalled seInstalled = new SeInstalled();
        String siSname = request.getParameter("siSname");
        seInstalled.setSiSname(siSname);
        seInstalled.setSiDate(new Date());
        seInstalledService.save(seInstalled);
        Map<String, Object> map = new HashMap<>();
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
        Map<String, Object> map = new HashMap<>();
        String sdId = request.getParameter("siIds");
        String[] sdIds = sdId.split(",");
        Integer[] intTemp = new Integer[sdIds.length];
        for (int i = 0; i < sdIds.length; i++) {
            intTemp[i] = Integer.parseInt(sdIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        seInstalledService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }
}
