package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

/**
 * @author guo
 */
public interface OrderService {

    ServiceResult selectOrderListByUserId(Integer userId);

    ServiceResult selectOrderByOrderId(Integer userId, String orderId);

    ServiceResult selectOrderByOrderIdAndState(Integer userId, String orderId, Integer state);

    ServiceResult selectOrderListByShopId(Integer userId, Integer shopId);

    ServiceResult createOrderList(Integer userId, Order order);

    ServiceResult pay(Integer userId, String orderId, String alipayNum);

    ServiceResult deliver(Integer userId, String orderId, String expressCompany, String trackingNum);

    ServiceResult receive(Integer userId, String orderId);

    ServiceResult updateCommentState(Integer userId, String orderId, Integer goodsId);

    ServiceResult dropByOrderId(Integer userId, String orderId);
}
