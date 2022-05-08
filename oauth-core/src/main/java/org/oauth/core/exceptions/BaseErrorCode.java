package org.oauth.core.exceptions;

import lombok.Getter;

/**
 * @author yyl
 * @version V0.1
 * @since 2020年11月03日 14:10
 */
@Getter
public enum BaseErrorCode implements IBaseErrorCode {

    /**
     * 请求处理成功
     **/
    REQ_SUCCESS("00000", "请求处理成功"),
    NOT_FOUND_ERROR("00005", "系统错误"),
    AUTHENTICATION_ERROR("000010", "认证失败"),
    UNAUTHORIZED_EXCEPTION("000011", "未经授权"),
    FORBIDDEN_EXCEPTION("000012", "无权限"),
    METHOD_NOT_ALLOWED("000013", "无效的请求方法"),
    JSON_PARSE_ERROR("000014", "JSON解析异常"),
    JSON_TRANSFORM_ERROR("000015", "JSON转换异常"),
    SMS_ERROR("000016", "短信发送异常"),


    NO_SUCH_CLIENT("N0001","客户端信息不存在"),
    NOT_FOUND_ACCOUNT("N0002", "账户信息不存在"),
    INVALID_TOKEN("N0003", "无效的令牌"),
    NO_SUCH_USER("N0004", "用户信息不存在"),
    INVALID_GRANT("N0005", "无效的授权信息"),
    INVALID_SCOPE("N0006", "无效的授权范围"),
    INVALID_REQUEST("N0007", "无效的请求"),
    REDIRECT_MISMATCH("N0008", "重定向请求失败"),
    UNSUPPORTED_GRANT_TYPE("N0009", "不支持的授权类型"),
    UNSUPPORTED_RESPONSE("N0010", "不支持的返回值类型"),
    ACCESS_DENIED("N0011", "拒绝访问，权限不足"),
    NO_SUCH_TENANT("N0012", "租户信息不存在"),




    INVALID_PARAM("M0001", "无效的请求参数"),
    REQ_PARAM_EXIST("M0002", "请求{0}已存在"),
    REQ_PARAM_NOT_EXIST("M0002", "请求{0}不存在"),
    REQ_PARAM_ERROR("M0002", "{0}"),
    REQ_METHOD_NOT_SUPPORT("M0003", "http请求方法不支持"),
    NO_SUCH_POOL("M0004", "用户池信息不存在"),
    ;

    /**
     * 错误码
     */
    private final String code;
    /**
     * 错误描述
     */
    private final String msg;

    BaseErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
