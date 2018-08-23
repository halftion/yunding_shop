package yunding.shop.service.impl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.GoodsService;
import yunding.shop.service.SearchService;
import yunding.shop.service.ShopService;
import yunding.shop.util.Separator;
import yunding.shop.util.StringFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guo
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ShopService shopService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public ServiceResult platformSearch(String keyword) {
        try{
            String keyword1 = StringFilter.searchKeyword(keyword);
            if( keyword1 == null || "".equals(keyword1)){
                return ServiceResult.success(null);
            }
            else{
                String newKeyword = Separator.percent(keyword1);
                ServiceResult serviceResult = shopService.selectByName(newKeyword);

                if(!serviceResult.isSuccess()){
                    //获取店铺失败
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                Map<String,Object> resultMap = new HashMap();
                resultMap.put("shop",serviceResult.getData());

                serviceResult = goodsService.selectByName(newKeyword);

                if(!serviceResult.isSuccess()){
                    //获取商品失败
                    return ServiceResult.failure(serviceResult.getMessage());
                }

                resultMap.put("goods",serviceResult.getData());

                return ServiceResult.success(resultMap);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("搜索失败");
        }
    }

    @Override
    public ServiceResult platformHint(String keyword) {
        try{
            String keyword1 = StringFilter.searchKeyword(keyword);
            if( keyword1 == null || "".equals(keyword1)){
                return ServiceResult.success(null);
            }
            else{
                String newKeyword = Separator.percent(keyword1);
                if(goodsService.selectNameByKeyword(newKeyword).isSuccess()){
                    return ServiceResult.success(goodsService.selectNameByKeyword(newKeyword).getData());
                }else {
                    //获取商品名称失败
                    return ServiceResult.failure(goodsService.selectNameByKeyword(newKeyword).getMessage());
                }
            }
        }catch (Exception e){
            return ServiceResult.failure("提示错误");
        }
    }

    @Override
    public ServiceResult shopSearch(String keyword, Integer shopId) {
        try{
            String keyword1 = StringFilter.searchKeyword(keyword);
            if( keyword1 == null || "".equals(keyword1)){
                return ServiceResult.success(null);
            }
            else{
                String newKeyword = Separator.percent(keyword1);
                if(goodsService.selectByShopIdAndGoodsName(shopId,newKeyword).isSuccess()){
                    return ServiceResult.success(goodsService.selectByShopIdAndGoodsName(shopId,newKeyword).getData());
                }else{
                    //获取商品失败
                    return ServiceResult.failure(goodsService.selectByShopIdAndGoodsName(shopId,newKeyword).getMessage());
                }
            }
        }catch (Exception e){
            return ServiceResult.failure("Service错误");
        }
    }
}
