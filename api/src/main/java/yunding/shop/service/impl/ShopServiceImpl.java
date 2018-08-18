package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.OrderGoods;
import yunding.shop.entity.OrderInfo;
import yunding.shop.entity.Shop;
import yunding.shop.mapper.ShopMapper;
import yunding.shop.service.ShopService;

import java.util.List;

/**
 * 店铺
 * @author guo
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public ServiceResult selectByName(String keyword) {
        try {
            JSONArray shopList = JSONArray.fromObject(shopMapper.selectByName(keyword));
            return ServiceResult.success(shopList);
        }catch (Exception e){
            return ServiceResult.failure("获取店铺失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult selectUserIdByShopId(Integer shopId) {
        try {
            Integer userId = shopMapper.selectUserIdByShopId(shopId);
            return ServiceResult.success(userId);
        }catch (Exception e){
            throw new RuntimeException("获取商户ID失败");
        }
    }

    @Override
    public ServiceResult selectShopIdByUserId(Integer userId) {
        try {
            Integer shopId = shopMapper.selectShopIdByUserId(userId);
            return ServiceResult.success(shopId);
        }catch (Exception e){
            throw new RuntimeException("获取店铺ID失败");
        }
    }

    @Override
    public ServiceResult selectShopNameByShopId(Integer shopId) {
        try {
            String shopName = shopMapper.selectShopNameByShopId(shopId);
            return ServiceResult.success(shopName);
        }catch (Exception e){
            throw new RuntimeException("获取店铺名称失败");
        }
    }

    @Override
    public ServiceResult processOrderCreate(OrderInfo orderInfo, List<OrderGoods> orderGoodsList){

        try {
            Integer shopId = orderInfo.getShopId();
            Integer goodsNum = 0;
            for (OrderGoods orderGoods : orderGoodsList){
                goodsNum += orderGoods.getGoodsNum();
            }
            ServiceResult serviceResult = updateSales(shopId,goodsNum);

            if (!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }

            Shop shop = shopMapper.selectById(shopId);
            orderInfo.setShopName(shop.getName());
            return ServiceResult.success();
        } catch (Exception e) {
            return ServiceResult.failure("处理订单店铺信息失败");
        }
    }

    @Override
    public ServiceResult selectAllShop() {
        try {
            List<Shop> shopList = shopMapper.selectAllShop();
            return ServiceResult.success(shopList);
        }catch (Exception e){
            throw new RuntimeException("获取所有店铺失败");
        }
    }

    /**
     * 根据店铺Id修改店铺销量
     * @param shopId 店铺Id
     * @param goodsNum 商品总销量
     */
    private ServiceResult updateSales(Integer shopId, Integer goodsNum) {
        try{
            Shop shop = shopMapper.selectById(shopId);
            Integer sales = shop.getSales();
            shop.setShopId(shopId);
            shop.setSales(sales + goodsNum);
            shop.updateAtNow();
            if(shopMapper.updateSales(shop) != 1){
                return ServiceResult.failure("数据库更改销量失败");
            }
            return ServiceResult.success();
        }catch (Exception e){
            throw  new RuntimeException("店铺销量更改失败");
        }
    }
}
