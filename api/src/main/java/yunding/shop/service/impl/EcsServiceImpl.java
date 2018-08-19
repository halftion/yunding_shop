package yunding.shop.service.impl;

import org.springframework.stereotype.Service;
import yunding.shop.dto.ServiceResult;
import yunding.shop.entity.EcsInfo;
import yunding.shop.service.EcsService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author guo
 */
@Service
public class EcsServiceImpl implements EcsService {


    @Override
    public ServiceResult ecsMessage(HttpServletRequest request) {
        try {
            EcsInfo ecsInfo = new EcsInfo();
            ecsInfo.setEcsIP(request.getLocalAddr());
            ecsInfo.setEcsPort(request.getLocalPort());
            ecsInfo.setDate(new Date());
            HttpSession session = request.getSession();
            ServletContext servletContext = session.getServletContext();
            Integer integer = (Integer)servletContext.getAttribute("onLineCount");
            ecsInfo.setSessionNum(integer);
            return ServiceResult.success(ecsInfo);
        } catch (Exception e) {
            return ServiceResult.failure("获取服务器信息异常");
        }
    }
}
