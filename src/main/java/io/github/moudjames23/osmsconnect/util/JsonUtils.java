package io.github.moudjames23.osmsconnect.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.moudjames23.osmsconnect.exception.SMSException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    /**
     * this method allows to convert Object to JSON
     * @param obj object to convert
     * @return JSON
     * @throws JsonProcessingException if converting failed
     */
    public static String getJsonStringFromObject(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * this method allows you to convert json to Object
     * @param json json to convert
     * @param clazz class to map
     * @param <T> generic
     * @return Mapped class
     * @throws JsonProcessingException if mapping failed
     */
    public static <T> T getObjectFromJsonString(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }


    /**
     * this method
     * @param value
     * @return
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // Handle encoding exception here
            throw new SMSException("Unsupported value for encoding");
        }
    }


}
