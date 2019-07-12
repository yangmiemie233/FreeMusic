package com.kerwin.server.dao.interfaces.album;

import com.kerwin.server.dao.IDao;

import java.util.List;
import java.util.Map;

public interface IAlbumDao extends IDao {
    public <T> List<T> query(Class<T> clazz, int indexpage, String sql, List<Object> params);

    public Integer count(String sql, List<Object> params);

    public List<Map<String,Object>> query(String sql, List<Object> params);

    public boolean add(Object obj);

    public boolean del(String sql, List<Object> params);

}
