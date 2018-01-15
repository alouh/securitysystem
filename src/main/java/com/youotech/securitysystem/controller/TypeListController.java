package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.TypeList;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.TypeListService;
import com.youotech.securitysystem.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 台账管理
 * Created by HanJiafeng on 2018-01-12.
 */
@Controller
@RequestMapping("typeList")
public class TypeListController {
    @Autowired
    private TypeListService typeListService;

    /**
     * 展示台账管理列表
     *
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("showTypeList")
    @ResponseBody
    public Pager<TypeList> showTypeList(HttpServletRequest request) throws BizException {
        Integer pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageIndex - 1) * pageSize;
        int end = pageSize;
        TypeList typeList = new TypeList();
        return typeListService.findByParamPage(typeList, start, end);
    }

    /**
     * 手动维护 添加设备
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("addType")
    @ResponseBody
    public Map<String, Object> addType(HttpServletRequest request) throws BizException {
        TypeList typeList = new TypeList();
        String tl_Type = request.getParameter("tl_Type");
        String tl_Path = request.getParameter("tl_Path");
        String tl_Allow = request.getParameter("tl_Allow");
        typeList.setTl_Type(tl_Type);
        typeList.setTl_Path(tl_Path);
        typeList.setTl_Allow(tl_Allow);
        typeList.setTl_Date(new Date());

        typeListService.save(typeList);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    /**
     * 修改USB设备类型
     * @param request 请求
     * @return page
     * @throws BizException 异常
     */
    @RequestMapping("updateTypeList")
    @ResponseBody
    public Map<String, Object> updateTypeList(HttpServletRequest request) throws BizException {

        TypeList typeList = new TypeList();

        String tl_IdStr = request.getParameter("selectedId");
        String tl_Type = request.getParameter("tl_Type");
        String tl_Path = request.getParameter("tl_Path");
        String tl_Allow = request.getParameter("tl_Allow");
        int tl_Id = Integer.valueOf(tl_IdStr);

        typeList.setTl_Id(tl_Id);

        if (StringUtils.isNotBlank(tl_Type)){
            typeList.setTl_Type(tl_Type);
        }
        if (StringUtils.isNotBlank(tl_Path)){
            typeList.setTl_Path(tl_Path);
        }
        if (StringUtils.isNotBlank(tl_Allow)){
            typeList.setTl_Allow(tl_Allow);
        }

        typeListService.update(typeList);
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
    @RequestMapping("delTypeByIds")
    @ResponseBody
    public Map<String, Object> delTypeByIds(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<>();
        String sdId = request.getParameter("tl_Ids");
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
