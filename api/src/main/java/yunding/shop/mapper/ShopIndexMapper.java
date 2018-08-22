package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author guo
 */
public interface ShopIndexMapper {

    /**
     * 根据店铺Id和类型获取对应html
     * @param shopId 店铺Id
     * @param type 类型
     * @return html信息
     */
    String selectShopHtml(@Param("shopId") Integer shopId, @Param("type") Integer type);
}
