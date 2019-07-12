package com.kerwin.server.dao.impls.user;


import com.kerwin.server.dao.interfaces.user.userIDao;
import jdbc2.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class userDaoImpl implements userIDao {


    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean update(String sql, List<Object> params) {
        return false;
    }


    @Override
    public <T> List<T> query(Class<T> t, String sql, List<Object> params) {

        List<T> data = new ArrayList<T>();
        JDBCUtil util = new JDBCUtil();
        //System.out.println("query方法");
        data = util.executeQuery(sql, params, t);
        return data;
    }

    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
        return null;
    }

    @Override
    public boolean add(Object... obj) {
        JDBCUtil util = new JDBCUtil();
        int addFlg = util.add(obj);
        System.out.println(addFlg);
        if (addFlg > 0) {
            return true;
        }else {
            return false;
        }
    }

}
