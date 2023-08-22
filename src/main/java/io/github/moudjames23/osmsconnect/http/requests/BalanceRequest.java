package io.github.moudjames23.osmsconnect.http.requests;


import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;

public class BalanceRequest extends BearAuthRequest {

    private final Country country;

    public BalanceRequest(String token, Country country) {
        super(token);
        this.country = country;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUri() {
        return getBaseUrl().concat("/sms/admin/v1/contracts?country=" +this.country.getCode());
    }
}
