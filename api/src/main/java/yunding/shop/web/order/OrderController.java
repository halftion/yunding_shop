package yunding.shop.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.entity.Order;
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

    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public RequestResult createOrder(@RequestBody Order order , HttpServletRequest request){
        try {
//            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
            return null;
        }catch (Exception e){
            return RequestResult.failure("订单创建失败");
        }
    }
}
