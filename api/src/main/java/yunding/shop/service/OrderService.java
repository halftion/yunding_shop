package yunding.shop.service;

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
     * @return
     */
    ServiceResult createOrder(Integer userId , Order order);
}
