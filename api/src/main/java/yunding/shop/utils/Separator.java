package yunding.shop.utils;

/**
 * 分隔符工具类
 * @author guo
 */
public class Separator {

    /**
     * 在字符串每个字符间添加百分号作为分隔符
     * @param str 传入待处理字符串
     * @return 处理完成字符串
     * @throws NullPointerException
     */
    public static String percent(String str) throws NullPointerException{
        StringBuffer newStr = null;
        StringBuffer stringBuffer = new StringBuffer(str);
        for(int i = 0 ; i < str.length()*2+1;i+=2){
            newStr = stringBuffer.insert(i,'%');
        }
        return newStr.toString();
    }
}
