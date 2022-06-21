package org.oauth.core.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName EnumUtils
 * @Author yyl
 * @Date 2022-03-01 14:20:45
 * @Description 使用该枚举工具类需要指定的枚举实现 {@link ValueEnum} OR {@link NameValueEnum}接口
 * @Version 1.0
 */
public final class EnumUtils {

    /**
     * 判断枚举值是否存在于指定枚举数组中
     * @param enums
     * @param value
     * @param <T>
     * @return
     */
    public static <T> boolean isExist(ValueEnum<T>[] enums, T value) {
        if (value == null) {
            return false;
        }
        for (ValueEnum<T> e : enums) {
            if (value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断枚举值是否存与指定枚举类中
     * @param enumClass
     * @param value
     * @param <E>
     * @param <V>
     * @return
     */
    public static <E extends Enum<? extends ValueEnum<V>>, V> boolean isExist(Class<E> enumClass, V value) {
        for (Enum<? extends ValueEnum<V>> e : enumClass.getEnumConstants()) {
            if (((ValueEnum<V>) e).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举值获取其对应的名字
     * @param enums
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String getNameByValue(NameValueEnum<T>[] enums, T value) {
        if (value == null) {
            return null;
        }
        for (NameValueEnum<T> e : enums) {
            if (value.equals(e.getValue())) {
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取对应的枚举值
     * @param enums
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getValueByName(NameValueEnum<T>[] enums, String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (NameValueEnum<T> e : enums) {
            if (name.equals(e.getName())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 根据枚举值获取对应的枚举对象
     * @param enums
     * @param value
     * @param <E>
     * @param <V>
     * @return
     */
    public static <E extends Enum<? extends ValueEnum<V>>, V> E getEnumByValue(E[] enums, V value) {
        for (E e : enums) {
            if (((ValueEnum<V>) e).getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据枚举值获取对应的枚举对象
     * @param enumClass
     * @param value
     * @param <E>
     * @param <V>
     * @return
     */
    public static <E extends Enum<? extends ValueEnum<V>>, V> E getEnumByValue(Class<E> enumClass, V value) {
        return getEnumByValue(enumClass.getEnumConstants(), value);
    }

}
