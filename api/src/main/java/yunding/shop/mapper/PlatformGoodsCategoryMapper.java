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

    /**
     * 在平台分类中修改对应的商品数量
     * @param platformGoodsCategory 待修改分类 更新时间
     */
    Integer updateGoodsNum(PlatformGoodsCategory platformGoodsCategory);

    /**
     * 在平台分类中减少对应的商品数量
     * @param platformGoodsCategory 待修改分类 更新时间
     */
    Integer updateAndDeleteGoodsNum(PlatformGoodsCategory platformGoodsCategory);

    /**
     * 添加平台分类
     * @param platformGoodsCategory 平台分类名称 创建时间 更新时间
     */
    Integer insertPlatformCategory(PlatformGoodsCategory platformGoodsCategory);

    /**
     * 移除平台分类
     * @param categoryId 平台分类Id
     * @return
     */
    Integer updatePlatformCategory(Integer categoryId);
}
