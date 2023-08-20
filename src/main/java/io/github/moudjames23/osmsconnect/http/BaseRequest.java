package io.github.moudjames23.osmsconnect.http;


import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.model.HttpResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public abstract class BaseRequest {

    protected static final  String BASE_URL = "https://api.orange.com";

    protected static final String CONTENT_TYPE = "application/json";
    protected static final String BASIC = "Basic ";
    protected static final String AUTHORIZATION = "AUTHORIZATION";
    protected static final String BEARER = "Bearer ";
    protected static final String GRANT_TYPE = "grant_type";
    protected static final String CLIENT_CREDENTIALS = "client_credentials";

    protected abstract HttpMethod method();

    protected abstract String uri();

    protected abstract Headers headers();

    protected abstract RequestBody body();

    public HttpResponse execute() throws IOException {
        HttpResponse httpResponse = new HttpResponse();

        Request request;

        if (this.method() == HttpMethod.POST)
            request = new Request.Builder()
                    .headers(this.headers())
                    .post(this.body())
                    .url(this.uri())
                    .build();
        else
            request = new Request.Builder()
                    .headers(this.headers())
                    .get()
                    .url(this.uri())
                    .build();

        Response response = new OkHttpClient().newCall(request).execute();

        httpResponse.setStatus(response.code());
        httpResponse.setData(Objects.requireNonNull(response.body()).string());

        return httpResponse;
    }


}
