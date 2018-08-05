package yunding.shop.web.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.PhotoService;
import yunding.shop.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ren
 */

@RequestMapping("/api/user")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public RequestResult saveAvatar(MultipartFile pic, HttpServletRequest request){

        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            String realpath=request.getServletContext().getRealPath("/static/upload");
            ServiceResult serviceResult=photoService.saveAvatar(userId,pic,realpath);
            if(serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("头像上传失败");
        }
    }
}
