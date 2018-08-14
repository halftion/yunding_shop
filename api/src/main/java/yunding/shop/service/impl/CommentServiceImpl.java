package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Comment;
import yunding.shop.entity.UserInfo;
import yunding.shop.mapper.CommentMapper;
import yunding.shop.service.CommentService;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.service.UserService;

import java.util.Date;
import java.util.List;


/**
 * @author 齐语冰
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserService userService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult publish(Comment comment) {
        try {
            Integer userId = comment.getUserId();
            String orderId = comment.getOrderId();
            Integer goodsId = comment.getGoodsId();

            ServiceResult serviceResult = orderService.updateCommentState(userId,orderId,goodsId);

            //检测此用户是否合法并更新订单商品评论状态
            if(!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }

            //完善评论信息
            serviceResult = userService.getById(userId);
            if (!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }
            UserInfo userInfo = (UserInfo) serviceResult.getData();

            comment.setNickName(userInfo.getNickName());
            comment.setAvatar(userInfo.getAvatar());
            comment.setCreatedAt(new Date());
            comment.setUpdatedAt(new Date());

            if(commentMapper.insert(comment) != 1){
                return ServiceResult.failure("新增评论失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("评价失败");
        }
    }

    @Override
    public ServiceResult getByGoodsId(Integer goodsId){
        try {
            List<Comment> commentList = commentMapper.selectByGoodsId(goodsId);
            return ServiceResult.success(commentList);
        } catch (Exception e) {
            return ServiceResult.failure("获取商品评论失败");
        }
    }
}
