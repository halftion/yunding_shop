package yunding.shop.web.verificationCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.VerificationCodeService;

/**
 * @author 齐语冰
 */
@RestController
@RequestMapping("/api/verificationCode")
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    /**
     * 接收电话号发送验证码
     * @param phoneNumber 电话号
     */
    @RequestMapping(value = "/register/{phoneNumber}",method = RequestMethod.GET)
    public RequestResult register(@PathVariable String phoneNumber){
        try{
            ServiceResult result = verificationCodeService.sendAndSave(phoneNumber);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取验证码失败");
        }
    }
}
