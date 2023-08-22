package io.github.moudjames23.osmsconnect.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Country {

    BOSTWANA("BWA", "tel:+2670000"),
    BURKINAFASO("BFA", "tel:+2260000"),
    CAMEROON("CMR", "tel:+2370000"),
    IVORYCOAST("CIV", "tel:+2250000"),
    GUINEA("GIN", "tel:+2240000"),
    GUINEA_BISSAU("GNB", "tel:+2450000"),
    DR_CONGO("COD", "tel:+2430000"),
    JORDAN("JOR", "tel:+9620000"),
    LIBERIA("LBR", "tel:+2310000"),
    MALI("MLI", "tel:+2230000"),
    MADAGASCAR("MDG", "tel:+2610000"),
    SENEGAL("SEN", "tel:+2210000"),
    TUNISIA("TUN", "tel:+2160000");

    private final String code;
    private final String senderName;
}
