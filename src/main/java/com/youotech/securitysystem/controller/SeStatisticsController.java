package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeResult;
import com.youotech.securitysystem.bo.SeStatistics;
import com.youotech.securitysystem.bo.StatisticsCount;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeResultService;
import com.youotech.securitysystem.service.SeStatisticsService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安全分析结果统计
 * Created by chenzc on 2017-11-28.
 */
@Controller
@RequestMapping("seStatistics")
public class SeStatisticsController {
    @Autowired
    private SeStatisticsService seStatisticsService;
    @Autowired
    private SeResultService seResultService;

    /**
     * 安全分析统计图
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showStatisticsView")
    @ResponseBody
    public List<Object> showStatisticsView(HttpServletRequest request) throws BizException, ParseException,IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdType = URLDecoder.decode(request.getParameter("sdType"), "UTF-8");
        String startDate1 = URLDecoder.decode(request.getParameter("startDate"), "UTF-8");
        String endDate1 = URLDecoder.decode(request.getParameter("endDate"), "UTF-8");
        Date startDate = null;
        if (StringUtils.isNotBlank(startDate1)) {
            startDate = sdf.parse(startDate1);
        } else {
            startDate = sdf.parse("1990-12-12");
        }
        Date endDate = null;
        if (StringUtils.isNotBlank(endDate1)) {
            endDate = sdf.parse(endDate1);
        } else {
            endDate = new Date();
        }
        List<Object> list = new ArrayList<Object>();
        List<SeStatistics> seStatisticsList = seStatisticsService.findEntityByParamPlus(0, sdType, startDate, endDate);
        List<SeStatistics> seStatisticsList2 = seStatisticsService.findEntityByParamPlus(1, sdType, startDate, endDate);
        List<SeStatistics> seStatisticsList3 = seStatisticsService.findEntityByParamPlus(2, sdType, startDate, endDate);
        List<SeStatistics> seStatisticsList4 = seStatisticsService.findEntityByParamPlus(3, sdType, startDate, endDate);
        list.add(seStatisticsList);
        list.add(seStatisticsList2);
        list.add(seStatisticsList3);
        list.add(seStatisticsList4);
        return list;
    }

    /**
     * 安全分析统计列表
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showStatistics")
    @ResponseBody
    public Pager<StatisticsCount> showStatistics(HttpServletRequest request) throws BizException , ParseException,IOException{
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdType = URLDecoder.decode(request.getParameter("sdType"), "UTF-8");
        String startDate1 = URLDecoder.decode(request.getParameter("startDate"), "UTF-8");
        String endDate1 = URLDecoder.decode(request.getParameter("endDate"), "UTF-8");
        Date startDate = null;
        if (StringUtils.isNotBlank(startDate1)) {
            startDate = sdf.parse(startDate1);
        } else {
            startDate = sdf.parse("1990-12-12");
        }
        Date endDate = null;
        if (StringUtils.isNotBlank(endDate1)) {
            endDate = sdf.parse(endDate1);
        } else {
            endDate = new Date();
        }
        Pager<StatisticsCount> statisticsCountPager = seStatisticsService.findreversalByParamPager(sdType, startDate, endDate,start,end);
        return  statisticsCountPager;
    }
    /**
     * 安全分析统计详情
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
        String sdType1 = URLDecoder.decode(request.getParameter("sdType1"));
        String srTypes=URLDecoder.decode(request.getParameter("srType"), "UTF-8");
        String sdIp=URLDecoder.decode(request.getParameter("sdIp"), "UTF-8");
        String srRname=URLDecoder.decode(request.getParameter("srRname"), "UTF-8");
        Integer srType = 0;
        String sdType=null;
        if (StringUtils.isNotBlank(sdType1)) {
            sdType = URLDecoder.decode(request.getParameter("sdType"), "UTF-8");
        }
        if (StringUtils.isNotBlank(srTypes)) {
            srType = Integer.valueOf(srTypes);
        }
        String scDate = URLDecoder.decode(request.getParameter("scDate"), "UTF-8");
        Date stDate=null;
        if (StringUtils.isNotBlank(scDate)) {
            stDate = sdf.parse(scDate);
        }
        Pager<SeResult> seResultPager = seResultService.findDetailStatisticsPager(sdType, srType, stDate,sdIp,srRname,start,end);
        return  seResultPager;
    }

}
