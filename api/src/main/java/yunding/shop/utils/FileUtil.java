package yunding.shop.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @author 齐语冰
 */
public class FileUtil {

    public static String saveFile(MultipartFile pic, String realPath) throws Exception {
        // 获取原始文件的后缀
        String originalFilename = pic.getOriginalFilename();
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1);

        // 生成随机文件名
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;

        String fileSaveName = realPath + "/" + filename;
        pic.transferTo(new File(fileSaveName));
        return "http://127.0.0.1:8080/static/upload/" + filename;
    }

    public static String getRealPath(HttpServletRequest request){
        return request.getServletContext().getRealPath("/static/upload");
    }
}
