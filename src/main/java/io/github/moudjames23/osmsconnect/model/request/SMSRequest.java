package io.github.moudjames23.osmsconnect.model.request;

import io.github.moudjames23.osmsconnect.enums.Country;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSRequest {

    @NonNull
    private Country from;

    @NonNull
    private String to;

    @NonNull
    private String message;

    private String senderName;
}
