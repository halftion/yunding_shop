package yunding.shop.web.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.service.GoodsService;

import javax.servlet.http.HttpServletRequest;

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
    public RequestResult getCommentByGoodsId( @PathVariable("goodsId") Integer goodsId ){
        try {
            ServiceResult serviceResult = goodsService.getCommentByGoodsId(goodsId);
            if(serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取商品评论失败");
        }
    }
    /**
     * 店铺更改商品(无图)
     * @param goods
     * @return
     */

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public RequestResult updateGoods(@RequestBody Goods goods){
        try {

            ServiceResult result=goodsService.updategoods(goods);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure();
        }
    }

    /**
     * 更改图片
     */
    @RequestMapping(value = "/pic/{goodsId}",method = RequestMethod.PUT)
    public RequestResult updateGoods(@PathVariable Integer goodsId,@RequestParam("picture") MultipartFile pic, HttpServletRequest request){
        try {

            ServiceResult result=goodsService.updatePic(request,pic,goodsId);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure();
        }
    }
}
