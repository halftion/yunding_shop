package yunding.shop.service;

import com.alipay.api.AlipayApiException;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.OrderInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author huguobin
 */
public interface AlipayService {
    /**
     * 支付
     * @param orderInfo 订单
     * @return
     */
    ServiceResult purchase(OrderInfo orderInfo);

    /**
     * 处理异步通知
     * @param request
     * @param out_no
     */

    String notifyUrl(HttpServletRequest request, OrderInfo orderInfo, String out_no);

    /**
     * 处理同步通知
     * @param request
     */
    String returnUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException;
}
