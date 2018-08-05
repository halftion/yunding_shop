package yunding.shop.service;

import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;

/**
 * @author ren
 */
public interface PhotoService {
    ServiceResult saveAvatar(Integer userId,MultipartFile pic,String realPath);
}
