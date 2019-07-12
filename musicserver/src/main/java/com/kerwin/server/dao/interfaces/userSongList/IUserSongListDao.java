package com.kerwin.server.dao.interfaces.userSongList;

import com.kerwin.server.dao.IDao;
import com.kerwin.server.pojo.UserSongList;

import java.util.List;

/**
 * 用户歌单的dao层接口
 * @Author liuyun
 * @Time 2019/07/11
 *
 */
public interface IUserSongListDao extends IDao {

    /**
     * 添加用户歌曲的方法
     * @param userSongList
     * @return
     */
    public boolean InsertUserSongList(UserSongList userSongList);

    /**
     * 删除用户歌单的方法
     * @param Id
     * @return
     */
    public boolean deleteUserSongList(Integer Id);

    /**
     * 更新用户歌单的方法
     * @param userSongList
     * @return
     */
    public boolean updateUserSongList(UserSongList userSongList);

    /**
     * 更改是否为推荐歌单的方法
     * @param Id
     * @param recommend
     * @return
     */
    public boolean changeRecommend(Integer Id, String recommend);

    /**
     * 查找此用户的所有歌单
     * @param T
     * @param Id
     * @param <T>
     * @return
     */
    public <T> List<T> queryAllByUserId(Class<T> T, Integer Id);

    /**
     * 通过歌单序号查找所有的歌曲
     * @param T
     * @param id
     * @param <T>
     * @return
     */
    public <T> List<T> querySongBySongUserListId(Class<T> T, Integer id);

    public <T> List<T> queryRecomendUserSongList(Class<T> T);
}
