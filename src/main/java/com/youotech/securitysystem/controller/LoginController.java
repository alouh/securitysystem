package com.youotech.securitysystem.controller;

import com.youotech.securitysystem.bo.SeUser;
import com.youotech.securitysystem.exception.BizException;
import com.youotech.securitysystem.service.SeUserService;
import com.youotech.securitysystem.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录
 * Created by chenzc on 2017-11-16.
 */
@Controller
@RequestMapping("landManage")
public class LoginController {

    @Autowired
    private SeUserService seUserService;

    /**
     * 用户登录
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public Map<String, Object> Login(HttpServletRequest request) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIn2zWqU7K/2qm5pOpq5bp9R+3MTnStWTfJU9nC/Vo7UKH9dITPvrELCTK+qlqpx5Fes+l0GY7n6u4n4jyiw4ejsvkZYQ5ww477yLOn2FcoEGuZEwPgSCmfTST0OFUgQqn+/J11k9L92jEHyieE3qmhMkMt0UsVUSJwx/nZxo30ZAgMBAAECgYBD3YHigeuEC4R+14iaf8jo2j0kuGtB3Cxvnlez0otTqw1YyYkBsU49cLKkXvfKVEgM0Ow/QltgKvSBxCE31PrrDka5TygVMqqA/IM7NrDvjUcGLjyoeNmLA8660fWcDxUTlAGN5kxIvUATayVwKVflpWPWu0FPKsWrZustnEo+4QJBAMCmYsWqAKWYMVRXFP3/XGRfio8DV793TOckyBSN9eh8UhgoZyT3u7oeHmDJEwm4aNMHlg1Pcdc6tNsvi1FRCiUCQQC3VNzfF4xOtUgX7vWPL8YVljLuXmy12iVYmg6ofu9l31nwM9FLQ1TRFglvF5LWrIXTQb07PgGd5DJMAQWGsqLlAkAPE7Z9M73TN+L8b8hDzJ1leZi1cpSGdoa9PEKwYR/SrxAZtefEm+LEQSEtf+8OfrEtetWCeyo0pvKKiOEFXytFAkEAgynL/DC0yXsZYUYtmYvshHU5ayFTVagFICbYZeSrEo+BoUDxdI9vl0fU6A5NmBlGhaZ65G+waG5jLc1tTrlvoQJAXBEoPcBNAosiZHQfYBwHqU6mJ9/ZacJh3MtJzGGebfEwJgtln5b154iANqNWXpySBLvkK+Boq7FYRiD83pqmUg==";
        String passWord = RSAUtils.decryptDataOnJava(password, PRIVATEKEY);
        //String passWord = request.getParameter("passWord");
        SeUser seUser = new SeUser();
        seUser.setUserName(userName);
        List<SeUser> seUserList = seUserService.findEntityByParam(seUser);
        if (seUserList.size() < 1) {
            map.put("msg", "用户名不存在");
            map.put("success", false);
        } else {
            seUser.setPassWord(passWord);
            List<SeUser> seUserList1 = seUserService.findEntityByParam(seUser);
            if (seUserList1.size() < 0) {
                map.put("msg", "密码错误");
                map.put("success", false);
            } else if (seUserList1.size() == 1) {
                session.setAttribute("userName", userName);
                session.setAttribute("userId", seUserList1.get(0).getUserId());
                session.setMaxInactiveInterval(1800);
                map.put("success", true);
                map.put("userName", userName);
                map.put("passWord", passWord);
                map.put("userId", seUserList1.get(0).getUserId());
            } else {
                map.put("msg", "该用户存在异常，请联系管理员");
                map.put("success", false);
            }
        }

        return map;
    }

    /**
     * 退出登陆，并销毁session
     *
     * @param request
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public Boolean logout(HttpServletRequest request) throws Exception {
        String userName = request.getParameter("userName");
        if (userName != null) {
            request.getSession(false).removeAttribute("userName");
        }
        return true;
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     * @throws BizException
     */
    @RequestMapping("updatePwd")
    @ResponseBody
    public Map<String, Object> updatePwd(HttpServletRequest request) throws BizException {

        String newPassword = request.getParameter("newPassword");
        String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIn2zWqU7K/2qm5pOpq5bp9R+3MTnStWTfJU9nC/Vo7UKH9dITPvrELCTK+qlqpx5Fes+l0GY7n6u4n4jyiw4ejsvkZYQ5ww477yLOn2FcoEGuZEwPgSCmfTST0OFUgQqn+/J11k9L92jEHyieE3qmhMkMt0UsVUSJwx/nZxo30ZAgMBAAECgYBD3YHigeuEC4R+14iaf8jo2j0kuGtB3Cxvnlez0otTqw1YyYkBsU49cLKkXvfKVEgM0Ow/QltgKvSBxCE31PrrDka5TygVMqqA/IM7NrDvjUcGLjyoeNmLA8660fWcDxUTlAGN5kxIvUATayVwKVflpWPWu0FPKsWrZustnEo+4QJBAMCmYsWqAKWYMVRXFP3/XGRfio8DV793TOckyBSN9eh8UhgoZyT3u7oeHmDJEwm4aNMHlg1Pcdc6tNsvi1FRCiUCQQC3VNzfF4xOtUgX7vWPL8YVljLuXmy12iVYmg6ofu9l31nwM9FLQ1TRFglvF5LWrIXTQb07PgGd5DJMAQWGsqLlAkAPE7Z9M73TN+L8b8hDzJ1leZi1cpSGdoa9PEKwYR/SrxAZtefEm+LEQSEtf+8OfrEtetWCeyo0pvKKiOEFXytFAkEAgynL/DC0yXsZYUYtmYvshHU5ayFTVagFICbYZeSrEo+BoUDxdI9vl0fU6A5NmBlGhaZ65G+waG5jLc1tTrlvoQJAXBEoPcBNAosiZHQfYBwHqU6mJ9/ZacJh3MtJzGGebfEwJgtln5b154iANqNWXpySBLvkK+Boq7FYRiD83pqmUg==";
        String passWord = RSAUtils.decryptDataOnJava(newPassword, PRIVATEKEY);
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        SeUser seUser = new SeUser();
        seUser.setPassWord(passWord);
        seUser.setUserId(userId);
        seUserService.update(seUser);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }
}
