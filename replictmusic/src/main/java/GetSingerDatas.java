


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetSingerDatas {
    public static void main(String []args)throws Exception{
        char []prefixs = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int m=6;m<prefixs.length;m++) {
            char prefix = prefixs[m];
            Document document= Jsoup.connect("http://www.kuwo.cn/api/www/artist/artistInfo?prefix=" + prefix + "&pn=1&rn=15").ignoreContentType(true).get();
            String body = document.body().html();

            List singerinfo = new ArrayList();

            JSONObject bodyjson = new JSONObject(body);

            JSONArray jsonArray = bodyjson.getJSONObject("data").getJSONArray("artistList");

            int result = 0;

            for (int k = 0; k < jsonArray.length(); k++) {

                Thread.sleep(5000);

                JSONObject singerdata = jsonArray.getJSONObject(k);

                //获取歌手id
                String singerid = singerdata.get("id").toString();
                singerinfo.add(singerid);

                //获取歌手粉丝数量
                String funsnum = singerdata.get("artistFans").toString();
                singerinfo.add(funsnum);

                //获取歌手图片
                String singerpic300 = singerdata.get("pic300").toString();
                String singerpic120 = singerdata.get("pic120").toString();
                singerinfo.add(singerpic120);
                singerinfo.add(singerpic300);

                //获取歌手歌曲数量
                String songNum = singerdata.get("musicNum").toString();
                singerinfo.add(songNum);

                //获取歌手专辑数量
                String albumsNum = singerdata.get("albumNum").toString();
                singerinfo.add(albumsNum);

                //获取歌手详细信息
                String singerurl = "http://www.kuwo.cn/singer_detail/"+singerid+"/info";
                Document singerdocument = Jsoup.connect(singerurl).ignoreContentType(true).get();
                Elements select = singerdocument.body().select("span[data-v-71746b0d]");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    if(i % 2 != 0){
                        Element element = select.get(i);
                        singerinfo.add(element.html());

                    }
                }
                singerinfo.add(prefix);
                System.out.println(singerinfo.toString());
                String sql = "insert into singer(singerId,fansNum,pic120,pic300,songsNum,albumsNum,singerName,singerEnglishName," +
                        "sex,singerNation,singerArea,singerLanguage,singerBirthday,constellation,singerHeight,singerWeight,prefix) " +
                        "values(";

                for (int i = 0; i < singerinfo.size(); i++) {
                    sql += "'"+singerinfo.get(i)+"'";
                    if(i < singerinfo.size() - 1){
                        sql+= ",";
                    }
                }
                sql += ")";
                 new JDBCDB().jdbcprocess2(sql);
                 singerinfo.clear();
            }
        }

    }

/*    public void SingerData(int Pn,int Rn,String[] arr) throws Exception{
        Document document= Jsoup.connect("http://www.kuwo.cn/api/www/artist/artistInfo?category=0&pn="+Pn+"&rn="+Rn).ignoreContentType(true).get();
        String body = document.body().html();

        List singerinfo = new ArrayList();

        JSONObject bodyjson = new JSONObject(body);

        JSONArray jsonArray = bodyjson.getJSONObject("data").getJSONArray("artistList");


        int result = 0;

        for (int k = 0; k < jsonArray.length(); k++) {

                Thread.sleep(2000);


            JSONObject singerdata = jsonArray.getJSONObject(k);

            //获取歌手id
            String singerid = singerdata.get("id").toString();
            singerinfo.add(singerid);

            //获取歌手粉丝数量
            String funsnum = singerdata.get("artistFans").toString();
            singerinfo.add(funsnum);

            //获取歌手图片
            String singerpic300 = singerdata.get("pic300").toString();
            String singerpic120 = singerdata.get("pic120").toString();
            singerinfo.add(singerpic120);
            singerinfo.add(singerpic300);

            //获取歌手歌曲数量
            String songNum = singerdata.get("musicNum").toString();
            singerinfo.add(songNum);

            //获取歌手专辑数量
            String albumsNum = singerdata.get("albumNum").toString();
            singerinfo.add(albumsNum);

            //获取歌手详细信息
            String singerurl = "http://www.kuwo.cn/singer_detail/"+singerid+"/info";
            Document singerdocument = Jsoup.connect(singerurl).ignoreContentType(true).get();
            Elements select = singerdocument.body().select("span[data-v-71746b0d]");
            int size = select.size();
            for (int i = 0; i < size; i++) {
                if(i % 2 != 0){
                    Element element = select.get(i);
                    singerinfo.add(element.html());

                }
            }

            System.out.println(singerinfo.toString());
            String sql = "insert into singer(singerId,fansNum,pic120,pic300,songsNum,albumsNum,singerName,singerEnglishName," +
                    "sex,singerNation,singerArea,singerLanguage,singerBirthday,constellation,singerHeight,singerWeight) " +
                    "values(";

            for (int i = 0; i < singerinfo.size(); i++) {
                sql += "'"+singerinfo.get(i)+"'";
                if(i < singerinfo.size() - 1){
                    sql+= ",";
                }
            }
            sql += ")";
            arr[k]=sql;
            // new JDBCDB().jdbcprocess2(sql);
            singerinfo.clear();
        }
        result = new JDBCUtil().executeUpdate(arr);
        if(result!=0){
            System.out.println("成功插入"+result+"条数据");
        }else{
            System.out.println("插入失败！");
        }
    }

    public static void main(String []args)throws Exception{
        //定义一共需要插入多少条数据
        int totalSingers = 300;

        //还剩多少条数据没有插入
        int rest = totalSingers;

        //已经插入的数据条数
        int sum = 0;

        //第几页数据开始
        int pn = 1;

        //每页多少条数据
        int rn = 10;

        //用来接收sql语句
        String [] arr = null;


        while(totalSingers>sum&&rest>0){
            GetSingerDatas getSingerDatas = new GetSingerDatas();
            if(rn > rest)
            {
                arr = new String [rn];
                getSingerDatas.SingerData(pn,rn,arr);
            }else{
                rn = rest;
                arr = new String [rn];
                getSingerDatas.SingerData(pn,rn,arr);
            }
            sum=pn*rn;
            pn+=1;
            rest-=rn;
        }

    }*/
}
