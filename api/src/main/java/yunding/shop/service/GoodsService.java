package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * @author ren
 */
public interface GoodsService {
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return ServiceResult
     */
    ServiceResult getById(Integer id);
}
