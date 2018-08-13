package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 注册实体类
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Register {
    /**
     * 登录名
     */
    @NotEmpty(message = "登录名不能为空")
    @Size(min = 11, max = 11, message = "登录名应为11位")
    private String loginName;

    /**
     * 验证码
     */
    @NotEmpty(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码应为6位")
    private String code;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码应介于6至20位之间")
    private String password;

    /**
     * 注册类型 默认0
     */
    private Integer type;

    /**
     * 用户昵称
     */
    @NotEmpty(message = "用户昵称不能为空")
    @Size(min = 3, max = 15, message = "用户昵称应介于3位至15位之间")
    private String nickName;



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
