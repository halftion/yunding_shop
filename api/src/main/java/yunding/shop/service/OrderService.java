package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import java.util.List;

/**
 * @author guo
 */
public interface OrderService {

    /**
     * 根据用户ID查询用户的订单
     * @param userId 用户ID
     * @return 用户的订单
     */
    ServiceResult selectByUserId(Integer userId);

    /**
     * 根据订单Id查询订单信息
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 订单详细信息
     */
    ServiceResult selectByOrderId(Integer userId,Integer orderId);

    /**
     * 根据店铺ID查询订单
     * @param userId 用户Id
     * @param shopId 店铺Id
     * @return 订单列表
     */
    ServiceResult selectByShopId (Integer userId , Integer shopId);

    /**
     * 创建一个新订单
     * @param userId 用户ID
     * @param orderList 订单信息
     */
    ServiceResult createOrder(Integer userId, List<Order> orderList);


    /**
     * 根据订单Id 买家付款
     * @param userId 用户Id
     * @param orderId 订单ID
     */
    ServiceResult userPayByOrderId(Integer userId,Integer orderId);

    /**
     * 根据订单Id 卖家发货
     * @param userId 用户Id
     * @param order 订单ID和物流单号
     */
    ServiceResult sendGoods(Integer userId, Order order);

    /**
     * 根据订单Id 买家收货
     * @param userId 用户Id
     * @param orderId 订单ID
     */
    ServiceResult receiveGoodsByOrderId(Integer userId,Integer orderId);

    /**
     * 根据订单 评论订单
     * @param userId 用户Id
     * @param order 传入用户Id和订单评论
     */
    ServiceResult commentOrder(Integer userId, Order order);

    /**
     * 根据订单Id删除订单
     * @param userId 用户Id
     * @param orderId 订单ID
     */
    ServiceResult deleteByOrderId(Integer userId,Integer orderId);

    /**
     * 根据商品Id查询用户Id和用户评价
     * @param goodsId 商品Id
     * @return 用户Id和用户评价
     */
    ServiceResult selectCommentByGoodsId(Integer goodsId);

}
