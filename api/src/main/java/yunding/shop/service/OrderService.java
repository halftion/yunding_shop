package yunding.shop.service;

import org.apache.ibatis.annotations.Param;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

/**
 * @author guo
 */
public interface OrderService {

    /**
     * 创建一个新订单
     * @param userId 用户ID
     * @param order 订单信息
     */
    ServiceResult createOrder(Integer userId, Order order);

    /**
     * 评论订单
     * @param userId 用户Id
     * @param order 传入用户Id和订单评论
     */
    ServiceResult commentOrder(Integer userId, Order order);

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
     * 根据订单Id删除订单
     * @param userId 用户Id
     * @param orderId 订单ID
     */
    ServiceResult deleteByOrderId(Integer userId,Integer orderId);

    /**
     * 修改订单状态
     * @param orderId 订单ID
     * @param state 订单状态
     */
    ServiceResult updateState(@Param("orderId") Integer orderId , @Param("state") Integer state);
}
