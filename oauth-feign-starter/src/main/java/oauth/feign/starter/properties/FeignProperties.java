package oauth.feign.starter.properties;

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
@ConfigurationProperties("custom.feign")
public class FeignProperties {

    /**
     * 是否开启自定义feign配置
     */
    private boolean enabled = true;

    @NestedConfigurationProperty
    private FeignHeader headers = new FeignHeader();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public FeignHeader getHeaders() {
        return headers;
    }

    public void setHeaders(FeignHeader headers) {
        this.headers = headers;
    }

    /**
     * feign的header处理
     */
    public static class FeignHeader {

        /**
         * 是否开启feign header处理
         */
        private boolean enabled = true;

        /**
         * 需要传播的header头
         */
        private List<String> transfer;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<String> getTransfer() {
            return transfer;
        }

        public void setTransfer(List<String> transfer) {
            this.transfer = transfer;
        }
    }

}
