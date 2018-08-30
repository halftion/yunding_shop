package yunding.shop.service;

import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @author guo
 */
public interface OrderService {

    ServiceResult selectOrderListByUserId(Integer userId);

    ServiceResult selectOrderByOrderId(Integer userId, String orderId);

    ServiceResult selectOrderByOrderIdAndState(Integer userId, String orderId, Integer state);

    ServiceResult selectOrderListByShopId(Integer userId, Integer shopId);

    ServiceResult createOrderList(Integer userId, Order order);

    ServiceResult createTrade(List<String> orderIdList);

    ServiceResult pay(Map<String, String[]> requestParams, String out_trade_no, String trade_no, String trade_status, String total_amount);

    ServiceResult checkPay(String tradeId);

    ServiceResult deliver(Integer userId, String orderId, String expressCompany, String trackingNum);

    ServiceResult receive(Integer userId, String orderId);

    ServiceResult updateCommentState(Integer userId, String orderId, Integer goodsId);

    ServiceResult dropByOrderId(Integer userId, String orderId);
}
