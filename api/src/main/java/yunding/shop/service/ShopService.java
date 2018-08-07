package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * 店铺
 * @author guo
 */
public interface ShopService {

    /**
     * 根据商品关键词查询商品
     * @param keyword 关键词
     */
    ServiceResult selectByName (String keyword);

    /**
     * 根据店铺Id查询商户Id
     * @param shopId 店铺Id
     * @return 商户Id
     */
    ServiceResult selectUserIdByShopId (Integer shopId);

    /**
     * 根据商户Id查询店铺Id
     * @param userId 商户Id
     * @return 店铺Id
     */
    ServiceResult selectShopIdByUserId (Integer userId);

    /**
     * 根据店铺ID查询店铺名称
     * @param shopId 店铺ID
     * @return 店铺名称
     */
    ServiceResult selectShopNameByShopId (Integer shopId);

    /**
     * 根据店铺Id修改店铺销量
     * @param shopId 店铺Id
     * @param goodsNum 商品总销量
     */
    ServiceResult updateSales (Integer shopId , Integer goodsNum);
}
