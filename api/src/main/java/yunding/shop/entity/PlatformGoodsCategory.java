package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/** 平台商品分类
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlatformGoodsCategory {
    /**
     * 商品所属平台类别 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer platformGoodsCategoryId;
    /**
     * 商品所属平台类别 名称
     */
    @NotEmpty(message = "分类名称不能为空")
    private String name;
    /**
     * 商品所属平台类别 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品所属平台类别 创建时间
     */
    private Date createdAt;
    /**
     * 商品所属平台类别 更新时间
     */
    private Date updatedAt;



    public Integer getPlatformGoodsCategoryId() {
        return platformGoodsCategoryId;
    }

    public void setPlatformGoodsCategoryId(Integer platformGoodsCategoryId) {
        this.platformGoodsCategoryId = platformGoodsCategoryId;
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
}
