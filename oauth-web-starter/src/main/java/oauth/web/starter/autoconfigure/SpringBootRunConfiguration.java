package oauth.web.starter.autoconfigure;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.url.UrlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @ClassName SpringBootRunConfiguration
 * @Author yyl
 * @Date 2022-05-15 22:27:25
 * @Description SpringBootRunConfiguration
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class SpringBootRunConfiguration implements ApplicationRunner, Ordered {

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment env = applicationContext.getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        int port = Convert.toInt(env.getProperty("server.port"), 8080);
        String url = UrlBuilder.create()
                .setHost(hostAddress)
                .setPort(port).build();
        log.info("\n___________________________________________________________________\n\t" +
                        "java_version: {} \n\t" +
                        "operating_system: {}:{}:{} \n\t" +
                        "application_name: {} \n\t" +
                        "swagger_doc：{}doc.html\n\t" +
                        "health_check：{}actuator/health\n" +
                        "___________________________________________________________________",
                System.getProperty("java.version"),
                System.getProperty("os.name"),
                System.getProperty("os.arch"),
                System.getProperty("os.version"),
                applicationName,
                url,
                url);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
