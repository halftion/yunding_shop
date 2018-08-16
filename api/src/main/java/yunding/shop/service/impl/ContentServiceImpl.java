package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Content;
import yunding.shop.mapper.ContentMapper;
import yunding.shop.service.ContentService;

/**
 * 文章获取
 * @author guo
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public ServiceResult getContentFromType(Integer typeId) {
        try{
            JSONArray shopList = JSONArray.fromObject(contentMapper.selectByTypeId(typeId));
            return ServiceResult.success(shopList);
        }catch (Exception e){
            return ServiceResult.failure("获取文章列表失败");
        }
    }

    @Override
    public ServiceResult addContent(Content content) {
        return null;
    }

    @Override
    public ServiceResult deleteContent(Integer contentId) {
        return null;
    }
}
