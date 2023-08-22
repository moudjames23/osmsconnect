package io.github.moudjames23.osmsconnect.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.response.AuthorizationResponse;
import io.github.moudjames23.osmsconnect.model.response.BalanceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {


    @Test
    void itShouldConvertObjectToJson() throws JsonProcessingException {
        // Given
        SMSRequest smsRequest = SMSRequest.builder()
                .to("224620000000")
                .from(Country.GUINEA)
                .message("Hello World !")
                .senderName("OSMSConnect")
                .build();

        // When
        String excepted = JsonUtils.getJsonStringFromObject(smsRequest);

        // Then
        assertTrue(excepted.contains(smsRequest.getMessage()));
    }

    @Test
    void itShouldConvertJsonToObject() throws JsonProcessingException {
        // Given
        String data = "{\"token_type\": \"Bearer\", \"access_token\": \"xxxxxxxxxxx\",\"expires_in\": \"3600\"}";

        // When
        AuthorizationResponse authorizationResponse = JsonUtils.getObjectFromJsonString(data, AuthorizationResponse.class);

        // Then
        assertEquals(3600, authorizationResponse.getExpireIn());
        assertEquals("Bearer", authorizationResponse.getTokenType() );
        assertEquals("xxxxxxxxxxx", authorizationResponse.getAccessToken());

    }

    @Test
    void itShouldNotConvertJsonToObject() {
        // Given
        String data = "{\"token_type\": \"Bearer\", \"access_token\": \"xxxxxxxxxxx\",\"expires_in\": \"3600\"}";

        // When
        // Then
       assertThrows(JsonProcessingException.class, () -> {
           JsonUtils.getObjectFromJsonString(data, BalanceResponse.class);
       });
    }
}
