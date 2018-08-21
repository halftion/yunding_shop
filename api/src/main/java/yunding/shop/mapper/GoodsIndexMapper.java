package yunding.shop.mapper;

public interface GoodsIndexMapper {

    /**
     * 根据商品Id获取商品html
     * @param goodsId 商品Id
     * @return html信息
     */
    String selectGoodsHtml(Integer goodsId);

}
