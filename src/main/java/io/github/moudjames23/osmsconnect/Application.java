package io.github.moudjames23.osmsconnect;

import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.http.SMS;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.response.BalanceResponse;
import io.github.moudjames23.osmsconnect.model.response.OrderHistoryResponse;
import io.github.moudjames23.osmsconnect.model.response.SendSMSResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException {


        SMS sms = new SMS("XXXXXXXXXXX", "XXXXXXXXXXXXX");

        BalanceResponse balance = sms.getRemainingBalance(Country.GUINEA);
        System.out.println("Balance: " +balance.getAvailableUnits());
        System.out.println("Expire: " +balance.getExpirationDate());

        SMSRequest smsRequest = SMSRequest.builder()
                .from(Country.GUINEA)
                .to("2246XXXXXXXXX")
                .senderName("SPECIFIC_SENDER_NAME")
                .message("Wesh le Pro")
                .build();

        SendSMSResponse smsResponse = sms.send(smsRequest);
        System.out.println("SMSID: " +smsResponse.getSMSId());

        OrderHistoryResponse[] orderHistoryResponses = sms.getOrderHistory(Country.GUINEA);

    }
}
