package yunding.shop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 用户
 * @author 齐语冰
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    /**
     * 用户 id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer userId;
    /**
     * 用户 昵称
     */
    private String nickName;
    /**
     * 用户 头像链接
     */
    private String avatar;
    /**
     * 用户 性别：0-保密，1-男，2-女
     */
    @NotEmpty
    @Min(value = 0, message = "性别：0-保密，1-男，2-女")
    @Max(value = 2, message = "性别：0-保密，1-男，2-女")
    private Integer gender;
    /**
     * 用户 生日
     */
    private Date birthday;
    /**
     * 用户 手机号
     */
    private String phoneNumber;
    /**
     * 用户 邮箱地址
     */
    private String email;
    /**
     * 用户 用户经验值
     */
    private Integer xp;
    /**
     * 用户 状态：-1 封禁，0-正常买家(默认) ，1-买家+卖家
     */
    private Integer state;
    /**
     * 用户 创建时间
     */
    private Date createdAt;
    /**
     * 用户 更新时间
     */
    private Date updatedAt;


    public UserInfo() {
    }

    public UserInfo(String nickName, Date createdAt, Date updatedAt) {
        this.nickName = nickName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
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


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", xp=" + xp +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';

    }
}
