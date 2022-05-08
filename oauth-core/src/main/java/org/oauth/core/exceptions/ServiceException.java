package org.oauth.core.exceptions;

import org.oauth.core.utils.StringUtils;

/**
 * @author yunjiang
 * @version V0.1
 * @since 2020年11月03日 14:04
 */
public class ServiceException extends BaseException{

    public ServiceException() {

    }

    public ServiceException(String msg) {
        super("999999", msg);
    }

    public ServiceException(String code, String msg) {
        super(code, msg);
    }

    public ServiceException(BaseErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMsg());
    }

    public ServiceException(BaseErrorCode errorCode, Object... args) {
        super(errorCode.getCode(), StringUtils.msgFormat(errorCode.getMsg(), args));
    }

    /**
     * 覆盖fillInStackTrace，Service异常不需要打印
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
