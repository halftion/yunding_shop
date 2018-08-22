package yunding.shop.service;

import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.OrderGoods;

/**
 * @author ren
 * @author guo
 */
public interface GoodsService {

    /**
     * 根据商品id查询商品信息 + 不同属性商品的 goodsId+property
     * @param id 商品id
     * @return ServiceResult
     */
    ServiceResult selectById(Integer id);

    /**
     * 为order添加商品信息
     * @param orderGoods 订单商品
     */
    ServiceResult processOrderCreate(OrderGoods orderGoods);

    /**
     * 在删除订单时修改商品库存
     * @param orderGoods 订单商品
     */

    @Transactional(rollbackFor=Exception.class)
    ServiceResult processOrderDelete(OrderGoods orderGoods);

    /**
     * 根据商品关键词查询商品
     * @param keyword 关键词
     */
    ServiceResult selectByName (String keyword);

    /**
     * 根据商品关键词查询商品名称
     * @param keyword 关键字
     */
    ServiceResult selectNameByKeyword(String keyword);

    /**
     * 根据店铺id和商品名称查找对应商品
     * @param shopId 店铺id
     * @param keyword 商品名称
     */
    ServiceResult selectByShopIdAndGoodsName(Integer shopId, String keyword);

    /**
     * 根据平台分类id查询指定平台分类中商品
     * @param categoryId 指定平台分类的Id
     */
    ServiceResult selectByPlatformCategoryId(Integer categoryId);

    /**
     * 根据店铺id和店铺分类id查找店铺分类中的商品
     * @param shopId 店铺Id
     * @param category 店铺分类Id
     */
    ServiceResult selectByShopCategoryId(Integer shopId, Integer category);

    /**
     * 根据商品ID修改评论数量
     * @param goodsId 商品Id
     */
    ServiceResult changeCommentNum(Integer goodsId);

    /**
     * 更改商品信息
     * @param goods 商品
     */
    ServiceResult updategoods(Goods goods);

    /**
     * 根据商品Id查询商品店铺
     * @param goodsId 商品Id
     * @return 商品店铺
     */
    ServiceResult selectShopIdByGoodsId(Integer goodsId);

    /**
     * 保存商品图片
     * @param userId 用户id
     * @param goods goodsId + picture
     */
    ServiceResult saveGoodsPicture(Integer userId, Goods goods);

    /**
     * 新建商品
     * @param userId 用户Id
     * @param goods 商品详情
     */
    ServiceResult insertGoods(Integer userId , Goods goods);

    /**
     * 删除商品
     * @param userId 用户Id
     * @param goodsId 商品Id
     */
    ServiceResult deleteGoods(Integer userId , Integer goodsId);

    /**
     * 查询所有商品
     * @return 所有商品信息
     */
    ServiceResult selectAllGoods();

    ServiceResult selectAllGoods(Integer currentPage);

    /**
     * 根据商品Id上架或下架商品
     * @param goodsId 商品Id
     * @param identifier 标识符
     */
    ServiceResult updateGoodsState(Integer goodsId, Integer identifier);

}
