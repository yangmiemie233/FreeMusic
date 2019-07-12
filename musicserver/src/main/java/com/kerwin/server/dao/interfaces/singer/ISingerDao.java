package com.kerwin.server.dao.interfaces.singer;

import com.kerwin.server.dao.IDao;

import java.util.List;

public interface ISingerDao extends IDao {


    /**
     * 写接口的核心意义
     * 我只管定要求和出去的数据
     * 要求是参数
     * 出去的数据是返回值
     * @param clazz
     * @param indexpage
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz,int indexpage,String sql,List<Object> params);
    public Integer count(String sql, List<Object> params);

    public boolean add(Object obj);

    public boolean del(String sql, List<Object> params);


}
