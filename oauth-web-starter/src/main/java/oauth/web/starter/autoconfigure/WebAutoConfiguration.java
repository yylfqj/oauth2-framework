package oauth.web.starter.autoconfigure;

import oauth.web.starter.properties.WebProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName WebAutoConfiguration
 * @Author yyl
 * @Date 2022-05-15 22:04:51
 * @Description WebAutoConfiguration
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(WebProperties.class)
@ConditionalOnProperty(value = "custom.web.enable", havingValue = "true", matchIfMissing = true)
@Import({
        WebMvcConfiguration.class,
        SpringBootRunConfiguration.class
})
public class WebAutoConfiguration {
}
