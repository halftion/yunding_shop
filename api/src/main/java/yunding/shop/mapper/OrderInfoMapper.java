package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.OrderGoods;
import yunding.shop.entity.OrderInfo;

import java.util.List;

/**
 * 提供有关于订单的方法
 * @author guo
 */
public interface OrderInfoMapper {

    /**
     * 根据订单Id查询订单所有信息(包含 state = -1)
     * @param orderId 订单id
     * @return 订单信息
     */
    OrderInfo selectByOrderId(String orderId);

    /**
     * 根据订单id和state查询订单信息
     * @param orderId 根据订单
     * @param state 状态
     * @return 订单信息
     */
    OrderInfo selectByOrderIdAndState(@Param("orderId") String orderId, @Param("state") Integer state);

    /**
     * 根据用户ID查询未删除的订单信息列表(state != -1)
     * @param userId 用户ID
     * @return 订单信息列表
     */
    List<OrderInfo> selectByUserId(Integer userId);

    /**
     * 通过店铺id查询所有订单信息列表(包括 state = -1)
     * @param shopId 店铺id
     * @return 订单信息列表
     */
    List<OrderInfo> selectByShopId(Integer shopId);

    /**
     * 创建 订单
     * @param orderInfo 订单信息：orderId, userId, shopId,
     *                  shopName, totalPrice, address, phoneNumber, consigneeName, remark, createdAt, updatedAt
     * @return 1为成功，否则失败
     */
    Integer insert(OrderInfo orderInfo);

    /**
     * 买家付款，添加支付宝交易号
     * @param orderInfo order_id, alipayNum, state, updated_at
     * @return 影响条数
     */
    Integer updateAlipayNum(OrderInfo orderInfo);

    /**
     * 发货
     * @param orderInfo orderId, expressCompany, trackingNum, state, updatedAt
     * @return 影响条数
     */
    Integer updateTrackingInfo (OrderInfo orderInfo);

    /**
     * 修改订单状态
     * @param orderInfo orderId, state, updated_at
     * @return 影响条数
     */
    Integer updateState(OrderInfo orderInfo);
}
