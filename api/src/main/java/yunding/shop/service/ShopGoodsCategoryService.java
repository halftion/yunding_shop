package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.ShopGoodsCategory;

/**
 * @author huguobin
 * 店铺相关service
 */
public interface ShopGoodsCategoryService {

    /**
     * 查询店铺商品分类
     * @param shopId 店铺id
     * @return 分类列表
     */
    ServiceResult getCategoryList(int shopId);

    /**
     * 查询该分类下所有商品
     * @param shopId 店铺id
     * @param category 店铺分类id
     * @return 商品列表
     */
    ServiceResult getAllGoods(int shopId, int category);

    /**
     * 添加商品分类
     * @param userId 商户ID
     * @param name 分类名称
     */
    ServiceResult insertShopCategory (Integer userId ,String name);
}
