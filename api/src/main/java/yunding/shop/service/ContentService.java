package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Content;

/**
 * @author guo
 */
public interface ContentService {
    /**
     * 根据板块类型获取文章信息
     * @param typeId
     * @return
     */
    ServiceResult getContentFromType (Integer typeId);

    /**
     * 添加文章
     * @param content 文章信息
     */
    ServiceResult addContent(Content content);

    /**
     * 根据文章Id移除或恢复文章
     * @param contentId 文章Id
     * @param identifier 标识符
     */
    ServiceResult updateContentState(Integer contentId, Integer identifier);

    /**
     * 获取所有文章
     * @return 文章详细信息
     */
    ServiceResult selectAll();

    /**
     * 根据店铺ID和板块类型获取店铺文章信息
     * @param shopId 店铺Id
     * @param type 店铺文章类型
     * @return 店铺文章列表
     */
    ServiceResult getShopContentFromType (Integer shopId, Integer type);

}
