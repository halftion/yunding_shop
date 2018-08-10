package yunding.shop.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * 商品
 * @author 齐语冰
 */
public class Goods implements Comparator {
    /**
     * 商品 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer goodsId;
    /**
     * 商品所属店铺id
     */
    private Integer shopId;
    /**
     * 商品所属店铺名称
     */
    private String shopName;
    /**
     * 商品 所属平台类别id
     */
    @NotEmpty
    @Size(max = 1)
    private Integer platformGoodsCategoryId;
    /**
     * 商品 所属店铺类别id
     */
    @NotEmpty
    @Size(max = 1)
    private Integer shopGoodsCategoryId;
    /**
     * 商品 名称
     */
    @NotEmpty
    @Size(max = 75)
    private String name;
    /**
     * 商品 名称
     */
    @Size(max = 50)
    private String property;
    /**
     * 商品 库存数量
     */
    @NotEmpty
    @Size
    private Integer stockNum;
    /**
     * 商品 价格
     */
    @Size
    private BigDecimal price;
    /**
     * 商品 预览图片地址
     */
    private String picture;
    /**
     * 商品 介绍图片地址
     */
    private String introduction;
    /**
     * 商品 销量
     */
    private Integer sales;
    /**
     * 商品 评论总数
     */
    private Integer commentNum;
    /**
     * 商品 被收藏数量
     */
    private Integer collectionNum;
    /**
     * 商品 状态：0-正常(默认)，-1-下架
     */
    private int state;
    /**
     * 商品 创建时间
     */
    private Date createdAt;
    /**
     * 商品 更新时间
     */
    private Date updatedAt;





    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getPlatformGoodsCategoryId() {
        return platformGoodsCategoryId;
    }

    public void setPlatformGoodsCategoryId(Integer platformGoodsCategoryId) {
        this.platformGoodsCategoryId = platformGoodsCategoryId;
    }

    public Integer getShopGoodsCategoryId() {
        return shopGoodsCategoryId;
    }

    public void setShopGoodsCategoryId(Integer shopGoodsCategoryId) {
        this.shopGoodsCategoryId = shopGoodsCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
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

    public void createAtNow(){
        this.createdAt = new Date();
    }

    public void updateAtNow(){
        this.updatedAt = new Date();
    }



    @Override
    public int compare(Object o1, Object o2) {
        Goods g1= (Goods) o1;
        Goods g2= (Goods) o2;
        if (g1.sales>g2.sales){
            return 1;
        }else if(g1.sales<g2.sales){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Goods goods = (Goods) o;
        return Objects.equals(goodsId, goods.goodsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId);
    }
}
