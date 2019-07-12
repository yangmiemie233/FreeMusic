package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.admin.AdminDaoImpl;
import com.kerwin.server.dao.interfaces.admin.IAdmin;
import com.kerwin.server.pojo.Admin;
import com.kerwin.server.utils.APIRequest;

import java.util.List;

/**
 * 管理员的业务管理层 Service 实现类
 * @Author liuyun
 * @Time   2019/07/11
 */
public class AdminServiceImpl {

    private IAdmin  adminDao = new AdminDaoImpl();

    /**
     * 登录的方法
     * @param uname
     * @param pwd
     * @return
     */
    public APIRequest login(String uname,String pwd){
        APIRequest apiRequest = new APIRequest();
        List<Admin> admins= adminDao.login(Admin.class,uname,pwd);

        if(admins == null || admins.size()<=0){

            apiRequest.setResult(false);
            apiRequest.setMsg("登录失败 请检查账号或密码是否正确 或 稍后重试");
            return apiRequest;
        }

        apiRequest.setResult(true);
        apiRequest.setMsg("登录成功");
        apiRequest.setData(admins);

        return apiRequest;
    }
}
