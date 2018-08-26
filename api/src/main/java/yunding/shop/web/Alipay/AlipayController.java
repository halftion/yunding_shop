package yunding.shop.web.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yunding.shop.config.AlipayConfig;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.OrderInfo;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.util.UserUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huguobin
 */
@Controller
@RequestMapping("/api/aliPay")
public class AlipayController {

    /*@Autowired
    AlipayService alipayService;

    @Autowired
    OrderService orderService;

    private OrderInfo orderInfo;
    //支付宝交易号
    private String outNo;

    @RequestMapping(value = "/purchase/{orderId}",method = RequestMethod.POST)
    public RequestResult purchase(@PathVariable String orderId, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            OrderInfo orderInfo =(OrderInfo) orderService.selectOrderByOrderIdAndState(userId,orderId, Constant.WAIT_PAY).getData();
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
            outNo =alipayService.returnUrl(request);
            request.getRequestDispatcher("").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/notifyUrl",method = RequestMethod.POST)
    public String notifyUrl(HttpServletRequest request){
        try {
            System.out.println("收到异步通知");
            String result=alipayService.notifyUrl(request, orderInfo, outNo);
            System.out.println(result);
            return result;
        }catch (Exception e){
            return "fail";
        }
    }*/

/*    @RequestMapping("/")
    public String payIndex(){
        return "index";
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void aliPay(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        JSONObject params = new JSONObject();
        params.accumulate("out_trade_no","20150320010101002");
        params.accumulate("product_code","FAST_INSTANT_TRADE_PAY");
        params.accumulate("total_amount",88.88);
        params.accumulate("subject","Iphone6 16G");
        params.accumulate("body","Iphone6 16G");
        params.accumulate("passback_params","merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
        params.accumulate("extend_params","{\"sys_service_provider_id\":\"2088511833207846\" }");
        params.accumulate("qr_pay_mode","4");
        params.accumulate("qrcode_width","128");
        alipayRequest.setBizContent(params.toString());//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        System.out.println(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
