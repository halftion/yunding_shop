package yunding.shop.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.entity.OrderInfo;
import yunding.shop.service.OrderService;
import yunding.shop.util.UserUtil;
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
     * 根据用户ID查询用户的订单
     * @param request request对象
     * @return 用户的所有订单信息
     */
    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public RequestResult selectByUserId(HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.selectOrderListByUserId(userId);
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
    @RequestMapping(value = "/{orderId}" , method = RequestMethod.GET)
    public RequestResult selectByOrderId(@PathVariable("orderId") String  orderId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.selectOrderByOrderId(userId,orderId);
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
     * 根据店铺ID查询订单
     * @param shopId 店铺Id
     */
    @RequestMapping(value = "/shop/{shopId}" , method = RequestMethod.GET)
    public RequestResult selectByShopId(@PathVariable("shopId") Integer shopId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.selectOrderListByShopId(userId ,shopId);
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
     * 创建一个新订单
     * @param order 订单列表
     * @param request request对象
     */
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public RequestResult createOrder(@RequestBody @Validated Order order,
                                     HttpServletRequest request, BindingResult bindingResult){
        try {

            if (bindingResult.hasErrors()) {
                return RequestResult.failure(bindingResult.getFieldError().getDefaultMessage());
            }

            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.createOrderList(userId,order);
            if (serviceResult.isSuccess()){
                return RequestResult.success(null);
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("订单创建失败");
        }
    }

    /**
     * 订单发货
     * @param orderInfo 订单Id和物流单号
     * @param request request对象
     */
    @RequestMapping(value = "/send" , method = RequestMethod.PUT)
    public RequestResult sendGoods(@RequestBody OrderInfo orderInfo, HttpServletRequest request){
        try {

            Integer userId = UserUtil.getCurrentUserId(request);

            ServiceResult serviceResult = orderService.deliver(
                    userId, orderInfo.getOrderId(),orderInfo.getExpressCompany(),orderInfo.getTrackingNum());

            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("Controller 发货失败");
        }
    }

    /**
     * 订单收货
     * @param orderId 订单Id
     * @param request request对象
     */
    @RequestMapping(value = "/receive/{orderId}" , method = RequestMethod.PUT)
    public RequestResult receiveGoods( @PathVariable("orderId") String orderId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.receive(userId , orderId);
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
     * 根据订单ID 删除订单
     * @param orderId 订单ID
     * @param request request对象
     */
    @RequestMapping(value = "/{orderId}" , method = RequestMethod.DELETE)
    public RequestResult deleteOrder(@PathVariable("orderId") String orderId , HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = orderService.dropByOrderId(userId,orderId);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("删除订单失败");
        }
    }
}
