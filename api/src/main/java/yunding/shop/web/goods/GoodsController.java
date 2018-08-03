package yunding.shop.web.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.GoodsService;

/**
 * 根据商品id查询商品
 * @author ren
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public RequestResult list(@PathVariable Integer id){
        try {
            ServiceResult serviceResult=goodsService.getById(id);
            if(serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取商品失败");
        }
    }
}
