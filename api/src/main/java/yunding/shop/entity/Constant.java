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

    /**
     * 订单 待付款状态
     */
    public static final Integer WAIT_PAY = 0;
    /**
     * 订单 待发货状态
     */
    public static final Integer WAIT_SEND_GOOD = 1 ;
    /**
     * 订单 待收货状态
     */
    public static final Integer WAIT_RECEIVE_GOOD = 2;
    /**
     * 订单 待评价状态
     */
    public static final Integer WAIT_COMMENT = 3;
    /**
     * 订单 结束
     */
    public static final Integer OVER_ORDER = 4;
}
