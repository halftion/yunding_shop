package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Comment;
import yunding.shop.entity.OrderInfo;
import yunding.shop.entity.OrderGoods;

import java.util.List;

/**
 * @author 齐语冰
 */
public interface OrderGoodsMapper {

    /**
     * 通过订单id查询订单商品列表(不限制state)
     * @param orderId 订单id
     * @return 订单商品列表
     */
    List<OrderGoods> selectByOrderId(String orderId);

    /**
     * 通过订单id查询订单商品列表
     * @param orderId 订单id
     * @param state 状态
     * @return 订单商品列表
     */
    List<OrderGoods> selectByOrderIdAndState(@Param("orderId") String orderId, @Param("state") Integer state);

    /**
     * 通过订单id和商品id精确搜索订单商品
     * @param orderId 订单id
     * @param goodsId 商品id
     * @param state 状态
     * @return 订单商品
     */
    OrderGoods selectByOrderIdAndGoodsIdAndState(@Param("orderId") String orderId, @Param("goodsId") Integer goodsId, @Param("state") Integer state);

    /**
     * 添加订单商品
     * @param orderGoods 订单商品 orderId, goodsId, goodsName, goodsPic,
     *                   goodsNum, unitPrice, totalPrice, createdAt, updatedAt
     * @return 影响条数
     */
    Integer insert(OrderGoods orderGoods);

    /**
     * 根据店铺ID查询所有订单商品(不限制state)
     * @param shopId 店铺Id
     * @return 订单信息
     */
    List<OrderGoods> selectByShopId(Integer shopId);

    /**
     * 更新state
     * @param orderGoods order_id, goods_id, state, updated_at
     * @return 影响条数
     */
    Integer updateState(OrderGoods orderGoods);
}
