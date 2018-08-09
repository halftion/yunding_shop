package yunding.shop.service.impl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.GoodsService;
import yunding.shop.service.SearchService;
import yunding.shop.service.ShopService;
import yunding.shop.util.Separator;
import yunding.shop.util.StringFilter;

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
                if(!shopService.selectByName(newKeyword).isSuccess()){
                    //获取店铺失败
                    return ServiceResult.failure(shopService.selectByName(newKeyword).getMessage());
                }
                if(!goodsService.selectByName(newKeyword).isSuccess()){
                    //获取商品失败
                    return ServiceResult.failure(goodsService.selectByName(newKeyword).getMessage());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("shop",shopService.selectByName(newKeyword).getData());
                jsonObject.put("goods",goodsService.selectByName(newKeyword).getData());
                return ServiceResult.success(jsonObject);
            }
        }catch (Exception e){
            return ServiceResult.failure("Service错误");
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
                if(goodsService.selectNameByGoodsName(newKeyword).isSuccess()){
                    return ServiceResult.success(goodsService.selectNameByGoodsName(newKeyword).getData());
                }else {
                    //获取商品名称失败
                    return ServiceResult.failure(goodsService.selectNameByGoodsName(newKeyword).getMessage());
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
