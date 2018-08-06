package yunding.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import yunding.shop.entity.Goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 齐语冰
 */
@Configuration
public class beanConfig {
    @Bean(name = "cartMap")
    public Map<Integer, List<Goods>> cartMap(){
        return new HashMap<>(16);
    }
}
