package oauth.web.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName WebProperties
 * @Author yyl
 * @Date 2022-05-15 22:24:14
 * @Description WebProperties
 * @Version 1.0
 */
@ConfigurationProperties("custom.web")
public class WebProperties {

    /**
     * 是否开启自定义web配置
     */
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
