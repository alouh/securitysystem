package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.TypeList;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.TypeListService;
import com.youotech.securitysystem.utils.Pager;
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
@RequestMapping("typeList")
public class TypeListController {
    @Autowired
    private TypeListService typeListService;

    /**
     * 展示台账管理列表
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showTypeList")
    @ResponseBody
    public Pager<TypeList> showTypeList(HttpServletRequest request) throws BizException,IOException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        TypeList typeList = new TypeList();
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        return typeListService.findByParamPage(typeList, start, end);
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
        TypeList typeList = new TypeList();
        String sdType = request.getParameter("sdType");
        typeList.setSdType(sdType);
        typeList.setSdDate(new Date());
        typeListService.save(typeList);
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
        String sdId = request.getParameter("sdIds");
        String[] sdIds = sdId.split(",");
        Integer[] intTemp = new Integer[sdIds.length];
        for (int i = 0; i < sdIds.length; i++) {
            intTemp[i] = Integer.parseInt(sdIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        typeListService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }

}
