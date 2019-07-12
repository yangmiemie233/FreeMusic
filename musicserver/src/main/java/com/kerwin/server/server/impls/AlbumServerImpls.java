package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.album.AlbumDaoImpls;
import com.kerwin.server.dao.interfaces.album.IAlbumDao;
import com.kerwin.server.pojo.Album;
import com.kerwin.server.pojo.Song;
import com.kerwin.server.utils.APIRequest;
import com.kerwin.server.utils.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumServerImpls {
    IAlbumDao dao = new AlbumDaoImpls();

    public APIRequest querysingerInfoAlbums(Integer singerId, Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject=new ArrayList<Object>();
        countObject.add(singerId);
        Integer countrows = dao.count("select count(1) from album where albumSingerId=?", countObject);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select albumId," +
                "albumSingerId," +
                "albumSingerName," +
                "albumName," +
                "albumDate," +
                "albumImg," +
                "albumMessage," +
                "albumPicLag from album where albumSingerId=? limit ?,?";
        List params = new ArrayList();
        params.add(singerId);
        params.add(p.getBeginrows()-1);
        params.add(p.getPagesize());
        List<Album> query = dao.query(Album.class, sql, params);
        p.setData(query);
        System.out.println(p.getBeginrows());
        System.out.println(p.getPagesize());
        for (Album qu:query){
            System.out.println(qu.getAlbumName());
        }
        return new APIRequest(p);
    }

    public APIRequest getAlbuminfo(Integer albumId){


        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject=new ArrayList<Object>();
        countObject.add(albumId);

        String sql = "select albumId," +
                "albumSingerId," +
                "albumSingerName," +
                "albumName," +
                "albumDate," +
                "albumImg," +
                "albumMessage," +
                "albumPicLag from album where albumId=?";
        List params = new ArrayList();
        params.add(albumId);

        List<Album> query = dao.query(Album.class, sql, params);
        for (Album qu:query){
            System.out.println(qu.getAlbumName());
        }
        return new APIRequest(query);
    }



    /*学威*/

    public APIRequest aquery(Integer indexpage) {

        if (indexpage == null) {
            indexpage = 1;
        }

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from album", null);

        //2.把总记录书交给工具运算
        Pager p = new Pager(countrows, indexpage);

        //3.运算结果作为参数传递
        String sql = "select albumId," +
                "albumSingerId," +
                "albumSingerName," +
                "albumName," +
                "albumDate," +
                "albumImg," +
                "albumMessage," +
                "albumPicLag from album limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows());
        params.add(p.getPagesize());


        List<Map<String,Object>> query = dao.query(sql,params);

        List<Object> datas = new ArrayList<Object>();
        datas.add(query);

        p.setData(datas);

        return new APIRequest(p);

    }

    public APIRequest aqueryByAlbumId(Integer albumId) {

        String sql = "select albumId," +
                "albumSingerId," +
                "albumSingerName," +
                "albumName," +
                "albumDate," +
                "albumImg," +
                "albumMessage," +
                "albumPicLag from album where albumId = ? ";

        List params = new ArrayList();

        params.add(albumId);

        System.out.println(sql);


        List<Map<String,Object>> query = dao.query(sql,params);

        query.toArray().toString();

        return new APIRequest(query);


    }

    public APIRequest aadd(Album album) {

        System.out.println(album.toString());

        boolean flg = false;

        flg = dao.add(album);

        return new APIRequest(flg);
    }

    public APIRequest adel(Integer albumId) {

        System.out.println(albumId);
        List params = new ArrayList();
        String sql = "delete from album where albumId =? ";
        params.add(albumId);
        boolean flg = dao.del(sql, params);
        return new APIRequest(flg);
    }

    public APIRequest aupdateAlbumInfo(Album album) {
        String sql = "update album set ";

        if (album.getAlbumName() != null) {
            sql += " albumName = " + "'" + album.getAlbumName() + "'" + " , ";

        }

        if (album.getAlbumSingerId() != null) {
            sql += " albumSingerId = " + album.getAlbumSingerId() + " , ";
        }

        if (album.getAlbumSingerName() != null) {
            sql += " albumSingerName = " + "'" + album.getAlbumSingerName() + "'" + " , ";
        }

        if (album.getAlbumDate() != null) {
            sql += " albumDate = " + "'" + album.getAlbumDate() + "'" + " , ";
        }

        if (album.getAlbumImg() != null) {
            sql += " albumImg = " + "'" + album.getAlbumImg() + "'" + ",";
        }

        if (album.getAlbumMessage() != null) {
            sql += " albumMessage = " + "'" + album.getAlbumMessage() + "'" + " , ";
        }

        if (album.getAlbumPicLag() != null) {
            sql += " albumPicLag = " + "'" + album.getAlbumPicLag() + "'";
        }


        System.out.println(sql);

        sql += " where albumId = ? ";

        System.out.println(sql);

        List params = new ArrayList();
        params.add(album.getAlbumId());

        boolean flg = dao.update(sql, params);

        return new APIRequest(flg);

    }

    public APIRequest aqueryByAlbumName(String albumName) {
        String sql = "select albumId," +
                "albumSingerId," +
                "albumSingerName," +
                "albumName," +
                "albumDate," +
                "albumImg," +
                "albumMessage," +
                "albumPicLag from album where albumName like '" + albumName + "%'";

        List params = new ArrayList();


        System.out.println(sql);

        List<Map<String,Object>> query = dao.query(sql,params);

        return new APIRequest(query);

    }

    /**
     * 模糊查询专辑信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    public APIRequest getSearchAlbum(String searchWords, Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        System.out.println("搜索的数据999999999999999999"+searchWords);
        countObject.add(searchWords);
        Integer countrows = dao.count("select count(1) from album where albumName like ?", countObject);
        System.out.println("++++++++++++++++++++++++++++++"+countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select albumId," +
                "albumName," +
                "albumImg," +
                "albumDate from album where albumName like '"+searchWords+"%'"+" limit ?,?";
        List params = new ArrayList();
        System.out.println(sql);
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        System.out.println(params.size());
        List<Album> query = dao.query(Album.class, sql, params);
        p.setData(query);
        return new APIRequest(p);
    }


}
