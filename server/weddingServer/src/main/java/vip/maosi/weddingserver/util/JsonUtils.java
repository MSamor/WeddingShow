package vip.maosi.weddingserver.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import vip.maosi.weddingserver.config.CustomSerializerProvider;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializerProvider(new CustomSerializerProvider());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object any) {
        try {
            return objectMapper.writeValueAsString(any);
        } catch (Throwable ignored) {
        }
        return "";
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Throwable ignored) {
        }
        return null;
    }
}
