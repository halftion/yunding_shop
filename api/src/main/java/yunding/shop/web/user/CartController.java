package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.service.GoodsService;
import yunding.shop.utils.UserUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/cartMap")
@Scope("singleton")
public class CartController {

    @Autowired
    Map<Integer, List<Goods>> cartMap;

    @Autowired
    GoodsService goodsService;

    /**
     * 在购物车中添加商品
     * @param goodsId 商品id
     */
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.PUT)
    public RequestResult addGoods(@PathVariable Integer goodsId,
                                  HttpServletRequest request){

        try {
            Integer userId = UserUtil.getCurrentUserId(request);

            List<Goods> goodsList;

            ServiceResult serviceResult = goodsService.selectById(goodsId);

            if (serviceResult.isSuccess())
            {
                //购物车中是否包含此用户
                if (cartMap.containsKey(userId)){
                    goodsList= cartMap.get(userId);
                } else {
                    goodsList = new ArrayList<>();
                }

                goodsList.add((Goods) serviceResult.getData());
                cartMap.put(userId,goodsList);
                return RequestResult.success(goodsList);
            } else {
                return RequestResult.failure("获取商品失败");
            }
        } catch (Exception e) {
            return RequestResult.failure("添加商品失败");
        }
    }

    /**
     * 获取用户购物车
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RequestResult getGoods(HttpServletRequest request){
        Integer userId = null;
        try {
            userId = UserUtil.getCurrentUserId(request);
            return RequestResult.success(cartMap.get(userId));
        } catch (Exception e) {
            return RequestResult.failure("获取商品失败");
        }
    }

    /**
     * 从购物车中移除商品
     * @param goodsId 商品id
     */
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.DELETE)
    public RequestResult dropGoods(@PathVariable Integer goodsId,
                                   HttpServletRequest request){

        try {
            Integer userId = UserUtil.getCurrentUserId(request);

            List<Goods> goodsList;

            ServiceResult serviceResult = goodsService.selectById(goodsId);

            if (serviceResult.isSuccess())
            {
                //购物车中是否包含此用户
                if (cartMap.containsKey(userId)){
                    goodsList= cartMap.get(userId);
                    System.out.println(goodsList.lastIndexOf((Goods) serviceResult.getData()));
                    goodsList.remove(
                            goodsList.lastIndexOf((Goods) serviceResult.getData()));
                    cartMap.put(userId,goodsList);
                    return RequestResult.success(goodsList);
                } else {
                    return RequestResult.failure("购物车中无商品");
                }
            } else {
                return RequestResult.failure("获取商品失败");
            }
        } catch (Exception e) {
            return RequestResult.failure("删除商品失败");
        }
    }
}
