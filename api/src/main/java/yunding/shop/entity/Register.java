package yunding.shop.entity;

/**
 * 注册实体类
 * @author 齐语冰
 */
public class Register {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    private String password;
    /**
     * 注册类型
     */
    private Integer type;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
