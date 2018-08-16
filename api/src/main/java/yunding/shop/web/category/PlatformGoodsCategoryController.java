package yunding.shop.web.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.PlatformGoodsCategoryService;

/**
 * 平台商品分类Controller
 * @author 齐语冰
 * @author huguobin
 */
@RestController
@RequestMapping("/api/platformCategory")
public class PlatformGoodsCategoryController {

    @Autowired
    private PlatformGoodsCategoryService platformGoodsCategoryService;

    /**
     * 获取平台分类列表
     * @return 平台分类列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RequestResult list(){
        try {
            ServiceResult serviceResult = platformGoodsCategoryService.getCategoryList();
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }
            else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("查询失败");
        }
    }

    /**
     * 通过平台分类id获取对应的所有商品
     * @param categoryId 平台分类id
     * @return 对应商品列表
     */
    @RequestMapping(value = "/allGoods/sales/{categoryId}",method = RequestMethod.GET)
    public RequestResult allGoodsSales(@PathVariable int categoryId){
        try{
            ServiceResult result = platformGoodsCategoryService.getAllGoods(categoryId);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.success(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("根据Id查询平台商品分类错误");
        }
    }
}
