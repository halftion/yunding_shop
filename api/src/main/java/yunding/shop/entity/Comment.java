package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;


/**
 * @author guo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价状态：-1-删除, 0-正常(默认)
     */
    private Integer state;

    /**
     * 评论创建时间
     */
    private Date createdAt;

    private Date updatedAt;


    public void createAtNow(){
        this.createdAt = new Date();
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
