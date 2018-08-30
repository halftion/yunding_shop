package yunding.shop.service.impl;

import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yunding.shop.config.AlipayConfig;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.*;
import yunding.shop.mapper.OrderGoodsMapper;
import yunding.shop.mapper.OrderInfoMapper;
import yunding.shop.service.*;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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
    private CartService cartService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private PayService payService;

    @Autowired
    private JedisPool jedisPool;

    private ServiceResult selectOrderByOrderInfo(OrderInfo orderInfo) {
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

    private ServiceResult selectOrderListByOrderInfoList(List<OrderInfo> orderInfoList) {
        try {
            List<Order> orderList = new ArrayList<>();

            ServiceResult serviceResult;

            for (OrderInfo orderInfo : orderInfoList) {

                serviceResult = selectOrderByOrderInfo(orderInfo);

                if (!serviceResult.isSuccess()) {
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                orderList.add((Order) serviceResult.getData());
            }
            return ServiceResult.success(orderList);
        } catch (Exception e) {
            return ServiceResult.failure("通过订单信息列表查询订单列表失败");
        }
    }

    private String getRandomId(Integer userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sdf.format(new Date());
        int uuid = (int) (Math.random() * 100);
        return date + userId + uuid;
    }

    @Override
    public ServiceResult selectOrderListByUserId(Integer userId) {
        try {
            List<OrderInfo> orderInfoList = orderInfoMapper.selectByUserIdSortByTime(userId);
            ServiceResult serviceResult = selectOrderListByOrderInfoList(orderInfoList);

            if (!serviceResult.isSuccess()) {
                return ServiceResult.failure(serviceResult.getMessage());
            }

            return ServiceResult.success(serviceResult.getData());
        } catch (Exception e) {
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderByOrderId(Integer userId, String orderId) {
        try {
            OrderInfo orderInfo = orderInfoMapper.selectByOrderId(orderId);

            if (orderInfo == null) {
                return ServiceResult.failure("没有此订单");
            }

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);

            return ServiceResult.success(new Order(orderInfo, orderGoodsList));

        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderByOrderIdAndState(Integer userId, String orderId, Integer state) {
        try {
            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, state);

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);

            return ServiceResult.success(new Order(orderInfo, orderGoodsList));

        } catch (Exception e) {
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    public ServiceResult selectOrderListByShopId(Integer userId, Integer shopId) {
        try {
            ServiceResult serviceResult = shopService.selectUserIdByShopId(shopId);

            if (!serviceResult.isSuccess()) {
                //获取商户Id失败
                return ServiceResult.failure(serviceResult.getMessage());
            }

            if (!serviceResult.getData().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            List<OrderInfo> orderInfoList = orderInfoMapper.selectByShopIdSortByTime(shopId);

            serviceResult = selectOrderListByOrderInfoList(orderInfoList);

            if (serviceResult.isSuccess()) {
                return ServiceResult.success(serviceResult.getData());
            } else {
                return ServiceResult.failure(serviceResult.getMessage());
            }

        } catch (Exception e) {
            return ServiceResult.failure("订单查询失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult createOrderList(Integer userId, Order order) {
        try {

            OrderInfo orderInfo = order.getOrderInfo();
            orderInfo.setUserId(userId);

            List<OrderGoods> orderGoodsList = order.getOrderGoodsList();

            List<String> orderIdList = new ArrayList<>();

//            List<Order> orderList = new ArrayList<>();
            Map<Integer, List<OrderGoods>> orderGoodsMapGroupByShopId = new HashMap<>();

            ServiceResult serviceResult;


            //将订单商品列表按店铺id分组
            for (OrderGoods orderGoods : orderGoodsList) {

                serviceResult = cartService.dropGoods(userId,orderGoods.getGoodsId());

                if (!serviceResult.isSuccess()){
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                /*完善orderGoods信息*/

                serviceResult = goodsService.selectShopIdByGoodsId(orderGoods.getGoodsId());

                if (!serviceResult.isSuccess()) {
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                Integer shopId = (Integer) serviceResult.getData();

                if (orderGoodsMapGroupByShopId.containsKey(shopId)) {
                    orderGoodsMapGroupByShopId.get(shopId).add(orderGoods);
                } else {
                    List<OrderGoods> tempOrderList = new ArrayList<>();
                    tempOrderList.add(orderGoods);
                    orderGoodsMapGroupByShopId.put(shopId, tempOrderList);
                }
            }

            Set<Integer> shopIdSet = orderGoodsMapGroupByShopId.keySet();

            for (Integer shopId : shopIdSet) {
                //订单总价
                BigDecimal totalPirce = new BigDecimal(0);
                //订单id
                String orderId = getRandomId(userId);

                List<OrderGoods> newOrderGoodsList = orderGoodsMapGroupByShopId.get(shopId);

                for (OrderGoods orderGoods : newOrderGoodsList) {
                    /*完善orderGoods信息*/
                    orderGoods.setOrderId(orderId);
                    orderGoods.setCreatedAt(new Date());
                    orderGoods.setUpdatedAt(new Date());

                    //补全订单商品信息,价格
                    serviceResult = goodsService.processOrderCreate(orderGoods);
                    if (!serviceResult.isSuccess()) {
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
                shopService.processOrderCreate(orderInfo, orderGoodsList);

                orderInfoMapper.insert(orderInfo);
                //向方法的全局列表添加
                orderIdList.add(orderInfo.getOrderId());
            }

            //创建交易
            serviceResult = createTrade(orderIdList);

            if (!serviceResult.isSuccess()) {
                return ServiceResult.failure(serviceResult.getMessage());
            }
            //返回生成的二维码表单
            return ServiceResult.success(serviceResult.getData());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult createTrade(List<String> orderIdList) {

        try (Jedis jedis = jedisPool.getResource()) {
            BigDecimal totalPrice = new BigDecimal(0);
            jedis.select(0);
            String tradeId = getRandomId(0);
            String key = "trade_" + tradeId;
            for (String orderId : orderIdList) {
                jedis.lpush(key, orderId);
                jedis.expire(key, 10800);

                OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.WAIT_PAY);

                if (orderInfo == null) {
                    return ServiceResult.failure("未查询到该待付款订单");
                }

                totalPrice = totalPrice.add(orderInfo.getTotalPrice());

            }

            ServiceResult serviceResult = payService.createTrade(totalPrice.doubleValue(), tradeId);

            if (!serviceResult.isSuccess()) {
                throw new RuntimeException(serviceResult.getMessage());
            }

            Map<String,Object> map = new HashMap();

            map.put("tradeId",tradeId);
            map.put("totalPrice",totalPrice.toString());
            map.put("html",serviceResult.getData());

            return ServiceResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建交易失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult pay(Map<String, String[]> requestParams,String out_trade_no, String trade_no, String trade_status, String total_amount) {

        try (Jedis jedis = jedisPool.getResource()) {

            Map<String, String> params = new HashMap<>();
            for (String name : requestParams.keySet()) {

                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes(/*StandardCharsets.ISO_8859_1*/), StandardCharsets.UTF_8);

                params.put(name, valueStr);
            }

            AlipayConfig.logResult("开始验签\n");

            //调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

            //——请在这里编写您的程序（以下代码仅作参考）——

	        /*  实际验证过程建议商户务必添加以下校验：
	            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	            4、验证app_id是否为该商户本身。
	        */

            //验证成功
            if (signVerified) {

                if ("TRADE_FINISHED".equals(trade_status)) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    return ServiceResult.failure("暂不支持");

                } else if ("TRADE_SUCCESS".equals(trade_status)) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知

                    String key = "trade_" + out_trade_no;
                    //获取流水号对应的订单列表
                    List<String> orderIdList = jedis.lrange(key, 0, jedis.llen(key));

                    if (orderIdList == null) {
                        return ServiceResult.failure("此订单已失效");
                    }

                    //将redis中的交易流水标为已完成支付
                    jedis.lpush(key,"havePay");

                    for (String orderId : orderIdList) {
                        OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.WAIT_PAY);

                        if (orderId == null) {
                            return ServiceResult.failure("未查询到该待付款订单");
                        }

                        if (trade_no == null) {
                            return ServiceResult.failure("支付宝交易号不能为空");
                        }

                        if (! new BigDecimal(total_amount).equals(orderInfo.getTotalPrice())) {
                            return ServiceResult.failure("总金额错误");
                        }

                        orderInfo.setAlipayNum(trade_no);
                        orderInfo.updateAtNow();
                        orderInfo.setState(Constant.HAVE_PAY);

                        if (orderInfoMapper.updateAlipayNum(orderInfo) != 1) {
                            throw new RuntimeException("修改支付信息失败");
                        }
                    }
                }
                return ServiceResult.success();

            } else {
                //调试用，写文本函数记录程序运行情况是否正常
                String sWord = AlipaySignature.getSignCheckContentV1(params);
                AlipayConfig.logResult("验签失败\n:"+sWord);
                return ServiceResult.failure("验证失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    public ServiceResult checkPay(String tradeId){
        try (Jedis jedis = jedisPool.getResource()){
            String string = jedis.lrange("trade_"+tradeId,0,1).get(0);
            if ("havePay".equals(string)){
                return ServiceResult.success();
            } else {
                return ServiceResult.failure("此交易非付款状态");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.failure("检测失败");
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult deliver(Integer userId, String orderId, String expressCompany, String trackingNum) {
        try {

            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.HAVE_PAY);

            if (orderInfo == null) {
                return ServiceResult.failure("未查询到该已付款订单");
            }

            Integer shopId = orderInfo.getShopId();

            ServiceResult serviceResult = shopService.selectUserIdByShopId(shopId);

            if (expressCompany == null || trackingNum == null) {
                return ServiceResult.failure("物流信息不能为空");
            }

            if (!serviceResult.isSuccess()) {
                return ServiceResult.failure(serviceResult.getMessage());
            }

            if (!serviceResult.getData().equals(userId)) {
                return ServiceResult.failure("商户信息不匹配");
            }

            orderInfo.setExpressCompany(expressCompany);
            orderInfo.setTrackingNum(trackingNum);
            orderInfo.updateAtNow();
            orderInfo.setState(Constant.HAVE_DELIVER);

            if (orderInfoMapper.updateTrackingInfo(orderInfo) != 1) {
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发货失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult receive(Integer userId, String orderId) {
        try {
            OrderInfo orderInfo = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.HAVE_DELIVER);

            if (orderInfo == null) {
                return ServiceResult.failure("未查询到已发货订单");
            }

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }
            if (!orderInfo.getState().equals(Constant.HAVE_DELIVER)) {
                return ServiceResult.failure("订单状态有误");
            }

            orderInfo.setState(Constant.HAVE_RECEIVE_GOOD);
            orderInfo.updateAtNow();

            if (orderInfoMapper.updateState(orderInfo) != 1) {
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单状态修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult updateCommentState(Integer userId, String orderId, Integer goodsId) {
        try {

            Integer dbUserId = orderInfoMapper.selectByOrderIdAndState(orderId, Constant.HAVE_RECEIVE_GOOD).getUserId();

            if (!userId.equals(dbUserId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            OrderGoods orderGoods = orderGoodsMapper.selectByOrderIdAndGoodsIdAndState(orderId, goodsId, Constant.WAIT_COMMENT);

            if (!orderGoods.getState().equals(Constant.WAIT_COMMENT)) {
                return ServiceResult.failure("商品已评价或被删除");
            }

            orderGoods.setState(Constant.HAVE_COMMENT);
            orderGoods.setUpdatedAt(new Date());

            if (orderGoodsMapper.updateState(orderGoods) != 1) {
                return ServiceResult.failure("更新订单商品状态失败");
            }

            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单评论失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult dropByOrderId(Integer userId, String orderId) {
        try {
            OrderInfo orderInfo = orderInfoMapper.selectByOrderId(orderId);

            if (!orderInfo.getUserId().equals(userId)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            if (orderInfo.getState() == -1) {
                return ServiceResult.failure("订单状态错误");
            }

            if (orderInfo.getState() <= Constant.HAVE_PAY) {
                List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(orderId);
                for (OrderGoods orderGoods : orderGoodsList) {
                    //回滚销量和库存
                    ServiceResult serviceResult = goodsService.processOrderDelete(orderGoods);
                    if (!serviceResult.isSuccess()) {
                        return ServiceResult.failure(serviceResult.getMessage());
                    }
                    /*回滚购物车*/
                    Goods goods = new Goods();
                    goods.setGoodsId(orderGoods.getGoodsId());
                    goods.setName(orderGoods.getGoodsName());
                    goods.setShopName(orderInfo.getShopName());
                    goods.setPicture(orderGoods.getGoodsPic());
                    goods.setPrice(orderGoods.getTotalPrice());
                    cartService.addGoods(userId,goods);
                }
            }

            orderInfo.setState(Constant.DROP_STATE);
            orderInfo.updateAtNow();

            if (orderInfoMapper.updateState(orderInfo) != 1) {
                return ServiceResult.failure("订单状态修改失败");
            }

            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除订单失败");
        }
    }

}
