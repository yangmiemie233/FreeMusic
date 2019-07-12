package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.user.UserDaoImpls;
import com.kerwin.server.dao.interfaces.user.IUserDao;
import com.kerwin.server.pojo.User;
import com.kerwin.server.utils.APIRequest;
import com.kerwin.server.utils.Pager;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户server层 实现用户的基本方法
 *
 * @Author zxw
 * @Time 2019/07/11
 */
public class UserServerImpls {

    IUserDao dao = new UserDaoImpls();

    public APIRequest aquery(Integer indexpage) {

        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from user", null);

        //2.把总记录书交给工具运算
        Pager p = new Pager(countrows, indexpage);

        //3.运算结果作为参数传递
        String sql = "select userId ,userName, userPassword ,userEmail from user limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows()-1);
        params.add(p.getPagesize());

        List<Object> query = dao.query(User.class, sql, params);

        p.setData(query);

        return new APIRequest(p);

    }

    public APIRequest aqueryByUserId(Integer userId) {

        Boolean flg = false;

        String msg = null;

        String sql = "select userName," +
                "userPassword," +
                "userEmail from user where userId = ? ";

        List params = new ArrayList();

        params.add(userId);

        System.out.println(sql);

        List<User> query = dao.query(User.class, sql, params);

        if (query.size() <= 0) {
            msg = "查询的内容为空";

        } else {
            flg = true;
        }

        query.toArray().toString();

        return new APIRequest(flg, msg, query);


    }

    public APIRequest adel(Integer userId) {
        List params = new ArrayList();
        System.out.println(userId);
        String sql = "delete from user where userId = ? ";

        params.add(userId);
        boolean flg = dao.del(sql, params);
        return new APIRequest(flg);

    }


    public APIRequest aqueryByUserName(String userName) {

        String sql = "select userId," +
                "userName," +
                "userPassword," +
                "userEmail from user where userName like '" + userName + "%'";

        List params = new ArrayList();

        System.out.println(sql);

        List<User> query = dao.query(User.class, sql, params);

        query.toArray().toString();

        return new APIRequest(query);

    }

    public APIRequest aupdateuser(User user) {
        String sql = "update user set ";

        if (user.getUserPassword() != null) {
            sql += " userPassword = " + "'" + user.getUserPassword() + "' ,";

        }
        if (user.getUserName() != null) {
            sql += " userName = " + "'" + user.getUserName() + "' ,";

        }
        if (user.getUserEmail() != null) {
            sql += " userEmail = " + "'" + user.getUserEmail() + "' ";

        }

        sql += " where userId = ? ";

        System.out.println(sql);

        List params = new ArrayList();
        params.add(user.getUserId());

        boolean flg = dao.update(sql, params);

        return new APIRequest(flg);

    }

    public APIRequest aadd(User user) {

        System.out.println(user.toString());

        boolean flg = false;

        flg = dao.add(user);

        return new APIRequest(flg);
    }
}
