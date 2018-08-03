package yunding.shop.entity;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * 登录信息
 * @author 齐语冰
 */
public class Login {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
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
}
