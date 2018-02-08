package com.youotech.usbmonitor.controller;

import com.youotech.usbmonitor.bo.SeUser;
import com.youotech.usbmonitor.exception.BizException;
import com.youotech.usbmonitor.service.SeUserService;
import com.youotech.usbmonitor.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 用户管理
 * Created by chenzc on 2017-11-16.
 */
@Controller
@RequestMapping("userManger")
public class UserMangerController {
    @Autowired
    private SeUserService seUserService;

    /**
     * 展示用户列表
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("showUser")
    @ResponseBody
    public Pager<SeUser> showUser(HttpServletRequest request) throws BizException {
        Integer pageindex = Integer.valueOf(request.getParameter("pageindex"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int start = (pageindex - 1) * pageSize;
        int end = pageSize;
        SeUser seUser = new SeUser();
        return seUserService.findByParamPage(seUser, start, end);
    }

    /**
     * 添加用户
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(HttpServletRequest request) throws BizException {
        String userName = request.getParameter("userName");
        String realName = request.getParameter("realName");
      /*  Integer sex = Integer.valueOf(request.getParameter("sex"));*/
        SeUser seUser = new SeUser();
        seUser.setPassWord("1e9e9f6fef3369cdc763284d80ae5feb1q2w3e4r!");
        seUser.setUserName(userName);
        Map<String, Object> map = new HashMap<String, Object>();
        seUserService.save(seUser);
        map.put("success", true);
        return map;
    }

    /**
     * 批量刪除人员
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("delUserByIds")
    @ResponseBody
    public Map<String, Object> delUserByIds(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        String userId = request.getParameter("userIds");
        String[] userIds = userId.split(",");
        Integer[] intTemp = new Integer[userIds.length];
        for (int i = 0; i < userIds.length; i++) {
            intTemp[i] = Integer.parseInt(userIds[i]);
        }
        List<Integer> ids = Arrays.asList(intTemp);
        seUserService.deleteByIds(ids);
        map.put("success", true);
        return map;
    }
}
