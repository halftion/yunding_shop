package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * 提供平台商品分类Service方法
 * @author 齐语冰
 * @author huguobin
 */
public interface PlatformGoodsCategoryService {
    /**
     * 查找所有平台商品分类
     * @return ServiceResult
     */
    ServiceResult getCategoryList();

    /**
     * 查找该平台分类下所有商品
     * @param categoryId 平台分类id
     * @return 商品列表
     */
    ServiceResult getAllGoods(int categoryId);

    /**
     * 在平台分类修改商品个数
     * @param platformGoodsCategoryId 平台商品类别
     * @param identifier 识别添加或删除
     */
    ServiceResult updateGoodsNum(int platformGoodsCategoryId, int identifier);
}
