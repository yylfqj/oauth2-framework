package org.oauth.core.enums;

/**
 * @ClassName ValueEnum
 * @Author yyl
 * @Date 2022-03-01 14:29:44
 * @Description 带有枚举值的枚举接口(可使用{@link cn.gongbao.core.utils.EnumUtils}中的方法)
 * @Version 1.0
 */
public interface ValueEnum<T> {

    /**
     * 获取枚举编码
     * @return
     */
    T getValue();

}
