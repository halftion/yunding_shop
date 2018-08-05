package yunding.shop.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Order;
import yunding.shop.service.OrderService;
import yunding.shop.utils.UserUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单信息
 * @author guo
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建一个新订单
     * @param orderList 订单列表
     * @param request request对象
     */
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public RequestResult createOrder(@RequestBody List<Order> orderList , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.createOrder(userId,orderList);
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
            Integer userId = UserUtil.getCurrentUserId(request);
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
            Integer userId = UserUtil.getCurrentUserId(request);
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
    @RequestMapping(value = "id/{orderId}" , method = RequestMethod.GET)
    public RequestResult selectByOrderId(@PathVariable("orderId") Integer orderId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
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

    /**
     * 根据订单ID删除订单
     * @param orderId 订单ID
     * @param request request对象
     */
    @RequestMapping(value = "/id/{orderId}" , method = RequestMethod.DELETE)
    public RequestResult deleteOrder(@PathVariable("orderId") Integer orderId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.deleteByOrderId(userId,orderId);
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
     * 订单发货
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/send/{orderId}" , method = RequestMethod.PUT)
    public RequestResult updateState(Integer orderId){
        try {
            ServiceResult serviceResult = orderService.updateState(orderId, Constant.WAIT_RECEIVE_GOOD);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("订单状态修改失败");
        }
    }

    /**
     * 订单收货
     * @param orderId 订单Id
     */
    @RequestMapping(value = "/receive/{orderId}" , method = RequestMethod.PUT)
    public RequestResult receiveState( Integer orderId){
        try {
            ServiceResult serviceResult = orderService.updateState(orderId, Constant.WAIT_COMMENT);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("收货失败");
        }
    }

    /**
     * 根据店铺ID查询订单
     * @param shopId 店铺Id
     */
    @RequestMapping(value = "/shop/{shopId}" , method = RequestMethod.GET)
    public RequestResult selectByShopId(@PathVariable("shopId") Integer shopId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.selectByShopId(userId ,shopId);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("收货失败");
        }
    }
}
