package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * 店铺商品分类
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopGoodsCategory {
    /**
     * 商品所属店铺类别 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer shopGoodsCategoryId;
    /**
     * 商品所属店铺类别 所属店铺id
     */
    @NotEmpty(message = "店铺id不能为空")
    private Integer shopId;
    /**
     * 商品所属店铺类别 名称
     */
    @NotEmpty(message = "分类名称不能为空")
    private String name;
    /**
     * 商品所属店铺类别 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品所属店铺类别 创建时间
     */
    private Date createdAt;
    /**
     * 商品所属店铺类别 更新时间
     */
    private Date updatedAt;





    public Integer getShopGoodsCategoryId() {
        return shopGoodsCategoryId;
    }

    public void setShopGoodsCategoryId(Integer shopGoodsCategoryId) {
        this.shopGoodsCategoryId = shopGoodsCategoryId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
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
