package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.mapper.ShopMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.SearchService;
import yunding.shop.service.ShopService;
import yunding.shop.utils.Separator;
import yunding.shop.utils.StringFilter;
import java.util.List;

import static yunding.shop.entity.Constant.HINT_SIZE;

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
                boolean b = true;
                if(!shopService.selectByName(newKeyword).isSuccess() ||
                        !goodsService.selectByName(newKeyword).isSuccess()){
                    b =false;
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("shop",shopService.selectByName(newKeyword).getData());
                jsonObject.put("goods",goodsService.selectByName(newKeyword).getData());
                if(b){
                    return ServiceResult.success(jsonObject);
                }else {
                    return ServiceResult.failure("商品和店铺获取失败");
                }
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
                List<String> hintList = goodsMapper.selectNameByGoodsName(newKeyword);
                if(hintList.size() > HINT_SIZE){
                    hintList = hintList.subList(0,5);
                }
                return ServiceResult.success(hintList);
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
                JSONArray goodsList = JSONArray.fromObject(
                        goodsMapper.selectByShopIdAndGoodsName(shopId,newKeyword));
                return ServiceResult.success(goodsList);
            }
        }catch (Exception e){
            return ServiceResult.failure("Service错误");
        }
    }


}
