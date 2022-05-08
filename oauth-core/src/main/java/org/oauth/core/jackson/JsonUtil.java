package org.oauth.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.oauth.core.exceptions.BaseErrorCode;
import org.oauth.core.exceptions.ServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName JsonUtil
 * @Author yyl
 * @Date 2022-03-07 17:01:58
 * @Description Jackson封装工具类
 * @Version 1.0
 */
@Slf4j
public class JsonUtil {

    public static <T> String toJson(T value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            throw new ServiceException(BaseErrorCode.JSON_TRANSFORM_ERROR);
        }
    }

    public static <T> String toJsonPretty(T value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        try {
            return getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            throw new ServiceException(BaseErrorCode.JSON_TRANSFORM_ERROR);
        }
    }

    public static byte[] toJsonAsBytes(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return null;
        }
        try {
            return getInstance().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new ServiceException(BaseErrorCode.JSON_TRANSFORM_ERROR);
        }
    }

    public static <T> T parse(String content, Class<T> valueType) {
        try {
            return getInstance().readValue(content, valueType);
        } catch (Exception e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parse(String content, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(content, typeReference);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parse(byte[] bytes, Class<T> valueType) {
        try {
            return getInstance().readValue(bytes, valueType);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parse(byte[] bytes, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(bytes, typeReference);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parse(InputStream in, Class<T> valueType) {
        try {
            return getInstance().readValue(in, valueType);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parse(InputStream in, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(in, typeReference);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> List<T> parseArray(String content, Class<T> valueTypeRef) {
        try {
            if (!StringUtils.startsWith(content, "[")) {
                content = "[" + content + "]";
            }

            List<Map<String, Object>> list = getInstance().readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });

            return list.stream().map((map) -> toPojo(map, valueTypeRef)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static Map toMap(String content) {
        try {
            return getInstance().readValue(content, Map.class);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> Map toMap(T val) {
        try {
            String jsonData = getInstance().writeValueAsString(val);
            return getInstance().readValue(jsonData, Map.class);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> Map<String, T> toMap(String content, Class<T> valueTypeRef) {
        try {
            Map<String, Map<String, Object>> map = getInstance().readValue(content, new TypeReference<Map<String, Map<String, Object>>>() {
            });
            Map<String, T> result = new HashMap<>(map.size());
            map.forEach((key, value) -> result.put(key, toPojo(value, valueTypeRef)));
            return result;
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T, E> Map<String, T> toMap(E data, Class<T> valueTypeRef) {
        try {
            Map<String, Map<String, Object>> map = getInstance().readValue(toJson(data), new TypeReference<Map<String, Map<String, Object>>>() {
            });
            Map<String, T> result = new HashMap<>(map.size());
            map.forEach((key, value) -> result.put(key, toPojo(value, valueTypeRef)));
            return result;
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T toPojo(Map fromValue, Class<T> toValueType) {
        return getInstance().convertValue(fromValue, toValueType);
    }

    public static <T> T toPojo(String data, Class<T> toValueType) {
        try {
            return getInstance().readValue(data, toValueType);
        } catch (JsonProcessingException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> T toPojo(JsonNode resultNode, Class<T> toValueType) {
        return getInstance().convertValue(resultNode, toValueType);
    }

    public static JsonNode readTree(String jsonString) {
        try {
            return getInstance().readTree(jsonString);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static JsonNode readTree(InputStream in) {
        try {
            return getInstance().readTree(in);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static JsonNode readTree(byte[] content) {
        try {
            return getInstance().readTree(content);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static JsonNode readTree(JsonParser jsonParser) {
        try {
            return getInstance().readTree(jsonParser);
        } catch (IOException e) {
            throw new ServiceException(BaseErrorCode.JSON_PARSE_ERROR);
        }
    }

    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    public static ObjectMapper newInstance() {
        return new JacksonObjectMapper();
    }

    private static class JacksonHolder {
        private final static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

    public static class JacksonObjectMapper extends ObjectMapper {
        private static final long serialVersionUID = 1L;

        public JacksonObjectMapper() {
            super();
            // 参考BaseConfig
            super.setLocale(Locale.CHINA)
                    .setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                    .findAndRegisterModules()
                    .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature())
                    .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature())
                    .enable(JsonParser.Feature.ALLOW_COMMENTS)//该特性决定parser将是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)//该特性决定parser是否允许单引号来包住属性名称和字符串值
                    .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .getDeserializationConfig()
                    .withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            super.registerModules(new CustomJacksonModule(), new CustomJavaTimeModule());
            super.findAndRegisterModules();
        }

        @Override
        public ObjectMapper copy() {
            return super.copy();
        }
    }

}
