package io.github.moudjames23.osmsconnect.http.requets;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.exception.SMSException;
import io.github.moudjames23.osmsconnect.util.JsonUtils;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.http.BaseRequest;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.request.SendSMSRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Objects;

import static io.github.moudjames23.osmsconnect.util.JsonUtils.urlEncode;
import static io.github.moudjames23.osmsconnect.model.request.SendSMSRequest.*;
import static io.github.moudjames23.osmsconnect.util.PhoneNumberUtil.*;


@Setter
@Getter
@RequiredArgsConstructor
public class SendRequest extends BaseRequest {


    private final SMSRequest smsRequest;

    private final String token;

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return BASE_URL.concat("/smsmessaging/v1/outbound/" + urlEncode(smsRequest.getFrom().getSenderName())
                .concat("/requests"));

    }

    @Override
    public Headers headers() {

        return new Headers.Builder()
                .add(AUTHORIZATION, BEARER + this.token)
                .build();
    }

    @Override
    public RequestBody body() {

        RequestBody requestBody = null;

        OutboundSMSTextMessageRequest outboundSMSTextMessageRequest = new OutboundSMSTextMessageRequest(smsRequest.getMessage());

        if (!isValid(smsRequest.getTo()))
            throw new SMSException("Phone number is not correct !");

        OutboundSMSMessageRequest messageRequest = new OutboundSMSMessageRequest();
        messageRequest.setSenderAddress(smsRequest.getFrom().getSenderName());
        messageRequest.setAddress("tel:" + normalize(smsRequest.getTo()));
        messageRequest.setSenderName(Objects.toString(smsRequest.getSenderName(), ""));
        messageRequest.setOutboundSMSTextMessage(outboundSMSTextMessageRequest);


        SendSMSRequest sendSMSRequest = builder()
                .outboundSMSMessageRequest(messageRequest)
                .build();

        try {
            requestBody = RequestBody.create(MediaType.get(CONTENT_TYPE), JsonUtils.getJsonStringFromObject(sendSMSRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return requestBody;
    }



}
