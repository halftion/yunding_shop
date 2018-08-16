package yunding.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;
import yunding.shop.entity.ResetPwd;
import yunding.shop.service.LoginService;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private LoginService loginService;

    /**
     * 检测登录名是否被占用
     * @param loginName 登录名
     */
    @RequestMapping(value = "/checkLoginName/{loginName}", method = RequestMethod.GET)
    public RequestResult checkLoginName(@PathVariable String loginName) {
        try {
            ServiceResult serviceResult = loginService.checkLoginName(loginName);
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
            return RequestResult.failure(bindingResult.getFieldError().getDefaultMessage());
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
     * 注册用户
     * @param register 注册实体类
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  RequestResult register(@Validated @RequestBody Register register,
                                   BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return RequestResult.failure(bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            ServiceResult registerResult = loginService.register(register);

            if (registerResult.isSuccess()){
                return RequestResult.success(registerResult.getData());
            }else {
                return RequestResult.failure(registerResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("注册失败");
        }
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/resetPwd",method = RequestMethod.POST)
    public RequestResult resetPwd(@RequestBody ResetPwd resetPwd,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return RequestResult.failure(bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            ServiceResult registerResult = loginService.resetPwd(resetPwd);

            if (registerResult.isSuccess()){
                return RequestResult.success(registerResult.getData());
            }else {
                return RequestResult.failure(registerResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("注册失败");
        }
    }
}
