package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Content;

import java.util.List;

/**
 * @author guo
 * 提供文章数据库方法
 */
public interface ContentMapper {

    /**
     * 根据板块类型获取文章信息
     * @param typeId
     * @return
     */
    List<Content> selectByTypeId (@Param("typeId") Integer typeId);
}
