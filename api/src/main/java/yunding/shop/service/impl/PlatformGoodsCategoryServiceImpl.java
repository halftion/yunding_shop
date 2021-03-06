package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Goods;
import yunding.shop.entity.PlatformGoodsCategory;
import yunding.shop.mapper.PlatformGoodsCategoryMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.PlatformGoodsCategoryService;
import java.util.Comparator;
import java.util.Date;
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
    @SuppressWarnings("unchecked")
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

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateGoodsNum( int platformGoodsCategoryId, int identifier) {
        try{
            PlatformGoodsCategory platformGoodsCategory = new PlatformGoodsCategory();
            platformGoodsCategory.setPlatformGoodsCategoryId(platformGoodsCategoryId);
            platformGoodsCategory.setUpdatedAt(new Date());
            //添加
            if(identifier == Constant.UPDATE_ADD && platformGoodsCategoryMapper.updateGoodsNum(platformGoodsCategory) != 1){
                return ServiceResult.failure("在平台分类添加商品个数失败");
            }
            //删除
            if(identifier == Constant.UPDATE_DEL && platformGoodsCategoryMapper.updateAndDeleteGoodsNum(platformGoodsCategory) != 1){
                return ServiceResult.failure("在平台分类减少商品个数失败");
            }
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("在平台分类修改商品个数失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult addCategory(String name) {
        try {
            PlatformGoodsCategory platformGoodsCategory = new PlatformGoodsCategory();
            platformGoodsCategory.setName(name);
            platformGoodsCategory.setCreatedAt(new Date());
            platformGoodsCategory.setUpdatedAt(new Date());
            if(platformGoodsCategoryMapper.insertPlatformCategory(platformGoodsCategory) != 1){
                return ServiceResult.failure("添加失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("添加分类异常");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deleteCategory(Integer categoryId) {
        try {
            if(platformGoodsCategoryMapper.updatePlatformCategory(categoryId) != 1){
                return ServiceResult.failure("更新分类失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("更新分类异常");
        }
    }
}
