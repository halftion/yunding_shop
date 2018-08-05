package yunding.shop.web.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @ResponseBody
    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public RequestResult saveAvatar(MultipartFile pic, HttpServletRequest request){

        try{
            System.out.println("!@#$$##@@@@@！！！！！！！！########"+pic.getOriginalFilename());
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
