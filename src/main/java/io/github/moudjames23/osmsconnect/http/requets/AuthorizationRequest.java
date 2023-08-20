package io.github.moudjames23.osmsconnect.http.requets;


import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.http.BaseRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.RequestBody;

import java.util.Base64;

@Setter
@Getter
@RequiredArgsConstructor
public class AuthorizationRequest extends BaseRequest {

    private final String clientId;

    private final String clientSecret;

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return BASE_URL.concat("/oauth/v3/token");
    }

    @Override
    public Headers headers() {

        String credentials = this.clientId + ":" + this.clientSecret;

        return new Headers.Builder()
                .add(AUTHORIZATION, BASIC + Base64.getEncoder().encodeToString(credentials.getBytes()))
                .build();
    }

    @Override
    public RequestBody body() {

        return new FormBody.Builder()
                .add(GRANT_TYPE, CLIENT_CREDENTIALS)
                .build();

    }


}
