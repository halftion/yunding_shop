package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.ShopGoodsCategory;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.mapper.ShopGoodsCategoryMapper;
import yunding.shop.service.ShopGoodsCategoryService;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author huguobin
 */
@Service
public class ShopGoodsCategoryServiceImpl implements ShopGoodsCategoryService {
    @Autowired
    private ShopGoodsCategoryMapper shopGoodsCategoryMapper;
    @Autowired
    private GoodsMapper goodsMapper;
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
    public ServiceResult getAllGoods(int shopId, int category) {
        try {
            List<Goods> goods=goodsMapper.selectByShopCategoryId(shopId,category);
            Collections.sort(goods, new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return o2.getSales()-o1.getSales();
                }
            });
            return ServiceResult.success(goods);
        }catch (Exception e){
            throw new RuntimeException("查询店铺分类商品失败");
        }
    }
}
