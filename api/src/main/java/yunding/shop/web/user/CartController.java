package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.service.CartService;
import yunding.shop.util.UserUtil;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

/*    @Autowired
    Map<Integer, List<Goods>> cartMap;*/

    @Autowired
    CartService cartService;

    /**
     * 获取用户购物车
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RequestResult getGoods(HttpServletRequest request){
        try {

            Integer userId = UserUtil.getCurrentUserId(request);

            ServiceResult serviceResult = cartService.getGoodsList(userId);

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
     * @param goods goodsId goodsName picture price
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RequestResult addGoods(@RequestBody Goods goods,
                                  HttpServletRequest request){
        try {
            Integer userId = UserUtil.getCurrentUserId(request);

            ServiceResult serviceResult = cartService.addGoods(userId,goods);

            if (serviceResult.isSuccess()){
                return RequestResult.success();
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
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

            ServiceResult serviceResult = cartService.dropGoods(userId,goodsId);

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
