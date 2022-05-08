package oauth.feign.starter.autoconfigure;

import oauth.feign.starter.interceptor.FeignHeaderTransferInterceptor;
import oauth.feign.starter.properties.FeignProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName FeignHeaderConfiguration
 * @Author yyl
 * @Date 2022-05-08 16:09:37
 * @Description FeignHeaderConfiguration
 * @Version 1.0
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(
        value = "custom.feign.headers.enable",
        havingValue = "true",
        matchIfMissing = true)
public class FeignHeaderConfiguration {

    @Autowired
    private FeignProperties feignProperties;

    @Bean
    public FeignHeaderTransferInterceptor feignHeaderTransferInterceptor() {
        return new FeignHeaderTransferInterceptor(feignProperties);
    }

}
