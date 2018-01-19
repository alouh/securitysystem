package com.youotech.usbmonitor.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截请求
 * @author chenzc
 * @date 2017/11/18
 * @time 09:48
 * @desc
 * @see
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("landManage") >= 0) {
            return true;
        }

     /*   if (requestURI.contains("ydqq")) {
            return true;
        }

        if (requestURI.contains("download")) {
            return true;
        }

        if (requestURI.contains("upload")) {
            return true;
        }*/

        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("userName");
        if (StringUtils.isNotBlank(userName)) {
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);

        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
