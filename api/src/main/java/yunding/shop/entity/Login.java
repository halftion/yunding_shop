package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 登录信息
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 登录名
     */
    @NotEmpty(message = "登录名不能为空")
    @Size(min = 11, max = 11, message = "登录名应为11位")
    private String loginName;
    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码应介于6至20位之间")
    private String password;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

    public Login() {
    }

    public Login(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public Login(Integer userId, String loginName, String password, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void createAtNow(){
        this.createdAt = new Date();
    }

    public void updateAtNow(){
        this.updatedAt = new Date();
    }

}
