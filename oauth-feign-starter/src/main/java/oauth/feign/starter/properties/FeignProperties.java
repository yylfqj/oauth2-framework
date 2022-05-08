package oauth.feign.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * @ClassName FeignProperties
 * @Author yyl
 * @Date 2022-05-08 16:52:33
 * @Description FeignProperties
 * @Version 1.0
 */
@Data
@ConfigurationProperties("custom.feign")
public class FeignProperties {

    /**
     * 是否开启指定feign配置
     */
    private boolean enabled = true;

    @NestedConfigurationProperty
    private FeignHeader headers = new FeignHeader();

    @Data
    public static class FeignHeader {

        /**
         * 是否开启feign header处理
         */
        private boolean enabled = true;

        /**
         * 需要传播的header头
         */
        private List<String> transfer;
    }

}
