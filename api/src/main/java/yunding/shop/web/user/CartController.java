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
import yunding.shop.service.CartService;
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
    CartService cartService;

    /**
     * 获取用户购物车
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RequestResult getGoods(HttpServletRequest request){
        Integer userId = UserUtil.getCurrentUserId(request);
        try {
            ServiceResult serviceResult = cartService.getGoods(userId,cartMap);

            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("获取商品失败");
        }
    }

    /**
     * 在购物车中添加商品
     * @param goodsId 商品id
     */
    @RequestMapping(value = "/{goodsId}", method = RequestMethod.PUT)
    public RequestResult addGoods(@PathVariable Integer goodsId,
                                  HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = cartService.addGoods(userId,goodsId,cartMap);

            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("添加商品失败");
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
            ServiceResult serviceResult = cartService.dropGoods(userId,goodsId,cartMap);

            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            } else{
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("移除商品失败");
        }
    }
}
