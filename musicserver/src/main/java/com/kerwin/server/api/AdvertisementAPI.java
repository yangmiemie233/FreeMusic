package com.kerwin.server.api;


import com.kerwin.server.pojo.Advertisement;
import com.kerwin.server.server.impls.AdvertisementImpls;
import com.kerwin.server.server.interfaces.advertisement.AdvertisementService;
import com.kerwin.server.utils.APIRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * 广告的Controller层接口
 * @Author liuyun
 * @Time 2019/07/10
 */
@RestController
public class AdvertisementAPI {

    AdvertisementService advertisementService=new AdvertisementImpls();

    /**
     * 添加广告的方法
     * @param title
     * @param link
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/main/insertAdvertise.do",method=RequestMethod.POST)
    public APIRequest Insert(String title,
                             String link,
                             MultipartFile file,
                             HttpServletRequest request) throws Exception {


        String filename=file.getOriginalFilename().substring(1);

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String path=request.getSession().getServletContext().getRealPath("/upload");

        String filepath=path+ File.separator+sdf.format(date)+filename.substring(1);
        File files=new File(filepath);

        if(!files.getParentFile().exists()){

           files.getParentFile().mkdir();
        }

        file.transferTo(files);

        Advertisement advertisement=new Advertisement();
        advertisement.setTitle(title);
        advertisement.setLink(link);
        advertisement.setPicture("upload/"+sdf.format(date)+filename.substring(1));

        return advertisementService.insert(advertisement);

    }

    /**
     * 显示未启用的广告
     * @return
     */
    @RequestMapping("/main/showUnuseAd.do")
    public APIRequest showUnuseAd(){
       return advertisementService.findAll();
    }

    /**
     * 显示已启用的广告
     * @return
     */
    @RequestMapping("/main/showUseAd.do")
    public APIRequest showUseAd(){
        return advertisementService.findShow();
    }


    /**
     * 删除数据的方法
     * @param adId
     * @return
     */
    @RequestMapping("/main/delteAd.do")
    public APIRequest deleteAd(Integer adId){
        return advertisementService.delete(adId);
    }

    /**
     * 更改启用状态
     * @param Id
     * @param status
     * @return
     */
    @RequestMapping("/main/changStatu.do")
    public APIRequest changeStatus(Integer Id,String status){
        if(Id == null || Id.equals("") || status ==null || status.equals("")){
            APIRequest apiRequest = new APIRequest();

            apiRequest.setResult(false);
            apiRequest.setMsg("值不为空");

            return apiRequest;
        }else{
            return advertisementService.changeShowStatus(Id,status);
        }
    }


}
