package com.kerwin.server.dao.interfaces.admin;

import com.kerwin.server.dao.IDao;

import java.util.List;

/**
 * 管理员的持久层接口
 * @Author liuyun
 * @Time    2019/07/11
 *
 */
public interface IAdmin extends IDao {

    /**
     * 通过姓名和密码查找账户
     * @param T
     * @param uname
     * @param pwd
     * @param <T>
     * @return
     */
    public <T> List<T> login(Class<T> T, String uname, String pwd);


}
