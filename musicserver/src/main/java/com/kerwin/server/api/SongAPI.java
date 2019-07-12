package com.kerwin.server.api;

import com.kerwin.server.pojo.Song;
import com.kerwin.server.server.impls.AlbumServerImpls;
import com.kerwin.server.server.impls.SingerServerImpls;
import com.kerwin.server.server.impls.SongServerImpls;
import com.kerwin.server.utils.APIRequest;
import org.jsoup.Jsoup;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SongAPI {

    SongServerImpls songServerImpls=new SongServerImpls();


    @RequestMapping("loadsingerInfo/singerSongs.do")
    public APIRequest singerInfoSongs(Integer singerId,Integer indexpage){
        return songServerImpls.querysingerInfoSongs(singerId,indexpage);
    }

    //新歌
    @RequestMapping("loadNewSong.do")
    public APIRequest loadNewSong(Integer indexpage){
        return songServerImpls.getRankingList(indexpage);
    }

    //热歌
    @RequestMapping("loadHotSong.do")
    public APIRequest loadHotSong(Integer indexpage){
        return songServerImpls.getRankingListloadHotSong(indexpage);
    }

    //飙升榜按照歌手粉丝数最前的歌手所唱的歌曲的播放量
    @RequestMapping("loadSpeedSong.do")
    public APIRequest loadSpeedSong(Integer indexpage){
        return songServerImpls.getRankingListSpeedSong(indexpage);
    }

    //欧美榜
    @RequestMapping("loadAmericanSong.do")
    public APIRequest loadAmericanSong(Integer indexpage){
        return songServerImpls.getRankingListAmericanSong(indexpage);
    }

    //韩语榜
    @RequestMapping("loadHanYuSong.do")
    public APIRequest loadHanYuSong(Integer indexpage){
        return songServerImpls.getRankingListHanYuSong(indexpage);
    }

    @RequestMapping("getMusic.do")
    public APIRequest query(Integer songRid){

        System.out.println(songRid);

        APIRequest api = new APIRequest();
        try {
            //爬取数据
            Document doc= Jsoup.connect("http://www.kuwo.cn/url?format=mp3&rid="+songRid+"&response=url&type=convert_url3&br=128kmp3&from=web&t=1562634060138&reqId=0430a3b0-a1e5-11e9-9c73-cd06653dcf31").ignoreContentType(true).get();
            //解析
            Element body = doc.body();
            JSONObject obj = new JSONObject(body.html());
            Object url = obj.get("url");
            api.setData(url);

            System.out.println("---------------");
            System.out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return api;
    }

    @RequestMapping("getOneSongInfo.do")
    public APIRequest getOneSongInfo(Integer songId){
        return  songServerImpls.getSongInfo(songId);
    }

    @RequestMapping("getAlbumAllSongs.do")
    public  APIRequest getAlbumAllSongs(Integer songAlbumId,Integer indexpage){
        return songServerImpls.getAlbumAllSongs(songAlbumId,indexpage);
    }


    /**
     * 显示所有的歌曲
     * @param currentPage
     * @return
     */
    @RequestMapping("/main/ShowAllSong.do")
    public APIRequest showAllSong(Integer currentPage){
        return songServerImpls.querySong(currentPage);
    }

    /**
     * 通过序号删除歌曲
     * @param id
     * @return
     */
    @RequestMapping("/main/deleteSong.do")
    public APIRequest deleteSong(Integer id){
        return songServerImpls.deleteSong(id);
    }

    /**
     * 通过Id查找歌曲 -- 编辑方法
     * @param Id
     * @return
     */
    @RequestMapping("/main/editSong.do")
    public APIRequest findById(Integer Id){
        return songServerImpls.findById(Id);
    }

    /**
     * 通过歌曲名或专辑名查找
     * @param name
     * @return
     */
    @RequestMapping("/main/findSong.do")
    public APIRequest queryByName(String name){
        if(name == null){
            APIRequest apiRequest = new APIRequest();
            apiRequest.setResult(false);
            apiRequest.setMsg("值不为空");
            return apiRequest;
        }else {
            return songServerImpls.findByName(name);
        }
    }

    /**
     * 更新歌曲的方法
     * @return
     */
    @RequestMapping("/main/updateSong.do")
    public APIRequest updateSong(Integer songId,
                                 String songName,
                                 String songTimeMinutes,
                                 Integer songRid,
                                 String songDate,
                                 String pic120,
                                 String pic300) throws ParseException {

        if(songId!=null && songName!=null && songTimeMinutes !=null &&
                songRid!=null && songDate !=null && pic120 !=null && pic300 !=null){

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date=sdf.parse(songDate);
            Song song = new Song(songId,songName,songTimeMinutes,songRid,date,pic120,pic300);

            return songServerImpls.updateSong(song);
        }else{
            APIRequest apiRequest=new APIRequest();

            return apiRequest;
        }
    }

    /**
     * 模糊查询歌曲信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    @RequestMapping("searchSongs.do")
    public APIRequest searchSongs(String searchWords,Integer indexpage){
        return songServerImpls.getSearchSongs(searchWords,indexpage);
    }

}
