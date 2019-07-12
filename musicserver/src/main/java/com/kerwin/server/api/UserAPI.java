package com.kerwin.server.api;


import com.kerwin.server.pojo.User;
import com.kerwin.server.server.impls.UserServerImpls;
import com.kerwin.server.server.impls.user.userServiceImpl;
import com.kerwin.server.server.interfaces.user.userIService;
import com.kerwin.server.utils.APIRequest;
import com.kerwin.server.utils.requestAPI;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


@CrossOrigin(allowCredentials="true",allowedHeaders="*")
@RestController
public class UserAPI {
    UserServerImpls server = new UserServerImpls();

    userIService us = new userServiceImpl();
    // 使用api类封装返回数据给前台

    // 登录实现
    @RequestMapping(value = "login.do",consumes ="application/x-www-form-urlencoded")
    public requestAPI findUserapi(String username, String password, HttpSession session){

        requestAPI ra = new requestAPI();
            ra = us.findUser(username,password);
            /*return us.findUser(username,password);*/
            if (ra != null){
                System.out.println(ra.getData());
                session.setAttribute("user",ra.getData());
                System.out.println("session:"+session.getAttribute("user"));
                System.out.println(session);
            }
            return ra;

    }

    // 注册实现
    @RequestMapping("register.do")
    public requestAPI registerapi(String username,String password,String email){
        if(username == "" || password == "" || email ==""){
            return new requestAPI(false);
        }else {
            return us.addUser(username,password,email);
        }
    }

    // 验证用户名
    @RequestMapping("confirmUser.do")
    public requestAPI confirmUserapi(String username){
        return us.confirmUser(username);
    }

    // 获取session中的user对象
    @RequestMapping(value = "getSession.do",consumes ="application/x-www-form-urlencoded")
    public requestAPI getSession(HttpSession session){
        session.getAttribute("user");
        System.out.println("获取到了: "+session.getAttribute("user"));
        System.out.println(session);
        Object user = session.getAttribute("user");
        return new requestAPI(user);
    }

    // 退出登录
    @RequestMapping(value = "exitUser.do",consumes ="application/x-www-form-urlencoded")
    public boolean exitUser(HttpSession session){
        System.out.println("退出模块: "+session);
        session.setAttribute("user",null);
        return true;
    }


    /**
     * 加载当前页面用户数据接口
     *
     * @param indexpage 页面传入的当前页码值
     * @return
     */
    @RequestMapping("aloaduser.do")
    public APIRequest aquery(Integer indexpage) {

        return server.aquery(indexpage);
    }

    /**
     * 利用用户id查询用户信息
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping("aquerybyuserid.do")
    public APIRequest aqueryByUserId(Integer userId) {

        System.out.println("query");
        return server.aqueryByUserId(userId);

    }

    /**
     * 利用用户id删除当前用户
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping("adeluser.do")
    public APIRequest adeluser(Integer userId) {
        return server.adel(userId);
    }

    @RequestMapping("aquerybyusername.do")
    public APIRequest aquerybyusername(String userName) {

        System.out.println("query");
        return server.aqueryByUserName(userName);

    }

    /**
     * 接收前段传过来的修改的用户对象修改用户信息
     *
     * @param userId 用户id ...
     * @return
     */
    @RequestMapping("amodifyuser.do")
    public APIRequest aupdate(Integer userId, String userName,
                              String userPassword, String userEmail) {

        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);

        user.toString();

        return server.aupdateuser(user);
    }


    /**
     * 接收前段传过来的修用户对象新增用户
     *
     * @param userName 用户id ...
     * @return
     */
    @RequestMapping("aadduser.do")
    public APIRequest aadd(String userName,
                           String userPassword, String userEmail) {

        User user = new User();

        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserEmail(userEmail);

        return server.aadd(user);
    }


}
