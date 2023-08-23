package io.github.moudjames23.osmsconnect.http.requests;


import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;

public class OrderHistoryRequest extends BearAuthRequest {

    private final Country country;

    public OrderHistoryRequest(String token, Country country) {
        super(token);
        this.country = country;
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUri() {
        return getBaseUrl().concat("/sms/admin/v1/purchaseorders?country=".concat(country.getCode()));
    }
}
