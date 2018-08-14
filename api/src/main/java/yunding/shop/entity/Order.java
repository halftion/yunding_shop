package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @NotNull(message = "订单信息不能为空")
    private OrderInfo orderInfo;

    @NotNull(message = "订单商品列表不能为空")
    private List<OrderGoods> orderGoodsList;


    public Order() {
    }

    public Order(OrderInfo orderInfo, List<OrderGoods> orderGoodsList) {
        this.orderInfo = orderInfo;
        this.orderGoodsList = orderGoodsList;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }
}
