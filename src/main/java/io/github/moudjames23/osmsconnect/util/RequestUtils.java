package io.github.moudjames23.osmsconnect.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.enums.ResponseSuccessCode;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.exception.SMSException;
import io.github.moudjames23.osmsconnect.http.requests.HttpRequest;
import io.github.moudjames23.osmsconnect.model.HttpResponse;
import io.github.moudjames23.osmsconnect.model.error.APIError;
import io.github.moudjames23.osmsconnect.model.error.AuthorizationError;
import io.github.moudjames23.osmsconnect.model.error.BaseError;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import static io.github.moudjames23.osmsconnect.util.JsonUtils.getObjectFromJsonString;

public class RequestUtils {
    private static final OkHttpClient httpClient = new OkHttpClient();

    private RequestUtils() {
    }

    public static <T> T performRequest(HttpRequest httpRequest, ResponseSuccessCode responseSuccessCode, Class<T> clazz, BaseError baseError) throws IOException {
        HttpResponse execute = execute(httpRequest);

        handleError(execute, responseSuccessCode, baseError);

        return getObjectFromJsonString(Objects.requireNonNull(execute.getData()), clazz);
    }

    private static HttpResponse execute(HttpRequest httpHttpRequestHeader) {
        Request.Builder requestBuilder = createRequestBuilder(httpHttpRequestHeader);

        try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
            return createHttpResponse(response);
        } catch (IOException e) {
            throw new SMSException(e.getMessage());
        }
    }

    @NotNull
    private static Request.Builder createRequestBuilder(HttpRequest httpHttpRequestHeader) {
        Request.Builder requestBuilder = new Request.Builder()
            .headers(httpHttpRequestHeader.getHeaders())
            .url(httpHttpRequestHeader.getUri());

        if (httpHttpRequestHeader.getMethod() == HttpMethod.POST) {
            requestBuilder.post(httpHttpRequestHeader.getBody());
        } else {
            requestBuilder.get();
        }

        return requestBuilder;
    }

    @NotNull
    private static HttpResponse createHttpResponse(Response response) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(response.code());
        httpResponse.setData(Objects.requireNonNull(response.body()).string());
        return httpResponse;
    }

    public static void handleError(HttpResponse execute, ResponseSuccessCode success, BaseError baseError) throws JsonProcessingException {
        if (execute.getStatus() != success.getCode()) {
            Class<? extends BaseError> errorClass = (baseError instanceof APIError) ? APIError.class : AuthorizationError.class;
            baseError = getObjectFromJsonString(execute.getData(), errorClass);
            throw new SMSException(baseError.error());
        }
    }
}
