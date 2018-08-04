package yunding.shop.web.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yunding.shop.dto.RequestResult;
import yunding.shop.dto.ServiceResult;
import yunding.shop.service.SearchService;

/**
 *在商品和店铺搜索时进行提示
 * @author guo
 */
@RestController
@RequestMapping("/api/hint")
public class HintController {

    @Autowired
    private SearchService searchService;

    /**
     * 根据关键词给出列表五个提示
     * @param keyword 关键词
     * @return 五个提示匹配结果的商品和店铺名
     */
    @RequestMapping(value = "/platform/{keyword}",method = RequestMethod.GET)
    public RequestResult platformHint(@PathVariable("keyword") String keyword){
        try {
            ServiceResult serviceResult = searchService.platformHint(keyword);
            if (serviceResult.isSuccess()) {
                return RequestResult.success(serviceResult.getData());
            } else {
                return RequestResult.failure(serviceResult.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("Controller错误");
        }
    }
}

