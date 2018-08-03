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

}
