package com.kerwin.server.dao.impls.admin;

import com.kerwin.server.dao.interfaces.admin.IAdmin;
import jdbc2.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员的实现类
 * @Author liuyun
 * @Time 2019/07/11
 *
 */
public class AdminDaoImpl implements IAdmin {
    @Override
    public <T> List<T> login(Class<T> T, String uname, String pwd) {

        List<Object> params = new ArrayList<Object>();
        params.add(uname);
        params.add(uname);
        params.add(pwd);

        String sql =" select adminId , adminName , adminAccount , adminPassword " +
                "      from admin " +
                "      where (adminName = ?  or adminAccount = ?) and adminPassword=? ";

        try{

            JDBCUtil jdbcUtil = new JDBCUtil();
            List<T> datas=jdbcUtil.executeQuery(sql,params,T);


            return datas;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

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
    public <T> List<T> query(Class<T> T, String sql, List<Object> params) {
        return null;
    }

    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
        return null;
    }
}
