package io.github.moudjames23.osmsconnect.http.requests;

import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import okhttp3.Headers;
import okhttp3.RequestBody;

public interface HttpRequest {
    default String getBaseUrl() {
        return "https://api.orange.com";
    }

    default String getAuthorization() {
        return "Authorization";
    }

    default Headers getHeaders() {
        return new Headers.Builder().build(); // Provide default empty headers
    }

    default RequestBody getBody() {
        return null;
    }

    default HttpMethod getMethod() {
        return HttpMethod.GET; // Provide a default HTTP method (e.g., GET)
    }

    default String getUri() {
        return ""; // Provide a default empty URI
    }
}
