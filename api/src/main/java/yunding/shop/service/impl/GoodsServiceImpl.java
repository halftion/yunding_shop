package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.OrderGoods;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.service.ShopService;
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
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @Override
    public ServiceResult selectById(Integer id) {
        try {
          Goods goods= goodsMapper.selectByGoodsId(id);
          return ServiceResult.success(goods);
        }catch (Exception e){
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
            goods.updateAtNow();

            if (goodsMapper.updateStockAndSales(goods) != 1) {
                return ServiceResult.failure("修改商品信息失败");
            }

            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("库存销量修改失败");
        }
    }

    @Override
    public ServiceResult selectByName(String keyword) {
        try {
            JSONArray goodsList = JSONArray.fromObject(goodsMapper.selectByName(keyword));
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    public ServiceResult selectNameByKeyword(String keyword) {
        try {
            List<String> hintList = goodsMapper.selectNameByKeyword(keyword);
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
                    goodsMapper.selectByShopIdAndGoodsName(shopId,keyword));
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    public ServiceResult selectByPlatformCategoryId(Integer categoryId) {
        try {
            List<Goods> goodsList=goodsMapper.selectByPlatformCategoryId(categoryId);
            return ServiceResult.success(goodsList);
        }catch (Exception e){
            return ServiceResult.failure("获取商品集合失败");
        }
    }

    @Override
    public ServiceResult selectByShopCategoryId(Integer shopId, Integer category) {
        try {
            List<Goods> goods=goodsMapper.selectByShopCategoryId(shopId,category);
            return ServiceResult.success(goods);
        }catch (Exception e){
            return ServiceResult.failure("获取商品集合失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult commentGoods(Integer goodsId) {
        try {
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.updateAtNow();
            goodsMapper.commentGoods(goods);
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("商品评价数量修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updategoods(Goods goods) {
        try {
            goods.updateAtNow();
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
    public ServiceResult saveGoodsPhoto(Integer goodsId, String picture) {
        try {
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setPicture(picture);
            goods.updateAtNow();
            if (goodsMapper.saveGoodsPhoto(goods) == 1) {
                return ServiceResult.success();
            } else {
                return ServiceResult.failure("保存图片失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("获取店铺ID失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult insertGoods(Integer userId, Goods goods) {
        try {
            ServiceResult sr1 = shopService.selectShopIdByUserId(userId);
            if (!sr1.isSuccess()) {
                return ServiceResult.failure(sr1.getMessage());
            }
            Integer shopId = (Integer) sr1.getData();
            ServiceResult sr2 = shopService.selectShopNameByShopId(shopId);
            if (!sr2.isSuccess()) {
                return ServiceResult.failure(sr2.getMessage());
            }
            String shopName = (String) sr2.getData();
            goods.setShopId(shopId);
            goods.setShopName(shopName);
            goods.createAtNow();
            goods.updateAtNow();
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
            goods.updateAtNow();
            if (goodsMapper.deleteGoods(goods) != 1) {
                return ServiceResult.failure("删除商品失败");
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("发货失败");
        }
    }

    @Override
    public ServiceResult selectGoodsProperty(Integer goodsId) {
        try{
            Integer shopId = goodsMapper.selectShopIdByGoodsId(goodsId);
            String name = goodsMapper.selectByGoodsId(goodsId).getName();
            List<Goods> propertyList = goodsMapper.selectGoodsProperty(name, shopId);
            return ServiceResult.success(propertyList);
        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("查找异常");
        }
    }
}
