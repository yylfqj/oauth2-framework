package org.oauth.core.exceptions;

/**
 * @ClassName IBaseErrorCode
 * @Author yyl
 * @Date 2021-08-18 13:51
 * @Description IBaseErrorCode
 * @Version 1.0
 */
public interface IBaseErrorCode {

    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getMsg();

}
