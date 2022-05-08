package oauth.cache.starter.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.cache.starter.aspect.AopUnityCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName CacheAutoConfiguration
 * @Author yyl
 * @Date 2022-04-20 11:31:08
 * @Description CacheAutoConfiguration
 * @Version 1.0
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CacheAutoConfiguration {
    
    private final RedisTemplate<String, Object> redisTemplate;

    @Bean
    public AopUnityCache genAopUnityCache() {
        return new AopUnityCache(redisTemplate);
    }

}
