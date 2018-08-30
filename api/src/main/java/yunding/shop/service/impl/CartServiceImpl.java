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
import java.util.*;

/**
 * @author 齐语冰
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private JedisPool jedisPool;


    @Override
    public ServiceResult getGoodsList(Integer userId) {
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String,String> map = jedis.hgetAll("cart_" + userId);

            Collection<String> collection = map.values();

            List<Goods> goodsList = new ArrayList<>();

            for (String goodsStr : collection){
                goodsList.add(
                        new Gson().fromJson(goodsStr, Goods.class));
            }

            return ServiceResult.success(goodsList);
        } catch (Exception e) {
            return ServiceResult.failure("获取购物车列表失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult addGoods(Integer userId, Goods goods) {

        try (Jedis jedis = jedisPool.getResource()) {

            String goodsJsonStr = new Gson().toJson(goods);

            jedis.hset("cart_" + userId, goods.getGoodsId().toString(), goodsJsonStr);

            return ServiceResult.success();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("购物车添加商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult dropGoods(Integer userId, Integer goodsId) {

        try (Jedis jedis = jedisPool.getResource()) {

            jedis.hdel("cart_" + userId, goodsId.toString());

            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("购物车移除商品失败");
        }
    }
}
