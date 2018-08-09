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
 * 上传图片
 * @author ren
 */

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    /**
     * 上传头像
     * @param pic 图片
     * @param request request对象
     */
    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public RequestResult saveAvatar(@RequestParam("pic") MultipartFile pic, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            String realPath = FileUtil.getAvatarRealPath(request);
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

    /**
     * 上传/修改 商品图片
     * @param goodsId 商品Id
     * @param pic 图片
     * @param request request 对象
     * @return
     */
    @RequestMapping(value = "/goods/{goodsId}",method = RequestMethod.POST)
    public RequestResult saveGoodsPhoto(@PathVariable("goodsId")Integer goodsId,
            @RequestParam("pic") MultipartFile pic, HttpServletRequest request){
        try{
            Integer userId = UserUtil.getCurrentUserId(request);
            String realPath = FileUtil.getAvatarRealPath(request);
            ServiceResult serviceResult = photoService.saveGoodsPhoto(userId, goodsId, pic,realPath);
            if(serviceResult.isSuccess()){
                return RequestResult.success(serviceResult.getData());
            }else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
          return RequestResult.failure("商品图片上传失败");
        }
    }
}
