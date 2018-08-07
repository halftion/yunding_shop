package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.UserInfo;

/**
 * @author ren
 * @author guo
 */
public interface UserService {
    /**
     * 检测登录名是否被占用
     * @param loginName 登录名
     * @return 是否被占用
     */
    ServiceResult checkLoginName(String loginName);

    /**
     * 检测是否登录成功
     * @param login 登录信息
     * @return 是否登录成功
     */
    ServiceResult login(Login login);

    /**
     * 根据用户Id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    ServiceResult getById(Integer id);

    /**
     * 根据用户类修改用户个人信息
     * @param userId 用户id
     * @param userInfo 用户类
     * @return 更新后的用户信息
     */
    ServiceResult updateUserInfo(Integer userId, UserInfo userInfo);

    /**
     * 注册用户
     * @param loginName 登录名
     * @param password 密码
     * @return 是否注册成功
     */
    ServiceResult register(String loginName, String password);

    /**
     * 更新用户头像地址
     * @param userId 用户id
     * @param avatar 头像地址
     * @return 是否成功
     */
    ServiceResult updateAvatar(Integer userId, String avatar);

    /**
     * 根据用户ID获取用户昵称
     * @param userId 用户Id
     * @return 用户昵称
     */
    ServiceResult getNickNameById (Integer userId);
}
