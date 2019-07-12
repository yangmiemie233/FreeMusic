package com.kerwin.server.dao.impls.singer;

import com.kerwin.server.dao.interfaces.singer.ISingerDao;
import jdbc2.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class SingerDaoImpls implements ISingerDao {
    @Override
    public <T> List<T> query(Class<T> clazz, int indexpage, String sql, List<Object> params) {


        return null;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean add(Object obj) {

        JDBCUtil util = new JDBCUtil();
        int result = util.add(obj);
        if (result > 0) {
            return true;
        } else {
            return false;
        }

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

        JDBCUtil util = new JDBCUtil();
        int result = util.executeUpdate(sql, params);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public <T> List<T> query(Class<T> T, String sql, List<Object> params) {
        List<T> data = new ArrayList<T>();
        JDBCUtil util = new JDBCUtil();
        data = util.executeQuery(sql, params, T);
        return data;
    }

    //统计数据库查询的行数
    public Integer count(String sql, List<Object> params) {
        JDBCUtil util = new JDBCUtil();
        Integer data = util.executeQueryOnly(sql, params);
        return data;
    }

    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
        return null;
    }

    @Override
    public boolean del(String sql, List<Object> params) {

        JDBCUtil util = new JDBCUtil();
        int result = util.executeUpdate(sql, params);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}
