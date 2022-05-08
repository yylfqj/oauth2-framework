package oauth.feign.starter.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.feign.starter.properties.FeignProperties;
import org.oauth.core.context.OauthContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName FeignHeaderTransferInterceptor
 * @Author yyl
 * @Date 2022-05-07 15:59:42
 * @Description FeignHeaderTransferInterceptor
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
public class FeignHeaderTransferInterceptor implements RequestInterceptor {

    private final FeignProperties feignProperties;

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.warn("path={}, 未配置FeignConfiguration类", template.path());
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        //指定header传递
        List<String> transferHeaders = feignProperties.getHeaders().getTransfer();
        if (transferHeaders != null && !transferHeaders.isEmpty()) {
            log.info("指定feign的header传递，headers={}", transferHeaders);
            transferHeaders.forEach(headerName -> {
                String headerValue = request.getHeader(headerName);
                template.header(headerName, StringUtils.isEmpty(headerValue) ? OauthContextHolder.get(headerName) : headerValue);
            });
        }
        //未指定header传递
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("未指定feign的header传递，headers={}", headerNames);
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            template.header(headerName, StringUtils.isEmpty(headerValue) ? OauthContextHolder.get(headerName) : headerValue);
        }
    }
}
