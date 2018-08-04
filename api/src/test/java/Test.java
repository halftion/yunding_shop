import com.aliyuncs.exceptions.ClientException;
import yunding.shop.utils.SmsUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Goods goods = new Goods();
        Service service = new Service();
        service.setId(goods);
        System.out.println(goods.getId());
    }
}
