package io.github.moudjames23.osmsconnect.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.enums.ResponseSuccessCode;
import io.github.moudjames23.osmsconnect.http.requets.BalanceRequest;
import io.github.moudjames23.osmsconnect.http.requets.OrderHistoryRequest;
import io.github.moudjames23.osmsconnect.http.requets.SendRequest;
import io.github.moudjames23.osmsconnect.model.error.APIError;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.response.BalanceResponse;
import io.github.moudjames23.osmsconnect.model.response.OrderHistoryResponse;
import io.github.moudjames23.osmsconnect.model.response.SendSMSResponse;

import java.io.IOException;

import static io.github.moudjames23.osmsconnect.util.RequestUtils.performRequest;

public class SMS {

    private final SMSClient smsClient;


    public SMS(SMSClient smsClient) {
        this.smsClient = smsClient;
    }

    /**
     * this method allows you to get the number SMS remaining based on Country code
     * @param country access to country code
     * @return number SMS remaining and expiration date
     * @throws JsonProcessingException if  balance call failed
     */
    public BalanceResponse balance(Country country) throws IOException {

        BalanceRequest request = new BalanceRequest(this.smsClient.getToken(), country);
        return performRequest(request, ResponseSuccessCode.SUCCESS, BalanceResponse[].class, new APIError())[0];

    }

    /**
     *  this method allows you to send sms based on SMSRequest which
     * @param smsRequest contains Country sender, recipient number, message and  sendername
     * @return SMS informations
     * @throws JsonProcessingException if send sms call failed
     */
    public SendSMSResponse send(SMSRequest smsRequest) throws IOException {
        SendRequest request = new SendRequest(smsRequest, smsClient.getToken());
        return performRequest(request, ResponseSuccessCode.CREATED, SendSMSResponse.class, new APIError());
    }

    /**
     * this method allows to get the history of all your pack purchases from your account.
     * @param country country code
     * @return history purchase
     * @throws JsonProcessingException if order history call failed
     */
    public OrderHistoryResponse[] orderHistory(Country country) throws IOException {
        OrderHistoryRequest request = new OrderHistoryRequest(this.smsClient.getToken(), country);
        return performRequest(request, ResponseSuccessCode.SUCCESS, OrderHistoryResponse[].class, new APIError());
    }

}
