package com.kerwin.server.dao.interfaces.user;


import com.kerwin.server.dao.IDao;

import java.util.List;

public interface userIDao extends IDao {

    boolean add();

    public <T> List<T> query(Class<T> t, String sql, List<Object> params);

    public boolean add(Object... obj);



}
