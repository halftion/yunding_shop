package yunding.shop.mapper;

import yunding.shop.entity.Goods;
import yunding.shop.entity.PlatformGoodsCategory;

import java.util.List;

/**
 * 提供平台商品分类数据库方法
 * @author 齐语冰
 */
public interface PlatformGoodsCategoryMapper {
    /**
     * 查找所有平台商品分类
     * @return 分类结果列表
     */
    List<PlatformGoodsCategory> selectAll();
}
