package yunding.shop.service;

import yunding.shop.dto.ServiceResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guo
 */
public interface EcsService {

    /**
     * 获取服务器信息
     * @param request request参数
     * @return 服务器信息
     */
    ServiceResult ecsMessage(HttpServletRequest request);
}
