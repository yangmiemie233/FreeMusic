package com.kerwin.server.pojo;

import jdbc2.Column;

import java.util.Date;

public class Song {
    @Column("songId")
    private Integer songId;//歌曲id;
    @Column("singerId")
    private Integer singerId;//歌手id
    @Column("singerName")
    private String singerName;//歌手名字
    @Column("songAlbumName")
    private String songAlbumName;//歌曲所属专辑名
    @Column("songAlbumId")
    private Integer songAlbumId;//所属专辑id;
    @Column("songName")
    private String songName;//歌曲名字
    @Column("songTimeMinutes")
    private String songTimeMinutes;//歌曲播放时长
    @Column("songRid")
    private  Integer songRid;//歌曲资源id
    @Column("songDate")
    private Date songDate;//歌曲发布时间
    @Column("songWords")
    private String songWords;//歌词
    @Column("pic120")
    private String pic120;//小图片
    @Column("pic300")
    private String pic300;//大图片
    @Column("playNum")
    private Integer playNum;//播放量
    public Song() {
        super();
    }
    public Song(Integer songId, String songName, String songTimeMinutes, Integer songRid, Date songDate, String pic120, String pic300) {
        this.songId = songId;
        this.songName = songName;
        this.songTimeMinutes = songTimeMinutes;
        this.songRid = songRid;
        this.songDate = songDate;
        this.pic120 = pic120;
        this.pic300 = pic300;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSongAlbumName() {
        return songAlbumName;
    }

    public void setSongAlbumName(String songAlbumName) {
        this.songAlbumName = songAlbumName;
    }

    public Integer getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Integer playNum) {
        this.playNum = playNum;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongAlbumId() {
        return songAlbumId;
    }

    public void setSongAlbumId(Integer songAlbumId) {
        this.songAlbumId = songAlbumId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongTimeMinutes() {
        return songTimeMinutes;
    }

    public void setSongTimeMinutes(String songTimeMinutes) {
        this.songTimeMinutes = songTimeMinutes;
    }

    public Integer getSongRid() {
        return songRid;
    }

    public void setSongRid(Integer songRid) {
        this.songRid = songRid;
    }

    public Date getSongDate() {
        return songDate;
    }

    public void setSongDate(Date songDate) {
        this.songDate = songDate;
    }

    public String getSongWords() {
        return songWords;
    }

    public void setSongWords(String songWords) {
        this.songWords = songWords;
    }

    public String getPic120() {
        return pic120;
    }

    public void setPic120(String pic120) {
        this.pic120 = pic120;
    }

    public String getPic300() {
        return pic300;
    }

    public void setPic300(String pic300) {
        this.pic300 = pic300;
    }
}
