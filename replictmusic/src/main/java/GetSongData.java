
import jdbc2.JDBCUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GetSongData {

    public static void main(String[] args) throws Exception {
       String sql = "select albumId from album";
        List<Map<String, Object>> albumIdList = new JDBCUtil().executeQuery(sql, null);
        System.out.println("--------专辑长度-------" + albumIdList.size());
        List songInfo = new ArrayList();
        //System.out.println("专辑个数" + albumIdList.size());
        for (int k = 0; k < albumIdList.size(); k++) {
            System.out.println("已经爬到"+k+"个专辑了");
            Thread.sleep(4000);

            Integer albumId = (Integer) albumIdList.get(k).get("albumId");
            System.out.println("专辑id为" + albumId);

            Document songDoc = Jsoup.connect("http://www.kuwo.cn/api/www/album/albumInfo?albumId=" + albumId).ignoreContentType(true).get();
            JSONObject songObject ;
            try {
                songObject= new JSONObject(songDoc.body().html());


            if(!(songObject.isNull("data"))){
                JSONArray songList = songObject.getJSONObject("data").getJSONArray("musicList");

                if (songList != null) {
                    for (int i = 0; i < songList.length(); i++) {
                        JSONObject song = songList.getJSONObject(i);

                        //获取歌手id
                        String singerId = song.get("artistid").toString();
                        songInfo.add(singerId);
                        System.out.println("歌手id为" + singerId);
                        //获取歌手名字
                        String singerName = song.get("artist").toString();
                        songInfo.add(singerName);
                        //获取歌曲id
                        String songid = song.get("musicrid").toString();
                        String arrs[] = songid.split("_");
                        String songId = arrs[1];
                        songInfo.add(songId);

                        //存入歌曲所属专辑id
                        songInfo.add(albumId);

                        //获取歌曲所属专辑名字
                        String albumName = song.get("album").toString();
                        songInfo.add(albumName);

                        //获取歌曲名字
                        String songName = song.get("name").toString();
                        songInfo.add(songName);

                        //获取歌曲时长
                        String songTimeMinutes = song.get("songTimeMinutes").toString();
                        songInfo.add(songTimeMinutes);

                        //获取歌曲文件地址rid
                        String songRid = song.get("rid").toString();
                        songInfo.add(songRid);

                        //获取歌曲发布时间
//                        String songDate = song.get("releaseDate").toString();
//                        songInfo.add(songDate);

                         String songDate = song.get("releaseDate").toString();
                            Date date;
                            try {
                             date = new SimpleDateFormat("yyyy-MM-dd").parse(songDate);
                            }catch (Exception e){
                              Date date1=new Date();
                                Timestamp t = new Timestamp(date1.getTime());
                              date=t;
                            }
                            SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");

                            songInfo.add(timeFormat.format(date ));

                        //获取歌曲小图片
                        String pic120 = song.get("pic120").toString();
                        songInfo.add(pic120);

                        //获取歌曲大图片
                        String pic300 = song.get("pic").toString();
                        songInfo.add(pic300);

                        //获取歌词
                        String songWordsUrl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId=" + songId;
                        Document wordsDoc = Jsoup.connect(songWordsUrl).ignoreContentType(true).get();
                        String songWords = wordsDoc.body().html();
                        JSONObject words;
                        try {
                            words = new JSONObject(songWords);

                            JSONObject data = words.getJSONObject("data");

                            if (!(data.isNull("lrclist"))) {
                                JSONArray array = data.getJSONArray("lrclist");
                                String Words = "";
                                for (int s = 0; s < array.length(); s++) {
                                    String lineWord = "";
                                    JSONObject lineWords = array.getJSONObject(s);
                                    lineWord = lineWords.get("lineLyric").toString();
                                    boolean flag = lineWord.contains("'");
                                    if (flag) {
                                        lineWord = lineWord.replace("'", "*");
                                    }
                                    Words += lineWord;
                                    if (s < array.length() - 1) {
                                        Words += "/";
                                    }
                                }
                                songInfo.add(Words);

                                int playNum = (int) (Math.random() * 100000);
                                songInfo.add(playNum);

                                String sql1 = "insert into song(singerId,singerName,songId,songAlbumId,songAlbumName,songName,songTimeMinutes,songRid,songDate,pic120,pic300,songWords,playNum) values(";
                                for (int j = 0; j < songInfo.size(); j++) {
                                    sql1 += "'" + songInfo.get(j) + "'";
                                    if (j < songInfo.size() - 1) {
                                        sql1 += ",";
                                    }
                                }
                                sql1 += ")";
                                System.out.println(sql1);
                                try {
                                    new JDBCDB().jdbcprocess2(sql1);
                                } catch (Exception e) {

                                }
                                songInfo.clear();
                            }
                        }catch (Exception e){

                        }
                    }
            }

            }}catch (Exception e){

            }
        }
 /*     String sql = "select singerId from singer";
        List<Map<String, Object>> albumIdList = new JDBCUtil().executeQuery(sql, null);
        System.out.println(albumIdList);
        List songInfo = new ArrayList();
        for (int k = 0; k < albumIdList.size(); k++) {
            System.out.println("已经爬到" + k + "个歌手了");
            Thread.sleep(4000);

            Integer singerId = (Integer) albumIdList.get(k).get("singerId");
            System.out.println("歌手id为" + singerId);

            Document songDocument = Jsoup.connect(("http://www.kuwo.cn/api/www/artist/artistMusic?artistid=") + singerId + ("&pn=1&rn=50")).ignoreContentType(true).get();
            JSONObject songObject = new JSONObject(songDocument.body().html());

            if (songObject.has("data")) {

                if (songObject.getJSONObject("data") != null) {
                    JSONArray songList = songObject.getJSONObject("data").getJSONArray("list");
                    if (songList != null) {
                        for (int s = 0; s < songList.length(); s++) {
                            Thread.sleep(1000);
                            JSONObject songInfos = (JSONObject) songList.get(s);
                            System.out.println("----------------" + songInfos);
                            //获取歌手id
                            String singerid = songInfos.get("artistid").toString();
                            songInfo.add(singerid);
                            //获取歌手名字
                            String singerName = songInfos.get("artist").toString();
                            songInfo.add(singerName);
                            //获取歌曲id
                            String songid = songInfos.get("musicrid").toString();
                            String arrs[] = songid.split("_");
                            String songId = arrs[1];
                            songInfo.add(songId);

                            //存入歌曲所属专辑id
                            String albumid = songInfos.get("albumid").toString();
                            songInfo.add(albumid);

                            //获取歌曲所属专辑名字
                            String albumName = songInfos.get("album").toString();
                            songInfo.add(albumName);

                            //获取歌曲名字
                            String songName = songInfos.get("name").toString();
                            songInfo.add(songName);

                            //获取歌曲时长
                            String songTimeMinutes = songInfos.get("songTimeMinutes").toString();
                            songInfo.add(songTimeMinutes);

                            //获取歌曲文件地址rid
                            String songRid = songInfos.get("rid").toString();
                            songInfo.add(songRid);

                            //获取歌曲发布时间
                            String songDate = songInfos.get("releaseDate").toString();
                            Date date;
                            try {
                             date = new SimpleDateFormat("yyyy-MM-dd").parse(songDate);
                            }catch (Exception e){
                              Date date1=new Date();
                                Timestamp t = new Timestamp(date1.getTime());
                              date=t;
                            }
                            SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");

                            songInfo.add(timeFormat.format(date ));

                            //获取歌曲小图片
                            String pic120 = songInfos.get("pic120").toString();
                            songInfo.add(pic120);

                            //获取歌曲大图片
                            String pic300 = songInfos.get("pic").toString();
                            songInfo.add(pic300);

                            //获取歌词
                            String songWordsUrl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId=" + songId;
                            Document wordsDoc = Jsoup.connect(songWordsUrl).ignoreContentType(true).get();
                            String songWords = wordsDoc.body().html();
                            JSONObject words = new JSONObject(songWords);
                            JSONObject data = words.getJSONObject("data");


                            if (!(data.isNull("lrclist"))) {
                                JSONArray array = data.getJSONArray("lrclist");

                                String Words = "";
                                for (int m = 0; m < array.length(); m++) {
                                    String lineWord = "";
                                    JSONObject lineWords = array.getJSONObject(m);
                                    lineWord = lineWords.get("lineLyric").toString();
                                    boolean flag = lineWord.contains("'");
                                    if (flag) {
                                        lineWord = lineWord.replace("'", "*");
                                    }
                                    Words += lineWord;
                                    if (m < array.length() - 1) {
                                        Words += "/";
                                    }
                                }
                                songInfo.add(Words);

                                int playNum = (int) (Math.random() * 100000);
                                songInfo.add(playNum);
                               // System.out.println("=================" + songInfo);
                                String sql1 = "insert into song(singerId,singerName,songId,songAlbumId,songAlbumName,songName,songTimeMinutes,songRid,songDate,pic120,pic300,songWords,playNum) values(";
                                for (int j = 0; j < songInfo.size(); j++) {
                                    sql1 += "'" + songInfo.get(j) + "'";
                                    if (j < songInfo.size() - 1) {
                                        sql1 += ",";
                                    }
                                }
                                sql1 += ")";
                                System.out.println(sql1);
                                try {
                                    new JDBCDB().jdbcprocess2(sql1);
                                }catch (Exception e){

                                }

                                songInfo.clear();
                            }
                        }*/
         /*           }
                }
            }
        }*/
    }
}

