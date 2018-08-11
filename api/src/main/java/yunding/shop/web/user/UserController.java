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
import yunding.shop.service.LoginService;
import yunding.shop.service.UserService;
import yunding.shop.service.VerificationCodeService;
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

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private LoginService loginService;

    /**
     * 检测登录名是否被占用
     * @param loginName 登录名
     */
    @RequestMapping(value = "/checkLoginName/{loginName}", method = RequestMethod.GET)
    public RequestResult checkLoginName(@PathVariable String loginName) {
        try {
            ServiceResult serviceResult = loginService.checkExist(loginName);
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
            ServiceResult serviceResult = loginService.login(login);
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

        String loginName = register.getLoginName();
        String code = register.getCode();

        if (bindingResult.hasErrors()) {
            return RequestResult.failure("非法注册信息");
        }

        try {
            ServiceResult verificationCodeServiceResult = verificationCodeService.verify(
                    loginName,code);
            //验证码正确
            if (verificationCodeServiceResult.isSuccess()){
                ServiceResult registerResult = loginService.register(register);
                //注册成功
                if(registerResult.isSuccess()){
                    return RequestResult.success(
                            loginService.login(register.toLogin()).getData());
                }else {
                    //注册失败
                    return RequestResult.failure(registerResult.getMessage());
                }
            }else {
                //验证失败
                return RequestResult.failure(verificationCodeServiceResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("注册失败");
        }
    }

    @RequestMapping(value = "/verifyCode")
    public RequestResult verifyCode(@RequestBody String loginName, @RequestBody String code){
        try {
            verificationCodeService.verify(loginName,code);
            return RequestResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("验证码错误");
        }
    }
}


   