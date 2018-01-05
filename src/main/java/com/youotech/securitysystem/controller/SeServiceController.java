package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SeServer;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeDeviceService;
import com.youotech.securitysystem.service.SeServerService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 网络设备服务开放详情
 * Created by chenzc on 2017-11-24.
 */
@Controller
@RequestMapping("seServer")
public class SeServiceController {
    @Autowired
    private SeServerService seServerService;

    @Autowired
    private SeDeviceService seDeviceService;

    /**
     * 展示台账管理列表
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showServer")
    @ResponseBody
    public Pager<SeServer> showSeDevice(HttpServletRequest request) throws BizException,ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
      /*  String sdIp = request.getParameter("sdIp");*/
        String type = request.getParameter("type");
        String svDates = URLDecoder.decode(request.getParameter("svDate"));
        SeServer seServer = new SeServer();
        if ("1".equals(type)) {
            seServer.setSvHttp(1);
        } else if ("2".equals(type)) {
            seServer.setSvHttp(0);
        } else if ("3".equals(type)) {
            seServer.setSvSsh(1);
        } else if ("4".equals(type)) {
            seServer.setSvSsh(0);
        } else if ("5".equals(type)) {
            seServer.setSvTelnet(0);
        } else if ("6".equals(type)) {
            seServer.setSvTelnet(1);
        }
        Date svDate=null;
        if(StringUtils.isNotBlank(svDates)){
            svDate=sdf.parse(svDates);
            seServer.setSvDate(svDate);
        }
        Pager<SeServer> seServerPager = seServerService.findByParamPagePlus(seServer, start, end,svDates);
        for (SeServer server1 : seServerPager.getRows()) {
            SeDevice seDevice = new SeDevice();
            seDevice.setSdId(server1.getSdId());
            List<SeDevice> seDeviceList = seDeviceService.findEntityByParam(seDevice);
            if(seDeviceList.size()==1) {
                /*server1.setSdName(seDeviceList.get(0).getSdName());
                server1.setSdIp(seDeviceList.get(0).getSdIp());
                server1.setSdUser(seDeviceList.get(0).getSdUser());*/
            }
        }
        return seServerPager;
    }
}
