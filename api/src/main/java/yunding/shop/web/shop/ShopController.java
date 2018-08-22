package yunding.shop.web.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.ShopService;

/**
 * @author guo
 */
@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 根据店铺Id和类型获取对应html
     * @param shopId 店铺ID
     * @param type 类型
     * @return html信息
     */
    @RequestMapping(value = "/index/{shopId}/{type}", method = RequestMethod.GET)
    RequestResult selectHtml(@PathVariable("shopId")Integer shopId,
                             @PathVariable("type")Integer type){
        try {
            ServiceResult sr =shopService.selectShopHtml(shopId, type);
            if(sr.isSuccess()){
                return RequestResult.success(sr.getData());
            }else{
                return RequestResult.failure(sr.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("查询HTML异常");
        }

    }
}
