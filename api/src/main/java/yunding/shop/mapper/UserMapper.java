package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.UserInfo;

/**
 * 提供用户数据库方法
 * @author 齐语冰
 */
public interface UserMapper {
    /**
     *根据用户ID获取用户信息
     * @param userId 用户id
     * @return 对应用户
     */
    UserInfo selectById(@Param("userId") Integer userId );

    /**
     * 更新用户信息
     * @param userInfo 用户类
     */
    void update(UserInfo userInfo);

    /**
     * 插入新用户
     * @param userInfo 用户类
     */
    void insert(UserInfo userInfo);

    /**
     * 根据用户id修改用户头像
     * @param userInfo 用户类
     */
    Integer updateAvatar(UserInfo userInfo);

    /**
     * 根据用户Id查询用户昵称
     * @param userId 用户id
     * @return 用户昵称
     */
    String selectNickNameById(Integer userId);

}
