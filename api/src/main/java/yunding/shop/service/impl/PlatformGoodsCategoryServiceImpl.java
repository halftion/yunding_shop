package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.mapper.PlatformGoodsCategoryMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.PlatformGoodsCategoryService;
import java.util.Comparator;
import java.util.List;

/**
 * 提供平台商品分类Service方法
 * @author 齐语冰
 */
@Service
public class PlatformGoodsCategoryServiceImpl implements PlatformGoodsCategoryService {

    @Autowired
    private PlatformGoodsCategoryMapper platformGoodsCategoryMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public ServiceResult getCategoryList() {
        try {
            return ServiceResult.success(platformGoodsCategoryMapper.selectAll());
        } catch (Exception e) {
            return ServiceResult.failure("查询失败");
        }
    }

    @Override
    public ServiceResult getAllGoods(int categoryId) {
        try{
            if(!goodsService.selectByPlatformCategoryId(categoryId).isSuccess()){
                //获取商品集合失败
                return ServiceResult.failure(goodsService.selectByPlatformCategoryId(categoryId).getMessage());
            }
            List<Goods> goods=(List<Goods>)
                    goodsService.selectByPlatformCategoryId(categoryId).getData();
            goods.sort(new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return o2.getSales() - o1.getSales();
                }
            });
            return ServiceResult.success(goods);
        }catch (Exception e){
            throw new RuntimeException("根据Id查询平台商品分类异常");
        }
    }
}
