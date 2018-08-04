package yunding.shop.entity;

import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import java.util.Date;

/** 订单
 * @author 齐语冰
 */
public class Order {
    /**
     * 订单 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer orderId;
    /**
     * 订单 用户id
     */
    private Integer userId;
    /**
     * 订单 商品id
     */
    private Integer goodsId;
    /**
     * 订单 商品名称
     */
    private String goodsName;
    /**
     * 订单 商品数量
     */
    private Integer goodsNum;
    /**
     * 订单 商品单价
     */
    private BigDecimal unitPrice;
    /**
     * 订单 商品总价
     */
    private BigDecimal totalPrice;
    /**
     * 订单 店铺id
     */
    private Integer shopId;
    /**
     * 订单 店铺名称
     */
    private String shopName;
    /**
     * 订单 买家备注
     */
    private String remark;
    /**
     * 订单 收货地址
     */
    private String address;
    /**
     * 订单 收货电话
     */
    private String phoneNumber;
    /**
     * 订单 收货人姓名
     */
    private String consigneeName;
    /**
     * 订单 商品评论
     */
    private String comment;
    /**
     * 订单 状态：
     * -1-删除，0-待付款(默认)，1-待发货，2-待收货，3-待评论，4-已评论
     */
    private int state;
    /**
     * 订单 创建时间
     */
    private Date createdAt;
    /**
     * 订单 更新时间
     */
    private Date updatedAt;




    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void createAtNow(){
        this.createdAt = new Date();
    }

    public void updateAtNow(){
        this.updatedAt = new Date();
    }


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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