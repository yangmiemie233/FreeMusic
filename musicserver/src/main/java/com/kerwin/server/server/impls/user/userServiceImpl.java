package com.kerwin.server.server.impls.user;


import com.kerwin.server.dao.impls.user.userDaoImpl;
import com.kerwin.server.dao.interfaces.user.userIDao;
import com.kerwin.server.pojo.User;
import com.kerwin.server.server.interfaces.user.userIService;
import com.kerwin.server.utils.requestAPI;

import java.util.ArrayList;
import java.util.List;

public class userServiceImpl implements userIService {

    userIDao ui = new userDaoImpl();

    @Override
    public requestAPI findUser(String username, String password) {

        //定义一个requestAPI对象
        requestAPI api = new requestAPI();
        String sql = "select * from user where userName = ? and userPassword = ?";
        List params = new ArrayList();
        params.add(username);
        params.add(password);
        System.out.println("用户名: " + username);
        System.out.println("密码: " + password);
        List<User> users = ui.query(User.class, sql, params);
        System.out.println(params);
        System.out.println(users);
        api.setData(users);
        return api;
    }

    //注册
    @Override
    public requestAPI addUser(String username, String password, String email) {
        User user = new User();
        //requestAPI api = new requestAPI();
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserEmail(email);
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        boolean flg = ui.add(user);
        System.out.println(flg);
        //api.setFlg(flg);
        return new requestAPI(flg);
    }

    @Override
    public requestAPI confirmUser(String username) {
        requestAPI api = new requestAPI();
        String sql = "select * from user where userName = ?";
        List params = new ArrayList();
        params.add(username);
        List<User> user = ui.query(User.class, sql, params);
        api.setData(user);
        return api;
    }
}
