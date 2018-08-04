package yunding.shop.web.Alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;
import yunding.shop.entity.UserInfo;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huguobin
 */
@Controller
@RequestMapping("apilay")
public class AlipayController {
    @Autowired
    AlipayService alipayService;
    @Autowired
    OrderService orderService;
    @ResponseBody
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
}
