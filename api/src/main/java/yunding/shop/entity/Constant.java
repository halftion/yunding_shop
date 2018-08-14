package yunding.shop.entity;

/**
 * 常量类
 * @author 齐语冰
 * @author guo
 */
public class Constant {
    /**
     * 提示最大条数
     */
    public static final Integer HINT_SIZE = 5;
    
    /*
        订单状态：-1-删除，0-待付款(默认)，1-已付款，2-已发货，3-已收货
     */
    public static final Integer WAIT_PAY = 0;
    public static final Integer HAVE_PAY = 1;
    public static final Integer HAVE_DELIVER = 2;
    public static final Integer HAVE_RECEIVE_GOOD = 3;

    /*订单商品状态*/
    public static final Integer WAIT_COMMENT = 0;
    public static final Integer HAVE_COMMENT = 1;

    /**
     * 删除状态值
     */
    public static final Integer DROP_STATE = -1 ;

    /**
     * 项目IP地址
     */
    public static final String IP_ADDRESS = "http://localhost:8080";

}
