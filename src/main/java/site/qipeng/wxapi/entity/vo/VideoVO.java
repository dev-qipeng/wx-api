package site.qipeng.wxapi.entity.vo;

import site.qipeng.wxapi.entity.Video;

import java.util.Date;

public class VideoVO {
    private Integer id;

    private String name;

    private Integer categoryId;

    private String description;

    private String poster;

    private String url;

    private Double score;

    private Integer likeNum;

    private Integer playNum;

    private Date createTime;

    private Date updateTime;

    private boolean like;

    public VideoVO(Video video) {
        this.id = video.getId();
        this.name = video.getName();
        this.categoryId = video.getCategoryId();
        this.description = video.getDescription();
        this.poster = video.getPoster();
        this.url = video.getUrl();
        this.score = video.getScore();
        this.likeNum = video.getLikeNum();
        this.playNum = video.getPlayNum();
        this.createTime = video.getCreateTime();
        this.updateTime = video.getUpdateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Integer playNum) {
        this.playNum = playNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "VideoVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", url='" + url + '\'' +
                ", score=" + score +
                ", likeNum=" + likeNum +
                ", playNum=" + playNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", like=" + like +
                '}';
    }
}
