package yunding.shop.web.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.ShopGoodsCategory;
import yunding.shop.service.ShopGoodsCategoryService;
import yunding.shop.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huguobin
 */
@RestController
@RequestMapping("/api/shopCategory")
public class ShopGoodsCategoryController {

    @Autowired
    private ShopGoodsCategoryService shopGoodsCategoryService;

    /**
     * 通过店铺id获取店铺分类列表
     * @param shopId 店铺id
     * @return 店铺分类列表
     */
    @RequestMapping(value = "/list/{shopId}",method = RequestMethod.GET)
    public RequestResult list(@PathVariable int shopId){
        try{
            ServiceResult result = shopGoodsCategoryService.getCategoryList(shopId);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("查询店铺分类错误");
        }
    }

    /**
     * 通过店铺id和店铺分类id获取对应全部商品
     * @param shopId 店铺id
     * @param categoryId 店铺分类id
     * @return 对应商品列表
     */
    @RequestMapping(value = "/allGoods/sales/{shopId}/{categoryId}",method = RequestMethod.GET)
    public RequestResult allGoodsBySales(@PathVariable int shopId,@PathVariable int categoryId){
        try{
            ServiceResult result=shopGoodsCategoryService.getAllGoods(shopId,categoryId);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("查询失败");
        }
    }

    /**
     * 添加商品分类
     * @param shopGoodsCategory shopGoodsCategory对象
     * @param request request对象
     */
    @RequestMapping(value = "" , method = RequestMethod.POST)
    RequestResult insertShopCategory(@RequestBody ShopGoodsCategory shopGoodsCategory, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult result=shopGoodsCategoryService.insertShopCategory(userId, shopGoodsCategory);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("查询失败");
        }
    }
}
