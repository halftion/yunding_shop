package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

/**
 * @author 齐语冰
 */
public interface VerificationCodeService {
    /**
     * 发送并保存验证码
     * @param phoneNumber 手机号
     * @return 是否成功
     */
    ServiceResult sendAndSave(String phoneNumber);

    /**
     * 验证验证码是否正确
     * @param loginName 登录名
     * @param code 待验证的验证码
     * @return 是否正确
     */
    ServiceResult verify(String loginName, String code);

    /**
     * 使用户的验证码失效
     * @param loginName 用户名
     */
    ServiceResult dropCode(String loginName);
}
