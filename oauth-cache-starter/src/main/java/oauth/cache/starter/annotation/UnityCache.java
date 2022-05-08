package oauth.cache.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UnityCache
 * @Author yyl
 * @Date 2021-04-25 16:10
 * @Description UnityCache 统一缓存注解
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnityCache {

    String name();

    /**
     * 缓存对象的关键标识，使用spel表达式表示，常规增,删,改使用同一个标识字段
     * 选取的key在入参时不能为空，不然会报错
     * @return
     */
    String key() default "";

    /**
     * 缓存时间
     * @return
     */
    long expire() default 20L;

    /**
     * 缓存时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
