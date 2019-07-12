package com.kerwin.server.dao.interfaces.advertisement;

import com.kerwin.server.dao.IDao;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  广告的持久层接口 主要负责增删改查之操作
 * @Author liuyun
 * @Time    2019/07/10
 *
 */
public interface IAdvertisement extends IDao {

    /**
     * 添加广告的方法
     * @param title
     * @param link
     * @param picture
     * @return
     */
    public boolean add(String title, String link, String picture);

    /**
     * 设为不启用此广告
     * @param advertisementId
     * @param status
     * @return
     */
    public boolean changeStatus(Integer advertisementId, String status);

    /**
     * 设为启用此广告
     * @param advertisementId
     * @param status
     * @param date
     * @return
     */
    public boolean changeStatus(Integer advertisementId, String status, Date openTime);



    /**
     * 删除的方法
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 更新广告数据的方法
     * @param advertisementId
     * @param title
     * @param link
     * @param picture
     * @return
     */
    public boolean update(Integer advertisementId, String title, String link, String picture);


    public <T> List<T> query(Class<T> T, String status);

    /**
     * 通过状态量查找数据
     * @param status
     * @return
     */
    public List<Map<String,Object>> queryByStatus(String status);

}
