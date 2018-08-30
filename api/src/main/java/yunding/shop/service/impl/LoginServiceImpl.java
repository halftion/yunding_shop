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
import yunding.shop.entity.ResetPwd;
import yunding.shop.mapper.LoginMapper;
import yunding.shop.service.LoginService;
import yunding.shop.service.UserService;
import yunding.shop.service.VerificationCodeService;
import yunding.shop.util.CheckUtil;
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

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Override
    public ServiceResult checkLoginName(String loginName) {
        try{
            if (CheckUtil.checkPhoneNumberFormat(loginName)) {
                Login dbLogin = loginMapper.selectByLoginName(loginName);
                if(dbLogin == null){
                    return ServiceResult.success();
                }else {
                    return ServiceResult.failure("该手机号已被占用");
                }
            } else {
                return ServiceResult.failure("手机号格式错误");
            }
        }catch (Exception e){
            return ServiceResult.failure("检测失败");
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
            String code = register.getCode();

            ServiceResult serviceResult = checkLoginName(loginName);


            //用户名合法可用
            if (serviceResult.isSuccess()) {

                //验证验证码，并使验证码失效
                serviceResult = verificationCodeService.verify(loginName,code);


                //验证码正确
                if (serviceResult.isSuccess()){

                    verificationCodeService.dropCode(loginName);

                    //创建用户信息，并返回自动递增主键
                    serviceResult = userService.create(nickName);

                    //创建用户信息成功
                    if(serviceResult.isSuccess()){
                        Integer userId = (Integer) serviceResult.getData();

                        //创建登录信息
                        Login login = new Login(userId,loginName,password,new Date(),new Date());
                        if (!loginMapper.insert(login).equals(1)){
                            throw new RuntimeException("添加用户登录信息失败");
                        }

                        //登录，返回token
                        serviceResult = login(login);

                        //登录成功
                        if (serviceResult.isSuccess()){
                            return ServiceResult.success(serviceResult.getData());
                        } else {
                            return ServiceResult.failure(serviceResult.getMessage());
                        }
                    } else {
                        throw new RuntimeException(serviceResult.getMessage());
                    }
                } else {
                    return ServiceResult.failure(serviceResult.getMessage());
                }
            } else {
                return ServiceResult.failure(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult resetPwd(ResetPwd resetPwd){
        try {
            String loginName = resetPwd.getLoginName();
            String password = resetPwd.getPassword();
            String code = resetPwd.getCode();

            //验证验证码，并使验证码失效
            ServiceResult serviceResult = verificationCodeService.verify(loginName,code);

            //验证码正确
            if (serviceResult.isSuccess()){
                //验证码正确，注销验证码
                verificationCodeService.dropCode(loginName);

                Login login = new Login();
                login.setLoginName(loginName);
                login.setPassword(password);
                login.setUpdatedAt(new Date());

                if (!loginMapper.updatePwd(login).equals(1)){
                    throw new RuntimeException("更新用户密码失败");
                }
                return ServiceResult.success();
            } else {
                return ServiceResult.failure(serviceResult.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("重置密码失败");
        }
    }
}
