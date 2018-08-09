package yunding.shop.utils;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author huguobin
 */
@Configuration
@PropertySource(value = "classpath:Alipay.properties")
public class AlipayUtil {
    /**
     * 应用id
     */
    @Value("${appId}")
    private  String appId;
    /**
     * 私钥
     */
    @Value("${privateKey}")
    private  String privateKey;
    /**
     *
     */
    @Value("{format}")
    private   String format;
    /**
     * 公钥
     */
    @Value("${publicKey}")
    private  String publicKey;
    /**
     * 签名方式
     */
    @Value("${signType}")
    private  String signType;
    /**
     * 字符编码格式
     */
    @Value("${charset}")
    private  String charset;
    /**
     * 支付宝网关
     */
    @Value("${gatewayUrl}")
    private  String gatewayUrl;
    /**
     * returlUrl
     */
    @Value("${returnUrl}")
    public  String returnUrl;
    /**
     * notifyUrl
     */
    @Value("${notifyUrl}")
    public  String notifyUrl;
    // 支付宝网关
    @Value("${logPath}")
    private  String logPath;


    public  void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(logPath + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public  AlipayClient alipayClient(){

        return new DefaultAlipayClient(gatewayUrl,appId,privateKey,format,charset,publicKey,signType);
    }

    /**
     *  用于支付
     * @param orderId 商户订单号
     * @param orderName 订单名称
     * @param body 商品描述
     * @param totalPrice 付款金额
     * @return 用于生成一个支付网页的字符串
     * @throws AlipayApiException
     */
    public  String alipayClient(String orderId,String orderName,String body,String totalPrice) throws AlipayApiException {
        AlipayClient client=alipayClient();
        AlipayTradePagePayRequest alipayTradePagePayRequest=new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setReturnUrl(returnUrl);
        alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ orderId +"\","
                + "\"total_amount\":\""+ totalPrice +"\","
                + "\"subject\":\""+ orderName +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        return client.pageExecute(alipayTradePagePayRequest,"alipay.trade.page.pay").getBody();

    }

    public String notifyUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, publicKey, charset, signType); //调用SDK验证签名
        if(signVerified) {//验证成功
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                System.out.println("做过处理");
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                System.out.println("没做过处理");

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
           return "success";
        }else {//验证失败
           return "fail";

        }
    }


    @Override
    public String toString() {
        return "alipay{" +
                "appId='" + appId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", format='" + format + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", signType='" + signType + '\'' +
                ", charset='" + charset + '\'' +
                ", gatewayUrl='" + gatewayUrl + '\'' +
                ", log_path='" + logPath + '\'' +
                '}';
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
