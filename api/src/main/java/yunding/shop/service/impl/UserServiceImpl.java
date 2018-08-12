package yunding.shop.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.JwtResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;
import yunding.shop.entity.UserInfo;
import yunding.shop.mapper.LoginMapper;
import yunding.shop.mapper.UserMapper;
import yunding.shop.service.LoginService;
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

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult create(String nickName) {
        try {
            Date now = new Date();
            UserInfo userInfo = new UserInfo(nickName,now,now);
            userMapper.insert(userInfo);
            return ServiceResult.success(userInfo.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("创建用户信息失败");
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

    @Override
    public ServiceResult getAvatarById(Integer userId) {
        try{
            String avatar = userMapper.selectAvatarById(userId);
            return ServiceResult.success(avatar);
        }catch (Exception e){
            return ServiceResult.failure("获取头像失败");
        }
    }
}

