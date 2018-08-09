package yunding.shop.util;

import yunding.shop.entity.UserInfo;
import yunding.shop.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 齐语冰
 */
public class UserUtil {

    public static UserInfo getCurrentUser(HttpServletRequest request) {

        return (UserInfo) (new UserServiceImpl()
                .getById((Integer) request
                        .getAttribute("currentUserId"))).getData();
    }

    public static Integer getCurrentUserId(HttpServletRequest request){
        return (Integer) request.getAttribute("currentUserId");
}
/*    private static RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

    public static UserInfo getCurrentUser(){
        if(null != requestAttributes){
            HttpServletRequest request = (
                    (ServletRequestAttributes) requestAttributes).getRequest();
            return (UserInfo) new UserServiceImpl().getById(((UserInfo)request
                    .getAttribute("currentUser"))
                            .getUserId()).getData();
        }else {
            return null;
        }
    }*/

}
