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
     * Converts an object to JSON.
     *
     * @param obj The object to convert.
     * @return JSON representation of the object.
     * @throws JsonProcessingException if converting the object to JSON fails.
     */
    public static String getJsonStringFromObject(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Converts JSON to an object of the specified class.
     *
     * @param json  The JSON to convert.
     * @param clazz The class to map the JSON to.
     * @param <T>   The generic type.
     * @return An instance of the specified class with data from the JSON.
     * @throws JsonProcessingException if mapping the JSON to the object fails.
     */
    public static <T> T getObjectFromJsonString(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * URL encodes a string using UTF-8 encoding.
     *
     * @param value The string to be URL-encoded.
     * @return URL-encoded string.
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
