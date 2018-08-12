package yunding.shop.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
    @NotEmpty
    @Size(min = 1)
    private Integer goodsId;
    /**
     * 订单 商品名称
     */
    private String goodsName;
    /**
     * 订单 商品图片
     */
    private String goodsPic;
    /**
     * 订单 商品数量
     */
    @NotEmpty(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
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
    @NotEmpty(message = "收货地址不能为空")
    private String address;
    /**
     * 订单 收货电话
     */
    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号应为11为")
    private String phoneNumber;
    /**
     * 订单 收货人姓名
     */
    @NotEmpty(message = "收货人不能为空")
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
     * 订单 支付宝交易号
     */
    private String alipayNum;
    /**
     * 订单 快递公司
     */
    private String expressCompany;
    /**
     * 订单 运单号
     */
    private String trackingNum;
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

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
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

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public String getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", remark='" + remark + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", comment='" + comment + '\'' +
                ", state=" + state +
                ", trackingNum='" + trackingNum + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}