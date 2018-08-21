package yunding.shop.service;

import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;

/**
 * 上传图片
 * @author ren
 * @author guo
 */
public interface PhotoService {
    /**
     * 保存头像的url
     * @param userId 用户id
     * @param url url
     * @return 是否成功
     */
    ServiceResult saveAvatarUrl(Integer userId, String url);

    /**
     * 保存头像
     * @param userId 用户id
     * @param pic 用户头像文件
     * @param realPath 保存路径
     * @return 保存后的头像地址
     */
    /*ServiceResult saveAvatar(Integer userId,MultipartFile pic,String realPath);*/

    /**
     * 保存商品图片
     * @param userId 用户Id
     * @param goodsId 商品Id
     * @param pic 用户头像文件
     * @param realPath 保存路径
     * @return 保存后的头像地址
     */
    ServiceResult saveGoodsPhoto(Integer userId, Integer goodsId, MultipartFile pic, String realPath);
}
