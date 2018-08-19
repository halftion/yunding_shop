package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Content;
import yunding.shop.mapper.ContentMapper;
import yunding.shop.service.ContentService;

import java.util.List;

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
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult addContent(Content content) {
        try {
            if(contentMapper.insertContent(content) != 1){
                return ServiceResult.failure("添加文章失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("添加文章异常");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateContentState(Integer contentId, Integer identifier) {
        try {
            Content content = new Content();
            content.setContentId(contentId);
            if(identifier.equals(Constant.UPDATE_ADD)){
                content.setState(0);
            }else if(identifier.equals(Constant.UPDATE_DEL)) {
                content.setState(-1);
            }else {
                return ServiceResult.failure("更新文章状态失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("更新文章状态异常");
        }
    }

    @Override
    public ServiceResult selectAll() {
        try {
            List<Content> contentList = contentMapper.selectAll();
            return ServiceResult.success(contentList);
        } catch (Exception e) {
            return ServiceResult.failure("获取文章异常");
        }
    }
}
