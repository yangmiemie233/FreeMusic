package com.kerwin.server.dao;

import java.util.List;

public interface IDao {


    public boolean add();

    public boolean edit();

    public boolean update();

    public boolean update(String sql, List<Object> params);

    public <T> List<T> query(Class<T> T,String sql,List<Object> params);


    public <T> T query(Class<T> T,String sql,Object id);



}
