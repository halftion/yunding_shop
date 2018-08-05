package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

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
}
