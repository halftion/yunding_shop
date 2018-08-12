package yunding.shop.mapper;

import yunding.shop.entity.IdentifyingCode;

/**
 * @author huguobin
 * 用于存储验证码、手机号等信息
 */
public interface VerificationCodeMapper {
    /**
     * 保存验证码
     * @param identifyingCode 验证码实体类
     */
    void insert(IdentifyingCode identifyingCode);

    /**
     * 通过登录名搜索 尚未失效的验证码(只可能是0个或1个)
     * @param loginName 登录名
     * @return 验证码实体类
     */
    IdentifyingCode selectByLoginName(String loginName);

    /**
     * 使对应登录名的有效验证码都失效
     * @param loginName 登录名
     */
    void drop(String loginName);
}
