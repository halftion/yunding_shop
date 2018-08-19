package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.GeneratedValue;

/**
 * 首页文章
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {
    /**
     * 文章 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer contentId;
    /**
     * 文章 商品Id
     */
    private Integer goodsId;
    /**
     * 文章 标题1
     */
    private String title1;
    /**
     * 文章 标题2
     */
    private String title2;
    /**
     * 文章 标题3
     */
    private String title3;
    /**
     * 文章 图片地址
     */
    private String pic;
    /**
     * 文章 类型：0-轮播图，1-热卖商品，2-新品首发，3-精选好物小图 ,4精选好物大图，5精选好物长图
     */
    private Integer type;
    /**
     * 文章 状态：0-正常，-1-删除
     */
    private Integer state;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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
