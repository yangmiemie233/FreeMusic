package com.kerwin.server.pojo;

import jdbc2.Column;
import jdbc2.Table;

/**
 * 用户歌单的单元表-定义歌单
 * @Author liuyun
 * @Time 2019/07/11
 *
 */

@Table(tableName = "usersonglist")
public class UserSongList {

    @Column("songListId")//主键
    private Integer songListId;

    @Column("userId")//歌单所属用户id
    private Integer userId;

    @Column("songListName")//用户歌单名
    private String songListName;

    @Column("recommend")//是否为推荐歌单(0为不推荐，1为推荐)
    private String recommend;


    public UserSongList() {
        super();
    }

    public UserSongList(Integer userId, String songListName, String recommend) {
        this.userId = userId;
        this.songListName = songListName;
        this.recommend = recommend;
    }

    public UserSongList(String songListName) {
        this.songListName = songListName;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSongListName() {
        return songListName;
    }

    public String getRecommend() {
        return recommend;
    }
}
