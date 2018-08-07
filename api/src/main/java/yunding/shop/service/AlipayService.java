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
     * 处理异步通知
     * @param request
     */

    String notifyUrl(HttpServletRequest request,Order order);


}
