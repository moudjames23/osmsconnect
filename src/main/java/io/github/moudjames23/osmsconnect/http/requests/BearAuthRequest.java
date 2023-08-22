package io.github.moudjames23.osmsconnect.http.requests;

import lombok.*;
import okhttp3.*;

@RequiredArgsConstructor
public class BearAuthRequest implements HttpRequest {
    private static final String BEARER = "Bearer ";
    private final String token;

    @Override
    public Headers getHeaders() {
        return new Headers.Builder()
            .add(getAuthorization(), BEARER.concat(this.token))
            .build();
    }
}
