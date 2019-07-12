
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

public class getAlbumDatas {
    public static void main(String[] args) throws Exception {
        String sql1 = "select singerId from singer";
        System.out.println(sql1);
        List<Map<String, Object>> singerIdList = new JDBCUtil().executeQuery(sql1, null);
        System.out.println("歌手个数" + singerIdList.size());
        for (int k = 0; k < singerIdList.size(); k++) {
            Thread.sleep(5000);
            Integer singerId = (Integer) singerIdList.get(k).get("singerId");
            System.out.println("singerId为"+singerId);
            Document albumDoc = Jsoup.connect("http://www.kuwo.cn/api/www/artist/artistAlbum?artistid=" + singerId + "&pn=1 &rn=5").ignoreContentType(true).get();
            String album = albumDoc.body().html();
            JSONObject jsonObject;
            try {
               jsonObject = new JSONObject(album);

           /* if (jsonObject.getJSONObject("data") != null)*/
            if (!(jsonObject.isNull("data")))
            {
                JSONArray albumList = jsonObject.getJSONObject("data").getJSONArray("albumList");
                List albuminfo = new ArrayList();
                if(albumList!=null) {
                    for (int i = 0; i < albumList.length(); i++) {

                        JSONObject albumInfo = albumList.getJSONObject(i);

                        //获取专辑id
                        String albumId = albumInfo.get("albumid").toString();
                        albuminfo.add(albumId);
                        System.out.println("专辑ID为"+albumId);
                        //存入专辑所属歌手id
                        albuminfo.add(singerId);

                        //获取专辑简介
                        String albumMessage = albumInfo.get("albuminfo").toString();
                        albuminfo.add(albumMessage);

                        //获取专辑歌手名
                        String albumSingerName = albumInfo.get("artist").toString();
                        albuminfo.add(albumSingerName);

                        //获取专辑名
                        String albumName = albumInfo.get("album").toString();
                        albuminfo.add(albumName);

                        //获取专辑发布日期
                      /*  String albumDate = albumInfo.get("releaseDate").toString().trim();
                        System.out.println("--------------"+albumDate+"----------------------");
                        albuminfo.add(albumDate)*/;



                        String albumDate = albumInfo.get("releaseDate").toString().trim();
                        Date date;
                        try {
                            date = new SimpleDateFormat("yyyy-MM-dd").parse(albumDate);
                        }catch (Exception e){
                            Date date1=new Date();
                            Timestamp t = new Timestamp(date1.getTime());
                            date=t;
                        }
                        SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");

                        albuminfo.add(timeFormat.format(date ));



                        //获取专辑图片
                        String albumImg = albumInfo.get("pic").toString();
                        albuminfo.add(albumImg);
                        //获取专辑语种
                        String albumPicLag = albumInfo.get("lang").toString();
                        albuminfo.add(albumPicLag);

                        System.out.println("----------------------" + albumInfo);
                        String sql = "insert into album(albumId,albumSingerId,albumMessage,albumSingerName,albumName,albumDate,albumImg,albumPicLag) values(";
                        for (int j = 0; j < albuminfo.size(); j++) {
                            sql += "'" + albuminfo.get(j) + "'";
                            if (j < albuminfo.size() - 1) {
                                sql += ",";
                            }
                        }
                        sql += ")";
                       try {
                           new JDBCDB().jdbcprocess2(sql);
                       }catch (Exception e){

                       }
                        System.out.println(sql);
                        albuminfo.clear();
                    }
                }
            }   }catch (Exception e){

            }
        }
    }
}

