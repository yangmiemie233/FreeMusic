package com.kerwin.server.dao.impls.advertisement;

import com.kerwin.server.dao.interfaces.advertisement.IAdvertisement;
import jdbc2.JDBCUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author liuyun
 * @Time   2019/07/09
 * @Descripe
 *      广告的持久层实现类
 */
public class AdvertisementDaoImpls implements IAdvertisement {

    /**
     * 添加广告的方法
     * @param title
     * @param link
     * @param picture
     * @return
     */
    @Override
    public boolean add(String title, String link, String picture) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format=sdf.format(date);

        String sql="insert into advertisement(title,link,picture,creatTime,status)" +
                    "values('"+title+"','"+link+"','"+picture+"','"+format+"','0')";

        try {
            JDBCUtil jdbcUtil=new JDBCUtil();
            if(jdbcUtil.executeUpdate(sql)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更改广告状态的方法——从使用中改为不使用
     * @param advertisementId
     * @param status
     * @return
     */
    @Override
    public boolean changeStatus(Integer advertisementId, String status) {

        try {

            JDBCUtil jdbcUtil=new JDBCUtil();
            String sql="update advertisement " +
                    "set status='"+status+"'"+
                    "where advertisementId = '"+advertisementId+"' ";

            if(jdbcUtil.executeUpdate(sql)>0){
                return true;
            }else{
                return false;
            }



        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

    /**
     * 更改广告状态的方法--从不使用改为使用 并创建开启时间
     * @param advertisementId
     * @param status
     * @param openTime
     * @return
     */
    @Override
    public boolean changeStatus(Integer advertisementId, String status, Date openTime) {

        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            JDBCUtil jdbcUtil=new JDBCUtil();
            String sql="update advertisement " +
                        "set status='"+status+"' , openTime='"+sdf.format(openTime)+"' " +
                        "where advertisementId = '"+advertisementId+"' ";


            if(jdbcUtil.executeUpdate(sql)>0){
                return true;
            }else{
                return false;
            }



        }catch (Exception e){
            e.printStackTrace();
        }




        return false;
    }

    /**
     *  删除的方法
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        String sql = "delete from advertisement where advertisementId= ?";
        List<Object> params=new ArrayList<Object>();
        params.add(id);

            JDBCUtil jdbcUtil=new JDBCUtil();

            if(jdbcUtil.executeUpdate(sql,params)>0){
                return true;
            }else{
                return  false;
            }
    }

    /**
     * 更新广告数据的方法
     * @param advertisementId
     * @param title
     * @param link
     * @param picture
     * @return
     */
    @Override
    public boolean update(Integer advertisementId, String title, String link, String picture) {
        String sql="update advertisement set  title=? , link=? , picture =? where advertisementId=? ";
        List<Object> params = new ArrayList<Object>();

        JDBCUtil jdbcUtil = new JDBCUtil();
        if (jdbcUtil.executeUpdate(sql,params)>0){
            return true;
        }else{
            return  false;
        }
    }

    /**
     * 查找数据的方法
     * @param T
     * @param status    根据状态查找
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> query(Class<T> T, String status) {
        List<Object> params=new ArrayList<Object>();
        params.add(status);
        String sql="select advertisementId,title,link,picture,creatTime,openTime " +
                "   from Advertisement " +
                "   where status = ?";
        JDBCUtil jdbcUtil=new JDBCUtil();

        List<T> datas = jdbcUtil.executeQuery(sql,params,T);

        return datas;

    }

    /**
     * 通过状态量查找数据
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByStatus(String status) {

        List<Object> params=new ArrayList<Object>();
        params.add(status);
        String sql="select advertisementId,title,link,picture,creatTime,openTime " +
                "   from Advertisement " +
                "   where status = ?";

        try{
            JDBCUtil jdbcUtil=new JDBCUtil();

            List<Map<String,Object>> datas ;
            datas   = jdbcUtil.executeQuery(sql,params);

            return datas;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> query(Class<T> T, String sql, List<Object> params) {
        return null;
    }

    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
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
}
