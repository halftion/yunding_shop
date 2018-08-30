package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.OrderInfo;

import java.util.List;

/**
 * @author huguobin
 */
public interface PayService {

    ServiceResult createTrade(double totalAmount, String tradeId);
}
