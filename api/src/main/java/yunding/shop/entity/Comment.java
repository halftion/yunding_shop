package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;

/**
 * @author guo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    /**
     * 商品 ID
     */
    private Integer goodsId;

    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 用户 昵称
     */
    private String nickName;

    /**
     * 用户 头像
     */
    private String avatar;

    /**
     * 商品 评价
     */
    private String comment;

    /**
     * 更新 时间
     */
    private Date updatedAt;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "goodsId=" + goodsId +
                ", userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
