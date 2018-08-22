package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.OrderGoods;
import yunding.shop.entity.OrderInfo;

import java.util.List;

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
     * 修改订单创建时的店铺信息
     * @param orderInfo 订单信息
     * @param orderGoodsList 订单商品列表
     * @return
     */
    ServiceResult processOrderCreate(OrderInfo orderInfo, List<OrderGoods> orderGoodsList);

    /**
     * 查询所有店铺
     * @return 店铺详情
     */
    ServiceResult selectAllShop();

    /**
     * 根据店铺Id和类型获取对应html
     * @param shopId 店铺Id
     * @param type 类型
     * @return html信息
     */
    ServiceResult selectShopHtml(Integer shopId, Integer type);
}
