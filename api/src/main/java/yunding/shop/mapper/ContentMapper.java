package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Content;

import java.util.List;

/**
 * 提供文章数据库方法
 * @author guo
 */
public interface ContentMapper {

    /**
     * 根据板块类型获取文章信息
     * @param typeId
     * @return 文章列表
     */
    List<Content> selectByTypeId (@Param("typeId") Integer typeId);
}
