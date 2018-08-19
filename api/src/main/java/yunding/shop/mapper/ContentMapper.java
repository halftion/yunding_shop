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
     * @param type 文章类型
     * @return 文章列表
     */
    List<Content> selectByTypeId (@Param("type") Integer type);

    /**
     * 添加文章
     * @param content 文章详细信息
     */
    Integer insertContent(Content content);

    /**
     * 修改文章状态
     * @param content 文章Id 文章状态
     */
    Integer updateContentState(Content content);

    /**
     * 获取所有文章
     * @return 文章详细信息
     */
    List<Content> selectAll();
}
