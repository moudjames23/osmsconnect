package io.github.moudjames23.osmsconnect.http.requests;


import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import lombok.Getter;
import lombok.Setter;
import okhttp3.FormBody;
import okhttp3.RequestBody;


@Setter
@Getter
public class AuthorizationRequest extends BasicAuthRequest implements HttpRequest {

    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    public AuthorizationRequest(String clientId, String clientSecret) {
        super(clientId, clientSecret);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUri() {
        return getBaseUrl().concat("/oauth/v3/token");
    }

    @Override
    public RequestBody getBody() {
        return new FormBody.Builder()
                .add(GRANT_TYPE, CLIENT_CREDENTIALS)
                .build();

    }
}
