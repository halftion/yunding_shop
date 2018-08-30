package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderGoods {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品id
     */
    @NotEmpty(message = "商品id不能为空")
    @Size(min = 1, message = "商品id不能小于1")
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPic;

    /**
     * 商品数量
     */
    @NotEmpty(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
    private Integer goodsNum;

    /**
     * 商品单价
     */
    private BigDecimal unitPrice;

    /**
     * 该商品总价(单价*数量)
     */
    private BigDecimal totalPrice;


    /**
     * 状态：0-待评价，1-已评价
     */
    private Integer state;

    /**
     * 订单 创建时间
     */
    private Date createdAt;
    /**
     * 订单 更新时间
     */
    private Date updatedAt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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
