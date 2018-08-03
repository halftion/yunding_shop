package yunding.shop.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.entity.User;
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

    /**
     * 创建一个新订单
     * @param order 订单类
     * @param request request对象
     */
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public RequestResult createOrder(@RequestBody Order order , HttpServletRequest request){
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
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

    /**
     * 评论订单
     * @param order 用户类（orderID和comment）
     * @param request request对象
     */
    @RequestMapping(value = "/comment" ,method = RequestMethod.PUT)
    public RequestResult commentOrder(@RequestBody Order order , HttpServletRequest request){
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
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

    /**
     * 根据用户ID查询用户的订单
     * @param request request对象
     * @return 用户的所有订单信息
     */
    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public RequestResult selectByUserId(HttpServletRequest request){
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
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

    /**
     * 根据订单Id查询订单信息
     * @param orderId 订单ID
     * @param request request对象
     * @return 订单详细信息
     */
    @RequestMapping(value = "/orderId/{orderId}" , method = RequestMethod.GET)
    public RequestResult selectByOrderId(@PathVariable("orderId") Integer orderId, HttpServletRequest request){
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
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
