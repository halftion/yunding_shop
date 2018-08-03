package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Goods;
import yunding.shop.entity.Order;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.mapper.OrderMapper;
import yunding.shop.mapper.UserMapper;
import yunding.shop.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author guo
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult createOrder(Integer userId, Order order) {
        try{
            order.setUserId(userId);
            Integer goodsId = order.getGoodsId();
            Goods goods = (goodsMapper.selectByGoodsId(goodsId));
            order.setUnitPrice(goods.getPrice());
            order.setTotalPrice(goods.getPrice().multiply(BigDecimal.valueOf(order.getGoodsNum())));
            order.setShopId(goods.getShopId());
            order.setShopName(goods.getShopName());
            order.setCreatedAt(new Date());
            order.setUpdatedAt(new Date());
            Integer i = orderMapper.createOrder(order);
            Integer j = goodsMapper.updateStockAndSales(goodsId,
                    (goods.getStockNum() - order.getGoodsNum()),
                    (goods.getState() + order.getGoodsNum()));
            if(i == 1 && j ==1){
                return ServiceResult.success();
            }else {
                return ServiceResult.failure();
            }
        }catch (Exception e){
            throw  new RuntimeException("创建订单失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult commentOrder(Integer userId, Order order) {
        try{
            Order newOrder = orderMapper.selectByOrderId(order.getOrderId());
            Integer orderUser = newOrder.getUserId();
            Integer state = newOrder.getState();
            if(orderUser.equals(userId)){
                if(state.equals(Constant.WAIT_COMMENT) ){
                    order.setState(Constant.OVER_ORDER);
                    orderMapper.commentOrder(order);
                    return ServiceResult.success();
                }else {
                    return ServiceResult.failure("订单状态有误");
                }
            }else{
                return ServiceResult.failure("用户ID和订单不匹配");
            }
        }catch (Exception e){
            throw  new RuntimeException("订单评论失败");
        }
    }

    @Override
    public ServiceResult selectByUserId(Integer userId) {
        try{
            JSONArray orderList = JSONArray.fromObject(orderMapper.selectByUserId(userId));
            return ServiceResult.success(orderList);
        }catch (Exception e){
            return ServiceResult.failure("Service 错误 查询订单失败");
        }

    }

    @Override
    public ServiceResult selectByOrderId(Integer userId , Integer orderId){
        try{
            Order order = orderMapper.selectByOrderId(orderId);
            if(order.getUserId().equals(userId)){
                return ServiceResult.success(order);
            }else{
                return  ServiceResult.failure("用户信息不匹配");
            }
        }catch (Exception e){
            return ServiceResult.failure("Service 错误 查询订单失败");
        }
    }
}
