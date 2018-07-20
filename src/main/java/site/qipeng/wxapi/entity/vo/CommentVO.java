package site.qipeng.wxapi.entity.vo;

import java.util.Date;

public class CommentVO {

    private Integer id;

    private Integer userId;

    private Integer videoId;

    private Date createTime;

    private String content;

    private String nickname;

    private String headImg;

    public CommentVO(Integer id, Integer userId, Integer videoId, Date createTime, String content, String nickname, String headImg) {
        this.id = id;
        this.userId = userId;
        this.videoId = videoId;
        this.createTime = createTime;
        this.content = content;
        this.nickname = nickname;
        this.headImg = headImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", headImg='" + headImg + '\'' +
                ", videoId=" + videoId +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
