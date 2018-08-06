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
 * @author guo
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品Id查询商品
     * @param id 商品Id
     * @return 商品信息
     */
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public RequestResult list(@PathVariable Integer id){
        try {
            ServiceResult serviceResult=goodsService.selectById(id);
            if(serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取商品失败");
        }
    }

    /**
     * 根据商品Id获取商品所有评论
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/comment/{goodsId}" , method = RequestMethod.GET)
    public RequestResult getCommentByGoodsId( Integer goodsId ){
        try {
            ServiceResult serviceResult=goodsService.getCommentByGoodsId(goodsId);
            if(serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取商品评论失败");
        }
    }
}
