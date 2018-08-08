package yunding.shop.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author huguobin
 * 用于发送验证码
 */
@Configuration
@PropertySource(value = "classpath:SMS.properties", encoding = "utf-8")
public class SmsUtil {
    /**
     * 产品名称:云通信短信API产品
     */
    @Value("${SMS.product}")
    private  String product;
    /**
     *   产品域名
     */
    @Value("${SMS.domain}")
    private  String domain;
    /**
     *云账号密码
     */
    @Value("${SMS.accessKeyId}")
    private  String accessKeyId ;
    @Value("${SMS.accessKeySecret}")
    private  String accessKeySecret ;
    /**
     * 短信签名
     */
    @Value("${SMS.signName}")
    private String signName;
    /**
     * 短信模板
     */
    @Value("${SMS.templateCode}")
    private  String templateCode;
    /**
     * 手机号码
     */
    private  String phoneNumber;
    /**
     * 过期时间
     */
    @Value("${SMS.outTime")
    private  String outTime;

    /**
     *
     * @param phoneNumber 请求短信验证码的手机号
     * @return
     * @throws ClientException
     */

    public  SendSmsResponse sendSms(String phoneNumber,String verification) throws ClientException {

        this.phoneNumber = phoneNumber;

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", outTime);
        System.setProperty("sun.net.client.defaultReadTimeout", outTime);
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{ \"code\":\""+verification+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(accessKeyId);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println(1);

        return sendSmsResponse;
    }
    public  QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(outTime));
        System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(outTime));

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNumber);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

    /**
     * 发送短信
     */
    public  void sendMessaging(String phoneNumber,String verification) throws ClientException {

        System.out.println(product);
        System.out.println(domain);
        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);
        System.out.println(signName);
        System.out.println(templateCode);
        System.out.println(outTime);
        System.out.println(verification);
        System.out.println(phoneNumber);

        //发短信
        SendSmsResponse response = sendSms(phoneNumber,verification);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

//            Thread.sleep(3000L);

        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse =querySendDetails(response.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }else {
            throw  new RuntimeException("发送验证码错误");
        }
    }
    /**
     * 随机生成6位数验证码
     * @return 验证码
     */
    public static String randomVerificationCode(){
        return UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

//    public void setSignName(String signName) {
//        this.signName = signName;
//    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
