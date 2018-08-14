package yunding.shop.service.impl;

import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.OrderInfo;
import yunding.shop.service.AlipayService;
import yunding.shop.service.OrderService;
import yunding.shop.util.AlipayUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayUtil alipayUtil;
    @Override
    public ServiceResult purchase(OrderInfo orderInfo) {
       try{

           String result= (String) alipayUtil.alipayClient(String.valueOf(orderInfo.getOrderId()), orderInfo.getShopName(),"", String.valueOf(orderInfo.getTotalPrice()));
           return ServiceResult.success(result);
       }catch (Exception e){
           throw new RuntimeException("支付失败");
       }
    }

    @Override
    public String notifyUrl(HttpServletRequest request, OrderInfo orderInfo, String outNo) {
        try{
            String result=alipayUtil.notifyUrl(request);
            String success = "success";
            if (success.equals(result)){
                if(orderInfo.getState().equals(Constant.WAIT_PAY)){
                    orderInfo.setAlipayNum(outNo);
                    orderService.pay(orderInfo.getUserId(), orderInfo.getOrderId(),orderInfo.getAlipayNum());
                }else{
                    throw new RuntimeException("订单已经支付");
                }
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException("异步通知 异常");
        }
    }

    @Override
    public String returnUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        return  alipayUtil.returnUrl(request);
    }


}
