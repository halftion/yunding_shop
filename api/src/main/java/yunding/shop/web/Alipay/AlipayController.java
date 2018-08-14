package yunding.shop.web.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.OrderInfo;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.util.UserUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private OrderInfo orderInfo;
    //支付宝交易号
    private String out_no;

    @RequestMapping(value = "/purchase/{orderId}",method = RequestMethod.POST)
    public RequestResult purchase(@PathVariable Integer orderId, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            OrderInfo orderInfo =(OrderInfo) orderService.selectByOrderId(userId,orderId).getData();
            this.orderInfo = orderInfo;
            ServiceResult result=alipayService.purchase(orderInfo);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure();
        }
    }

    @RequestMapping(value = "/returnUrl",method = RequestMethod.GET)
    public void retuenUrl(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("收到同步通知");
            out_no=alipayService.returnUrl(request);
            request.getRequestDispatcher("").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/notifyUrl",method = RequestMethod.POST)
    public String notifyUrl(HttpServletRequest request){
        try {
            System.out.println("收到异步通知");
            String result=alipayService.notifyUrl(request, orderInfo,out_no);
            System.out.println(result);
            return result;
        }catch (Exception e){
            return "fail";
        }
    }

}
