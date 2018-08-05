package yunding.shop.web.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.PhotoService;
import yunding.shop.utils.FileUtil;
import yunding.shop.utils.UserUtil;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ren
 */

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public RequestResult saveAvatar(@RequestParam MultipartFile pic, HttpServletRequest request){

        try{
            System.out.println("!@#$$##@@@@@！！！！！！！！########"+(pic == null));
            Integer userId = UserUtil.getCurrentUserId(request);
            String realPath = FileUtil.getRealPath(request);

            ServiceResult serviceResult = photoService.saveAvatar(userId,pic,realPath);

            if(serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("头像上传失败");
        }
    }
}
