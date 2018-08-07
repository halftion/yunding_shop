package yunding.shop.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Goods;
import yunding.shop.entity.Order;
import yunding.shop.mapper.GoodsMapper;
import yunding.shop.service.GoodsService;
import yunding.shop.service.OrderService;
import yunding.shop.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
    public ServiceResult processOrderCreate(Order order) {
        Integer goodsId = order.getGoodsId();
        Goods goods = (goodsMapper.selectByGoodsId(goodsId));
        order.setUnitPrice(goods.getPrice());
        order.setTotalPrice(goods.getPrice().multiply(BigDecimal.valueOf(order.getGoodsNum())));
        order.setShopId(goods.getShopId());
        order.setShopName(goods.getShopName());
        Integer i = goodsMapper.updateStockAndSales(goodsId,
                (goods.getStockNum() - order.getGoodsNum()),
                (goods.getSales() + order.getGoodsNum()));
        if(i ==1){
            return ServiceResult.success();
        }else {
            return ServiceResult.failure("修改商品信息失败");
        }
    }

    @Override
    public ServiceResult processOrderDelete(Order order) {
        Integer goodsId = order.getGoodsId();
        Goods goods = goodsMapper.selectByGoodsId(goodsId);
        Integer i = goodsMapper.updateStockAndSales(goodsId,
                (goods.getStockNum() + order.getGoodsNum()),
                (goods.getSales() - order.getGoodsNum()));
        if ( i == 1){
            return ServiceResult.success();
        }else {
            return ServiceResult.failure("修改商品信息失败");
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
    public ServiceResult selectNameByGoodsName(String keyword) {
        try {
            List<String> hintList = goodsMapper.selectNameByGoodsName(keyword);
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
            List<Goods> goods=goodsMapper.selectByPlatformCategoryId(categoryId);
            return ServiceResult.success(goods);
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
    public ServiceResult commentGoods(Integer goodsId) {
        try {
            goodsMapper.commentGoods(goodsId);
            return ServiceResult.success();
        }catch (Exception e){
            return ServiceResult.failure("商品评价数量修改失败");
        }
    }

    @Override
    public ServiceResult getCommentByGoodsId(Integer goodsId) {
        try {
            ServiceResult serviceResult = orderService.selectCommentByGoodsId(goodsId);
            if(serviceResult.isSuccess()){
                return ServiceResult.success(serviceResult.getData());
            }else {
                return ServiceResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return ServiceResult.failure("商品评价查询失败");
        }
    }

    @Override
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
    public ServiceResult updatePic(HttpServletRequest request, MultipartFile pic, int goodsId) {
        try {

            String realPath= FileUtil.getRealPath(request);
            String webPath=FileUtil.saveFile(pic,realPath);

            goodsMapper.updatePic(webPath,goodsId);
            return ServiceResult.success();
        }catch (Exception e){
            throw new RuntimeException("更新商品信息失败");
        }
    }
}
