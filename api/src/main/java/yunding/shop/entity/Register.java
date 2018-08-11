package yunding.shop.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * 注册实体类
 * @author 齐语冰
 */
public class Register {
    /**
     * 登录名
     */
    @NotEmpty
    @Size(min = 11, max = 11)
    private String loginName;

    /**
     * 验证码
     */
    @NotEmpty
    @Size(min = 6, max = 6)
    private String code;

    /**
     * 密码
     */
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    /**
     * 注册类型 默认0
     */
    private Integer type;

    /**
     * 用户昵称
     */
    @NotEmpty
    @Size(min = 3,max = 10)
    private String nickName;


    public Login toLogin(){
        return new Login(this.getLoginName(),this.getPassword());
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
