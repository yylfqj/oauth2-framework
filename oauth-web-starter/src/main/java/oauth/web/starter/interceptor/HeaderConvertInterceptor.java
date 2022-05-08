package oauth.web.starter.interceptor;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.oauth.core.constants.OauthContextConstants;
import org.oauth.core.constants.OauthRequestHeaderConstants;
import org.oauth.core.context.OauthContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName HeaderConvertInterceptor
 * @Author yyl
 * @Date 2022-04-19 22:54:27
 * @Description 请求头拦截转换为threadLocal
 * @Version 1.0
 */
@Slf4j
public class HeaderConvertInterceptor implements HandlerInterceptor {

    private static final List<String> THREAD_LOCAL_ATTR = Lists.newArrayList(
            OauthRequestHeaderConstants.TENANT_ID,
            OauthRequestHeaderConstants.POOL_ID,
            OauthRequestHeaderConstants.CLIENT_ID,
            OauthRequestHeaderConstants.TOKEN
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //数据存储到threadLocal
        THREAD_LOCAL_ATTR.forEach(headerName -> {
            if (headerName.equals(OauthRequestHeaderConstants.TOKEN)) {
                String token = request.getHeader(headerName);
                OauthContextHolder.set(headerName, StringUtils.removeStartIgnoreCase(token, OauthContextConstants.TOKEN_PREFIX));
            } else {
                OauthContextHolder.set(headerName, request.getHeader(headerName));
            }
        });
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        OauthContextHolder.remove();
    }
}
