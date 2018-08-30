import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yunding.shop.entity.Goods;

public class Test {
    public static void main(String[] args) {
        try {
/*            ApplicationContext context = new
                    ClassPathXmlApplicationContext("classpath*:spring-redis-test.xml");*/
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
            JedisPool jedisPool = context.getBean(JedisPool.class);
            System.out.println(jedisPool);
            Jedis jedis = jedisPool.getResource();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
