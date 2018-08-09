package yunding.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.GoodsService;
import yunding.shop.service.PhotoService;
import yunding.shop.service.ShopService;
import yunding.shop.service.UserService;
import yunding.shop.util.FileUtil;

/**
 * 上传头像
 * @author ren
 * @author guo
 */
@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopService shopService;

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

    @Override
    public ServiceResult saveGoodsPhoto(Integer userId, Integer goodsId, MultipartFile pic, String realPath) {
        try {
            String picture = FileUtil.saveFile(pic,realPath);

            ServiceResult sr1 = shopService.selectShopIdByUserId(userId);
            if (!sr1.isSuccess()) {
                //获取店铺Id失败
                return ServiceResult.failure(sr1.getMessage());
            }
            Integer shopId1 = (Integer) sr1.getData();

            ServiceResult sr2 = goodsService.selectShopIdByGoodsId(goodsId);
            if (!sr2.isSuccess()){
                //获取店铺Id失败
                return ServiceResult.failure(sr2.getMessage());
            }
            Integer shopId2 = (Integer) sr2.getData();

            if(!shopId1.equals(shopId2)){
                return ServiceResult.failure("用户信息不匹配");
            }
            ServiceResult sr = goodsService.saveGoodsPhoto(goodsId,picture);
            if(sr.isSuccess())
            {
                return ServiceResult.success(picture);
            }else {
                //保存图片失败
                return ServiceResult.failure(sr.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("作品上传失败");
        }
    }
}
