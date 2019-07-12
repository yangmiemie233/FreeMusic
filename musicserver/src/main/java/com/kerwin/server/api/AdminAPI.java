package com.kerwin.server.api;


import com.kerwin.server.pojo.Admin;
import com.kerwin.server.server.impls.AdminServiceImpl;
import com.kerwin.server.utils.APIRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员的Controller层实现类
 * @Author liuyun
 * @Time    2019/07/11
 *
 */
@RestController
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class AdminAPI {

    private AdminServiceImpl adminService = new AdminServiceImpl();

    /**
     * 登录的方法
     * @param uname
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping(value = "/main/adminlogin.do",consumes ="application/x-www-form-urlencoded")
    public APIRequest login(String uname, String pwd, HttpSession session){
        if(uname == null || "".equals(uname) || pwd == null || "".equals(pwd)){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("账号或密码不为空");
            return apiRequest;
        }

        APIRequest apiRequest = adminService.login(uname,pwd);
        if(apiRequest.isResult()){

            Object admins = apiRequest.getData();
            session.setAttribute("admin",admins);

            Object admin = session.getAttribute("admin");
            System.out.println("login-admin:"+admin);

            apiRequest.setResult(true);
            apiRequest.setMsg("登录成功");

            return apiRequest;

        }else{
            return apiRequest;
        }
    }

    /**
     * 判断登录状态 获取用户信息的方法
     * @param session
     * @return
     */
    @RequestMapping(value = "/main/isLogin.do",consumes ="application/x-www-form-urlencoded")
    public APIRequest isLogin(HttpSession session){


        APIRequest apiRequest = new APIRequest();


        Admin admin = (Admin)session.getAttribute("admin");
        System.out.println("sessionname:"+session.getAttributeNames());
        System.out.println(admin);
        if(admin != null ){

        }else{ List<Object> params = new ArrayList<Object>();
            params.add(admin.getAdminName());
            params.add(admin.getAdminAccount());

            apiRequest.setResult(true);
            apiRequest.setData(params);
            apiRequest.setResult(false);
            apiRequest.setMsg("账号信息不存在");
        }

        return apiRequest;

    }


    /**
     * 登出的方法
     * @param session
     * @return
     */
    @RequestMapping(value = "/main/loginOut.do",consumes ="application/x-www-form-urlencoded")
    public APIRequest loginOut(HttpSession session){
        session.removeAttribute("admin");
        APIRequest apiRequest = new APIRequest();
        apiRequest.setResult(true);
        apiRequest.setMsg("退出登录成功");
        return apiRequest;
    }
}
