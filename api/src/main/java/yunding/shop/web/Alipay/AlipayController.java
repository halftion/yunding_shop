package yunding.shop.web.Alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huguobin
 */
@RestController
@RequestMapping("/api/aliPay")
public class AlipayController {

    @Autowired
    AlipayService alipayService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/purchase/{orderId}",method = RequestMethod.POST)
    public RequestResult purchase(@PathVariable Integer orderId, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            Order order=(Order) orderService.selectByOrderId(userId,orderId).getData();
            ServiceResult result=alipayService.purchase(order);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure();
        }
    }

    @RequestMapping(value = "/purchase/result",method = RequestMethod.POST)
    public String result(HttpServletRequest request){
        try {
            alipayService.result(request);
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }

}
