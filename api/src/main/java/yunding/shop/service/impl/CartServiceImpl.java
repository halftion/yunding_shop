package yunding.shop.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.service.CartService;
import yunding.shop.service.GoodsService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 齐语冰
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis = null;

    @Override
    public ServiceResult getGoods(Integer userId) {
        try {
            jedis  = jedisPool.getResource();

            Long length = jedis.llen("cart_"+userId);
            List<String> jsonStrList = jedis.lrange("cart_"+userId,0,length);
            List<Goods> goodsList = new ArrayList<>();

            for (String jsonStr : jsonStrList){
                Goods goods = new Gson().fromJson(jsonStr,Goods.class);
                goodsList.add(goods);
            }

            return ServiceResult.success(goodsList);
        } catch (Exception e) {
            return ServiceResult.failure("获取购物车列表失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult addGoods(Integer userId, Integer goodsId) {

        try {
            System.out.println(jedisPool);
            jedis  = jedisPool.getResource();
            jedis.select(0);

            ServiceResult serviceResult = goodsService.selectById(goodsId);
            Goods goods = (Goods) serviceResult.getData();

            //此商品是否存在
            if (serviceResult.isSuccess()){

                String goodsJsonStr = new Gson().toJson(goods);
                jedis.lpushx("cart_"+userId,goodsJsonStr);

                return ServiceResult.success();
            } else {
                return ServiceResult.failure("该商品不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("购物车添加商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult dropGoods(Integer userId, Integer goodsId) {
        jedis  = jedisPool.getResource();

        ServiceResult serviceResult = goodsService.selectById(goodsId);
        Goods goods = (Goods) serviceResult.getData();
        return null;
    }
}
