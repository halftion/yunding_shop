package yunding.shop.entity;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author huguobin
 */
public class Alipay {
//    /**
//     * 应用id
//     */
//    private String appId="2016091800539964";
//    /**
//     * 私钥
//     */
//    private String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZstYPZ+bVZx3sVb+lzLO4xuF6EfEy1IaIoZrhQgZYBKH4GYZQhkmNlchAIe/W20seNtgCCztk1DMy3AzCb9RDZvv0Gu2OOl9CZNBaxIXTdaKwKv8TipKFmDxJrz0Z5YsunB8fj5ES3Ek3FKHaQbHlFdTfYQqnERCGQ0bFzM4RvYjkmobS4XmQBK+Kj3ycn8gVW9QAEchSAzYH5bQ09pe9NoB9IoULN0UfLVxUYnI6UomtqE/1rfoMoEeGepTjFFZJUN/aVoKiDTXM+Fu7ByrBtos5aTsnUT3rfXVYFbgQGBl5MHOngdx/XlzKFah81q12LFy8zkKaiwgne6/xM3dpAgMBAAECggEABzkoZkDz0EOwXFMLVrdwGtb98ca2FzV1vSYbKUr5EVnmYsqQTWiCPG01pA30P5y+wwTA0hfFCtAON+2vi9PFngVRAf/h+jU0FOK4CAwEXhPBOZZLtpdkm3K0IqJhtL3c9KvRqqIMp15C8pu9n4xG2AVWo8jjv0ZGCIlQ4irl5F95Twm+Uh9nM13zPSic1YgL+vFvz7vVXAts4966HASiBeWFGvkChZhvBlO8vlDwuis1Z3DUIfs1xAzTh1h4De0N4cKR2uU03HJetuaYM1aAbAOq3HfWfKc8UUAcrAqC8maFVDn97DOSS4JOCxzv77T8MXpMeg5Oduc4kOENHZR4AQKBgQDOB8mLNSMPHO+XGhYo27ws+lQzh8fmaS/BxyUl3bHqO+d3fzE14wqPELxn8v3rX+M3YhmZN1JVeoQ+SIzw1gplMYryPCnDyWYZIGhNrtxh0pNqjCJPg/jUVwFc4Jn1h93wPewvtBPXHwOqd3bC7UZDS2A43onMi/NrYzkfUMIH4QKBgQC++dHeCacIeU27Fw1xKjVEal8rWBGaxYTqAUhe5v+v9GFnXy4Gy4DGHiuL0KEdiPxSvWiD+ZeCDjynJlLCxXdW7S4FxCdyPcltBo14RsoOiw6brbe2vCMjDnby4psYkL/V4uMBm4i+TgTKeZqnVfh8P4km1Lochc7YmjwVEAVAiQKBgQCJyVFzZN0GfW6KGisZtiJ53gwzzinVISGMMZfZZBNZOCjLlOefRzDHjwTBbBN+t0jowBWojwPkIKSSVsjTRAH9vQ1UhQHVazp5sV+wHDJMxZxLFF7Z2MOvlmRV7eto9i1qPq9XE9J0+svj1DM7l5124dD/F20Gu83WUqxgEg1BYQKBgBnUcC433/+laH4FgCvuKYY3XOff4YGtdWyw/7jWRX7m7sWsee0xWRlyuzEN5ry36mjGdXxkJbnRcyKobEkrrBh1u+PLxAoPkU7NtBSwfmO5KyCjPlXY3GfBbRUs0Q1hhpDHPyzYfIZAxyUbjW9UkWc8sLAv1XxGHNPvCHFQ2WUBAoGAaMJP8SX7tI+o+pGEnqVSTUdDD1YNvMD180y+tKs3gOkdsAF/8oj7BAnGz3bO6sw2yfjPlmi9TIczXgRiRAwT/TIoLTT2uoDSWdMDw+wUinVrIWmHRDX/wuWu2HOgjAnWim/geXQwmQ2ho7sX+iOKdKidalUsXHPfgiPZ36iFDNo=";
//    /**
//     * 公钥
//     */
//    private String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAukmN6wr0BiXohXf5djIqFWSCpXQE5Fr5QWxh4P1nesR2wmbt4DJdbEU5i0kj75h/YQ3Q2Eme76lUU0W1giAdQFIDl7bcFeDx4TENMPjgD5a/7pDl4PzZNnwia82WRaVXGuF7RfTXFSIQ4RmpWNqL1BkCg4MWukAh5IVTC7SRnpj2wFkE7FAdI+/V5jOHfqM2kEsFpP7Z3+WnwVSdLe2lACMTilXfXf9aDv1R8peaMjamFyzZwAnY2crtEJLyQA2rojl/+LfRHrriB2n3p5xbisHa0LswIFoXIBEI8FZDVHdKucUcCvoDgVHrbLVIjQL2tr5SnqWlRhDaN+lzt32cLQIDAQAB";
//    /**
//     * 签名方式
//     */
//    private String signType="RSA2";
//    /**
//     * 字符编码格式
//     */
//    private String charset="utf-8";
//    /**
//     * 支付宝网关
//     */
//    private String gatewayUrl="https://openapi.alipaydev.com/gateway.do";
//    /**
//     * returlUrl
//     */
//     public static String returnUrl="http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
//    /**
//     * notifyUrl
//     */
//    public static String notifyUrl="";
//    // 支付宝网关
//    public static String log_path = "C:\\";
//
//
//    public static AlipayClient alipayClient;
//    {
//
//    }
//
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091800539964";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZstYPZ+bVZx3sVb+lzLO4xuF6EfEy1IaIoZrhQgZYBKH4GYZQhkmNlchAIe/W20seNtgCCztk1DMy3AzCb9RDZvv0Gu2OOl9CZNBaxIXTdaKwKv8TipKFmDxJrz0Z5YsunB8fj5ES3Ek3FKHaQbHlFdTfYQqnERCGQ0bFzM4RvYjkmobS4XmQBK+Kj3ycn8gVW9QAEchSAzYH5bQ09pe9NoB9IoULN0UfLVxUYnI6UomtqE/1rfoMoEeGepTjFFZJUN/aVoKiDTXM+Fu7ByrBtos5aTsnUT3rfXVYFbgQGBl5MHOngdx/XlzKFah81q12LFy8zkKaiwgne6/xM3dpAgMBAAECggEABzkoZkDz0EOwXFMLVrdwGtb98ca2FzV1vSYbKUr5EVnmYsqQTWiCPG01pA30P5y+wwTA0hfFCtAON+2vi9PFngVRAf/h+jU0FOK4CAwEXhPBOZZLtpdkm3K0IqJhtL3c9KvRqqIMp15C8pu9n4xG2AVWo8jjv0ZGCIlQ4irl5F95Twm+Uh9nM13zPSic1YgL+vFvz7vVXAts4966HASiBeWFGvkChZhvBlO8vlDwuis1Z3DUIfs1xAzTh1h4De0N4cKR2uU03HJetuaYM1aAbAOq3HfWfKc8UUAcrAqC8maFVDn97DOSS4JOCxzv77T8MXpMeg5Oduc4kOENHZR4AQKBgQDOB8mLNSMPHO+XGhYo27ws+lQzh8fmaS/BxyUl3bHqO+d3fzE14wqPELxn8v3rX+M3YhmZN1JVeoQ+SIzw1gplMYryPCnDyWYZIGhNrtxh0pNqjCJPg/jUVwFc4Jn1h93wPewvtBPXHwOqd3bC7UZDS2A43onMi/NrYzkfUMIH4QKBgQC++dHeCacIeU27Fw1xKjVEal8rWBGaxYTqAUhe5v+v9GFnXy4Gy4DGHiuL0KEdiPxSvWiD+ZeCDjynJlLCxXdW7S4FxCdyPcltBo14RsoOiw6brbe2vCMjDnby4psYkL/V4uMBm4i+TgTKeZqnVfh8P4km1Lochc7YmjwVEAVAiQKBgQCJyVFzZN0GfW6KGisZtiJ53gwzzinVISGMMZfZZBNZOCjLlOefRzDHjwTBbBN+t0jowBWojwPkIKSSVsjTRAH9vQ1UhQHVazp5sV+wHDJMxZxLFF7Z2MOvlmRV7eto9i1qPq9XE9J0+svj1DM7l5124dD/F20Gu83WUqxgEg1BYQKBgBnUcC433/+laH4FgCvuKYY3XOff4YGtdWyw/7jWRX7m7sWsee0xWRlyuzEN5ry36mjGdXxkJbnRcyKobEkrrBh1u+PLxAoPkU7NtBSwfmO5KyCjPlXY3GfBbRUs0Q1hhpDHPyzYfIZAxyUbjW9UkWc8sLAv1XxGHNPvCHFQ2WUBAoGAaMJP8SX7tI+o+pGEnqVSTUdDD1YNvMD180y+tKs3gOkdsAF/8oj7BAnGz3bO6sw2yfjPlmi9TIczXgRiRAwT/TIoLTT2uoDSWdMDw+wUinVrIWmHRDX/wuWu2HOgjAnWim/geXQwmQ2ho7sX+iOKdKidalUsXHPfgiPZ36iFDNo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAukmN6wr0BiXohXf5djIqFWSCpXQE5Fr5QWxh4P1nesR2wmbt4DJdbEU5i0kj75h/YQ3Q2Eme76lUU0W1giAdQFIDl7bcFeDx4TENMPjgD5a/7pDl4PzZNnwia82WRaVXGuF7RfTXFSIQ4RmpWNqL1BkCg4MWukAh5IVTC7SRnpj2wFkE7FAdI+/V5jOHfqM2kEsFpP7Z3+WnwVSdLe2lACMTilXfXf9aDv1R8peaMjamFyzZwAnY2crtEJLyQA2rojl/+LfRHrriB2n3p5xbisHa0LswIFoXIBEI8FZDVHdKucUcCvoDgVHrbLVIjQL2tr5SnqWlRhDaN+lzt32cLQIDAQAB\n";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
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
