package io.github.moudjames23.osmsconnect.http;

import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.enums.ResponseSuccessCode;

import io.github.moudjames23.osmsconnect.exception.SMSException;
import io.github.moudjames23.osmsconnect.http.requests.BalanceRequest;
import io.github.moudjames23.osmsconnect.http.requests.SendRequest;
import io.github.moudjames23.osmsconnect.http.requests.AuthorizationRequest;
import io.github.moudjames23.osmsconnect.http.requests.OrderHistoryRequest;


import io.github.moudjames23.osmsconnect.model.error.AuthorizationError;
import io.github.moudjames23.osmsconnect.model.error.APIError;

import io.github.moudjames23.osmsconnect.model.request.SMSRequest;

import io.github.moudjames23.osmsconnect.model.response.BalanceResponse;
import io.github.moudjames23.osmsconnect.model.response.AuthorizationResponse;
import io.github.moudjames23.osmsconnect.model.response.SendSMSResponse;
import io.github.moudjames23.osmsconnect.model.response.OrderHistoryResponse;

import java.io.IOException;
import java.util.*;

import static io.github.moudjames23.osmsconnect.util.RequestUtils.performRequest;

public class SMS {

    private final String token;

    private final String clientId;

    private final String clientSecret;

    public SMS(String clientId, String clientSecret) throws IOException {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.token = generateAccessToken();
    }

    /**
     * generate access token
     * @throws IOException @exception
     * @throws SMSException @see {@link SMSException}
     */
    public String generateAccessToken() throws SMSException, IOException {
        AuthorizationRequest request = new AuthorizationRequest(clientId, clientSecret);

        Optional<AuthorizationResponse> authorizationResponse = Optional.ofNullable(performRequest(request, ResponseSuccessCode.SUCCESS, AuthorizationResponse.class, new AuthorizationError()));

        return authorizationResponse
                .map(AuthorizationResponse::getAccessToken)
                .orElseThrow(()-> new SMSException("Unable to generate token"));
    }
    /**
     * Get the remaining SMS balance based on the country code.
     *
     * @param country Country code
     * @return Balance information
     * @throws IOException if the balance call fails
     */
    public BalanceResponse getRemainingBalance(Country country) throws IOException {
        BalanceRequest request = new BalanceRequest(token, country);
        return performRequest(request, ResponseSuccessCode.SUCCESS, BalanceResponse[].class, new APIError())[0];
    }

    /**
     * Send an SMS based on the provided SMSRequest.
     *
     * @param smsRequest SMSRequest containing sender, recipient, message, and sender name
     * @return SMS information
     * @throws IOException if the send SMS call fails
     */
    public SendSMSResponse send(SMSRequest smsRequest) throws IOException {
        SendRequest sendRequest = new SendRequest(token, smsRequest);
        return performRequest(sendRequest, ResponseSuccessCode.CREATED, SendSMSResponse.class, new APIError());
    }

    /**
     * Get the history of pack purchases from your account.
     *
     * @param country Country code
     * @return Purchase history
     * @throws IOException if the order history call fails
     */
    public OrderHistoryResponse[] getOrderHistory(Country country) throws IOException {
        OrderHistoryRequest request = new OrderHistoryRequest(token, country);

        return performRequest(request, ResponseSuccessCode.SUCCESS, OrderHistoryResponse[].class, new APIError());
    }
}
