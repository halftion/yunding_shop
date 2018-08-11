package yunding.shop.service;

import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;

/**
 * @author 齐语冰
 */
public interface LoginService {
    /**
     * 检查用户名是否被占用
     * @param loginName 用户名
     */
    ServiceResult checkExist(String loginName);

    /**
     * 登录
     * @param login 登录信息
     */
    ServiceResult login(Login login);

    /**
     * 注册
     * @param register 注册信息
     */
    ServiceResult register(Register register);
}
