package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.PhotoService;
import yunding.shop.service.UserService;

import java.io.File;
import java.util.UUID;

/**
 * @author ren
 * 上传头像
 */
@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    UserService userService;

    @Override
    public ServiceResult saveAvatar(Integer userId,MultipartFile pic, String realPath) {
        try {
            String picture=saveFile(pic,realPath);
            if(userService.updateAvatar(userId,picture).isSuccess())
            {
                return ServiceResult.success();
            }else {
                return ServiceResult.failure("图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("作品上传失败");
        }
    }



    private String saveFile(MultipartFile pic,String realPath) throws Exception{
        // 获取原始文件的后缀
        String originalFilename=pic.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        //生成随机文件名
        String uuid= UUID.randomUUID().toString().toLowerCase().replace("-","");
        String filename=uuid+"."+suffix;
        String fileSaveName=realPath+"/"+filename;
        pic.transferTo(new File(fileSaveName));
        return "http://127.0.0.1:8080/static/upload"+filename;
    }
}
