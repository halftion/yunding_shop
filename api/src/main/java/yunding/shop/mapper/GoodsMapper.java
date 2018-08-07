package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * @author 齐语冰
 */
public interface GoodsMapper {
    /**
     * 根据商品名称查询商品
     * @param name 商品名称
     * @return 商品结果列表
     */
    List<Goods> selectByName(String name);

    /**
     * 根据平台分类id查询指定平台分类中商品
     * @param categoryId 指定平台分类的Id
     * @return 指定平台分类中商品集合
     */
    List<Goods> selectByPlatformCategoryId(int categoryId);

    /**
     * 根据店铺id和店铺分类id查找店铺分类中的商品
     * @param shopId 店铺id
     * @param categoryId 分类id
     * @return 店铺分类中的商品集合
     */
    List<Goods> selectByShopCategoryId(@Param("shopId") int shopId, @Param("categoryId") int categoryId);

    /**
     * 根据商品id获取对应商品
     * @param id 商品id
     * @return 对应的商品
     */
    Goods selectByGoodsId(Integer id);

    /**
     * 根据商品关键词查询商品名称
     * @param goodsName 商品关键字
     * @return 商品名称列表
     */
    List<String> selectNameByGoodsName(String goodsName);

    /**
     * 根据店铺id和商品名称查找对应商品
     * @param shopId 店铺id
     * @param goodsName 商品名称
     * @return 对应的商品列表
     */
    List<Goods> selectByShopIdAndGoodsName(@Param("shopId") Integer shopId, @Param("goodsName") String goodsName);

    /**
     * 创建订单时更改商品的库存和销量
     * @param goods 商品更改后库存、商品更改后销量
     */
    Integer updateStockAndSales ( Goods goods);

    /**
     * 根据用户ID添加商品评论
     * @param goods 商品类
     */
    Integer commentGoods(Goods goods);



    /**
     * 更改商品信息
     * @param goods 商品详情
     */
    void updateGoodsInfo(Goods goods);

    /**
     * 根据商品Id查询店铺Id
     * @param goodsId 商品Id
     * @return 店铺Id
     */
    Integer selectShopIdByGoodsId (Integer goodsId);

    /**
     * 保存商品图片
     * @param goods 商品图片
     */
    Integer saveGoodsPhoto(Goods goods);

    /**
     * 添加商品
     * @param goods 商品详情
     */
    Integer insertGoods (Goods goods);

    /**
     * 删除商品
     * @param goods 商品Id
     */
    Integer deleteGoods(Goods goods);


}
