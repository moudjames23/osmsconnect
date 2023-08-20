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
public class BalanceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("developerId")
    private String developerId;

    @JsonProperty("applicationId")
    private String applicationId;

    @JsonProperty("country")
    private String country;

    @JsonProperty("offerName")
    private String offerName;

    @JsonProperty("availableUnits")
    private int availableUnits;

    @JsonProperty("requestedUnits")
    private int requestedUnits;

    @JsonProperty("status")
    private String status;

    @JsonProperty("expirationDate")
    private String expirationDate;

    @JsonProperty("creationDate")
    private String creationDate;

    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;



}
