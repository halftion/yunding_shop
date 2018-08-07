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
}
