package yunding.shop.util;

import org.springframework.web.multipart.MultipartFile;
import yunding.shop.entity.Constant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author 齐语冰
 */
public class FileUtil {

    private static final String AVATAR_URI = "/static/upload/avatar/";
    private static final String GOODS_PIC_URI = "/static/upload/GoodsPic/";
    private static List<String> legalSuffixList = Arrays.asList("PNG","JPG","JPEG");

    public static String saveFile(MultipartFile pic, String realPath) throws Exception {

        //大于 3 MB
        if (pic.getSize() > 3*1024*1024){
            throw new RuntimeException("文件过大");
        }

        // 获取原始文件的后缀
        String originalFilename = pic.getOriginalFilename();
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1);

        //非法后缀
        if (!legalSuffixList.contains(suffix.toUpperCase())){
            throw new RuntimeException("非法后缀");
        }

        // 生成随机文件名
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;

        String fileSaveName = realPath + filename;

        pic.transferTo(new File(fileSaveName));
        return Constant.IP_ADDRESS + AVATAR_URI + filename;
    }

    public static String getAvatarRealPath(HttpServletRequest request){
        return request.getServletContext().getRealPath(AVATAR_URI);
    }

    public static String getGoodsRealPath(HttpServletRequest request){
        return request.getServletContext().getRealPath(GOODS_PIC_URI);
    }
}
