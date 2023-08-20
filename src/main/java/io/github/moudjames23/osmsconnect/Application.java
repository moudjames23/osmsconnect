package io.github.moudjames23.osmsconnect;

import io.github.moudjames23.osmsconnect.enums.Country;
import io.github.moudjames23.osmsconnect.http.SMS;
import io.github.moudjames23.osmsconnect.http.SMSClient;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.response.BalanceResponse;
import io.github.moudjames23.osmsconnect.model.response.OrderHistoryResponse;
import io.github.moudjames23.osmsconnect.model.response.SendSMSResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException {

        SMSClient client = new SMSClient("XXXXXXXXXXX", "XXXXXXXXXXXXX");

        System.out.println("Token: " +client.getToken());
        System.out.println("ExpireIn: " +client.getExpireIn());

        SMS sms = new SMS(client);

        BalanceResponse balance = sms.balance(Country.GuineaConakry);
        System.out.println("Balance: " +balance.getAvailableUnits());
        System.out.println("Expire: " +balance.getExpirationDate());

        SMSRequest smsRequest = SMSRequest.builder()
                .from(Country.GuineaConakry)
                .to("2246XXXXXXXXX")
                .senderName("SPECIFIC_SENDER_NAME")
                .message("Wesh le Pro")
                .build();

        SendSMSResponse smsResponse = sms.send(smsRequest);
        System.out.println("SMSID: " +smsResponse.getSMSId());

        OrderHistoryResponse[] orderHistoryResponses = sms.orderHistory(Country.GuineaConakry);



    }
}
