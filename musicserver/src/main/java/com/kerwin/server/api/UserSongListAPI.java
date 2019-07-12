package com.kerwin.server.api;

import com.kerwin.server.pojo.UserSongList;
import com.kerwin.server.server.impls.UserSongListServiceImpl;
import com.kerwin.server.utils.APIRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSongListAPI {

    private UserSongListServiceImpl userSongListService= new UserSongListServiceImpl();

    /**
     * 导入数据的方法
     * @param userId
     * @param songListName
     * @param recommend
     * @return
     */
    @RequestMapping("/main/InsertUserSongList.do")
    public APIRequest InsertUserSongList(Integer userId,String songListName, String recommend){

        if(userId == null || songListName == null || recommend == null){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("有表单项为空");
            return apiRequest;
        }

        UserSongList userSongList = new UserSongList(userId,songListName,recommend);

        return userSongListService.InsertUserSongList(userSongList);
    }

    /**
     *删除数据的方法
     * @param Id
     * @return
     */
    @RequestMapping("/main/deleteUserSongList.do")
    public  APIRequest deleteUserSongList(Integer Id){

        if(Id==null || Id.equals("")){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("删除失败！请刷新后重试");
            return apiRequest;
        }

        return userSongListService.deleteUserSongList(Id);
    }

    /**
     * 更新数据的方法
     * @param songListName
     * @return
     */
    @RequestMapping("/main/updateUserSongList.do")
    public APIRequest updateUserSongList(String songListName){

        if(songListName==null || songListName.equals("")){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("更新失败！表单项不为空");
            return apiRequest;
        }

        UserSongList userSongList=new UserSongList(songListName);
        return userSongListService.updateUserSongList(userSongList);
    }

    /**
     * 更改是否为推荐歌单
     * @param Id
     * @param recommend
     * @return
     */
    @RequestMapping("/main/changeRecommend.do")
    public APIRequest changeRecommend(Integer Id, String recommend){
        if(Id==null || Id.equals("")|| recommend==null || recommend.equals("")){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("表单项不为空！");
            return  apiRequest;
        }

        return userSongListService.changeRecommend(Id,recommend);
    }

    /**
     * 通过用户Id 查找所有的歌单
     * @param Id
     * @return
     */
    @RequestMapping("/main/queryAllByUserId.do")
    public APIRequest queryAllByUserId(Integer Id){

        if(Id==null || Id.equals("")){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("请刷新后重试");
            return apiRequest;
        }

        return userSongListService.queryAllByUserId(Id);
    }

    /**
     * 通过歌单序号查找所有歌曲的方法
     * @param Id
     * @return
     */
    @RequestMapping("/main/findSongsBySongUserListId.do")
    public APIRequest findSongsBySongUserListId(Integer Id){

        if(Id==null || Id.equals("")){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("请刷新后重试");
            return apiRequest;
        }

        return userSongListService.findSongsBySongUserListId(Id);
    }

    /**
     * 查找推荐歌单
     * @return
     */
    @RequestMapping("/main/findRecommend.do")
    public APIRequest findRecommend(){
        return userSongListService.findRecommend();
    }
}
