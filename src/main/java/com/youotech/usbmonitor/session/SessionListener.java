package com.youotech.usbmonitor.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author sundongyang
 * @date 2017/9/29 0029
 * @time 10:59
 * @desc
 * @see
 */
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {

    }

    public void sessionDestroyed(HttpSessionEvent event) {

        //在session销毁的时候 把loginMap中保存的键值对清除
        String userName = event.getSession().getAttribute("userName").toString();
        if (userName != null) {
            Map<String, String> loginMap = (Map<String, String>) event.getSession().getServletContext().getAttribute("loginMap");
            loginMap.remove(userName);
            event.getSession().getServletContext().setAttribute("loginMap", loginMap);
            // System.out.println("用户注销：" + loginMap);
        }
    }
}
