package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.song.SongDaoImpls;
import com.kerwin.server.dao.interfaces.song.ISongDao;
import com.kerwin.server.pojo.Album;
import com.kerwin.server.pojo.Singer;
import com.kerwin.server.pojo.Song;
import com.kerwin.server.utils.APIRequest;
import com.kerwin.server.utils.Pager;

import java.util.ArrayList;
import java.util.List;

public class SongServerImpls {
    ISongDao dao = new SongDaoImpls();

    /*获取歌手id搜索歌手的歌曲*/
    public APIRequest querysingerInfoSongs(Integer singerId, Integer indexpage) {

        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        countObject.add(singerId);
        Integer countrows = dao.count("select count(1) from song where singerId=?", countObject);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
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
                "songWords from song where singerId=? limit ?,?";
        List params = new ArrayList();
        params.add(singerId);
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        System.out.println(p.getBeginrows());
        System.out.println(p.getPagesize());
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);
    }

    /*排行榜的歌曲*/
    /*新歌榜*/
    public APIRequest getRankingList(Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from song order by songDate", null);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerName,"+
                "singerId," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "songWords from song order by songDate desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);

    }

    //热歌榜
    public APIRequest getRankingListloadHotSong(Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from song order by playNum", null);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerId," +
                "singerName," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "playNum," +
                "songWords from song order by playNum desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);
    }

    //欧美榜
    public APIRequest getRankingListAmericanSong(Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from song where singerId " +
                                                                     "in (select singerId from singer where singerLanguage like '%英语%') " +
                                                                     "order by playNum desc", null);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerId," +
                "singerName," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "playNum," +
                "songWords" +
                " from song " +
                "where singerId " +
                "in (select singerId from singer where singerLanguage like '%英语%') " +
                "order by playNum desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);
    }

    //韩语榜
    public APIRequest getRankingListHanYuSong(Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from song where singerId " +
                "in (select singerId from singer where singerLanguage like '%韩语%') " +
                "order by playNum desc", null);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerId," +
                "singerName," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "playNum," +
                "songWords" +
                " from song " +
                "where singerId " +
                "in (select singerId from singer where singerLanguage like '%韩语%') " +
                "order by playNum desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);
    }

    //飙升榜
    public APIRequest getRankingListSpeedSong(Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from song " +
                                                              "where singerId " +
                                                              "in (select singerId from singer where fansNum >200000) " +
                                                              "order by playNum desc", null);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerId," +
                "singerName," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "playNum," +
                "songWords" +
                " from song " +
                "where singerId " +
                "in (select singerId from singer where fansNum >200000) " +
                "order by playNum desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        for (Song qu : query) {
            System.out.println(qu.getSongName());
        }
        return new APIRequest(p);
    }

    public APIRequest getSongInfo(Integer songId){


        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        countObject.add(songId);

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
                "songWords from song where songId=?";
        List params = new ArrayList();
        params.add(songId);
        List<Song> query = dao.query(Song.class, sql, params);
        return new APIRequest(query);
    }

    public APIRequest getAlbumAllSongs(Integer songAlbumId,Integer indexpage){
        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        countObject.add(songAlbumId);
        Integer countrows = dao.count("select count(1) from song where songAlbumId=?", countObject);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerName,"+
                "singerId," +
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "songWords from song where songAlbumId=? limit ?,?";
        List params = new ArrayList();
        params.add(songAlbumId);
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        return new APIRequest(p);
    }


    /**
     * 删除歌曲的方法
     * @param songId
     * @return
     */
    public APIRequest deleteSong(Integer songId){
        APIRequest apiRequest=new APIRequest();
        if(dao.delete(songId)){
            apiRequest.setResult(true);
        }else{
            apiRequest.setResult(false);
        }
        return apiRequest;
    }

    /**
     * 查找歌曲的方法
     * @param currentPage   当前页_每页十个
     * @return
     */
    public APIRequest querySong(Integer currentPage){
        if (currentPage == null) {
            currentPage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录

        Integer countrows = dao.count("select count(1) from song ", null);

        Pager p = new Pager(countrows, currentPage);
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
                "songWords from song  limit ?,?";
        List params = new ArrayList();

        params.add(p.getBeginrows()-1);
        params.add(p.getPagesize());
        List<Object> query = dao.query(Song.class, sql, params);
        p.setData(query);

        return new APIRequest(p);
    }

    /**
     * 更新歌曲的方法
     * @param song
     * @return
     */
    public APIRequest updateSong(Song song){
        APIRequest apiRequest = new APIRequest();
        System.out.println("zz");

        if(dao.updateSong(song)){
            apiRequest.setResult(true);

            return apiRequest;
        }
        apiRequest.setResult(false);
        apiRequest.setMsg("更新失败了");
        return apiRequest;
    }

    /**
     * 通过序号查找歌曲
     * @param songId
     * @return
     */
    public APIRequest findById(Integer songId){
        APIRequest apiRequest=new APIRequest();
        try {
            apiRequest.setResult(true);
            apiRequest.setData(dao.queryById(Song.class,songId));

            return apiRequest;
        }catch (Exception e){
            e.printStackTrace();
        }

        apiRequest.setResult(false);
        apiRequest.setMsg("服务器处理错误");
        return apiRequest;
    }

    /**
     * 通过歌曲名或专辑名查找歌曲
     * @param songName
     * @return
     */
    public APIRequest findByName(String songName){

        APIRequest apiRequest = new APIRequest();
        List<Song> songs = dao.queryByName(Song.class,songName);
        if(songs==null){
            apiRequest.setResult(false);
        }else{
            apiRequest.setResult(true);
            apiRequest.setData(songs);
        }
        return apiRequest;
    }

    /**
     * 模糊查询歌曲信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    public APIRequest getSearchSongs(String searchWords, Integer indexpage) {
        System.out.println("----------"+searchWords);
        if (indexpage == null) {
            indexpage = 1;
        }
        System.out.println("search");
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        countObject.add(searchWords);
        Integer countrows = dao.count("select count(1) from song where songName like ?", countObject);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select songId," +
                "singerId," +
                "singerName,"+
                "songAlbumId," +
                "songAlbumName," +
                "songName," +
                "songTimeMinutes," +
                "songRid," +
                "songDate," +
                "pic120," +
                "pic300," +
                "songWords from song where songName like '"+searchWords+"%'"+" limit ?,?";
        List params = new ArrayList();
        System.out.println(sql);
        //  params.add(words);
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Song> query = dao.query(Song.class, sql, params);
        p.setData(query);
        System.out.println("queryNum:"+query.size());
        return new APIRequest(p);
    }

}


