package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.UserInfo;

/**
 * @author ren
 * @author guo
 */
public interface UserService {

    /**
     * 创建新用户信息
     * @param nickName 昵称
     * @return userId
     */
    ServiceResult create(String nickName);

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
     * 更新用户头像地址
     * @param userId 用户id
     * @param avatarUrl 头像地址
     * @return 是否成功
     */
    ServiceResult updateAvatar(Integer userId, String avatarUrl);

    /**
     * 根据用户ID获取用户昵称
     * @param userId 用户Id
     * @return 用户昵称
     */
    ServiceResult getNickNameById (Integer userId);

    /**
     * 根据用户Id获取用户头像
     * @param userId 用户Id
     * @return 用户头像
     */
    ServiceResult getAvatarById (Integer userId);

    /**
     * 获取所有用户信息
     * @return 用户信息
     */
    ServiceResult getAllUser();

    /**
     * 更改用户状态
     * @param userId 用户ID
     * @param state 用户状态
     */
    ServiceResult updateState(Integer userId, Integer state);
}
