package yunding.shop.entity;

import javax.persistence.GeneratedValue;

/**
 * @author guo
 */
public class ShopContent {
    /**
     * 店铺文章 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer contentId;
    /**
     * 店铺文章 店铺id
     */
    private Integer shopId;
    /**
     * 店铺文章 商品Id
     */
    private String url;
    /**
     * 店铺文章 标题1
     */
    private String title1;
    /**
     * 店铺文章 标题2
     */
    private String title2;
    /**
     * 店铺文章 标题3
     */
    private String title3;
    /**
     * 店铺文章 图片地址
     */
    private String pic;
    /**
     * 店铺文章 类型：0-轮播图，1-热卖商品，2-热卖商品长图，3-类目列表，4-新品首发
     */
    private Integer type;
    /**
     * 店铺文章 状态：0-正常，-1-删除
     */
    private Integer state;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
