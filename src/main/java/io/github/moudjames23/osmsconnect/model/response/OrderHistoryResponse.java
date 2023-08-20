package io.github.moudjames23.osmsconnect.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("developerId")
    private String developerId;

    @JsonProperty("contractId")
    private String contractId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("country")
    private String country;

    @JsonProperty("offerName")
    private String offerName;

    @JsonProperty("bundleId")
    private String bundleId;

    @JsonProperty("bundleDescription")
    private String bundleDescription;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("purchaseDate")
    private String purchaseDate;

    @JsonProperty("paymentMode")
    private String paymentMode;

    @JsonProperty("paymentProviderOrderId")
    private String paymentProviderOrderId;

    @JsonProperty("payerId")
    private String payerId;

    @JsonProperty("oldAvailableUnits")
    private int oldAvailableUnits;

    @JsonProperty("newAvailableUnits")
    private String newAvailableUnits;

    @JsonProperty("oldExpirationDate")
    private String oldExpirationDate;

    @JsonProperty("newExpirationDate")
    private String newExpirationDate;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("comment")
    private String comment;


}
