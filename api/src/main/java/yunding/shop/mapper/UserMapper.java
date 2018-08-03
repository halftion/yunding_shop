package yunding.shop.mapper;

import org.apache.ibatis.annotations.Param;
import yunding.shop.entity.Login;
import yunding.shop.entity.User;

/**
 * 提供用户数据库方法
 * @author 齐语冰
 */
public interface UserMapper {
    /**
     *根据用户ID获取用户信息
     * @param id 用户id
     * @return 对应用户
     */
    User selectById(@Param("id") Integer id );

    /**
     * 更新用户信息
     * @param user 用户类
     */
    void update(User user);

    /**
     * 插入新用户
     * @param user 用户类
     */
    void insert(User user);
}
