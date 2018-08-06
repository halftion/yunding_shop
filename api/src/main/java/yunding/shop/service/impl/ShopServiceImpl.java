package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.mapper.ShopMapper;
import yunding.shop.service.ShopService;

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


}
