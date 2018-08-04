package yunding.shop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author 齐语冰
 */
@Aspect
public class OrderServiceAspect {
    @Before("execution(* yunding.shop.service.UserService.)")
    public void afterCreateOrder(){
        System.out.println("@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$%%%%%%%%%%%%%%%%");
    }
}
