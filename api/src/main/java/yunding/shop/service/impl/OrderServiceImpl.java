package yunding.shop.service.impl;

import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.service.OrderService;

/**
 * @author guo
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ServiceResult createOrder(Integer userId, Order order) {
        try{

        }catch (Exception e){
            return ServiceResult.failure("Service 错误 创建订单失败");
        }
        return null;

    }
}
