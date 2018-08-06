package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Order;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.utils.AlipayUtil;

import javax.servlet.http.HttpServletRequest;


@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayUtil alipayUtil;
    @Override
    public ServiceResult purchase(Order order) {
       try{

           String result= (String) alipayUtil.alipayClient(String.valueOf(order.getOrderId()),order.getGoodsName(),"","");
           if(order.getState() == Constant.WAIT_PAY){
               orderService.userPayByOrderId(order.getUserId(),order.getOrderId());
           }else{
               return ServiceResult.failure("订单状态错误");
           }
           return ServiceResult.success(result);
       }catch (Exception e){
           throw new RuntimeException("支付失败");
       }
    }

    @Override
    public void result(HttpServletRequest request) {

    }
}
