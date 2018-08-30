package yunding.shop.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static String app_id = "2016091800537071";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+Cb3frX7RWv0C" +
            "D5YSjAzIJRMY8jUuDLD7NUgd0NojyL3cF5iHD6xiGbCiMNw2YDBwFN4bpdN85EXd" +
            "cvApiKwdz9P+q399RjOsWL4CVOamWLPHxNKtJSZyKpayKh+HuRSH1Sik38ucpF7y" +
            "Jlje9P0AgfAeRzDWn0N9LcTxF6dLGyrF4VsSoRul4w4U3p/p4IS+pqco6ZNIR26x" +
            "RXy83bWSU4x60o8W9wjLqyxJZIHnCaRUXQpxLaMuE+tleaIF3ybyW9AxAVxsfgu1" +
            "wCzJ5CsH5L/3ax8Fip2LhXOcpGfY9kcb2mhxZjsXcMej+Gs58DzsUNBwCzr807wH" +
            "kiTYe2trAgMBAAECggEABVRknNdSzTBOFDa3sMF4wkw34uU4FJiKhiESarOmyoYg" +
            "vQF0Z6VK4l1yFLpmEqhT83DCYegerEwCoZlNnvyURynC+kt5AdxsKpM6kPosldG7" +
            "CQk+F1kJYP5Mx6Zix/2q07xorWcRYM1DVGs65tXcGp2KTG8WsY456FLWSSSurm6C" +
            "Pj6DX6U9Fyrw6pKJ5iEZ2tcGjIUSpcRoyEZ9JgnZ3tKyoxUgF90KFmIAuSlu3pyn" +
            "yfdyhMYFUKggXrlmzYgO7YGYxd4ZHBELxt7LjUHNfYfP+jFMtA2XyQhQHQJXw5aU" +
            "BdceveIfeMsD+MmRP0Cu0HyqViYwksruC5uUvv7ScQKBgQDikXGHq1NqXV4TiQNI" +
            "1dSoCE9/6gPCAwPSKzmj0RxVC1HlaJWEgueRQy7rw+SzULQ6bWbwglsXyeIWIlUq" +
            "Ht+pP/+Yt56bSt+b3yPMVNbsgRZI39p5TJjev0AI5VLpkifOPKocfod3wLe9GTH3" +
            "+xv3s2hdUWvF5Wfnn45pK1KeFwKBgQDWuXwixaa6+fXJB51u8RYr1SNs8y5cKCVz" +
            "M2oVBGiHJ2FGcLRhoY1icVoc+09VIJE/wKA8cawXIs9baVZlqm4ETbnGc7Fzqu3Z" +
            "bPSfZ8VFThOeFxvN7P6U7SDD82yvUDrjOZAKoAQuLfwRiJxh3Do3IZTEEXUD25ll" +
            "vd099sKlzQKBgQCnFAx9uNkNwgajL1HNv2pP87j0olTdGK4Tqa6a4JUdHfjpzQMd" +
            "sg8kbz/FuzMHDQerYowb5nVYiJcCLZVSN2xGepx5OUdsS320i4vxncrUo6kUWofZ" +
            "FaRR9wU4yiwndJGII/JqiuBJbkPQ3iicL4CDXLkkv+tTB4ZlJ6O0aK9HRwKBgQCY" +
            "whyuIC8dlfZlriUyaBF93YP2Xor8xRLfghRWH2y9uiZaFpQ9YNFU149kTuWBiknL" +
            "MR7N4ZCsZPdf8PCmIzlhlmoz5Nq5j0DnDbx6KzkMtR7/uTDur0vuZ0SIIwWPM2Sx" +
            "gqIW7QAMWmqZjrayaBqCXkGXu1OkCvZ2izx6eLLBgQKBgApYURo/IZIL0ENmfcv0" +
            "ihRX2OasXlKwl4ZuhbSqxqfQVmyIyYq+Br650YfWn4Yrr5PWH+54p1l0RP4aJeFE" +
            "doyJe6jvbYq7Phfa7w8utA+HACaKoz7kXz/IiaAE7Ak7dV0WtP/1e1MAU2LnugS+" +
            "MgPLZyHHR1tMNUnCf1k9CEbv";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1X6HewAYya8ZXMcydvFmtva1BXybStyuGAJ233qz/vfHZD9XgDqK70RKisLCQNNbUFfQv0NdXOGPZLOVC6NCnu9K28qUZ4mCMfmmgrczq2+F3m8y+iEawCg2ddWhaWYgWfDXCZt21tBTHz/pmRNx9xdghP304ssVkcnpUeTixYD1SrYU7NQ/6M+5gAHj12zhJbX5F4R4yEupZgU2qzuMk6pY5XzcZg178GaBtvHkfRrLXCxWLrnURXUxGcP9bmqe9Hf6EN1Pi7F5VAyH6KK/1m98rQ600i65OPDjdrcyN8xG+m8nO9U67cbUkfeJSu9LPVV9jfzVwF/nxAPtfxsguQIDAQAB";

/*	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://qiyubing.cn/yundingShop/api";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://qiyubing.cn/yundingShop/api";*/

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    private static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝日志记录地址
    private static String log_path = "/usr/apache-tomcat-9.0.10/logs/aliPay/";

    private String format = "json";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset, alipay_public_key, sign_type);
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
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
}

