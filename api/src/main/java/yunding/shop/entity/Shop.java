package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 店铺
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop {
    /**
     * 店铺 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer shopId;
    /**
     * 店铺 所属用户id
     */
    private Integer userId;
    /**
     * 店铺 名称
     */
    @NotEmpty
    @Size(min = 3, max = 15, message = "店铺名称介于3至15位之间")
    private String name;
    /**
     * 店铺 销量
     */
    private Integer sales;
    /**
     * 店铺 被收藏数量
     */
    private Integer collectionNum;
    /**
     * 店铺 状态：-1：封禁 0：正常(默认)
     */
    private Integer state;
    /**
     * 店铺 创建时间
     */
    private Date createdAt;
    /**
     * 店铺 更新时间
     */
    private Date updatedAt;





    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
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

    public void createAtNow(){
        this.createdAt = new Date();
    }

    public void updateAtNow(){
        this.updatedAt = new Date();
    }

}
