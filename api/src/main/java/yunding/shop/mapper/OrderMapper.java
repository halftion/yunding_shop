package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供有关于订单的方法
 * @author guo
 */
public interface OrderMapper {

    /**
     * 创建 订单
     * @param order 传入order的信息
     * @return 1为成功，否则失败
     */
    Integer insertOrder(Order order);

    /**
     * 根据订单Id查询订单信息
     * @param orderId 订单id
     * @return 订单信息
     */
    Order selectByOrderId(Integer orderId);

    /**
     * 评价订单
     * @param orderId 订单ID
     * @param comment 评价
     */
    Integer updateComment(@Param("orderId") Integer orderId , @Param("comment") String comment);

    /**
     * 根据用户ID查询所有订单
     * @param userId 用户ID
     * @return 所有订单信息
     */
    List<Order> selectByUserId(Integer userId);

    /**
     * 修改订单状态
     * @param orderId 订单ID
     * @param state 订单状态
     */
    Integer updateState(@Param("orderId") Integer orderId ,@Param("state") Integer state);

    /**
     * 根据店铺ID查询所有订单
     * @param shopId 店铺Id
     * @return 所有订单信息
     */
    List<Order> selectByShopId(Integer shopId);

    /**
     * 根据商品Id查询商品评价
     * @param goodsId 商品Id
     * @return 用户Id和商品评价
     */
    HashMap<Integer,String> selectCommentByGoodsId (Integer goodsId);
}
