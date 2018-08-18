package yunding.shop.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.JwtResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Content;
import yunding.shop.entity.Login;
import yunding.shop.service.AdminService;
import yunding.shop.service.ContentService;
import yunding.shop.service.PlatformGoodsCategoryService;
import yunding.shop.service.UserService;
import yunding.shop.util.JwtUtil;

/**
 * @author guo
 */
@Service
public class AdminServiceImpl implements AdminService {

    public static final String LOGIN_NAME = "yundingAdmin";

    public static final String PASSWORD = "yunding2018";

    @Autowired
    private PlatformGoodsCategoryService platformGoodsCategoryService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Override
    public ServiceResult login(Login login) {
        try {
            //用户名不存在
            if(!login.getLoginName().equals(LOGIN_NAME)){
                return ServiceResult.failure("用户名不存在");
            }
            //密码正确
            if (login.getPassword().equals(PASSWORD)) {
                Claims claims = new DefaultClaims();
                claims.setId("2018");
                claims.put("adminId", 2018);
                JwtResult jwt = JwtUtil.createJwt(claims, 120 * 60);

                return ServiceResult.success(jwt);
            } else {
                //密码错误
                return ServiceResult.failure("用户名和密码不匹配");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.failure("登录失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult addPlatformCategory(String name) {
        try {
            ServiceResult sr = platformGoodsCategoryService.addCategory(name);
            if(!sr.isSuccess()){
                //添加分类失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("平台分类创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deletePlatformCategory(Integer categoryId) {
        try {
            ServiceResult sr = platformGoodsCategoryService.deleteCategory(categoryId);
            if(!sr.isSuccess()){
                //更新分类失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("移除平台分类失败");
        }
    }

    @Override
    public ServiceResult allContent() {
        try {
            ServiceResult sr = contentService.selectAll();
            if(!sr.isSuccess()){
                //获取所有文章失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success(sr.getData());
        } catch (Exception e) {
            return ServiceResult.failure("查询所有文章异常");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult addContent(Content content) {
        try {
            ServiceResult sr = contentService.addContent(content);
            if(!sr.isSuccess()){
                //添加文章失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("文章创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult deleteContent(Integer contentId) {
        try {
            ServiceResult sr = contentService.deleteContent(contentId);
            if(!sr.isSuccess()){
                //更新文章类型失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("文章移除失败");
        }
    }

    @Override
    public ServiceResult Alluser() {
        try {
            ServiceResult sr = userService.getAllUser();
            if(!sr.isSuccess()){
                //获取用户信息失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success(sr.getData());
        } catch (Exception e) {
            return ServiceResult.failure("查询所有用户异常");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateUserState(Integer userId, Integer state) {
        try {
            ServiceResult sr = userService.updateState(userId, state);
            if(!sr.isSuccess()){
                //更新用户信息失败
                return ServiceResult.failure(sr.getMessage());
            }
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("更新用户状态异常");
        }
    }
}