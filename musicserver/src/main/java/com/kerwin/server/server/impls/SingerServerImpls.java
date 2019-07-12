package com.kerwin.server.server.impls;

import com.kerwin.server.dao.impls.singer.SingerDaoImpls;
import com.kerwin.server.dao.interfaces.singer.ISingerDao;
import com.kerwin.server.pojo.Singer;
import com.kerwin.server.utils.APIRequest;
import com.kerwin.server.utils.Pager;

import java.util.ArrayList;
import java.util.List;

public class SingerServerImpls {

    ISingerDao dao = new SingerDaoImpls();

    //歌手推进部分查询
    public APIRequest query(Integer indexpage) {

        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from singer", null);
        //2.把总记录书交给工具运算
        Pager p = new Pager(countrows, indexpage);
        //3.运算结果作为参数传递
        String sql = "select singerId," +
                "singerName," +
                "singerEnglishName," +
                "sex,singerNation," +
                "singerArea," +
                "singerLanguage," +
                "constellation," +
                "pic120," +
                "pic300," +
                "fansNum," +
                "songsNum," +
                "albumsNum," +
                "singerWeight," +
                "singerHeight," +
                "singerBirthday," +
                "prefix from singer order by fansNum desc limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows());
        params.add(p.getPagesize());
        //4.提取数据
        List<Singer> query = dao.query(Singer.class, sql, params);

        //5。将数据封装到工具当中。因为，前台还需要工具的数据
        p.setData(query);

        return new APIRequest(p);

    }

    //歌手详情页信息
    public APIRequest querysingerInfo(Integer singerId) {
        String sql = "select singerId," +
                "singerName," +
                "singerEnglishName," +
                "sex,singerNation," +
                "singerArea," +
                "singerLanguage," +
                "constellation," +
                "pic120," +
                "pic300," +
                "fansNum," +
                "songsNum," +
                "albumsNum," +
                "singerWeight," +
                "singerHeight," +
                "singerBirthday," +
                "prefix from singer where singerId=?";
        List params = new ArrayList();
        params.add(singerId);
        List<Singer> query = dao.query(Singer.class, sql, params);
        return new APIRequest(query);

    }

    //歌手种类查询
    public APIRequest getSingerByType(String type, Integer indexpage) {
        if (indexpage == null) {
            indexpage = 1;
        }
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(type);

        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from singer where prefix=?", objectList);

        //2.把总记录书交给工具运算
        Pager p = new Pager(countrows, indexpage);
        //3.运算结果作为参数传递
        String sql = "select singerId," +
                "singerName," +
                "singerEnglishName," +
                "sex,singerNation," +
                "singerArea," +
                "singerLanguage," +
                "constellation," +
                "pic120," +
                "pic300," +
                "fansNum," +
                "songsNum," +
                "albumsNum," +
                "singerWeight," +
                "singerHeight," +
                "singerBirthday," +
                "prefix from singer where prefix=? order by fansNum desc limit ?,?";
        List params = new ArrayList();
        params.add(type);
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        //4.提取数据
        List<Singer> query = dao.query(Singer.class, sql, params);

        //5。将数据封装到工具当中。因为，前台还需要工具的数据
        p.setData(query);

        return new APIRequest(p);

    }


    public APIRequest login(String username, String password) {


        APIRequest request = null;
        if (username == null) {
            return new APIRequest(false, "用户名不能为空");
        } else if (password == null) {
            return new APIRequest(false, "密码不能为空");
        }


        return request;

    }

    public APIRequest aquery(Integer indexpage) {


        if (indexpage == null) {
            indexpage = 1;
        }


        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        Integer countrows = dao.count("select count(1) from singer", null);


        //2.把总记录书交给工具运算
        Pager p = new Pager(countrows, indexpage);


        //3.运算结果作为参数传递
        String sql = "select  singerId, singerName,singerEnglishName,sex,singerNation," +
                "singerArea,singerLanguage,constellation,pic120,pic300,fansNum,songsNum,albumsNum," +
                "singerWeight,singerHeight,singerBirthday,prefix from singer limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows());
        params.add(p.getPagesize());
        //4.提取数据
        List<Object> query = dao.query(Singer.class, sql, params);

        //5。将数据封装到工具当中。因为，前台还需要工具的数据
        p.setData(query);

        return new APIRequest(p);

    }

    //歌手详情页信息
    public APIRequest aquerysingerInfo(Integer singerId) {
        String sql = "select singerId," +
                "singerName," +
                "singerEnglishName," +
                "sex,singerNation," +
                "singerArea," +
                "singerLanguage," +
                "constellation," +
                "pic120," +
                "pic300," +
                "fansNum," +
                "songsNum," +
                "albumsNum," +
                "singerWeight," +
                "singerHeight," +
                "singerBirthday," +
                "prefix from singer where singerId=?";
        List params = new ArrayList();
        params.add(singerId);
        List<Singer> query = dao.query(Singer.class, sql, params);
        return new APIRequest(query);

    }


    public APIRequest aupdatesingerInfo(Singer singer) {
        String sql = "update singer set ";

        if (singer.getSingerName() != null) {
            sql += " singerName = " + "'" + singer.getSingerName() + "' ,";

        }

        if (singer.getConstellation() != null) {
            sql += " constellation = " + "'" + singer.getConstellation() + "' ,";
        }

        if (singer.getFansNum() > 0) {
            sql += " fansNum = " + singer.getFansNum() + " ,";

        }

        if (singer.getPic120() != null) {
            sql += " pic120 = " + "'" + singer.getPic120() + "' ,";
        }

        if (singer.getPic300() != null) {
            sql += " pic300 = " + "'" + singer.getPic300() + "' ,";
        }

        if (singer.getSex() != null) {
            sql += " sex = " + "'" + singer.getSex() + "' ,";
        }

        if (singer.getSingerArea() != null) {
            sql += " singerArea = " + "'" + singer.getSingerArea() + "' ,";
        }

        if (singer.getSingerBirthday() != null) {
            sql += " singerBirthday = " + "'" + singer.getSingerBirthday() + "' ,";
        }

        if (singer.getSingerEnglishName() != null) {
            sql += " singerEnglishName = " + "'" + singer.getSingerEnglishName() + "' ,";
        }

        if (singer.getSingerHeight() != null) {
            sql += " singerHeight = " + "'" + singer.getSingerHeight() + "' ,";
        }

        if (singer.getSingerWeight() != null) {
            sql += " singerWeight = " + "'" + singer.getSingerWeight() + "' ,";
        }

        if (singer.getSingerLanguage() != null) {
            sql += " singerLanguage = " + "'" + singer.getSingerLanguage() + "' ,";
        }

        if (singer.getSingerNation() != null) {
            sql += " singerNation = " + "'" + singer.getSingerNation() + "' ,";
        }

        if (singer.getFansNum() > 0) {
            sql += " fansNum = " + singer.getFansNum() + " ,";
        }

        if (singer.getAlbumsNum() > 0) {
            sql += " albumsNum = " + singer.getAlbumsNum() + " ,";
        }

        if (singer.getPrefix() != null) {
            sql += " prefix = " + "'" + singer.getPrefix() + "' ";
        }


        sql += " where singerId = ? ";

        System.out.println(sql);

        List params = new ArrayList();
        params.add(singer.getSingerId());

        boolean flg = dao.update(sql, params);

        return new APIRequest(flg);

    }




    public APIRequest adel(Integer singerId) {

        List params = new ArrayList();
        System.out.println(singerId);
        String sql = "delete from singer where singerId = ? ";
        params.add(singerId);
        boolean flg = dao.del(sql, params);
        return new APIRequest(flg);

    }

    public APIRequest aadd(Singer singer) {
        boolean flg = false;

        flg = dao.add(singer);

        return new APIRequest(flg);

    }

    public APIRequest aqueryBySingerName(String singerName) {

        String sql = "select singerId," +
                "singerName," +
                "singerEnglishName," +
                "sex,singerNation," +
                "singerArea," +
                "singerLanguage," +
                "constellation," +
                "pic120," +
                "pic300," +
                "fansNum," +
                "songsNum," +
                "albumsNum," +
                "singerWeight," +
                "singerHeight," +
                "singerBirthday," +
                "prefix from singer where  singerName like  '" + singerName + "%'";

        List params = new ArrayList();

        System.out.println(sql);

        List<Singer> query = dao.query(Singer.class, sql, params);

        query.toArray().toString();

        return new APIRequest(query);
    }

    /**
     * 模糊查询歌手信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    public APIRequest searchSinger(String searchWords, Integer indexpage) {
        System.out.println("获取到的参数值"+searchWords);
        if (indexpage == null) {
            indexpage = 1;
        }
        //1.去数据库里面找你这条 sql语句所产生了多少条记录
        List<Object> countObject = new ArrayList<Object>();
        countObject.add(searchWords);
        Integer countrows = dao.count("select count(1) from singer where singerName like ?", countObject);
        System.out.println(countrows);
        Pager p = new Pager(countrows, indexpage);
        String sql = "select singerId," +
                "singerName," +
                "songsNum," +
                "pic120 from singer where singerName like '"+searchWords+"%'"+" limit ?,?";
        List params = new ArrayList();
        params.add(p.getBeginrows() - 1);
        params.add(p.getPagesize());
        List<Singer> query = dao.query(Singer.class, sql, params);
        p.setData(query);
        return new APIRequest(p);
    }

}
