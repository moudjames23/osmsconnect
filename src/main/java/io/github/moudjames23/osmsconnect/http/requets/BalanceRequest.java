package io.github.moudjames23.osmsconnect.http.requets;


import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.http.BaseRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.Headers;
import okhttp3.RequestBody;


@RequiredArgsConstructor
public class BalanceRequest extends BaseRequest {

    private final String token;

    private final Country country;

    @Override
    public HttpMethod method() {
        return HttpMethod.GET;
    }

    @Override
    public String uri() {
        return BASE_URL.concat("/sms/admin/v1/contracts?country=" +this.country.getCode());
    }

    @Override
    public Headers headers() {
        return new Headers.Builder()
                .add(AUTHORIZATION, BEARER + this.token)
                .build();
    }

    @Override
    public RequestBody body()    {
        return null;
    }
}
