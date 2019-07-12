package com.kerwin.server.dao.interfaces.song;

import com.kerwin.server.dao.IDao;
import com.kerwin.server.pojo.Song;

import java.util.List;

public interface ISongDao extends IDao {

    /**
     * 查找歌曲的方法
     * @param clazz
     * @param indexpage 当前页面
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz,int indexpage,String sql,List<Object> params);

    /**
     * 查找歌曲数目
     * @param sql
     * @param params
     * @return
     */
    public Integer count(String sql, List<Object> params);

    /**
     * 删除歌曲的方法
     * @param songId
     * @return
     */
    public boolean delete(Integer songId);

    /**
     * 通过序号ID查找歌曲信息
     * @param T
     * @param songId
     * @param <T>
     * @return
     */
    public <T> List<T> queryById(Class<T> T,Integer songId);

    /**
     * 通过歌曲名或者专辑吗查找歌曲
     * @param T
     * @param name  歌曲名或者专辑名
     * @param <T>
     * @return
     */
    public <T> List<T> queryByName(Class<T> T,String name);

    /**
     * 更新歌曲的方法
     * @param song
     * @return
     */
    public boolean updateSong(Song song);
}
