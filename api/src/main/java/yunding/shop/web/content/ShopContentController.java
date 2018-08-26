package yunding.shop.web.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.ContentService;

/**
 * @author guo
 */
@RestController
@RequestMapping("/api/shopContent")
public class ShopContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据店铺ID和板块ID获取文章信息
     * @param typeId 板块ID
     * @return 文章信息
     */
    @RequestMapping(value = "/{shopId}/{typeId}" , method = RequestMethod.GET)
    public RequestResult getContentByTypeId(@PathVariable("shopId")Integer shopId, @PathVariable("typeId") Integer typeId){
        try{
            ServiceResult serviceResult = contentService.getShopContentFromType(shopId, typeId);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("Controller失败");
        }
    }
}
