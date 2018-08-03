package yunding.shop.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.service.OrderService;
import yunding.shop.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单信息
 * @author guo
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public RequestResult createOrder(@RequestBody Order order , HttpServletRequest request){
        try {
            Integer userId = 2;
//            ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = orderService.createOrder(userId,order);
            if (serviceResult.isSuccess()){
                return RequestResult.success(null);
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("订单创建失败");
        }
    }

    @RequestMapping(value = "/comment" ,method = RequestMethod.PUT)
    public RequestResult commentOrder(@RequestBody Order order , HttpServletRequest request){
        try {
            Integer userId = 2;
//           ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = orderService.commentOrder(userId,order);
            if (serviceResult.isSuccess()){
                return RequestResult.success(null);
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("订单评论失败");
        }
    }

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public RequestResult selectByUserId(HttpServletRequest request){
        try {
            Integer userId = 2;
//           ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = orderService.selectByUserId(userId);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("查询订单失败");
        }
    }

    @RequestMapping(value = "/orderId/{orderId}" , method = RequestMethod.GET)
    public RequestResult selectByOrderId(@PathVariable("orderId") Integer orderId, HttpServletRequest request){
        try {
            Integer userId = 2;
//           ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = orderService.selectByOrderId(userId,orderId);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("查询订单失败");
        }
    }
}
