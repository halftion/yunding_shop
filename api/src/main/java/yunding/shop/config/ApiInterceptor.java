package yunding.shop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import yunding.shop.util.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 齐语冰
 */
public class ApiInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER= "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        if(null == authorization){
            response.setStatus(401);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print("{\"code\": \"401\", \"error\": \"unauthorized\", \"message\": \"authorization required\"}");
            response.getWriter().flush();
            return false;
        }else {
            String token = authorization.replace(TOKEN_HEADER, "");

            try {
                Claims claims = JwtUtil.verifyToken(token);
                request.setAttribute("currentUserId", claims.get("userId"));
            } catch (ExpiredJwtException e) {
                response.reset();
                PrintWriter pw = response.getWriter();
                response.setStatus(404);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                pw.print("{\"code\": \"4042\", \"error\": \"ExpiredToken\", \"message\": \"" + e.getMessage() + "\"}");
                pw.flush();
            } catch (UnsupportedJwtException | MalformedJwtException e) {
                response.reset();
                PrintWriter pw = response.getWriter();
                response.setStatus(404);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                pw.print("{\"code\": \"4041\", \"error\": \"IllegalToken\", \"message\": \"" + e.getMessage() + "\"}");
                pw.flush();
            }
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
