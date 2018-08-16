package yunding.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Controller层返回的统一对象
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResult {

    /**
     * 标示service是否执行成功
     */
    private Integer code;

    /**
     * 单个数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;



    private RequestResult(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    /**
     * 请求成功-无数据
     * @return RequestResult
     */
    public static RequestResult success() {
        return new RequestResult(200, null, "操作成功！");
    }

    /**
     * 请求成功-有数据
     * @param data 实际数据
     * @return RequestResult
     */
    public static RequestResult success(Object data) {
        return new RequestResult(200, data, "操作成功！");
    }

    /**
     * 请求失败-默认错误
     * @return RequestResult
     */
    public static RequestResult failure() {
        return new RequestResult(500, null, "操作失败");
    }

    /**
     * 请求失败-自定义错误
     * @param message 错误信息
     * @return RequestResult
     */
    public static RequestResult failure(String message) {
        return new RequestResult(500, null, message);
    }





    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}