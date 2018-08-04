package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

/**
 * @author huguobin
 */
public interface AlipayService {
    /**
     * 支付
     * @param order 订单
     * @return
     */
    ServiceResult purchase(Order order);
}
