package yunding.shop.entity;

/**
 * @author guo
 */
public class Comment {

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 商品评价
     */
    private String comment;

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
