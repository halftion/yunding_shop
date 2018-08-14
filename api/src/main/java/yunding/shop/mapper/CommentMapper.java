package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Comment;

import java.util.List;

/**
 * @author 齐语冰
 */
public interface CommentMapper {

    /**
     * 根据商品Id查询商品评价(state = 0)
     * @param goodsId 商品Id
     * @return 用户Id 用户头像 用户昵称 商品评价 创建时间
     */
    List<Comment> selectByGoodsId(Integer goodsId);

    /**
     * 通过订单id和商品id精确搜索评论(state = 0)
     * @param orderId 订单id
     * @param goodsId 商品id
     * @return 评论
     */
    Comment selectByOrderIdAndGoodsId(@Param("orderId") String orderId, @Param("goodsId") Integer goodsId);

    /**
     * 添加评论
     * @param comment 评论的全部信息
     * @return 影响条数
     */
    Integer insert(Comment comment);

    /**
     * 删除评论 ---> orderId goodsId updatedAt
     * @return 影响条数
     */
    Integer drop(Comment comment);
}
