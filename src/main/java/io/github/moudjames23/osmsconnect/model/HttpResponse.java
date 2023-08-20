package io.github.moudjames23.osmsconnect.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HttpResponse {

    private int status;
    private String data;
}
