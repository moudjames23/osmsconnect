package io.github.moudjames23.osmsconnect.model.error;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class APIError extends BaseError {

    private int code;

    private String message;

    private String description;


    public String error()
    {
        return code+ " | " +message+ " | " +description;
    }

}
