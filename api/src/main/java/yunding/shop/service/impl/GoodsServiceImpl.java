package yunding.shop.service.impl;

import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Constant;
import yunding.shop.entity.Goods;
import yunding.shop.entity.OrderGoods;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.service.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import static yunding.shop.entity.Constant.HINT_SIZE;

/**
 * @author ren
 * @author guo
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ShopService shopService;

    @Autowired
    private PlatformGoodsCategoryService platformGoodsCategoryService;

    @Autowired
    private ShopGoodsCategoryService shopGoodsCategoryService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult saveGoodsPicture(Integer userId, Goods goods) {
        try {

            ServiceResult serviceResult = shopService.selectShopIdByUserId(userId);

            if (!serviceResult.isSuccess()) {
                //获取店铺Id失败
                return ServiceResult.failure(serviceResult.getMessage());
            }

            Integer shopId = (Integer) serviceResult.getData();

            serviceResult = selectShopIdByGoodsId(goods.getGoodsId());

            if (!serviceResult.isSuccess()){
                //获取店铺Id失败
                return ServiceResult.failure(serviceResult.getMessage());
            }

            Integer realShopId = (Integer) serviceResult.getData();

            if(!shopId.equals(realShopId)){
                return ServiceResult.failure("用户信息不匹配");
            }

            goods.setUpdatedAt(new Date());
            goodsMapper.saveGoodsPhoto(goods);

            return ServiceResult.success();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("作品上传失败");
        }
    }

    @Override
    public ServiceResult selectById(Integer goodsId) {
        try {
            Goods goods= goodsMapper.selectByGoodsId(goodsId);
            if (goods == null){
                return ServiceResult.failure("无此商品");
            }
            Integer shopId = goodsMapper.selectShopIdByGoodsId(goodsId);
            List<Goods> linkGoodsList = goodsMapper.selectGoodsProperty(goods.getName(), shopId);
            goods.setLinkGoodsList(linkGoodsList);
          return ServiceResult.success(goods);
        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult processOrderCreate(OrderGoods orderGoods) {
        try {
            Integer goodsId = orderGoods.getGoodsId();
            Goods goods = goodsMapper.selectByGoodsId(goodsId);
            /*完善订单信息*/
            orderGoods.setGoodsPic(goods.getPicture());
            orderGoods.setGoodsName(goods.getName());
            orderGoods.setUnitPrice(goods.getPrice());
            orderGoods.setTotalPrice(goods.getPrice().multiply(BigDecimal.valueOf(orderGoods.getGoodsNum())));

            goods.setStockNum(goods.getStockNum() - orderGoods.getGoodsNum());
            goods.setSales(goods.getSales() + orderGoods.getGoodsNum());
            goods.setUpdatedAt(new Date());
            Integer i = goodsMapper.updateStockAndSales(goods);

            if (i == 1) {
                return ServiceResult.success();
            } else {
                return ServiceResult.failure("修改商品库存和销量失败");
            }
        }catch (Exception e){
            throw new RuntimeException("处理订单商品信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult processOrderDelete(OrderGoods orderGoods) {
        try {
            Goods goods = goodsMapper.selectByGoodsId(orderGoods.getGoodsId());

            Integer stockNum = goods.getStockNum();
            Integer sales = goods.getSales();
            goods.setStockNum(stockNum + orderGoods.getGoodsNum());
            goods.setSales(sales - orderGoods.getGoodsNum());
            goods.setUpdatedAt(new Date());

            if (goodsMapper.updateStockAndSales(goods) != 1) {
                return ServiceResult.failure("库存销量回滚失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("库存销量回滚失败");
        }
    }

    @Override
    public ServiceResult selectByName(String keyword) {
        try {
            List<Goods> goodsList = goodsMapper.selectByNameSortBySales(keyword);
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    public ServiceResult selectNameByKeyword(String keyword) {
        try {
            List<String> hintList = goodsMapper.selectNameByKeywordSortBySales(keyword);
            if(hintList.size() > HINT_SIZE){
                hintList = hintList.subList(0,5);
            }
            return ServiceResult.success(hintList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品名称失败");
        }
    }

    @Override
    public ServiceResult selectByShopIdAndGoodsName(Integer shopId, String keyword) {
        try {
            JSONArray goodsList = JSONArray.fromObject(
                    goodsMapper.selectByShopIdAndGoodsNameSortBySales(shopId,keyword));
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    public ServiceResult selectByPlatformCategoryId(Integer categoryId) {
        try {
            List<Goods> goodsList=goodsMapper.selectByPlatformCategoryIdSortBySales(categoryId);
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品集合失败");
        }
    }

    @Override
    public ServiceResult selectByShopCategoryId(Integer shopId, Integer category) {
        try {
            List<Goods> goods=goodsMapper.selectByShopCategoryIdSortBySales(shopId,category);
            return ServiceResult.success(goods);
        }catch (Exception e){
            return ServiceResult.failure("获取商品集合失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult changeCommentNum(Integer goodsId) {
        try {
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setUpdatedAt(new Date());
            goodsMapper.changeCommentNum(goods);
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("商品评价数量修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updategoods(Goods goods) {
        try {
            goods.setUpdatedAt(new Date());
            goodsMapper.updateGoodsInfo(goods);
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("更新商品信息失败");
        }
    }

    @Override
    public ServiceResult selectShopIdByGoodsId(Integer goodsId) {
        try {
            Integer shopId = goodsMapper.selectShopIdByGoodsId(goodsId);
            return ServiceResult.success(shopId);
        } catch (Exception e) {
            return ServiceResult.failure("获取店铺ID失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult insertGoods(Integer userId, Goods goods) {
        try {
            ServiceResult sr1 = shopService.selectShopIdByUserId(userId);
            if (!sr1.isSuccess()) {
                //获取店铺Id失败
                return ServiceResult.failure(sr1.getMessage());
            }
            Integer shopId = (Integer) sr1.getData();

            ServiceResult sr2 = shopService.selectShopNameByShopId(shopId);
            if (!sr2.isSuccess()) {
                //获取店铺名称失败
                return ServiceResult.failure(sr2.getMessage());
            }
            ServiceResult sr3 = platformGoodsCategoryService.updateGoodsNum(goods.getPlatformGoodsCategoryId(), Constant.UPDATE_ADD);
            if (!sr3.isSuccess()) {
                //更新平台分类中的商品数量失败
                return ServiceResult.failure(sr3.getMessage());
            }
            ServiceResult sr4 = shopGoodsCategoryService.updateGoodsNum(goods.getShopGoodsCategoryId(),Constant.UPDATE_ADD);
            if (!sr4.isSuccess()) {
                //更新平台分类中的商品数量失败
                return ServiceResult.failure(sr4.getMessage());
            }

            String shopName = (String) sr2.getData();
            goods.setShopId(shopId);
            goods.setShopName(shopName);
            goods.setCreatedAt(new Date());
            goods.setUpdatedAt(new Date());
            if (goodsMapper.insertGoods(goods) != 1) {
                return ServiceResult.failure("保存失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("添加商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult deleteGoods(Integer userId, Integer goodsId) {
        try {
            ServiceResult sr1 = shopService.selectShopIdByUserId(userId);
            if (!sr1.isSuccess()) {
                //获取店铺Id失败
                return ServiceResult.failure(sr1.getMessage());
            }
            Integer shopId1 = (Integer) sr1.getData();
            Integer shopId2 = goodsMapper.selectShopIdByGoodsId(goodsId);
            if (!shopId1.equals(shopId2)) {
                return ServiceResult.failure("用户信息不匹配");
            }

            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setState(-1);
            goods.setUpdatedAt(new Date());
            if (goodsMapper.updateGoodsState(goods) != 1) {
                return ServiceResult.failure("删除商品失败");
            }

            ServiceResult sr2 = platformGoodsCategoryService.updateGoodsNum(goods.getPlatformGoodsCategoryId(),Constant.UPDATE_DEL);
            if (!sr2.isSuccess()) {
                //更新平台分类中的商品数量失败
                return ServiceResult.failure(sr2.getMessage());
            }
            ServiceResult sr3 = shopGoodsCategoryService.updateGoodsNum(goods.getShopGoodsCategoryId(),Constant.UPDATE_DEL);
            if (!sr3.isSuccess()) {
                //更新平台分类中的商品数量失败
                return ServiceResult.failure(sr3.getMessage());
            }

            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("发货失败");
        }
    }

    @Override
    public ServiceResult selectAllGoods() {
        try{
            List<Goods> goodsList = goodsMapper.selectAllGoods();
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("查找所有用户异常");
        }
    }

    @Override
    public ServiceResult selectAllGoods(Integer currentPage){
        try {
            int pageSize = 10;
            PageHelper.startPage(currentPage, pageSize);
            List<Goods> goodsList = goodsMapper.selectAllGoods();
            return ServiceResult.success(goodsList);
        } catch (Exception e) {
            return ServiceResult.failure("分页查找全部商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult updateGoodsState(Integer goodsId, Integer identifier) {
        try{
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setUpdatedAt(new Date());
            if(identifier.equals(Constant.UPDATE_ADD)){
                goods.setState(0);
            }else if(identifier.equals(Constant.UPDATE_DEL)){
                goods.setState(-1);
            }else {
                return ServiceResult.failure("标识符错误");
            }
            goodsMapper.updateGoodsState(goods);
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("修改商品状态失败");
        }
    }
}
