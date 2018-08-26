package yunding.shop.mapper;


import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.ShopContent;

import java.util.List;

/**
 * @author guo
 */
public interface ShopContentMapper {
    
    /**
     * 根据店铺ID和板块类型获取店铺文章信息
     * @param shopId 店铺Id
     * @param type 店铺文章类型
     * @return 店铺文章列表
     */
    List<ShopContent> selectByTypeId (@Param("shopId")Integer shopId,@Param("type") Integer type);

}
