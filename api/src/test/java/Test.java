import net.sf.json.JSONObject;
import yunding.shop.entity.Order;
import yunding.shop.entity.OrderGoods;
import yunding.shop.entity.OrderInfo;
import yunding.shop.util.CheckUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        OrderInfo orderInfo = new OrderInfo();
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId("11");
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        orderGoodsList.add(orderGoods);
        orderGoods.setOrderId("1");
        JSONObject jsonObject = JSONObject.fromObject(new Order(orderInfo,orderGoodsList));
        System.out.println(jsonObject);
    }
}
