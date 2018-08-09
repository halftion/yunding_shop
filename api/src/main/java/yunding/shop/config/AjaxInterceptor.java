package yunding.shop.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 齐语冰
 */
public class AjaxInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*解决已有自定义token拦截器后的CORS跨域问题*/
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "OPTIONS, Origin, X-Requested-With, Content-Type, Accept, Authorization");
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader("Access-Control-Allow-Credentials","true");

        String authorization = request.getHeader("Authorization");

        String preMethod = "OPTIONS";

        //CORS 预检请求入口(Method = OPTIONS)
        if (preMethod.equals(request.getMethod())) {
            //使预检请求成功
            response.setStatus(200);
            //阻止请求通过拦截器
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
