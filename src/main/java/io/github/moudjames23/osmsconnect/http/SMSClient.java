package io.github.moudjames23.osmsconnect.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.enums.ResponseSuccessCode;
import io.github.moudjames23.osmsconnect.http.requets.AuthorizationRequest;
import io.github.moudjames23.osmsconnect.model.error.AuthorizationError;
import io.github.moudjames23.osmsconnect.model.response.AuthorizationResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

import static io.github.moudjames23.osmsconnect.util.RequestUtils.performRequest;

@Getter
@Setter
public class SMSClient {

    private String token;

    private int expireIn;

    private String clientId;

    private String clientSecret;

    public SMSClient(String clientId, String clientSecret) throws IOException {
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        setupToken();
    }

    public SMSClient(String token) {
        this.token = token;
    }

    private void setupToken() throws IOException {

        AuthorizationRequest request = new AuthorizationRequest(this.clientId, this.clientSecret);

        AuthorizationResponse authorizationResponse = performRequest(request, ResponseSuccessCode.SUCCESS, AuthorizationResponse.class, new AuthorizationError());

        this.token = authorizationResponse.getAccessToken();
        this.expireIn = authorizationResponse.getExpireIn();

    }

    public String getToken() {
        return token;
    }
}
