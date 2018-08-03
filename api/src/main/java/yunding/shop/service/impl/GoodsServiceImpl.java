package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.service.GoodsService;

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
}
