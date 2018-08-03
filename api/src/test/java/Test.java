import com.aliyuncs.exceptions.ClientException;
import yunding.shop.utils.SmsUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            SmsUtil.sendMessaging("18634312616","qwer");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
