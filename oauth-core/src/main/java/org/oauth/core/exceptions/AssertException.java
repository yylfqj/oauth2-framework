package org.oauth.core.exceptions;

/**
 * @ClassName AssertException
 * @Author yyl
 * @Date 2022-06-21 22:20:03
 * @Description AssertException
 * @Version 1.0
 */
public class AssertException extends BaseException{

    private static final long serialVersionUID = -7506434888400271000L;

    public AssertException(Throwable cause) {
        super(cause);
    }

    public AssertException(String errorMessage) {
        super(BaseErrorCode.INVALID_PARAM.getCode(), errorMessage);
    }

    public AssertException(String errorMessage, Throwable cause) {
        super(BaseErrorCode.INVALID_PARAM.getCode(), errorMessage, cause);
    }

    public AssertException(final String format, Object... args) {
        super(BaseErrorCode.INVALID_PARAM.getCode(), format, args);
    }

    @Override
    public String toString() {
        return "AssertException [message=" + getMessage() + ", code=" + getCode() + "]";
    }
}
