package com.kerwin.server.dao.interfaces.user;

import com.kerwin.server.dao.IDao;
import com.kerwin.server.pojo.User;

import java.util.List;

public interface IUserDao extends IDao {

    public <T> List<T> query(Class<T> clazz, int indexpage, String sql, List<Object> params);


    @Override
    public <T> List<T> query(Class<T> clazz, String sql, List<Object> params);

    public Integer count(String sql, List<Object> params);

    public boolean add(Object obj);

    public boolean del(String sql, List<Object> params);

}
