package yunding.shop.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yunding.shop.dto.JwtResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.Login;
import yunding.shop.entity.Register;
import yunding.shop.entity.UserInfo;
import yunding.shop.mapper.LoginMapper;
import yunding.shop.service.LoginService;
import yunding.shop.service.UserService;
import yunding.shop.util.JwtUtil;

import java.util.Date;

/**
 * @author 齐语冰
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserService userService;

    @Override
    public ServiceResult checkExist(String loginName) {
        try{
            if(loginName.length() != 11){
                return ServiceResult.failure("请输入11位的登录名");
            }
            Login dbLogin = loginMapper.selectByLoginName(loginName);
            if(dbLogin == null){
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("该登录名已被占用");
            }
        }catch (Exception e){
            return ServiceResult.failure("产生异常");
        }
    }

    @Override
    public ServiceResult login(Login login) {
        Login dbLogin = loginMapper.selectByLoginName(login.getLoginName());
        try {
            //用户名不存在
            if(null == dbLogin){
                return ServiceResult.failure("用户名不存在");
            }
            //密码正确
            if (dbLogin.getPassword().equals(login.getPassword())) {
                String userId = dbLogin.getUserId().toString();
                Claims claims = new DefaultClaims();
                claims.setId(userId);
                claims.put("userId", userId);
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
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult register(Register register) {
        try {
            String nickName = register.getNickName();
            String loginName = register.getLoginName();
            String password = register.getPassword();

            //创建用户信息并返回自动递增主键
            Integer userId = (Integer) userService.create(nickName).getData();

            //创建登录信息
            Login login = new Login(userId,loginName,password,new Date(),new Date());
            loginMapper.insert(login);

            //注册成功返回用户信息
            return ServiceResult.success(userService.getById(userId));
        } catch (Exception e) {
            throw new RuntimeException("注册失败");
        }
    }
}
