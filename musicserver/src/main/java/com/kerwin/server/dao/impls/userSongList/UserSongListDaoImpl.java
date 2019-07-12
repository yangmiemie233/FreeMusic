package com.kerwin.server.dao.impls.userSongList;

import com.kerwin.server.dao.interfaces.userSongList.IUserSongListDao;
import com.kerwin.server.pojo.UserSongList;
import jdbc2.JDBCUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * 用户歌单的编辑方法
 * @Author liuyun
 * @Time 2019/07/11
 */
public class UserSongListDaoImpl implements IUserSongListDao {

    /**
     * 导入歌单的方法
     * @param userSongList
     * @return
     */
    @Override
    public boolean InsertUserSongList(UserSongList userSongList) {

        String sql = "Insert into usersonglist(userId,songListName,recommend)" +
                "                   values(?,?,?)";

        List<Object> params=new ArrayList<Object>();
        params.add(userSongList.getUserId());
        params.add(userSongList.getSongListName());
        params.add("0");

        try {
            JDBCUtil jdbcUtil=new JDBCUtil();

            if(jdbcUtil.executeUpdate(sql,params)>0){
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
     * 删除歌单的方法
     * @param Id 主键
     * @return
     */
    @Override
    public boolean deleteUserSongList(Integer Id) {

        List<Object> params = new ArrayList<Object>();
        params.add(Id);

        String sql="delete from usersonglist where songListId = ?";

        try {

            JDBCUtil jdbcUtil = new JDBCUtil();

            if( jdbcUtil.executeUpdate(sql,params) > 0 ){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 更新用户歌单的方法 - 暂时只更新名字
     * @param userSongList
     * @return
     */
    @Override
    public boolean updateUserSongList(UserSongList userSongList) {

        String sql="update usersonglist set " +
                "   songListName = ?";

        List<Object> params = new ArrayList<Object>();
        params.add(userSongList.getSongListName());

        try {
            JDBCUtil jdbcUtil = new JDBCUtil();

            if(jdbcUtil.executeUpdate(sql,params)>0){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 设置是否为推荐歌单
     * @param Id
     * @param recommend
     * @return
     */
    @Override
    public boolean changeRecommend(Integer Id, String recommend) {
        String sql="update usersonglist set " +
                "   recommend = ? where songListId = ?";

        List<Object> params = new ArrayList<Object>();
        params.add(recommend);
        params.add(Id);

        try {
            JDBCUtil jdbcUtil = new JDBCUtil();

            if(jdbcUtil.executeUpdate(sql,params)>0){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 通过用户序号查找所有的歌单
     * @param <T>
     * @param Id
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> queryAllByUserId(Class<T> T, Integer Id) {
        String sql= " select songListId , userId ,songListName , recommend " +
                "      from usersonglist " +
                "       where userId = ?";

        List<Object> params =new ArrayList<Object>();
        params.add(Id);

        try {
            JDBCUtil jdbcUtil =new JDBCUtil();

            List<T> datas = jdbcUtil.executeQuery(sql,params,T);

            return datas;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过歌单号查找所有的歌曲
     * @param <T>
     * @param id
     * @param <T>   Song
     * @return
     */
    @Override
    public <T> List<T> querySongBySongUserListId(Class<T> T, Integer id) {

        List<Object> params = new ArrayList<Object>();

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
                "songWords from song  where songId IN(" +
                "   select songId " +
                "   from usersongs" +
                "   where songListId = ? "+
                ")";

        try {

            JDBCUtil jdbcUtil = new JDBCUtil();

            List<T> datas = jdbcUtil.executeQuery(sql,params,T);

            return datas;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找推荐歌单
     * @param T
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> queryRecomendUserSongList(Class<T> T) {

        String sql= " select songListId , userId ,songListName , recommend " +
                "      from usersonglist " +
                "       where recommend = '1'";


        try {
            JDBCUtil jdbcUtil = new JDBCUtil();

            List<T> datas = jdbcUtil.executeQuery(sql,null,T);

            return datas;

        }catch (Exception e){
            e.printStackTrace();
        }

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
        return null;
    }

    @Override
    public <T> T query(Class<T> T, String sql, Object id) {
        return null;
    }
}
