package yunding.shop.service;

import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Content;
import yunding.shop.entity.Login;

/**
 * @author guo
 */
public interface AdminService {

    ServiceResult login(Login login);

    /**
     * 添加平台分类
     * @param name 平台分类名称
     */
    public ServiceResult addPlatformCategory(String name);

    /**
     * 移除平台分类
     * @param categoryId 平台分类Id
     */
    public ServiceResult deletePlatformCategory(Integer categoryId);

    /**
     * 获取所有文章
     * @return 文章详细信息
     */
    public ServiceResult allContent();

    /**
     * 添加首页文章
     * @param content 文章详细信息
     */
    public ServiceResult addContent(Content content);

    /**
     * 移除首页文章
     * @param contentId 文章Id
     */
    public ServiceResult deleteContent(Integer contentId);

    /**
     * 查看所有用户
     * @return 所有用户信息
     */
    public ServiceResult Alluser();

    /**
     * 修改用户状态
     * @param userId 用户Id
     * @param state 用户状态
     */
    public ServiceResult updateUserState(Integer userId, Integer state);
}

