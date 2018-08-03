package yunding.shop.mapper;

import yunding.shop.entity.Order;

import java.util.List;

/**
 * 提供有关于订单的方法
 * @author guo
 */
public interface OrderMapper {

    /**
     * 创建 订单
     * @param order 传入order的信息
     * @return 1为成功，否则失败
     */
    Integer createOrder(Order order);

    /**
     * 根据订单Id查询订单信息
     * @param orderId 订单id
     * @return 订单信息
     */
    Order selectByOrderId(Integer orderId);

    /**
     * 根据订单ID查询用户ID
     * @param orderId 订单ID
     * @return 用户ID
     */
    Integer selectUserIdByOrderId(Integer orderId);

    /**
     * 评论 订单
     * @param order 传入订单ID和评论
     * @return 1为成功，否则失败
     */
    Integer commentOrder(Order order);

    /**
     * 根据用户ID查询所有订单
     * @param userId 用户ID
     * @return 所有订单信息
     */
    List<Order> selectByUserId(Integer userId);
}
