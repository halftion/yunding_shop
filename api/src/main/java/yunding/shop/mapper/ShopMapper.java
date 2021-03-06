package yunding.shop.mapper;

import yunding.shop.entity.Shop;

import java.util.List;

/**
 * 店铺
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
     * 根据店铺Id查询店铺
     * @param shopId 店铺Id
     * @return 店铺详情
     */
    Shop selectById(Integer shopId);

    /**
     * 根据店铺Id查询商户Id
     * @param shopId 店铺Id
     * @return 商户Id
     */
    Integer selectUserIdByShopId (Integer shopId);

    /**
     * 根据商户Id查询店铺Id
     * @param userId 商户Id
     * @return 店铺Id
     */
    Integer selectShopIdByUserId (Integer userId);

    /**
     * 根据店铺Id查询店铺名称
     * @param shopId 店铺Id
     * @return 店铺名称
     */
    String selectShopNameByShopId(Integer shopId);

    /**
     * 修改店铺销量
     * @param shop 店铺信息
     */
    Integer updateSales (Shop shop);

    /**
     * 查询所有店铺
     * @return 店铺详细信息
     */
    List<Shop> selectAllShop();

}
