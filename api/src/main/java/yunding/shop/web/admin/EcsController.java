package yunding.shop.web.admin;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yunding.shop.config.CountSessionListener;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.EcsService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务器
 * @author guo
 */
@RestController
@RequestMapping("/api/ecs")
public class EcsController {

    @Autowired
    private EcsService ecsService;

    /**
     * 获取服务器信息
     * @param request request对象
     * @return 服务器信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public RequestResult ecsController(HttpServletRequest request){
        try{
            ServiceResult sr = ecsService.ecsMessage(request);
            if(sr.isSuccess()){
                return RequestResult.success(sr.getData());
            }else {
                return RequestResult.failure(sr.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("获取服务器端异常");
        }
    }
}
