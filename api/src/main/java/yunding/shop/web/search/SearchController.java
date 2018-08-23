package yunding.shop.web.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.SearchService;

/**
 * 关键字查询
 * @author guo
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 用关键字查询 店铺和商品的方法
     * @param keyword 搜索文本
     * @return 搜索结果
     */
    @RequestMapping(value = "/platform/{keyword}" ,method = RequestMethod.GET)
    public RequestResult platformSearch(@PathVariable("keyword") String keyword){
        try {
            ServiceResult serviceResult = searchService.platformSearch(keyword);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("搜索失败");
        }
    }

    /**
     * 根据商品名称和店铺id查询商品
     * @param keyword 商品名称
     * @param shopId 店铺ID
     * @return 对应商品
     */
    @RequestMapping(value = "/shop/{shopId}/{keyword}" , method = RequestMethod.GET)
    public RequestResult shopSearch(@PathVariable("keyword") String keyword,
                                    @PathVariable("shopId") Integer shopId){
        try {
            ServiceResult serviceResult = searchService.shopSearch(keyword,shopId);
            if(serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else{
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("搜索失败");
        }
    }
}
