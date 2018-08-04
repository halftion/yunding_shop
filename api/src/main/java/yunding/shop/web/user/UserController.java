package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;
import yunding.shop.entity.User;
import yunding.shop.service.UserService;
import yunding.shop.service.VerificationCodeService;
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
    @Qualifier("userServiceImpl")
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
     * @param request request对象
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RequestResult login(@RequestBody Login login, HttpServletRequest request) {
        try {
            ServiceResult serviceResult = userService.login(login);
            if (serviceResult.isSuccess()) {
                request.getSession().setAttribute("user",serviceResult.getData());
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("登录失败");
        }
    }

    /**
     * 根据session是否存在检测登录状态
     */
    @RequestMapping(value = "/loginState",method = RequestMethod.GET)
    public RequestResult checkLoginState(HttpServletRequest request){
        try {
            User user = (User)request.getSession().getAttribute("user");
            if(user != null)
            {
                return RequestResult.success(user);
            }else {
                return RequestResult.failure("尚未登陆");
            }
        }catch (Exception e){
            return RequestResult.failure("检测登录失败");
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public RequestResult logout(HttpServletRequest request){
        try {
            request.getSession().removeAttribute("user");
            return RequestResult.success();
        } catch (Exception e) {
            return RequestResult.failure("退出登录失败");
        }
    }

    /**
     * 根据ID查询用户
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public RequestResult searchById(HttpServletRequest request) {
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = userService.searchById(userId);
                if(serviceResult.isSuccess()) {
                    return RequestResult.success(serviceResult.getData());
                } else {
                    return RequestResult.failure(serviceResult.getMessage());
            }

        } catch (Exception e) {
            return RequestResult.failure("搜索失败");
        }
    }

    /**
     * 根据用户类修改用户个人信息
     * @param user 用户类
     * @param request request对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RequestResult updateUserInfo(@RequestBody User user,HttpServletRequest request){
        try {
            Integer userId = ((User)request.getSession().getAttribute("user")).getUserId();
            ServiceResult serviceResult = userService.updateUserInfo(userId,user);
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public  RequestResult register(@RequestBody Register register, HttpServletRequest request){
        try {
            ServiceResult verificationCodeServiceResult = verificationCodeService.verify(
                    register.getLoginName(),register.getCode());
            if (verificationCodeServiceResult.isSuccess()){
                ServiceResult registerResult = userService.register(
                        register.getLoginName(), register.getPassword());
                if(registerResult.isSuccess()){
                    User user = (User) registerResult.getData();
                    //登录
                    request.getSession().setAttribute("user",user);
                    return RequestResult.success(user);
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


   