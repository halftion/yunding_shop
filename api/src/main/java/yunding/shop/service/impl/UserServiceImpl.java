package yunding.shop.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.JwtResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.UserInfo;
import yunding.shop.mapper.LoginMapper;
import yunding.shop.mapper.UserMapper;
import yunding.shop.service.UserService;
import yunding.shop.util.JwtUtil;
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
            //密码正确
            if(null == dbLogin){
                return ServiceResult.failure("用户名不存在");
            }

            if (dbLogin.getPassword().equals(login.getPassword())) {
                UserInfo userInfo = userMapper.selectById(dbLogin.getUserId());
                Claims claims = new DefaultClaims();
                claims.setId(userInfo.getUserId().toString());
                claims.put("userId", userInfo.getUserId());
                JwtResult jwt = JwtUtil.createJwt(claims, 120 * 60);

                return ServiceResult.success(jwt);
            } else {
                //密码错误
                return ServiceResult.failure("用户名和密码不匹配");
            }
        } catch (Exception e) {
            return ServiceResult.failure("登录失败");
        }
    }

    @Override
    public ServiceResult getById(Integer id) {
        try{
            return ServiceResult.success(userMapper.selectById(id));
        }catch (Exception e){
            return ServiceResult.failure("获取个人信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateUserInfo(Integer userId, UserInfo userInfo) {
        try {
            userInfo.setUserId(userId);
            userInfo.updateAtNow();
            userMapper.update(userInfo);
            return ServiceResult.success(userMapper.selectById(userId));
        } catch (Exception e) {
            throw new RuntimeException("更新用户信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult register(String loginName, String password) {
        try {
            Date now = new Date();
            UserInfo userInfo = new UserInfo(now,now);
            userMapper.insert(userInfo);
            //返回自动递增主键
            Integer userId = userInfo.getUserId();
            Login login = new Login(userId,loginName,password,new Date(),new Date());
            loginMapper.insert(login);
            //注册成功返回用户信息
            return ServiceResult.success(userMapper.selectById(userId));
        } catch (Exception e) {
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult updateAvatar(Integer userId, String avatar) {
        UserInfo userInfo = userMapper.selectById(userId);
        userInfo.setUserId(userId);
        userInfo.setAvatar(avatar);
        userInfo.updateAtNow();
        if(userMapper.updateAvatar(userInfo) == 1){
            return ServiceResult.success();
        }else {
            throw new RuntimeException("更新头像失败");
        }
    }

    @Override
    public ServiceResult getNickNameById(Integer userId) {
        try{
            String nickName = userMapper.selectNickNameById( userId );
            return ServiceResult.success(nickName);
        }catch (Exception e){
            return ServiceResult.failure("获取昵称失败");
        }
    }
}

