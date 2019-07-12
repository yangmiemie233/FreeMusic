package com.kerwin.server.pojo;

import jdbc2.Column;

import java.util.Date;

public class Album {
    @Column("albumId")
    private Integer albumId;//专辑序号
    @Column("albumSingerId")
    private Integer albumSingerId;//专辑对应歌手的id
    @Column("albumSingerName")
    private String albumSingerName;//发布者名字
    @Column("albumName")
    private String albumName;//专辑名称
    @Column("albumDate")
    private Date albumDate;//辑专发行时间
    @Column("albumImg")
    private String albumImg;//专辑图片
    @Column("albumMessage")
    private String albumMessage;//专辑简介
    @Column("albumPicLag")
    private String albumPicLag;//专辑语种

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getAlbumSingerId() {
        return albumSingerId;
    }

    public void setAlbumSingerId(Integer albumSingerId) {
        this.albumSingerId = albumSingerId;
    }

    public String getAlbumSingerName() {
        return albumSingerName;
    }

    public void setAlbumSingerName(String albumSingerName) {
        this.albumSingerName = albumSingerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumMessage() {
        return albumMessage;
    }

    public void setAlbumMessage(String albumMessage) {
        this.albumMessage = albumMessage;
    }

    public String getAlbumPicLag() {
        return albumPicLag;
    }

    public void setAlbumPicLag(String albumPicLag) {
        this.albumPicLag = albumPicLag;
    }
}
