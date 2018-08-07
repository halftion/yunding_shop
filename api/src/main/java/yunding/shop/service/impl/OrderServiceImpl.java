package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Comment;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Order;
import yunding.shop.mapper.OrderMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.service.ShopService;
import yunding.shop.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserService userService;

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
    public ServiceResult selectByShopId(Integer userId, Integer shopId) {
        try{
            if (!shopService.selectUserIdByShopId(shopId).isSuccess()){
                //获取商户Id失败
                return ServiceResult.failure(shopService.selectUserIdByShopId(shopId).getMessage());
            }
            if(!shopService.selectUserIdByShopId(shopId).getData().equals(userId)){
                return ServiceResult.failure("用户信息不匹配");
            }
            JSONArray orderList = JSONArray.fromObject(orderMapper.selectByShopId(shopId));
            return ServiceResult.success(orderList);
        }catch (Exception e){
            return ServiceResult.failure("订单查询失败");
        }
    }

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
    public ServiceResult userPayByOrderId(Integer userId, Integer orderId) {
        try{
            Order order = orderMapper.selectByOrderId(orderId);
            if (!order.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            ServiceResult serviceResult =goodsService.processOrderCreate(order);
            if(order.getState() == Constant.WAIT_PAY) {
                order.setState(Constant.WAIT_SEND_GOOD);
                order.updateAtNow();
                orderMapper.updateState(order);
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("订单状态修改失败");
            }
        }catch (Exception e){
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult sendGoods(Integer userId, Order order) {
        try{
            Order newOrder = orderMapper.selectByOrderId(order.getOrderId());
            Integer shopId = newOrder.getShopId();
            ServiceResult serviceResult = shopService.selectUserIdByShopId(shopId);
            if(!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }
            if (!serviceResult.getData().equals(userId)) {
                return ServiceResult.failure("商户信息不匹配");
            }
            if(newOrder.getState() == Constant.WAIT_SEND_GOOD) {
                order.setState(Constant.WAIT_RECEIVE_GOOD);
                order.updateAtNow();
                orderMapper.sendGoods(order);
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("订单状态修改失败");
            }
        }catch (Exception e){
            throw new RuntimeException("发货失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult receiveGoodsByOrderId(Integer userId, Integer orderId) {
        try{
            Order order = orderMapper.selectByOrderId(orderId);
            if (!order.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            if(order.getState() != Constant.WAIT_RECEIVE_GOOD) {
                return ServiceResult.failure("订单状态有误");
            }
            order.setState(Constant.WAIT_COMMENT);
            order.updateAtNow();
            if(orderMapper.updateState(order) != 1){
                return ServiceResult.failure("订单状态修改失败");
            }
            if(!shopService.updateSales(order.getShopId(), order.getGoodsNum()).isSuccess())
            {
                //店铺销量修改失败
                return ServiceResult.failure(shopService.updateSales(order.getShopId(), order.getGoodsNum()).getMessage());
            }
            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Service 订单状态修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult commentOrder(Integer userId, Order order) {
        try{
            Order newOrder = orderMapper.selectByOrderId(order.getOrderId());
            Integer orderUser = newOrder.getUserId();
            Integer state = newOrder.getState();
            Integer num = newOrder.getGoodsNum();
            if(!orderUser.equals(userId)){
                return ServiceResult.failure("用户ID和订单不匹配");
            }
            if(state.equals(Constant.WAIT_COMMENT) ){
                order.setState(Constant.OVER_ORDER);
                order.updateAtNow();
                orderMapper.updateComment(order);
            }else {
                return ServiceResult.failure("订单状态有误");
            }
            if(goodsService.commentGoods(order.getOrderId()).isSuccess()){
                return ServiceResult.success();
            }else {
                //商品评价数量修改失败
                return ServiceResult.failure(goodsService.commentGoods(order.getOrderId()).getMessage());
            }
        }catch (Exception e){
            throw  new RuntimeException("订单评论失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deleteByOrderId(Integer userId, Integer orderId) {
        try{
            Order order = orderMapper.selectByOrderId(orderId);
            if (!order.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            if( order.getState()>= Constant.WAIT_RECEIVE_GOOD &&
                    order.getState() <= Constant.OVER_ORDER){
                if(!goodsService.processOrderDelete(order).isSuccess()){
                    //修改商品库存失败
                    return ServiceResult.failure(goodsService.processOrderDelete(order).getMessage());
                }
            }
            order.setState(Constant.DELETE_ORDER);
            order.updateAtNow();
            if(orderMapper.updateState(order) != 1){
                return ServiceResult.failure("订单状态修改失败");
            }
            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Service 错误 删除订单失败");
        }
    }

    @Override
    public ServiceResult selectCommentByGoodsId(Integer goodsId) {
        try{
            List<Comment> commentList = orderMapper.selectCommentByGoodsId(goodsId);
            for (Comment comment : commentList){
                comment.setNickName((String)userService.getNickNameById(comment.getUserId()).getData());
            }
            return ServiceResult.success(commentList);
        }catch (Exception e){
            return ServiceResult.failure("查询评论列表失败");
        }
    }
}
