package org.oauth.core.utils;

import java.text.MessageFormat;

/**
 * @ClassName StringUtils
 * @Author yyl
 * @Date 2022-02-22 16:55:38
 * @Description String类型数据处理类型包
 * @Version 1.0
 */
public final class StringUtils {

    /**
     * 格式化消息；格式内容为空 返回原值
     *
     * @param val
     * @param args
     * @return
     */
    public static String msgFormat(String val, Object... args) {
        String t = MessageFormat.format(val, args);
        return org.apache.commons.lang3.StringUtils.isEmpty(t) ? val : t;
    }

}
