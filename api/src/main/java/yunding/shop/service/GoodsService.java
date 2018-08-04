package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Order;

/**
 * @author ren
 * @author guo
 */
public interface GoodsService {
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return ServiceResult
     */
    ServiceResult getById(Integer id);

    /**
     * 为order添加商品信息
     * @param order 订单
     */
    ServiceResult processOrderCreate(Order order);

    /**
     * 在删除订单时修改商品库存
     * @param order 订单
     */
    ServiceResult processOrderDelete(Order order);
}
