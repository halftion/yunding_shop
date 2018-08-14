package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.ShopGoodsCategory;
import yunding.shop.mapper.ShopGoodsCategoryMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.ShopGoodsCategoryService;
import yunding.shop.service.ShopService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author huguobin
 * @author guo
 */
@Service
public class ShopGoodsCategoryServiceImpl implements ShopGoodsCategoryService {

    @Autowired
    private ShopGoodsCategoryMapper shopGoodsCategoryMapper;

    @Autowired
    private ShopService shopService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public ServiceResult getCategoryList(int shopId) {
        try{
            List<ShopGoodsCategory> shopGoodsCategories=
                    shopGoodsCategoryMapper.selectByShopId(shopId);
            return ServiceResult.success(shopGoodsCategories);
        }catch (Exception e){
            throw new RuntimeException("查询店铺分类异常");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ServiceResult getAllGoods(int shopId, int category) {
        try {
            ServiceResult serviceResult = goodsService.selectByShopCategoryId(shopId , category);
            if(!serviceResult.isSuccess()){
                //获取商品集合失败
                return ServiceResult.failure(serviceResult.getMessage());
            }
            List<Goods> goods=(List<Goods>) serviceResult.getData();
            goods.sort((o1, o2) -> o2.getSales() - o1.getSales());
            return ServiceResult.success(goods);
        }catch (Exception e){
            throw new RuntimeException("查询店铺分类商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult insertShopCategory(Integer userId, String name) {
        try{
            ServiceResult serviceResult =  shopService.selectShopIdByUserId(userId);
            Integer shopId = (Integer) serviceResult.getData();
            if(!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }
            ShopGoodsCategory shopGoodsCategory = new ShopGoodsCategory();
            shopGoodsCategory.setShopId(shopId);
            shopGoodsCategory.setName(name);
            shopGoodsCategory.createAtNow();
            shopGoodsCategory.updateAtNow();
            if (shopGoodsCategoryMapper.insertShopCategory(shopGoodsCategory) == 1){
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("添加分类异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加店铺分类异常");
        }
    }
}
