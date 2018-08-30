package yunding.shop.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import net.sf.json.JSONObject;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.service.PayService;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AlipayServiceImpl implements PayService {

    /**
     * 获得初始化的AlipayClient
     */
    @Autowired
    private AlipayClient alipayClient;

    @Override
    public ServiceResult createTrade(double totalPrice, String tradeId) {

        JSONObject params = new JSONObject();

        params.accumulate("out_trade_no", tradeId);
        params.accumulate("product_code","FAST_INSTANT_TRADE_PAY");
        params.accumulate("total_amount", totalPrice);
        params.accumulate("subject", "云顶电商平台");
        //公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝只会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
        params.accumulate("qr_pay_mode","4");
        params.accumulate("qrcode_width","128");
        params.accumulate("timeout_express","120m");

        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();

        //在公共参数中设置回跳和通知地址
//        alipayTradePagePayRequest.setReturnUrl("");
        alipayTradePagePayRequest.setNotifyUrl(Constant.IP_ADDRESS+"/yundingShop/api/order/aliPay");

        //填充业务参数

        alipayTradePagePayRequest.setBizContent(params.toString());

        try {
            //调用SDK生成表单html
            String html = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
            String encodeHtml = DatatypeConverter.printBase64Binary(html.getBytes(Charsets.UTF_8));
            return ServiceResult.success(encodeHtml);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建交易失败");
        }
    }
}
