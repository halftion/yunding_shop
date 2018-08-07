package yunding.shop.service;

import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ren
 * @author guo
 */
public interface GoodsService {

    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return ServiceResult
     */
    ServiceResult selectById(Integer id);

    /**
     * 为order添加商品信息
     * @param order 订单
     */
    ServiceResult processOrderCreate(Order order);

    /**
     * 在删除订单时修改商品库存
     * @param order 订单
     */
    ServiceResult processOrderDelete(Order order);

    /**
     * 根据商品关键词查询商品
     * @param keyword 关键词
     */
    ServiceResult selectByName (String keyword);

    /**
     * 根据商品关键词查询商品名称
     * @param keyword 关键字
     */
    ServiceResult selectNameByGoodsName(String keyword);

    /**
     * 根据店铺id和商品名称查找对应商品
     * @param shopId 店铺id
     * @param keyword 商品名称
     */
    ServiceResult selectByShopIdAndGoodsName(Integer shopId, String keyword);

    /**
     * 根据平台分类id查询指定平台分类中商品
     * @param categoryId 指定平台分类的Id
     */
    ServiceResult selectByPlatformCategoryId(Integer categoryId);

    /**
     * 根据店铺id和店铺分类id查找店铺分类中的商品
     * @param shopId 店铺Id
     * @param category 店铺分类Id
     */
    ServiceResult selectByShopCategoryId(Integer shopId, Integer category);

    /**
     * 根据商品ID评论商品
     * @param goodsId 商品Id
     */
    ServiceResult commentGoods(Integer goodsId);

    /**
     * 根据商品Id查询用户Id和用户评价
     * @param goodsId 商品Id
     * @return 用户Id和用户评价
     */
    ServiceResult getCommentByGoodsId(Integer goodsId);

    /**
     * 更改商品信息
     * @param goods
     * @return
     */
    ServiceResult updategoods(Goods goods);
    /**
     * 更改图片
     */
    ServiceResult updatePic(HttpServletRequest request, MultipartFile pic, int goodsId);

}
