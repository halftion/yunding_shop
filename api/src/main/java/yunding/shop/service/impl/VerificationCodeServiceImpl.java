package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.IdentifyingCode;
import yunding.shop.mapper.IdentifyingCodeMapper;
import yunding.shop.service.VerificationCodeService;
import yunding.shop.utils.SmsUtil;

import java.util.Date;

/**
 * @author 齐语冰
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    IdentifyingCodeMapper identifyingCodeMapper;
    @Autowired
    SmsUtil smsUtil;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult sendAndSave(String phoneNumber) {
        try {
            //使该登录名的验证码都失效
            identifyingCodeMapper.drop(phoneNumber);
            String verificationCode= SmsUtil.randomVerificationCode();
            //发送验证码
            smsUtil.sendMessaging(phoneNumber,verificationCode);
            //插入到数据库
            identifyingCodeMapper.insert(new IdentifyingCode(phoneNumber,verificationCode,new Date()));
            return ServiceResult.success();
        } catch (Exception e) {
            throw new RuntimeException("发送和保存验证码错误");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult verify(String loginName, String code) {
        try {
            //验证码失效时间
            Integer outTime = 300000;
            Date now = new Date();
            IdentifyingCode identifyingCode = identifyingCodeMapper.selectByLoginName(loginName);
            System.out.println(identifyingCode.toString());
            //超时验证码失效
            if (now.getTime() - identifyingCode.getCreatedAt().getTime() > outTime){
                /*将state置为 -1*/
                identifyingCodeMapper.drop(loginName);
                return ServiceResult.failure("验证码超时");
            }else if (identifyingCode.getCode().equals(code.toUpperCase())){
                //服务层成功，验证码正确
                return ServiceResult.success(true);
            }else {
                //服务层成功，验证码错误
                return ServiceResult.failure("验证码错误");
            }
        } catch (Exception e) {
            //服务层失败
            throw new RuntimeException("验证失败");
        }
    }
}