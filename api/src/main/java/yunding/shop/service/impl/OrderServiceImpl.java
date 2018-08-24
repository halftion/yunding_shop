package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.*;
import yunding.shop.mapper.OrderGoodsMapper;
import yunding.shop.mapper.OrderInfoMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.service.ShopService;
import yunding.shop.service.UserService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author guo
 * @author 齐语冰 2018/8/13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopService shopService;

    private ServiceResult selectOrderByOrderInfo(OrderInfo orderInfo){
        try {
            Order order = new Order();
            List<OrderGoods> orderGoodsList;

            orderGoodsList = orderGoodsMapper.selectByOrderId((orderInfo.getOrderId()));
            order.setOrderInfo(orderInfo);
            order.setOrderGoodsList(orderGoodsList);

            return ServiceResult.success(order);
        } catch (Exception e) {
            return ServiceResult.failure("通过订单信息查询订单失败");
        }

    }

    private ServiceResult selectOrderListByOrderInfoList(List<OrderInfo> orderInfoList){
        try {
            List<Order> orderList = new ArrayList<>();

            ServiceResult serviceResult;

            for (OrderInfo orderInfo : orderInfoList){

                serviceResult = selectOrderByOrderInfo(orderInfo);

                if (!serviceResult.isSuccess()){
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                orderList.add((Order) serviceResult.getData());
            }
            return ServiceResult.success(orderList);
        } catch (Exception e) {
            return ServiceResult.failure("通过订单信息列表查询订单列表失败");
        }
    }

    private String getOrderNo(Integer userId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sdf.format(new Date());
        int uuid = (int) (Math.random() * 100);
        return date + userId + uuid;
    }

    @Override
    public ServiceResult selectOrderListByUserId(Integer userId) {
        try{
            List<OrderInfo> orderInfoList = orderInfoMapper.selectByUserId(userId);
            ServiceResult serviceResult = selectOrderListByOrderInfoList(orderInfoList);

            if (!serviceResult.isSuccess()) {
                return ServiceResult.failure(serviceResult.getMessage());
            }

            return ServiceResult.success(serviceResult.getData());
        }catch (Exception e){
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderByOrderId(Integer userId, String orderId){
        try{
            OrderInfo orderInfo = orderInfoMapper.selectByOrderId(orderId);

            if (orderId == null){
                return ServiceResult.failure("没有此订单");
            }

            if(!orderInfo.getUserId().equals(userId)){
                return  ServiceResult.failure("用户信息不匹配");
            }

            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);

            return ServiceResult.success(new Order(orderInfo,orderGoodsList));

        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderByOrderIdAndState(Integer userId, String orderId, Integer state){
        try{
            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, state);

            if(!orderInfo.getUserId().equals(userId)){
                return  ServiceResult.failure("用户信息不匹配");
            }

            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);

            return ServiceResult.success(new Order(orderInfo,orderGoodsList));

        }catch (Exception e){
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderListByShopId(Integer userId, Integer shopId) {
        try{
            ServiceResult serviceResult = shopService.selectUserIdByShopId(shopId);

            if (!serviceResult.isSuccess()){
                //获取商户Id失败
                return ServiceResult.failure(serviceResult.getMessage());
            }

            if(!serviceResult.getData().equals(userId)){
                return ServiceResult.failure("用户信息不匹配");
            }

            List<OrderInfo> orderInfoList = orderInfoMapper.selectByShopId(shopId);

            serviceResult = selectOrderListByOrderInfoList(orderInfoList);

            if (serviceResult.isSuccess()){
                return ServiceResult.success(serviceResult.getData());
            } else {
                return ServiceResult.failure(serviceResult.getMessage());
            }

        }catch (Exception e){
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult createOrderList(Integer userId, Order order) {
        try{

            OrderInfo orderInfo = order.getOrderInfo();
            orderInfo.setUserId(userId);
            List<OrderGoods> orderGoodsList = order.getOrderGoodsList();

            List<Order> orderList = new ArrayList<>();
            Map<Integer,List<OrderGoods>> orderGoodsMapGroupByShopId = new HashMap<>();

            ServiceResult serviceResult;



            //将订单商品列表按店铺id分组
            for (OrderGoods orderGoods : orderGoodsList){

                /*完善orderGoods信息*/

                serviceResult = goodsService.selectShopIdByGoodsId(orderGoods.getGoodsId());

                if (!serviceResult.isSuccess()){
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                Integer shopId = (Integer) serviceResult.getData();

                if (orderGoodsMapGroupByShopId.containsKey(shopId)){
                    orderGoodsMapGroupByShopId.get(shopId).add(orderGoods);
                } else {
                    List<OrderGoods> tempOrderList = new ArrayList<>();
                    tempOrderList.add(orderGoods);
                    orderGoodsMapGroupByShopId.put(shopId,tempOrderList);
                }
            }

            Set<Integer> shopIdSet = orderGoodsMapGroupByShopId.keySet();

            for (Integer shopId : shopIdSet){
                //订单总价
                BigDecimal totalPirce = new BigDecimal(0);
                //订单id
                String orderId = getOrderNo(userId);

                List<OrderGoods> newOrderGoodsList = orderGoodsMapGroupByShopId.get(shopId);

                for (OrderGoods orderGoods : newOrderGoodsList){
                    /*晚上orderGoods信息*/
                    orderGoods.setOrderId(orderId);
                    orderGoods.setCreatedAt(new Date());
                    orderGoods.setUpdatedAt(new Date());

                    //补全订单商品信息,价格
                    serviceResult = goodsService.processOrderCreate(orderGoods);
                    if (!serviceResult.isSuccess()){
                        return ServiceResult.failure(serviceResult.getMessage());
                    }
                    orderGoodsMapper.insert(orderGoods);
                    //循环加和 计算订单总价
                    totalPirce = totalPirce.add(orderGoods.getTotalPrice());
                }

                /*完善orderInfo信息 店铺信息 商品信息 价钱 用户*/
                orderInfo.setOrderId(orderId);
                orderInfo.setShopId(shopId);
                orderInfo.createAtNow();
                orderInfo.updateAtNow();
                orderInfo.setTotalPrice(totalPirce);
                /*添加店铺信息，增加销量，减少库存*/
                shopService.processOrderCreate(orderInfo,orderGoodsList);

                orderInfoMapper.insert(orderInfo);

                Order newOrder = new Order(orderInfo, newOrderGoodsList);
                orderList.add(newOrder);
            }
            return ServiceResult.success(orderList);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("订单创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult pay(Integer userId, String orderId, String alipayNum) {
        try{
            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId,Constant.WAIT_PAY);

            if (orderId == null){
                return ServiceResult.failure("未查询到该待付款订单");
            }

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            if(alipayNum == null){
                return ServiceResult.failure("支付宝交易号不能为空");
            }

            orderInfo.setAlipayNum(alipayNum);
            orderInfo.updateAtNow();
            orderInfo.setState(Constant.HAVE_PAY);

            if (orderInfoMapper.updateAlipayNum(orderInfo) != 1){
                return ServiceResult.failure("更新支付宝交易号失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deliver(Integer userId, String orderId, String expressCompany, String trackingNum) {
        try{

            System.out.println("!!!!!!!!!!!!"+orderId);

            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId,Constant.HAVE_PAY);

            if (orderInfo == null){
                return ServiceResult.failure("未查询到该已付款订单");
            }

            Integer shopId = orderInfo.getShopId();

            ServiceResult serviceResult = shopService.selectUserIdByShopId(shopId);

            if(expressCompany == null || trackingNum == null){
                return ServiceResult.failure("物流信息不能为空");
            }

            if(!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }

            if (!serviceResult.getData().equals(userId)) {
                return ServiceResult.failure("商户信息不匹配");
            }

            orderInfo.setExpressCompany(expressCompany);
            orderInfo.setTrackingNum(trackingNum);
            orderInfo.updateAtNow();
            orderInfo.setState(Constant.HAVE_DELIVER);

            if(orderInfoMapper.updateTrackingInfo(orderInfo) != 1) {
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发货失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult receive(Integer userId, String orderId) {
        try{
            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.HAVE_DELIVER);

            if (orderInfo == null){
                return ServiceResult.failure("未查询到已发货订单");
            }

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            if(!orderInfo.getState().equals(Constant.HAVE_DELIVER)) {
                return ServiceResult.failure("订单状态有误");
            }

            orderInfo.setState(Constant.HAVE_RECEIVE_GOOD);
            orderInfo.updateAtNow();

            if(orderInfoMapper.updateState(orderInfo) != 1){
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateCommentState(Integer userId, String orderId, Integer goodsId) {
        try{

            Integer dbUserId = orderInfoMapper.selectByOrderIdAndState(orderId,Constant.HAVE_RECEIVE_GOOD).getUserId();

            if (!userId.equals(dbUserId)){
                return ServiceResult.failure("用户信息不匹配");
            }

            OrderGoods orderGoods = orderGoodsMapper.selectByOrderIdAndGoodsIdAndState(orderId,goodsId,Constant.WAIT_COMMENT);

            if (!orderGoods.getState().equals(Constant.WAIT_COMMENT)){
                return ServiceResult.failure("商品已评价或被删除");
            }

            orderGoods.setState(Constant.HAVE_COMMENT);
            orderGoods.setUpdatedAt(new Date());

            if(orderGoodsMapper.updateState(orderGoods) != 1){
                return ServiceResult.failure("更新订单商品状态失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("订单评论失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult dropByOrderId(Integer userId, String orderId) {
        try{
            OrderInfo orderInfo = orderInfoMapper.selectByOrderId(orderId);

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            if(orderInfo.getState() == -1 ){
                return ServiceResult.failure("订单状态错误");
            }

            if (orderInfo.getState() <= Constant.HAVE_PAY ){
                List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);
                for (OrderGoods orderGoods : orderGoodsList){
                    ServiceResult serviceResult = goodsService.processOrderDelete(orderGoods);
                    if (! serviceResult.isSuccess()){
                        return ServiceResult.failure("减少库存失败");
                    }
                }
            }

            orderInfo.setState(Constant.DROP_STATE);
            orderInfo.updateAtNow();

            if(orderInfoMapper.updateState(orderInfo) != 1){
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除订单失败");
        }
    }

}
