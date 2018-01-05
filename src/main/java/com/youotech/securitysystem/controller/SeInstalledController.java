package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeInstalled;
import com.youotech.securitysystem.bo.SeResult;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeDeviceService;
import com.youotech.securitysystem.service.SeInstalledService;
import com.youotech.securitysystem.service.SeResultService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private SeResultService seResultService;

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
        String siType = URLDecoder.decode(request.getParameter("siType"), "UTF-8");
        String siType1 = request.getParameter("siType1");
        String siSname = URLDecoder.decode(request.getParameter("siSname"), "UTF-8");
        Integer sdId = Integer.valueOf(URLDecoder.decode(request.getParameter("sdId"), "UTF-8"));
        SeInstalled seInstalled = new SeInstalled();
        seInstalled.setSdId(sdId);
        if (StringUtils.isNotBlank(siSname)) {
            seInstalled.setSiSname(siSname);
        }
        if (StringUtils.isNotBlank(siType1)) {
            seInstalled.setSiType(siType);
        }
        Pager<SeInstalled> seInstalledPager = seInstalledService.findByParamPage(seInstalled, start, end);
        for (SeInstalled seInstalled1 : seInstalledPager.getRows()) {
            SeDevice seDevice = new SeDevice();
            seDevice.setSdId(seInstalled1.getSdId());
            List<SeDevice> seDeviceList = seDeviceService.findEntityByParam(seDevice);
            if (seDeviceList.size() > 0) {
                /*seInstalled1.setSdName(seDeviceList.get(0).getSdName());
                seInstalled1.setSdIp(seDeviceList.get(0).getSdIp());*/
            }
        }

        return seInstalledPager;
    }


    /**
     * 展示软件/补丁/漏洞数目
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showInstalledStatistics")
    @ResponseBody
    public Pager<SeResult> showInstalledStatistics(HttpServletRequest request) throws BizException, ParseException,IOException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        String siIp = URLDecoder.decode(request.getParameter("siIp"));
        String sdTypes = URLDecoder.decode(request.getParameter("sdType"));
        String sdType1 = URLDecoder.decode(request.getParameter("sdType1"));
        String stDates = URLDecoder.decode(request.getParameter("stDate"));
        Date stDate = null;
        String sdType = null;
        if (StringUtils.isNotBlank(stDates)) {
            stDate = sdf.parse(stDates);
        }
        if (StringUtils.isNotBlank(sdType1)) {
            sdType = URLDecoder.decode(request.getParameter("sdType"), "UTF-8");
        }
        Pager<SeResult> seResultPager = seResultService.findAllLoopPager(siIp, sdType, stDate, start, end);
        for (SeResult seResult : seResultPager.getRows()) {
            SeResult seResult1 = new SeResult();
            seResult1.setSdId(seResult.getSdId());
            seResult1.setSrType(2);
            seResult1.setStDate(seResult.getStDate());
            List<SeResult> seResultList = seResultService.findEntityByParam(seResult1);//查询软件异常数量
            seResult.setSoftwareCount(seResultList.size());
            seResult1.setSrType(3);
            List<SeResult> seResultList1 = seResultService.findEntityByParam(seResult1);//漏洞/补丁数量
            seResult.setLoopholeCount(seResultList1.size());
        }
        return seResultPager;
    }

    /**
     * 异常详情
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showDetailStatistics")
    @ResponseBody
    public Pager<SeResult> showDetailStatistics(HttpServletRequest request) throws BizException , ParseException,IOException{
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdTypes = request.getParameter("sdType");
        String sdType1 = request.getParameter("sdType1");
        String srTypes=request.getParameter("srType");
        String sdIds=request.getParameter("sdId");
        Integer srType = 0;
        Integer sdId=0;
        String sdType=null;
        if (StringUtils.isNotBlank(sdType1)) {
            sdType = URLDecoder.decode(request.getParameter("sdType"), "UTF-8");
        }
        if (StringUtils.isNotBlank(srTypes)) {
            srType = Integer.valueOf(srTypes);
        }
        if (StringUtils.isNotBlank(sdIds)) {
            sdId = Integer.valueOf(sdIds);
        }
        String scDate = request.getParameter("scDate");
        Date stDate=null;
        if (StringUtils.isNotBlank(scDate)) {
            stDate = sdf.parse(scDate);
        }
        Pager<SeResult> seResultPager = seResultService.showDetailStatisticsPager(sdType, srType, stDate,sdId,start,end);
        return  seResultPager;
    }
}
