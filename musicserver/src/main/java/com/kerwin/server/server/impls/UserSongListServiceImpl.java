package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.userSongList.UserSongListDaoImpl;
import com.kerwin.server.dao.interfaces.userSongList.IUserSongListDao;
import com.kerwin.server.pojo.Song;
import com.kerwin.server.pojo.UserSongList;
import com.kerwin.server.utils.APIRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户歌单的Service层实现类
 * @Author liuyun
 * @Time   2019/07/11
 *
 */
public class UserSongListServiceImpl {

    IUserSongListDao iUserSongListDao = new UserSongListDaoImpl();


    /**
     * 添加用户歌单的方法
     * @param userSongList
     * @return
     */
    public APIRequest InsertUserSongList(UserSongList userSongList){

        APIRequest apiRequest = new APIRequest();
        if(iUserSongListDao.InsertUserSongList(userSongList)){
            apiRequest.setResult(true);
            apiRequest.setMsg("添加成功");

            return apiRequest;
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("添加失败");

        return  apiRequest;

    }

    /**
     * 删除用户歌单的方法
     * @param Id
     * @return
     */
    public APIRequest deleteUserSongList(Integer Id){
        APIRequest apiRequest = new APIRequest();

        if(iUserSongListDao.deleteUserSongList(Id)){
            apiRequest.setResult(true);
            apiRequest.setMsg("删除成功");

            return apiRequest;
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("删除失败了");

        return apiRequest;
    }

    /**
     * 更新用户歌单的方法
     * @param userSongList
     * @return
     */
    public APIRequest updateUserSongList(UserSongList userSongList){
        APIRequest apiRequest = new APIRequest();

        if(iUserSongListDao.updateUserSongList(userSongList)){
            apiRequest.setResult(true);
            apiRequest.setMsg("更新成功");
            return  apiRequest;
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("更新失败");
        return apiRequest;
    }

    /**
     * 更改是否为推荐歌单
     * @param Id
     * @param recommend 0 不推荐 | 1 推荐
     * @return
     */
    public APIRequest changeRecommend(Integer Id, String recommend){
        APIRequest apiRequest = new APIRequest();

        if(iUserSongListDao.changeRecommend(Id,recommend)){
            apiRequest.setResult(true);
            apiRequest.setMsg("更改成功");
            return apiRequest;
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("更改失败");

        return apiRequest;
    }

    /**
     * 通过用户Id 查找所有的歌单
     * @param Id
     * @return
     */
    public APIRequest queryAllByUserId( Integer Id){

        APIRequest apiRequest = new APIRequest();

        List<UserSongList> userSongListLists = iUserSongListDao.queryAllByUserId(UserSongList.class,Id);
        if(userSongListLists == null){
            apiRequest.setResult(false);
            apiRequest.setMsg("暂无数据");
            return apiRequest;
        }

        apiRequest.setResult(true);
        apiRequest.setData(userSongListLists);
        return apiRequest;
    }

    /**
     * 通过歌单序号查找所有歌曲的方法
     * @param Id
     * @return
     */
    public APIRequest findSongsBySongUserListId(Integer Id){
        APIRequest apiRequest = new APIRequest();

        List<Song> songs = new ArrayList<Song>();

        if(songs == null){
            apiRequest.setResult(false);
            apiRequest.setMsg("暂无数据");
            return apiRequest;
        }

        apiRequest.setResult(true);
        apiRequest.setData(songs);

        return apiRequest;
    }


    /**
     * 查找推荐歌单
     * @return
     */
    public APIRequest findRecommend(){

        APIRequest apiRequest = new APIRequest();

        List<UserSongList> userSongLists = new ArrayList<UserSongList>();

        if(userSongLists==null){
            apiRequest.setResult(false);
            apiRequest.setMsg("暂无数据");
            return apiRequest;

        }

        apiRequest.setResult(true);
        apiRequest.setData(userSongLists);
        return apiRequest;
    }




}
