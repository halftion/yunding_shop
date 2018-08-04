package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.Order;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.service.GoodsService;

import java.math.BigDecimal;

/**
 * @author ren
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public ServiceResult getById(Integer id) {
        try {
          Goods goods= goodsMapper.selectByGoodsId(id);
          return ServiceResult.success(goods);
        }catch (Exception e){
            return ServiceResult.failure("获取商品失败");
        }
    }

    @Override
    public ServiceResult processOrder(Order order) {
        Integer goodsId = order.getGoodsId();
        Goods goods = (goodsMapper.selectByGoodsId(goodsId));
        order.setUnitPrice(goods.getPrice());
        order.setTotalPrice(goods.getPrice().multiply(BigDecimal.valueOf(order.getGoodsNum())));
        order.setShopId(goods.getShopId());
        order.setShopName(goods.getShopName());

        Integer j = goodsMapper.updateStockAndSales(goodsId,
                (goods.getStockNum() - order.getGoodsNum()),
                (goods.getState() + order.getGoodsNum()));
        if(j ==1){
            return ServiceResult.success();
        }else {
            return ServiceResult.failure("添加商品信息失败");
        }
    }
}
