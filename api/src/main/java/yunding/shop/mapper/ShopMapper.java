package yunding.shop.mapper;

import yunding.shop.entity.Shop;

import java.util.List;

/**
 * @author 齐语冰
 */
public interface ShopMapper {
    /**
     * 根据店铺名称查询店铺
     * @param name 店铺名称
     * @return 店铺结果列表
     */
    List<Shop> selectByName(String name);

    /**
     * 根据店铺Id查询商户Id
     * @param shopId 店铺Id
     * @return 商户Id
     */
    Integer selectUserIdByShopId (Integer shopId);
}
