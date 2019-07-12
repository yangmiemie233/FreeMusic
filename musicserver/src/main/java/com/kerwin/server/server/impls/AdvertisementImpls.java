package com.kerwin.server.server.impls;




import com.kerwin.server.dao.impls.advertisement.AdvertisementDaoImpls;
import com.kerwin.server.dao.interfaces.advertisement.IAdvertisement;
import com.kerwin.server.pojo.Advertisement;
import com.kerwin.server.server.interfaces.advertisement.AdvertisementService;
import com.kerwin.server.utils.APIRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * 广告的Service层实现类
 * @Author liuyun
 * @Time  2019/07/09
 *
 */
public class AdvertisementImpls implements AdvertisementService {

    private IAdvertisement iAdvertisement = new AdvertisementDaoImpls();

    /**
     *  添加广告的方法
     * @param advertisement
     * @return
     */
    @Override
    public APIRequest insert(Advertisement advertisement) {
        APIRequest apiRequest=new APIRequest();

        try{

            if(iAdvertisement.add(advertisement.getTitle(),
                                  advertisement.getLink(),
                                  advertisement.getPicture())){
                apiRequest.setResult(true);
                apiRequest.setMsg("添加成功");

                return apiRequest;
            }

            apiRequest.setResult(false);

            apiRequest.setMsg("添加失败,数据格式错误");

            return apiRequest;
        }catch (Exception e){
            e.printStackTrace();
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("添加失败，服务器访问失败");
        return apiRequest;
    }

    /**
     * 删除数据的方法
     * @param advertisementId
     * @return
     */
    @Override
    public APIRequest delete(Integer advertisementId) {
        APIRequest apiRequest=new APIRequest();

        try {
            if(iAdvertisement.delete(advertisementId)){

                apiRequest.setResult(true);
                apiRequest.setMsg("恭喜您 删除成功");

                return apiRequest;
            }

            apiRequest.setResult(false);
            apiRequest.setMsg("卧槽 删除失败了，请稍后重试");


            return apiRequest;
        }catch (Exception e){
            e.printStackTrace();
        }
        apiRequest.setResult(false);
        apiRequest.setMsg("删除失败 服务器访问错误");
        return apiRequest;
    }

    /**
     * 更新广告的方法
     * @param advertisement
     * @return
     */
    @Override
    public APIRequest update(Advertisement advertisement) {

        APIRequest apiRequest=new APIRequest();

        try{

            if(iAdvertisement.update(advertisement.getAdvertisementId(),advertisement.getTitle(),advertisement.getLink(),advertisement.getPicture())){
                apiRequest.setResult(true);
                apiRequest.setMsg("恭喜 更新成功");

                return apiRequest;
            }

            apiRequest.setResult(false);
            apiRequest.setMsg("更新失败了");

        }catch (Exception e){
            e.printStackTrace();
        }
        apiRequest.setResult(false);
        apiRequest.setMsg("服务器连接错误 2333");
        return apiRequest;
    }


    /**
     * 查找未使用的数据
     * @return
     */
    @Override
    public APIRequest findAll() {
//        APIRequest apiRequest=new APIRequest();
//        try {
//
//            apiRequest.setResult(true);
//            apiRequest.setData(iAdvertisement.query(Advertisement.class,"0"));
//
//            return apiRequest;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        apiRequest.setResult(false);
//        apiRequest.setMsg("查询失败");
//        return apiRequest;

        APIRequest apiRequest = new APIRequest();

        List<Map<String,Object>> maps = iAdvertisement.queryByStatus("0");
        if( maps == null ) {
            apiRequest.setResult(false);
            apiRequest.setMsg("查询失败");
        }

        apiRequest.setResult(true);
        apiRequest.setData(maps);
        return apiRequest;
    }

    /**
     * 查找显示数据的方法
     * @return
     */
    @Override
    public APIRequest findShow() {
//        APIRequest apiRequest=new APIRequest();
//        try {
//
//            apiRequest.setResult(true);
//            List<Advertisement> advertisementList;
//            advertisementList=iAdvertisement.query(Advertisement.class,"1");
//            apiRequest.setData(advertisementList);
//
//            return apiRequest;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        apiRequest.setResult(false);
//        apiRequest.setMsg("查询失败");
//        return apiRequest;

        APIRequest apiRequest = new APIRequest();

        List<Map<String,Object>> maps = iAdvertisement.queryByStatus("1");
        if( maps == null ) {
            apiRequest.setResult(false);
            apiRequest.setMsg("查询失败");
        }

        apiRequest.setResult(true);
        apiRequest.setData(maps);
        return apiRequest;


    }

    /**
     * 更改使用状态
     * @param advertisementId
     * @param status    0 设为不启用  1 设为启用
     * @return
     */
    @Override
    public APIRequest changeShowStatus(Integer advertisementId, String status) {
        APIRequest apiRequest=new APIRequest();
       try {

           if(status.equals("0")){
               if(iAdvertisement.changeStatus(advertisementId,status)){
                   apiRequest.setResult(true);
                   apiRequest.setMsg("更新成功");
               }else {
                   apiRequest.setResult(false);
                   apiRequest.setMsg("更改失败了");
               }

           }else{
             if(iAdvertisement.changeStatus(advertisementId,status,new Date())){
                 apiRequest.setResult(true);
                 apiRequest.setMsg("更新成功");
             }else {
                 apiRequest.setResult(false);
                 apiRequest.setMsg("更改失败了");
             }
           }



            return apiRequest;
       }catch (Exception e){
           e.printStackTrace();
       }

       apiRequest.setResult(false);
       apiRequest.setMsg("服务器访问失败咯");
       return apiRequest;
    }
}
