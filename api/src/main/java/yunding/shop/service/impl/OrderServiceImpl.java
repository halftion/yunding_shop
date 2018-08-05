package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Order;
import yunding.shop.mapper.OrderMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.service.ShopService;

import java.util.List;

/**
 * @author guo
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopService shopService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult createOrder(Integer userId, List<Order> orderList) {
        try{
            for (Order order : orderList) {
                order.setUserId(userId);
                order.createAtNow();
                order.updateAtNow();
                if (!goodsService.processOrderCreate(order).isSuccess()) {
                    //修改商品信息失败
                    return ServiceResult.failure(goodsService.processOrderCreate(order).getMessage());
                }
                if (orderMapper.insertOrder(order) != 1) {
                    return ServiceResult.failure("单个订单创建失败");
                }
            }
            return ServiceResult.success();
        }catch (Exception e){
            throw  new RuntimeException("订单创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult commentOrder(Integer userId, Order order) {
        try{
            Order newOrder = orderMapper.selectByOrderId(order.getOrderId());
            Integer orderUser = newOrder.getUserId();
            Integer state = newOrder.getState();
            if(!orderUser.equals(userId)){
                return ServiceResult.failure("用户ID和订单不匹配");
            }
            if(state.equals(Constant.WAIT_COMMENT) ){
                orderMapper.updateComment(order.getOrderId(),order.getComment());
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("订单状态有误");
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
            return ServiceResult.failure("订单查询失败");
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
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deleteByOrderId(Integer userId, Integer orderId) {
        try{
            Order order = orderMapper.selectByOrderId(orderId);
            if (order.getUserId().equals(userId)) {
                if( order.getState()>=Constant.WAIT_RECEIVE_GOOD &&
                        order.getState() <=Constant.OVER_ORDER){
                    if(!goodsService.processOrderDelete(order).isSuccess()){
                        //修改商品信息失败
                        return ServiceResult.failure(goodsService.processOrderDelete(order).getMessage());
                    }
                }
                if(orderMapper.updateState(orderId,Constant.DELETE_ORDER )!= 1){
                    return ServiceResult.failure("订单状态修改失败");
                }
                return ServiceResult.success();
            }else{
                return  ServiceResult.failure("用户信息不匹配");
            }
        }catch (Exception e){
            throw new RuntimeException("Service 错误 查询订单失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateState(Integer orderId, Integer state) {
        try {
            if (state >= 0 && orderMapper.selectByOrderId(orderId).getState() != (state - 1 )) {
                return ServiceResult.failure("订单状态有误");
            }
            if (orderMapper.updateState(orderId, state) != 1) {
                return ServiceResult.failure("订单状态修改失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    public ServiceResult selectByShopId(Integer userId, Integer shopId) {
        try{
            if (!shopService.selectUserIdByShopId(shopId).isSuccess()){
                //获取商户Id失败
                return ServiceResult.failure(shopService.selectUserIdByShopId(shopId).getMessage());
            }
            if(!shopService.selectUserIdByShopId(shopId).getData().equals(userId)){
                return ServiceResult.failure("用户信息不匹配");
            }
            JSONArray orderList = JSONArray.fromObject(orderMapper.selectByUserId(userId));
            return ServiceResult.success(orderList);
        }catch (Exception e){
            return ServiceResult.failure("订单查询失败");
        }
    }
}
