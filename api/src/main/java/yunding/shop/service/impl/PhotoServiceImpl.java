package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.PhotoService;
import yunding.shop.service.UserService;
import yunding.shop.utils.FileUtil;

/**
 * @author ren
 * 上传头像
 */
@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServiceResult saveAvatar(Integer userId, MultipartFile pic, String realPath) {

        try {
            String picture = FileUtil.saveFile(pic,realPath);
            ServiceResult userServiceResult = userService.updateAvatar(userId,picture);
            if(userServiceResult.isSuccess())
            {
                return ServiceResult.success(picture);
            }else {
                //更新头像失败
                return ServiceResult.failure(userServiceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("作品上传失败");
        }
    }
}
