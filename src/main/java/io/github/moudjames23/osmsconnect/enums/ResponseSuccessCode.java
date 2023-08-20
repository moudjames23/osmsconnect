package io.github.moudjames23.osmsconnect.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseSuccessCode {

    SUCCESS(200), CREATED(201);

    private final int code;
}
