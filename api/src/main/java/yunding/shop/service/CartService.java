package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author 齐语冰
 */
public interface CartService {

    /**
     * 从购物车里获取商品列表
     * @param userId 用户id
     * @param cartMap 购物车表
     * @return 该用户当前购物车中商品列表
     */
    ServiceResult getGoodsFromCart(Integer userId, Map<Integer, List<Goods>> cartMap);

    /**
     * 向购物车中添加商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 该用户当前购物车中商品列表
     */
    ServiceResult addGoodsFromCart(Integer userId, Integer goodsId, Map<Integer, List<Goods>> cartMap);

    /**
     * 从购物车中移除商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @param cartMap 购物车表
     * @return 该用户当前购物车中商品列表
     */
    ServiceResult dropGoodsFromCart(Integer userId, Integer goodsId, Map<Integer, List<Goods>> cartMap);
}
