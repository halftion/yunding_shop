package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Goods;
import yunding.shop.entity.ShopGoodsCategory;

import java.util.List;

/**
 * @author huguobin
 */
public interface ShopGoodsCategoryMapper {
    /**
     * 查询店铺商品分类
     * @param shopId 店铺id
     * @return 店铺商品分类集合
     */
    List<ShopGoodsCategory> selectByShopId(int shopId);

    /**
     * 添加店铺商品分类
     * @param shopGoodsCategory ShopGoodsCategory类
     */
    Integer insertShopCategory(ShopGoodsCategory shopGoodsCategory);


    /**
     * 在店铺分类中修改对应的商品数量
     * @param shopGoodsCategory 待修改分类 更新时间
     */
    Integer updateGoodsNum(ShopGoodsCategory shopGoodsCategory);

    /**
     * 在店铺分类中减少对应的商品数量
     * @param shopGoodsCategory 待修改分类 更新时间
     */
    Integer updateAndDeleteGoodsNum(ShopGoodsCategory shopGoodsCategory);
}
