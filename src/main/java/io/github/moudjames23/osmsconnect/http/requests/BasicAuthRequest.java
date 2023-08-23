package io.github.moudjames23.osmsconnect.http.requests;

import io.github.moudjames23.osmsconnect.exception.SMSException;
import lombok.RequiredArgsConstructor;
import okhttp3.Headers;

import java.util.Base64;

@RequiredArgsConstructor
public class BasicAuthRequest implements HttpRequest {
    private static final String BASIC = "Basic ";
    private final String clientId;
    private final String clientSecret;

    @Override
    public Headers getHeaders() {
        try {
            String credentials = this.clientId + ":" + this.clientSecret;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            return new Headers.Builder()
                .add(getAuthorization(), BASIC + encodedCredentials)
                .build();
        } catch (Exception e) {
            // Handle the exception here, e.g., log it or throw a custom exception
            throw new SMSException("Failed to encode credentials: " + e.getMessage(), e);
        }
    }
}
