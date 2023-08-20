package io.github.moudjames23.osmsconnect.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationError extends BaseError {

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    public String error()
    {
        return error+ " | " +errorDescription;
    }
}
