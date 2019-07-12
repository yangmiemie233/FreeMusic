package com.kerwin.server.api;

import com.kerwin.server.pojo.Singer;
import com.kerwin.server.server.impls.SingerServerImpls;
import com.kerwin.server.server.impls.SongServerImpls;
import com.kerwin.server.utils.APIRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SingerAPI {

    SingerServerImpls server = new SingerServerImpls();

    @RequestMapping("loadsinger.do")
    public APIRequest query(Integer indexpage){
        return server.query(indexpage);
    }
    @RequestMapping("loadsingerInfo.do")
    public APIRequest querysingerInfo(Integer singerId){
        return server.querysingerInfo(singerId);
    }
    @RequestMapping("loadsingerInfo/getSingerByType.do")
    public APIRequest getSingerByType(String type,Integer indexpage){
        return server.getSingerByType(type,indexpage);
}


    /**
     * 加载当前页面歌手数据接口
     *
     * @param indexpage 页面传入的当前页码值
     * @return
     */
    @RequestMapping("aloadsinger.do")
    public APIRequest aquery(Integer indexpage) {

        return server.aquery(indexpage);
    }

    /**
     * 利用歌手id查询歌手信息
     *
     * @param singerId 歌手id
     * @return
     */
    @RequestMapping("aloadsingerInfo.do")
    public APIRequest aquerysingerInfo(Integer singerId) {
        return server.aquerysingerInfo(singerId);
    }


    /**
     * 利用歌手id删除当前歌手
     *
     * @param singerId 歌手id
     * @return
     */
    @RequestMapping("adelsinger.do")
    public APIRequest adelsinger(Integer singerId) {
        return server.adel(singerId);
    }


    /**
     * 接收前段传过来的修改的歌手对象修改歌手信息
     *
     * @param singerId 歌手id
     * @return
     */
    @RequestMapping("amodifysinger.do")
    public APIRequest aupdate(Integer singerId, String singerName,
                              String sex, String singerNation, String pic120,
                              String pic300, Integer fansNum, Integer songsNum,
                              Integer albumsNum, String singerBirthday, String prefix) {

        Singer singer = new Singer();

        singer.setSingerName(singerName);

        singer.setSingerId(singerId);

        singer.setSex(sex);

        singer.setSingerNation(singerNation);

        singer.setPic120(pic120);

        singer.setPic300(pic300);

        singer.setFansNum(fansNum);

        singer.setSongsNum(songsNum);

        singer.setAlbumsNum(albumsNum);

        singer.setSingerBirthday(singerBirthday);

        singer.setPrefix(prefix);

        System.out.println(singer.toString());

        return server.aupdatesingerInfo(singer);
    }

    @RequestMapping("aquerybysingername.do")
    public APIRequest aquerybysingername(String singerName) {

        System.out.println("query");
        return server.aqueryBySingerName(singerName);

    }

    /**
     * 模糊查询歌手信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    @RequestMapping("searchSinger.do")
    public APIRequest searchSinger(String searchWords,Integer indexpage){
        return server.searchSinger(searchWords,indexpage);
    }


}
