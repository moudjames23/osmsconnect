package io.github.moudjames23.osmsconnect.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpMethod {

    POST(1), GET(2);

    private final int code;
}
