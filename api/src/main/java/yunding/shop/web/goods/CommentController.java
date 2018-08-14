package yunding.shop.web.goods;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Comment;
import yunding.shop.entity.UserInfo;
import yunding.shop.service.CommentService;
import yunding.shop.service.UserService;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 根据商品Id获取商品所有评论
     * @param goodsId 商品id
     */
    @RequestMapping(value = "/{goodsId}" , method = RequestMethod.GET)
    public RequestResult getCommentByGoodsId(@PathVariable("goodsId") Integer goodsId ){
        try {
            ServiceResult serviceResult = commentService.getByGoodsId(goodsId);
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
     * 根据商品Id评论商品
     * @param comment 评论
     */
    @RequestMapping(value = "/" , method = RequestMethod.POST)
    public RequestResult commentByGoodsId(@RequestBody Comment comment ){
        try {
            ServiceResult serviceResult = commentService.publish(comment);
            if(serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("商品评论失败");
        }
    }
}
