package yunding.shop.dto;

/**
 * Service层返回的统一对象
 * @author 齐语冰
 */
public class ServiceResult {


    /**
     * 标示service是否执行成功
     */
    private boolean success;

    /**
     * 单个数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;



    private ServiceResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }



    /**
     * Service成功
     * @param data 实际数据
     * @return ServiceResult
     */
    public static ServiceResult success(Object data) {
        return new ServiceResult(true, data, null);
    }

    /**
     * Service 成功
     * @return ServiceResult
     */
    public static ServiceResult success() {
        return new ServiceResult(true, null, "操作成功");
    }

    /**
     * Service失败
     * @param message 错误信息
     * @return ServiceResult
     */
    public static ServiceResult failure(String message) {
        return new ServiceResult(false, null, message);
    }

    /**
     * Service失败
     * @return ServiceResult
     */
    public static ServiceResult failure() {
        return new ServiceResult(false, null,"操作失败");
    }





    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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