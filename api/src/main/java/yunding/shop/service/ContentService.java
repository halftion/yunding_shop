package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

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
     * 根据文章Id移除文章
     * @param contentId 文章Id
     * @return
     */
    ServiceResult deleteContent(Integer contentId);

}
