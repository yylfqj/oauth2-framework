package oauth.web.starter.autoconfigure;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.web.starter.interceptor.HeaderConvertInterceptor;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WebMvcConfiguration
 * @Author yyl
 * @Date 2022-05-15 22:06:01
 * @Description WebMvcConfiguration
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Bean
    public HeaderConvertInterceptor headerConvertInterceptor() {
        return new HeaderConvertInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AtomicInteger atomicInteger = new AtomicInteger(-99);
        initInterceptorList().forEach(interceptor -> {
            int increment = atomicInteger.getAndIncrement();
            registry.addInterceptor(applicationContext.getBean(interceptor))
                    .addPathPatterns("/**").order(increment);
            log.info("已加载拦截器：[{}]", ClassUtils.getName(interceptor));
        });
    }

    /**
     * 初始化拦截器
     * @return
     */
    private List<Class<? extends HandlerInterceptor>> initInterceptorList() {
        return Lists.newArrayList(HeaderConvertInterceptor.class);
    }

}
