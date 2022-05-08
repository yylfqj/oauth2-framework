package oauth.cache.starter.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import oauth.cache.starter.annotation.UnityCache;
import oauth.cache.starter.utils.SpelUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AopUnityCache
 * @Author yyl
 * @Date 2021-04-25 16:36
 * @Description AopUnityCache
 * @Version 1.0
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class AopUnityCache {

    private final RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(oauth.cache.starter.annotation.UnityCache)")
    public void cut(){

    }

    @Around("cut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //返回结果集
        Object result = null;
        //获取注解的方法信息
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取方法的返回值类型
        Type type = method.getGenericReturnType();
        //如果是void类型，不查找缓存，直接执行方法
        if (void.class.getName().equals(type.getTypeName())){
            joinPoint.proceed();
        } else {
            result = executeSelect(joinPoint, method);
        }
        return result;
    }

    /**
     * 返回查询结果
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object executeSelect(ProceedingJoinPoint joinPoint, Method method) throws Throwable {
        //获取注解参数
        UnityCache annotation = method.getAnnotation(UnityCache.class);
        //缓存标识前缀
        String name = annotation.name();
        //唯一标识字段
        String key = annotation.key();
        //过期时间
        long expire = annotation.expire();
        //过期时间单位
        TimeUnit timeUnit = annotation.timeUnit();

        Object result;
        //获取方法参数对象值
        Object[] param = joinPoint.getArgs();

        //根据spel表达式获取入参的key值
        Object valueBySpEL = SpelUtil.getValueBySpEL(key, param, method);
        //如果该唯一键的值不为null，获取redis中的值，否则获取数据库的值
        if (valueBySpEL != null) {
            String redisKey = name + ":" + valueBySpEL;
            result = redisTemplate.opsForValue().get(redisKey);
            //redis中为空，获取数据库的值
            if (result == null) {
                result = joinPoint.proceed();
                if (result != null) {
                    redisTemplate.opsForValue().set(redisKey, result, expire, TimeUnit.MINUTES);
                }
            }
        } else {
            log.error("缓存失效，缓存的key值没有获取到值，name:{};key:{};", name, key);
            result = joinPoint.proceed();
        }

        return result;
    }

}
