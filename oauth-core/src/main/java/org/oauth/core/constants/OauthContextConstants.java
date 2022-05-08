package org.oauth.core.constants;

/**
 * @ClassName OauthContextConstants
 * @Author yyl
 * @Date 2022-04-19 22:03:05
 * @Description 上下文请求常量
 * @Version 1.0
 */
public class OauthContextConstants {

    /**
     * 用户池id
     */
    public static final String USER_POOL_ID = "userPoolId";

    /**
     * 用户池密码
     */
    public static final String USER_POOL_SECRET = "userPoolSecret";

    /**
     * 用户池租户id
     */
    public static final String TENANT_ID = "tenantId";

    /**
     * token前缀，要和oauth的配置匹配
     */
    public static final String TOKEN_PREFIX = "Bearer ";
}
