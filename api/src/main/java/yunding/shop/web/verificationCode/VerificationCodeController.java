package yunding.shop.web.verificationCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.LoginService;
import yunding.shop.service.VerificationCodeService;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/verificationCode")
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    LoginService loginService;

    /**
     * 接收电话号发送验证码
     *
     * @param loginName 登录名(电话号)
     */
    @RequestMapping(value = "/send/{loginName}", method = RequestMethod.GET)
    public RequestResult register(@PathVariable String loginName) {
        try {
            ServiceResult serviceResult = verificationCodeService.sendAndSave(loginName);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("获取验证码失败");
        }
    }

    /**
     * 验证验证码是否正确
     * @param loginName 登录名
     * @param code 验证码
     */
    @RequestMapping(value = "/check/{loginName}/{code}", method = RequestMethod.GET)
    public RequestResult verifyCode(@PathVariable String loginName, @PathVariable String code) {
        try {
            ServiceResult serviceResult = verificationCodeService.verify(loginName, code);
            //验证成功
            if (serviceResult.isSuccess()) {
                return RequestResult.success();
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("验证码错误");
        }
    }
}
