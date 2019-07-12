package com.kerwin.server.dao.impls.song;

import com.kerwin.server.dao.interfaces.singer.ISingerDao;
import com.kerwin.server.dao.interfaces.song.ISongDao;
import com.kerwin.server.pojo.Song;
import jdbc2.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class SongDaoImpls implements ISongDao {

    @Override
    public <T> List<T> query(Class<T> clazz, int indexpage, String sql, List<Object> params) {
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

    @Override
    public <T> List<T> query(Class<T> T, String sql, List<Object> params) {
        List<T> data = new ArrayList<T>();
        JDBCUtil util = new JDBCUtil();
        data = util.executeQuery(sql, params, T);
        return data;
    }

    //统计数据库查询的行数
    public Integer count(String sql, List<Object> params) {
        JDBCUtil util = new JDBCUtil();
        Integer data = util.executeQueryOnly(sql, params);
        return data;
    }



    /**
     * 删除歌曲的方法
     * @param songId
     * @return
     */
    @Override
    public boolean delete(Integer songId) {
        String sql="delete from song where songId=?";
        List<Object> params=new ArrayList<Object>();
        params.add(songId);

        JDBCUtil jdbcUtil = new JDBCUtil();
        if(jdbcUtil.executeUpdate(sql,params)>0){
            return true;
        }

        return false;
    }

    /**
     * 通过序号查找歌曲
     * @param T
     * @param songId
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> queryById(Class<T> T,Integer songId) {
        String sql = "select " +
                "singerId," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300" +
                " from song  where songId=?";
        List<Object> params=new ArrayList<Object>();
        params.add(songId);

        JDBCUtil jdbcUtil=new JDBCUtil();
        List<T> data=jdbcUtil.executeQuery(sql,params, T);


        return data;
    }

    /**
     * 通过歌曲名或者专辑名查找歌曲
     * @param T
     * @param name
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> queryByName(Class<T> T, String name) {
        String sql = "select songId," +
                "singerId," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "songWords from song  where songName like '"+name+"%' or songAlbumName like '"+name+"%'";

        try {
            JDBCUtil jdbcUtil = new JDBCUtil();
            List<T> data=jdbcUtil.executeQuery(sql,null,T);
            return  data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新歌曲信息的方法
     * @param song
     * @return
     */
    @Override
    public boolean updateSong(Song song) {

        String sql = "update song set " +
                "songName = ? ," +
                "songTimeMinutes = ? ," +
                "songRid = ? ," +
                "songDate = ? ," +
                "pic120 = ? ," +
                "pic300= ?" +
                "where songId=?";

        List<Object> params = new ArrayList<Object>();
        params.add(song.getSongName());
        params.add(song.getSongTimeMinutes());
        params.add(song.getSongRid());
        params.add(song.getSongDate());
        params.add(song.getPic120());
        params.add(song.getPic300());
        params.add(song.getSongId());

        try {

            JDBCUtil jdbcUtil = new JDBCUtil();
            if(jdbcUtil.executeUpdate(sql,params)>0){
                System.out.println("dao");
                return true;

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 更新数据的方法
     * @param T
     * @param sql
     * @param id
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
        return null;
    }
}
