package com.kerwin.server.api;

import com.kerwin.server.pojo.Album;
import com.kerwin.server.server.impls.AlbumServerImpls;
import com.kerwin.server.utils.APIRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumAPI {
    AlbumServerImpls albumServerImpls = new AlbumServerImpls();

    @RequestMapping("loadsingerInfo/singerAlbums.do")
    public APIRequest singerAlbums(Integer singerId, Integer indexpage) {
        return albumServerImpls.querysingerInfoAlbums(singerId, indexpage);
    }

    //专辑信息加载
    @RequestMapping("loadSongAblum.do")
    public APIRequest loadSongAblum(Integer albumId) {
        return albumServerImpls.getAlbuminfo(albumId);
    }


    /**
     * 加载当前页面专辑数据接口
     *
     * @param indexpage 页面传入的当前页码值
     * @return
     */
    @RequestMapping("aloadalbum.do")
    public APIRequest aquery(Integer indexpage) {

        return albumServerImpls.aquery(indexpage);
    }

    /**
     * 当前页面传入的专辑id查询专辑
     *
     * @param albumId 当前页面传入的专辑id
     * @return
     */
    @RequestMapping("aquerybyalbumid.do")
    public APIRequest aqueryByAlbumId(Integer albumId) {

        System.out.println("query");
        return albumServerImpls.aqueryByAlbumId(albumId);

    }

    /**
     * 当前页面传入的专辑名查询专辑
     *
     * @param albumName 当前页面传入的专辑名
     * @return
     */
    @RequestMapping("aquerybyalbumname.do")
    public APIRequest aqueryByAlbumName(String albumName) {

        System.out.println("query");
        return albumServerImpls.aqueryByAlbumName(albumName);

    }

    /**
     * 当前页面传入专辑id删除此专辑
     *
     * @param albumId 当前页面传入的专辑id
     * @return
     */
    @RequestMapping("adelalbum.do")
    public APIRequest adelalbum(Integer albumId) {
        return albumServerImpls.adel(albumId);
    }


    /**
     * 当前页面传入专辑id以及修改当前的专辑
     *
     * @param albumId 当前页面传入的专辑id。。。
     * @return
     */
    @RequestMapping("amodifyalbum.do")
    public APIRequest aupdate(Integer albumId, String albumName,
                              String albumImg, String albumPicLag){


        Album album = new Album();
        album.setAlbumId(albumId);
        album.setAlbumName(albumName);
        album.setAlbumImg(albumImg);
        album.setAlbumPicLag(albumPicLag);

        album.toString();

        return albumServerImpls.aupdateAlbumInfo(album);
    }

    /**
     * 模糊查询专辑信息
     * @param searchWords
     * @param indexpage
     * @return
     */
    @RequestMapping("searchAlbum.do")
    public APIRequest searchAlbum(String searchWords,Integer indexpage){
        return albumServerImpls.getSearchAlbum(searchWords,indexpage);
    }

}
