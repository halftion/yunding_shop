package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.service.CartService;
import yunding.shop.service.GoodsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 齐语冰
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    GoodsService goodsService;

    @Override
    public ServiceResult getGoods(Integer userId, Map<Integer, List<Goods>> cartMap) {
        try {
            return ServiceResult.success(cartMap.get(userId));
        } catch (Exception e) {
            return ServiceResult.failure("获取购物车列表失败");
        }
    }

    @Override
    public ServiceResult addGoods(Integer userId, Integer goodsId, Map<Integer, List<Goods>> cartMap) {

        List<Goods> goodsList;

        ServiceResult serviceResult = goodsService.selectById(goodsId);
        Goods goods = (Goods) serviceResult.getData();

        //此商品是否存在
        if (serviceResult.isSuccess()){

            //此用户是否创建了购物车
            if (cartMap.containsKey(userId)){
                goodsList = cartMap.get(userId);

                //购物车中是否有此商品
                if(goodsList.contains(goods)){
                    return ServiceResult.success(goodsList);
                }
            } else {
                goodsList = new ArrayList<>();
            }
            goodsList.add(goods);
            cartMap.put(userId,goodsList);
            return ServiceResult.success(goodsList);
        } else {
            return ServiceResult.failure("该商品不存在");
        }
    }

    @Override
    public ServiceResult dropGoods(Integer userId, Integer goodsId, Map<Integer, List<Goods>> cartMap) {

        List<Goods> goodsList;

        ServiceResult serviceResult = goodsService.selectById(goodsId);
        Goods goods = (Goods) serviceResult.getData();

        //此商品是否存在
        if (serviceResult.isSuccess()){

            //此用户是否创建了购物车
            if (cartMap.containsKey(userId)){
                goodsList = cartMap.get(userId);

                //购物车中是否有此商品
                if(goodsList.contains(goods)){
                    goodsList.remove(goods);
                    cartMap.put(userId,goodsList);
                    return ServiceResult.success(goodsList);
                } else {
                    return ServiceResult.failure("购物车中无此商品");
                }
            } else {
                return ServiceResult.failure("该用户尚未创建购物车");
            }
        } else {
            return ServiceResult.failure("该商品不存在");
        }
    }
}
