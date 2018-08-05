package yunding.shop.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Alipay;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Order;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;


@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private OrderService orderService;

    @Override
    public ServiceResult purchase(Order order) {
       try{
           AlipayClient alipayClient = new DefaultAlipayClient(Alipay.gatewayUrl, Alipay.app_id, Alipay.merchant_private_key, "json", Alipay.charset, Alipay.alipay_public_key, Alipay.sign_type);

           AlipayTradePagePayRequest alipayTradePagePayRequest=new AlipayTradePagePayRequest();
           alipayTradePagePayRequest.setReturnUrl(Alipay.return_url);
           alipayTradePagePayRequest.setNotifyUrl(Alipay.notify_url);
           String orderId=new String(String.valueOf(order.getOrderId()).getBytes("ISO-8859-1"),"utf-8");
           String totalPrice= new String(String.valueOf(order.getTotalPrice()).getBytes("ISO-8859-1"),"utf-8");
           String orderName="";
           String body="";
           alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ orderId +"\","
                   + "\"total_amount\":\""+ totalPrice +"\","
                   + "\"subject\":\""+ orderName +"\","
                   + "\"body\":\""+ body +"\","
                   + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
           String result= alipayClient.pageExecute(alipayTradePagePayRequest).getBody();

           System.out.println(order);
           if(order.getState() == Constant.WAIT_PAY){
               orderService.updateState(order.getOrderId(),Constant.WAIT_SEND_GOOD);
           }else{
               return ServiceResult.failure("订单状态错误");
           }
           return ServiceResult.success(result);
       }catch (Exception e){
           throw new RuntimeException("支付失败");
       }
    }
}
