package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * 店铺
 * @author guo
 */
public interface ShopService {

    /**
     * 根据商品关键词查询商品
     * @param keyword 关键词
     */
    ServiceResult selectByName (String keyword);
}
