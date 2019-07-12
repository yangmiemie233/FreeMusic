package com.kerwin.server.pojo;



import jdbc2.Column;
import jdbc2.Table;

/**
 * 广告的实体类
 * @Author liuyun
 * @Time 2019/07/10
 */
@Table(tableName = "advertisement")
public class Advertisement {

    @Column("advertisementId")
    private Integer advertisementId;

    @Column("title")
    private String  title;
    @Column("link")
    private String  link;
    @Column("picture")
    private String  picture;
    @Column("creatTime")
    private String  creatTime;

    @Column("openTime")
    private String   openTime;
    @Column("status")
    private String  status;

    public Advertisement() {
        super();
    }

    public Advertisement(String title, String link, String picture, String creatTime) {
        this.title = title;
        this.link = link;
        this.picture = picture;
        this.creatTime = creatTime;
        this.status = "0";
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "advertisementId=" + advertisementId +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", picture='" + picture + '\'' +
                ", creatTime=" + creatTime +
                ", openTime=" + openTime +
                ", status='" + status + '\'' +
                '}';
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPicture() {
        return picture;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getStatus() {
        return status;
    }
}
