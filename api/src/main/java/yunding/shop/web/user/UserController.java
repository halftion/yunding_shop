package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.UserInfo;
import yunding.shop.service.UserService;
import yunding.shop.util.UserUtil;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 * @author ren
 * @author guo
 */
@RestController
@RequestMapping ("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/avatar/{avatarUrl}", method = RequestMethod.PUT)
    public RequestResult saveAvatarUrl(@PathVariable String avatarUrl, HttpServletRequest request){

        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = userService.updateAvatar(userId,avatarUrl);

            if(serviceResult.isSuccess()){
                return RequestResult.success();
            } else{
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("保存头像url失败");
        }
    }

    /**
     * 根据用户ID获取用户信息
     * @param request request对象
     * @return 用户信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public RequestResult searchById(HttpServletRequest request) {
        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = userService.getById(userId);
                if(serviceResult.isSuccess()) {
                    return RequestResult.success(serviceResult.getData());
                } else {
                    return RequestResult.failure(serviceResult.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("搜索失败");
        }
    }

    /**
     * 根据用户类修改用户个人信息
     * @param userInfo 用户类
     * @param request request对象
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public RequestResult updateUserInfo(@RequestBody UserInfo userInfo,
                                        HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return RequestResult.failure(bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            Integer userId = UserUtil.getCurrentUserId(request);
            ServiceResult serviceResult = userService.updateUserInfo(userId, userInfo);
            if (serviceResult.isSuccess())
            {
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("更新用户信息失败");
        }
    }
}


   