package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo {
    /**
     * 订单 id
     */
    private String orderId;
    /**
     * 订单 用户id
     */
    private Integer userId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 订单 商品总价
     */
    private BigDecimal totalPrice;

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
     * 物流公司
     */
    private String expressCompany;

    /**
     * 运单号
     */
    private String trackingNum;

    /**
     * 买家备注
     */
    private String remark;

    /**
     * 订单状态：-1-删除，0-待付款(默认)，1-已付款，2-已发货，3-已收货
     */
    private Integer state;
    /**
     * 订单 支付宝交易号
     */
    private String alipayNum;

    /**
     * 订单 创建时间
     */
    private Date createdAt;
    /**
     * 订单 更新时间
     */
    private Date updatedAt;



    public void createAtNow(){
        this.createdAt = new Date();
    }

    public void updateAtNow(){
        this.updatedAt = new Date();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
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