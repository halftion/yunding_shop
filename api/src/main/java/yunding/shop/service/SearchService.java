package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * @author guo
 */
public interface SearchService {

    /**
     * 根据关键词在平台内搜索商品和店铺
     * @param keyword 关键词
     * @return 匹配结果的商品和店铺
     */
    ServiceResult platformSearch(String keyword);

    /**
     * 根据关键词给出列表五个提示
     * @param keyword 关键词
     * @return 五个提示匹配结果的商品和店铺名
     */
    ServiceResult platformHint(String keyword);

    /**
     * 根据商品名称和店铺id查询商品
     * @param keyword 关键字
     * @param shopId 店铺名称
     * @return 查询到的店铺内的商品
     */
    ServiceResult shopSearch(String keyword, Integer shopId);
}
