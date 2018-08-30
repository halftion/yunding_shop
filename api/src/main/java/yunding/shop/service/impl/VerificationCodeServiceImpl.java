package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.IdentifyingCode;
import yunding.shop.mapper.VerificationCodeMapper;
import yunding.shop.service.LoginService;
import yunding.shop.service.VerificationCodeService;
import yunding.shop.util.SmsUtil;
import java.util.Date;

/**
 * @author 齐语冰
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    VerificationCodeMapper verificationCodeMapper;

    @Autowired
    SmsUtil smsUtil;

    @Autowired
    LoginService loginService;

    @Autowired
    JedisPool jedisPool;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult sendAndSave(String phoneNumber) {
        try (Jedis jedis = jedisPool.getResource()) {
            ServiceResult serviceResult = loginService.checkLoginName(phoneNumber);
            if (!serviceResult.isSuccess()){
                return ServiceResult.failure(serviceResult.getMessage());
            }

            //使该登录名的验证码都失效
            verificationCodeMapper.drop(phoneNumber);

            String verificationCode= SmsUtil.randomVerificationCode();

            //发送验证码
            smsUtil.sendMessaging(phoneNumber,verificationCode);

            //插入到数据库
//            verificationCodeMapper.insert(new IdentifyingCode(phoneNumber,verificationCode,new Date()));

            jedis.setex("code_"+phoneNumber,300,verificationCode);

            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送和保存验证码错误");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult verify(String loginName, String code) {
        try (Jedis jedis = jedisPool.getResource()) {
            //验证码失效时间 (s)
            int outTime = 300;
            /*Date now = new Date();
            IdentifyingCode identifyingCode = verificationCodeMapper.selectByLoginName(loginName);
            if (identifyingCode != null) {
                //超时验证码失效
                if (now.getTime() - identifyingCode.getCreatedAt().getTime() > outTime){
                    *//*将state置为 -1*//*
                    verificationCodeMapper.drop(loginName);
                    return ServiceResult.failure("验证码超时");
                    //验证码正确
                }else if (identifyingCode.getCode().equals(code)){
                    //验证成功
                    return ServiceResult.success();
                }else {
                    //服务层成功，验证码错误
                    return ServiceResult.failure("验证码错误");
                }
            } else {
                return ServiceResult.failure("该手机号未发送验证码");
            }*/
            String dbCode = jedis.get("code_"+loginName);

            if (code.equals(dbCode)){
                return ServiceResult.success();
            } else {
                return ServiceResult.failure("验证码错误");
            }

        } catch (Exception e) {
            //服务层失败
            throw new RuntimeException("验证失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult dropCode(String loginName) {
        try (Jedis jedis = jedisPool.getResource()){
//            verificationCodeMapper.drop(loginName);
            jedis.del("code_"+loginName);
            return ServiceResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("使验证码失效失败");
        }
    }
}