package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 支付结果
     * @param request
     */
    void result(HttpServletRequest request);
}
