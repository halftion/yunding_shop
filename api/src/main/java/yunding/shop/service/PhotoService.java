package yunding.shop.service;

import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;

/**
 * @author ren
 */
public interface PhotoService {
    /**
     * 保存头像
     * @param userId 用户id
     * @param pic 用户头像文件
     * @param realPath 保存路径
     * @return 保存后的头像地址
     */
    ServiceResult saveAvatar(Integer userId,MultipartFile pic,String realPath);
}
