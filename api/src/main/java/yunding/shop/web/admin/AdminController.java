package yunding.shop.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Content;
import yunding.shop.entity.Login;
import yunding.shop.service.AdminService;
import yunding.shop.service.GoodsService;

/**
 * 后台管理系统
 * @author guo
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 登录
     * @param login 登录对象
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RequestResult login(@RequestBody Login login) {
        try {
            ServiceResult serviceResult = adminService.login(login);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("登录失败");
        }
    }

    /**
     * 添加平台分类
     * @param name 平台分类名称
     */
    @RequestMapping(value = "/platformCategory/{name}", method = RequestMethod.POST)
    public RequestResult addPlatformCategory(@PathVariable("name") String name){
        try {
            ServiceResult serviceResult= adminService.addPlatformCategory(name);
            if (serviceResult.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("添加平台分类失败");
        }
    }

    /**
     * 移除平台分类
     * @param categoryId 平台分类Id
     */
    @RequestMapping(value = "/platformCategory/{categoryId}", method = RequestMethod.DELETE)
    public RequestResult deletePlatformCategory(@PathVariable("categoryId") Integer categoryId){
        try {
            ServiceResult serviceResult= adminService.deletePlatformCategory(categoryId);
            if (serviceResult.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("移除平台分类失败");
        }
    }

    /**
     * 获取所有文章
     * @return 文章详细信息
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    RequestResult selectAll(){
        try {
            ServiceResult serviceResult= adminService.allContent();
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("获取所有文章失败");
        }
    }

    /**
     * 添加首页文章
     * @param content 文章详细信息
     */
    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public RequestResult addContent(@RequestBody Content content){
        try {
            ServiceResult result= adminService.addContent(content);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("添加首页文章失败");
        }
    }

    /**
     * 移除首页文章
     * @param contentId 文章Id
     */
    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.DELETE)
    public RequestResult deleteContent(@PathVariable("contentId") Integer contentId){
        try {
            ServiceResult result= adminService.deleteContent(contentId);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                //文章移除异常
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("移除首页文章失败");
        }
    }

    /**
     * 恢复首页文章
     * @param contendId 文章Id
     */
    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.PUT)
    public RequestResult recoverContent(@PathVariable("contentId") Integer contendId){
        try {
            ServiceResult result= adminService.recoverContent(contendId);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                //文章恢复异常
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("恢复首页文章失败");
        }
    }
    /**
     * 查看所有用户
     * @return 所有用户信息
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public RequestResult user(){
        try {
            ServiceResult serviceResult= adminService.alluser();
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("查询所有用户失败");
        }
    }

    /**
     * 更新用户状态
     * @param userId 用户Id
     * @param state 用户状态
     */
    @RequestMapping(value = "/user/{userId}/{state}", method = RequestMethod.PUT)
    public RequestResult updateUserState(@PathVariable("userId") Integer userId,
                                         @PathVariable("state") Integer state){
        try {
            ServiceResult result= adminService.updateUserState(userId, state);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("更新用户状态失败");
        }
    }

    /**
     * 查询所有店铺
     * @return 所有店铺信息
     */
    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public RequestResult allShop(){
        try {
            ServiceResult serviceResult= adminService.allShop();
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("查询所有店铺失败");
        }
    }

    /**
     * 查询所有商品
     * @return 商品详细信息
     */
    @RequestMapping(value = "/goods/{currentPage}", method = RequestMethod.GET)
    public RequestResult pageAllGoods(@PathVariable Integer currentPage){
        try {
            ServiceResult serviceResult= goodsService.selectAllGoods(currentPage);
            if (serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("分页查询所有商品失败");
        }
    }

    /**
     * 下架商品
     * @param goodsId 商品ID
     */
    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.DELETE)
    public RequestResult pullOffShelves(@PathVariable("goodsId") Integer goodsId){
        try {
            ServiceResult result= adminService.pullOffShelves(goodsId);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("下架商品失败");
        }
    }

    /**
     * 上架商品
     * @param goodsId 商品Id
     */
    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.PUT)
    public RequestResult putOnSales(@PathVariable("goodsId") Integer goodsId){
        try {
            ServiceResult result= adminService.pullOnSales(goodsId);
            if (result.isSuccess()){
                return RequestResult.success();
            }else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("上架商品失败");
        }
    }

}
