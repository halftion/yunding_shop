package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Comment;

/**
 * @author 齐语冰
 */
public interface CommentService {

    ServiceResult publish(Comment comment);

    ServiceResult getByGoodsId(Integer goodsId);
}
