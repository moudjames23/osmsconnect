package io.github.moudjames23.osmsconnect.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.enums.ResponseSuccessCode;
import io.github.moudjames23.osmsconnect.exception.SMSException;
import io.github.moudjames23.osmsconnect.http.BaseRequest;
import io.github.moudjames23.osmsconnect.model.HttpResponse;
import io.github.moudjames23.osmsconnect.model.error.APIError;
import io.github.moudjames23.osmsconnect.model.error.AuthorizationError;
import io.github.moudjames23.osmsconnect.model.error.BaseError;

import java.io.IOException;
import java.util.Objects;

import static io.github.moudjames23.osmsconnect.util.JsonUtils.*;

public class RequestUtils {

    private RequestUtils() {
    }

    /**
     *
     * @param request
     * @param responseSuccessCode
     * @param clazz
     * @param baseError
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T performRequest(BaseRequest request, ResponseSuccessCode responseSuccessCode, Class<T> clazz, BaseError baseError) throws IOException {
        HttpResponse execute = request.execute();

        handleError(execute, responseSuccessCode, baseError);

        return getObjectFromJsonString(Objects.requireNonNull(execute.getData()), clazz);

    }

    /**
     *
     * @param execute
     * @param success
     * @param baseError
     * @throws JsonProcessingException
     */
    public static void handleError(HttpResponse execute, ResponseSuccessCode success, BaseError baseError) throws JsonProcessingException {
        if (execute.getStatus() != success.getCode()) {
            Class<? extends BaseError> errorClass = baseError instanceof APIError ? APIError.class : AuthorizationError.class;
            baseError = getObjectFromJsonString(execute.getData(), errorClass);
            throw new SMSException(baseError.error());

        }
    }
}
