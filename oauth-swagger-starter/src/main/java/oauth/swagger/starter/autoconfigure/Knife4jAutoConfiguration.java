package oauth.swagger.starter.autoconfigure;

import oauth.swagger.starter.properties.Knife4jProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * @ClassName Knife4jAutoConfiguration
 * @Author yyl
 * @Date 2022-05-10 22:44:03
 * @Description Knife4jAutoConfiguration
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(Knife4jProperties.class)
@ConditionalOnProperty(value = "custom.knife4j.enable", havingValue = "true", matchIfMissing = true)
@Import({
        Swagger3Configuration.class
})
@Profile("!prod")
public class Knife4jAutoConfiguration {
}
