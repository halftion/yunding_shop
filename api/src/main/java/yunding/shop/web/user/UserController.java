package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;
import yunding.shop.entity.UserInfo;
import yunding.shop.service.UserService;
import yunding.shop.service.VerificationCodeService;
import yunding.shop.utils.UserUtil;
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

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 检测登录名是否被占用
     * @param loginName 登录名
     */
    @RequestMapping(value = "/checkLoginName/{loginName}", method = RequestMethod.GET)
    public RequestResult checkLoginName(@PathVariable String loginName) {
        try {
            ServiceResult serviceResult = userService.checkLoginName(loginName);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("检测用户名失败");
        }
    }

    /**
     * 登录
     * @param login 登录对象
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RequestResult login(@Validated @RequestBody Login login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return RequestResult.failure("登录失败");
        }

        try {
            ServiceResult serviceResult = userService.login(login);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("登录失败");
        }
    }

    /**
     * 根据ID查询用户
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
    public RequestResult updateUserInfo(@Validated @RequestBody UserInfo userInfo,
                                        HttpServletRequest request, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return RequestResult.failure("更新用户信息失败");
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

    /**
     * 注册用户
     * @param register 注册实体类
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  RequestResult register(@Validated @RequestBody Register register,
                                   BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return RequestResult.failure("更新用户信息失败");
        }

        try {
            ServiceResult verificationCodeServiceResult = verificationCodeService.verify(
                    register.getLoginName(),register.getCode());
            if (verificationCodeServiceResult.isSuccess()){
                ServiceResult registerResult = userService.register(
                        register.getLoginName(), register.getPassword());
                if(registerResult.isSuccess()){
                    UserInfo userInfo = (UserInfo) registerResult.getData();
/*                    //登录
                    request.getSession().setAttribute("userInfo", userInfo);*/
                    return RequestResult.success(userInfo);
                }else {
                    //注册失败
                    return RequestResult.failure(registerResult.getMessage());
                }
            }else {
                //验证失败
                return RequestResult.failure(verificationCodeServiceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("注册失败"+e.getMessage());
        }
    }
}


   