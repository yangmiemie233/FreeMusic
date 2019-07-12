package com.kerwin.server.pojo;

import jdbc2.Column;
import jdbc2.Table;

@Table(tableName = "singer")
public class Singer {
    @Column(value = "singerId")
    private Integer singerId;//歌手id
    @Column(value = "singerName")
    private String singerName;//歌手名
    @Column(value = "singerEnglishName")
    private String singerEnglishName;//歌手英文名
    @Column(value ="sex" )
    private String sex;//歌手性别
    @Column(value = "singerNation")
    private String singerNation;//歌手国籍
    @Column(value = "singerArea")
    private String singerArea;//歌手出生地
    @Column(value = "singerLanguage")
    private String singerLanguage;//歌手语言
    @Column(value = "constellation")
    private String constellation;//星座
    @Column(value = "pic120")
    private String pic120;//歌手图片（小）
    @Column(value = "pic300")
    private String pic300;//歌手图片（大）
    @Column(value = "fansNum")
    private Integer fansNum;//歌手粉丝数
    @Column(value = "songsNum")
    private Integer songsNum;//歌手歌曲数
    @Column(value = "albumsNum")
    private Integer albumsNum;//歌手专辑数
    @Column(value = "singerWeight")
    private String singerWeight;//歌手体重
    @Column(value = "singerHeight")
    private String singerHeight;//歌手身高
    @Column(value = "singerBirthday")
    private String singerBirthday;//歌手出生日期
    @Column(value = "prefix")
    private String prefix;//歌手分类(A,B,C...)

    public Integer getSongsNum() {
        return songsNum;
    }

    public void setSongsNum(Integer songsNum) {
        this.songsNum = songsNum;
    }

    public Integer getAlbumsNum() {
        return albumsNum;
    }

    public void setAlbumsNum(Integer albumsNum) {
        this.albumsNum = albumsNum;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerEnglishName() {
        return singerEnglishName;
    }

    public void setSingerEnglishName(String singerEnglishName) {
        this.singerEnglishName = singerEnglishName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSingerNation() {
        return singerNation;
    }

    public void setSingerNation(String singerNation) {
        this.singerNation = singerNation;
    }

    public String getSingerArea() {
        return singerArea;
    }

    public void setSingerArea(String singerArea) {
        this.singerArea = singerArea;
    }

    public String getSingerLanguage() {
        return singerLanguage;
    }

    public void setSingerLanguage(String singerLanguage) {
        this.singerLanguage = singerLanguage;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
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

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }


    public String getSingerWeight() {
        return singerWeight;
    }

    public void setSingerWeight(String singerWeight) {
        this.singerWeight = singerWeight;
    }

    public String getSingerHeight() {
        return singerHeight;
    }

    public void setSingerHeight(String singerHeight) {
        this.singerHeight = singerHeight;
    }

    public String getSingerBirthday() {
        return singerBirthday;
    }

    public void setSingerBirthday(String singerBirthday) {
        this.singerBirthday = singerBirthday;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
