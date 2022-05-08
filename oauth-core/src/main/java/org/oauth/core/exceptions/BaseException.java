package org.oauth.core.exceptions;

/**
 * @author yunjiang
 * @version V0.1
 * @since 2020年11月03日 14:02
 */
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = -5875371379845226068L;

    /**
     * 具体异常码
     */
    protected String code;

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 返回值对象
     */
    protected Object result;

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String code, String msgFormat, Object... args) {
        this.code = code;
        if(args==null || args.length==0){
            this.msg = msgFormat;
            return;
        }
        this.msg = String.format(msgFormat, args);
    }

    public BaseException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public BaseException newInstance(String msgFormat, Object... args) {
        return new BaseException(code, msgFormat, args);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

}
