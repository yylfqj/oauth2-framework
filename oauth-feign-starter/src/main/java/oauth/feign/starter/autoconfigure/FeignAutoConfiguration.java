package oauth.feign.starter.autoconfigure;

import oauth.feign.starter.properties.FeignProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName FeignAutoConfiguration[
 * @Author yyl
 * @Date 2022-05-08 16:05:45
 * @Description FeignAutoConfiguration
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(FeignProperties.class)
@ConditionalOnProperty(value = "custom.feign.enable", havingValue = "true", matchIfMissing = true)
@Import({
        FeignHeaderConfiguration.class
})
public class FeignAutoConfiguration {

}
