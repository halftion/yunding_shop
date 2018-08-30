package yunding.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 齐语冰
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisPoolConfig jedisPoolConfig(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);

        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(jedisPoolConfig(),"qiyubing.cn",6379,30000,"Yubing.0616",0,false);
    }
}
