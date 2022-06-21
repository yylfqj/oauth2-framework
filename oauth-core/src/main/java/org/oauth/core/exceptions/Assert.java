package org.oauth.core.exceptions;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @ClassName Assert
 * @Author yyl
 * @Date 2022-06-21 21:35:41
 * @Description 常用断言类
 * @Version 1.0
 */
public class Assert {

    /**
     * 断言判断为空对象
     * @param object
     * @param messageTemplate
     * @param messages
     */
    public static void isNull(@Nullable Object object, String messageTemplate, Object... messages) {
        isNull(object, () -> new AssertException(messageTemplate, messages));
    }

    /**
     * 断言判断为空对象基础方法
     * @param object
     * @param messageSupplier
     * @param <S>
     * @throws S
     */
    public static <S extends Throwable> void isNull(@Nullable Object object, Supplier<S> messageSupplier) throws S {
        if (object != null) {
            throw nullSafeGet(messageSupplier);
        }
    }

    /**
     * 断言判断不为空对象
     * @param object
     * @param messageTemplate
     * @param messages
     */
    public static void notNull(@Nullable Object object, String messageTemplate, Object... messages) {
        notNull(object, () -> new AssertException(messageTemplate, messages));
    }

    /**
     * 断言判断不为空对象基础方法
     * @param object
     * @param messageSupplier
     * @param <S>
     * @throws S
     */
    public static <S extends Throwable> void notNull(@Nullable Object object, Supplier<S> messageSupplier) throws S {
        if (object == null) {
            throw nullSafeGet(messageSupplier);
        }
    }

    /**
     * 断言判断不为空集合
     * @param collection
     * @param messageTemplate
     * @param messages
     */
    public static void notEmpty(@Nullable Collection<?> collection, String messageTemplate, Object... messages) {
        notEmpty(collection, () -> new AssertException(messageTemplate, messages));
    }

    /**
     * 断言判断不为空集合基础方法
     * @param collection
     * @param messageSupplier
     * @param <S>
     * @throws S
     */
    public static <S extends Throwable> void notEmpty(@Nullable Collection<?> collection, Supplier<S> messageSupplier) throws S {
        if (collection == null || collection.isEmpty()) {
            throw nullSafeGet(messageSupplier);
        }
    }

    /**
     * 断言判断不为空数组
     * @param array
     * @param messageTemplate
     * @param messages
     */
    public static void notEmpty(@Nullable Object[] array, String messageTemplate, Object... messages) {
        notEmpty(array, () -> new AssertException(messageTemplate, messages));
    }

    /**
     * 断言判断不为空数组基础方法
     * @param array
     * @param messageSupplier
     * @param <S>
     * @throws S
     */
    public static <S extends Throwable> void notEmpty(@Nullable Object[] array, Supplier<S> messageSupplier) throws S {
        if (array == null || array.length == 0) {
            throw nullSafeGet(messageSupplier);
        }
    }

    /**
     * 断言判断不为空map集合
     * @param map
     * @param messageTemplate
     * @param messages
     */
    public static void notEmpty(@Nullable Map<?, ?> map, String messageTemplate, Object... messages) {
        notEmpty(map, () -> new AssertException(messageTemplate, messages));
    }

    /**
     * 断言判断不为空map集合基础方法
     * @param map
     * @param messageSupplier
     * @param <S>
     * @throws S
     */
    public static <S extends Throwable> void notEmpty(@Nullable Map<?, ?> map, Supplier<S> messageSupplier) throws S {
        if (map == null || map.isEmpty()) {
            throw nullSafeGet(messageSupplier);
        }
    }

    /**
     * 安全获取函数编程的错误消息
     * @param messageSupplier
     * @return
     */
    private static <S extends Throwable> S nullSafeGet(@Nullable Supplier<S> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }
}
