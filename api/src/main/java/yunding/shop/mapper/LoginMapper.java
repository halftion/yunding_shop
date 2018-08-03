package yunding.shop.mapper;

import yunding.shop.entity.IdentifyingCode;
import yunding.shop.entity.Login;

/**
 * @author huguobin
 * 登陆、注册有关
 */
public interface LoginMapper {

    /**
     * 插入登录信息到login表
     * @param login 登录信息
     */
    void insert(Login login);

    /**
     * 通过登录名获取登录信息
     * @param loginName 登录名
     * @return 登录信息
     */
    Login selectByLoginName(String loginName);
}
