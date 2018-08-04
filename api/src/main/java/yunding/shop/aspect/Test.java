package yunding.shop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author 齐语冰
 */
@Aspect
public class Test {
    @Before("execution(* yunding.shop.service.UserService.checkLoginName(..))")
    public void beforeCheckLogin(){
        System.out.println("@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$%%%%%%%%%%%%%%%%");
    }
}
