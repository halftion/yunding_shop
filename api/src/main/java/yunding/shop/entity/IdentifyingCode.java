package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * 验证码
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentifyingCode {
    /**
     * 验证码id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer codeId;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 验证码
     */
    private String code;
    /**
     * 验证码 状态 0-可用(默认)，-1-不可用
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createdAt;

    public IdentifyingCode() {
    }

    public IdentifyingCode(String loginName, String code, Date createdAt) {
        this.loginName = loginName;
        this.code = code;
        this.createdAt = createdAt;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "IdentifyingCode{" +
                "codeId=" + codeId +
                ", loginName='" + loginName + '\'' +
                ", code='" + code + '\'' +
                ", state=" + state +
                ", createdAt=" + createdAt +
                '}';
    }

    public void createAtNow(){
        this.createdAt = new Date();
    }
}
