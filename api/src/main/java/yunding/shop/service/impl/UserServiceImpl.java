package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.User;
import yunding.shop.mapper.LoginMapper;
import yunding.shop.mapper.UserMapper;
import yunding.shop.service.UserService;

import java.util.Date;

/**
 * @author ren
 * @author guo
 * @author 齐语冰
 * @author huguobin
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public ServiceResult checkLoginName(String loginName) {
        try {
            Login login = loginMapper.selectByLoginName(loginName);
            if(login == null) {
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("该登录名已被占用");
            }
        } catch (Exception e) {
            return ServiceResult.failure("验证失败");
        }
    }


    @Override
    public ServiceResult login(Login login) {
        Login dbLogin = loginMapper.selectByLoginName(login.getLoginName());
        try {
            if (dbLogin.getPassword().equals(login.getPassword())) {
                //密码正确,返回用户信息
                return ServiceResult.success(
                        userMapper.selectById(dbLogin.getUserId()));
            } else {
                //密码错误
                return ServiceResult.failure("用户名和密码不匹配");
            }
        } catch (Exception e) {
            return ServiceResult.failure("登录失败");
        }
    }

    @Override
    public ServiceResult searchById(Integer id) {
        try{
            return ServiceResult.success(userMapper.selectById(id));
        }catch (Exception e){
            return ServiceResult.failure("搜索失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateUserInfo(Integer userId, User user) {
        try {
            user.setUserId(userId);
            user.setUpdatedAt(new Date());
            userMapper.update(user);
            return ServiceResult.success(userMapper.selectById(user.getUserId()));
        } catch (Exception e) {
            throw new RuntimeException("更新用户信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult register(String loginName, String password) {
        try {
            Date now = new Date();
            User user = new User(now,now);
            userMapper.insert(user);
            //返回自动递增主键
            Integer userId = user.getUserId();
            Login login = new Login(userId,loginName,password,now,now);
            loginMapper.insert(login);
            //注册成功返回用户信息
            return ServiceResult.success(userMapper.selectById(userId));
        } catch (Exception e) {
            throw new RuntimeException("注册失败1"+e.getMessage());
        }
    }
}

