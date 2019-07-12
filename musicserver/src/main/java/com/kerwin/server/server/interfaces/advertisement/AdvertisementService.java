package com.kerwin.server.server.interfaces.advertisement;

import com.kerwin.server.pojo.Advertisement;
import com.kerwin.server.utils.APIRequest;


/**
 *
 * 主要是进行广告接口的实现类
 * @Author liuyun
 * @Time 2019/07/09
 *
 *
 */
public interface AdvertisementService {
    /**
     * 添加的方法
     * @param advertisement
     * @return
     */
    public APIRequest insert(Advertisement advertisement);

    /**
     * 删除的方法
     * @param advertisementId
     * @return
     */
    public APIRequest delete(Integer advertisementId);

    /**
     * 修改的方法
     * @param advertisement
     * @return
     */
    public APIRequest update(Advertisement advertisement);

    /**
     * 查找所有数据的方法
     * @return
     */
    public APIRequest findAll();

    /**
     * 查找显示的图片
     * @return
     */
    public APIRequest findShow();

    /**
     * 更改显示状态
     * @param advertisementId
     * @param status
     * @return
     */
    public APIRequest changeShowStatus(Integer advertisementId, String status);
}