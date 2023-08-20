package io.github.moudjames23.osmsconnect.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Country {

    Botswana("BWA", "tel:+2670000"),
    BurkinaFaso("BFA", "tel:+2260000"),
    Cameroon("CMR", "tel:+2370000"),
    IvoryCost("CIV", "tel:+2250000"),
    GuineaConakry("GIN", "tel:+2240000"),
    GuineaBissau("GNB", "tel:+2450000"),
    DRCongo("COD", "tel:+2430000"),
    Jordan("JOR", "tel:+9620000"),
    Liberia("LBR", "tel:+2310000"),
    Mali("MLI", "tel:+2230000"),
    Madagascar("MDG", "tel:+2610000"),
    Senegal("SEN", "tel:+2210000"),
    Tunisia("TUN", "tel:+2160000");

    private final String code;
    private final String senderName;
}
