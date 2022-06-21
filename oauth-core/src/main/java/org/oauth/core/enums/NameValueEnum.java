package org.oauth.core.enums;

/**
 * @ClassName NameValueEnum
 * @Author yyl
 * @Date 2022-03-01 14:31:13
 * @Description 带有枚举值以及枚举名称的枚举接口(可使用 {@link cn.gongbao.core.utils.EnumUtils} 中的方法)
 * @Version 1.0
 */
public interface NameValueEnum<T> extends ValueEnum<T> {

    /**
     * 获取枚举名称
     * @return
     */
    String getName();

}
