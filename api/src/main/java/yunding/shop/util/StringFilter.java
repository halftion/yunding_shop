package yunding.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对于传入String关键词的过滤器
 * @author guo
 */
public class StringFilter {

    /**
     * 去掉字符串中的特殊字符
     * @param str 待处理字符串
     * @return 成功去除特殊字符的字符串
     */
    public static String searchKeyword(String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String newStr = m.replaceAll("").trim();
        return newStr;
    }
}
