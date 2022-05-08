package org.oauth.core.constants;

/**
 * @ClassName OauthRequestHeaderConstants
 * @Author yyl
 * @Date 2022-04-19 22:10:00
 * @Description 上下文请求的自定义header常量
 * @Version 1.0
 */
public class OauthRequestHeaderConstants {

    /**
     * 用户池id
     */
    public static final String POOL_ID = "x-oauth-pool-id";

    /**
     * 客户端id
     */
    public static final String CLIENT_ID = "x-oauth-client-id";

    /**
     * 租户id
     */
    public static final String TENANT_ID = "x-oauth-tenant-id";

    /**
     * token信息
     */
    public static final String TOKEN = "Authorization";

}
