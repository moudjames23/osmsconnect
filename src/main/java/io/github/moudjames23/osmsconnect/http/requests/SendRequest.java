package io.github.moudjames23.osmsconnect.http.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.moudjames23.osmsconnect.exception.SMSException;
import io.github.moudjames23.osmsconnect.util.JsonUtils;
import io.github.moudjames23.osmsconnect.enums.HttpMethod;
import io.github.moudjames23.osmsconnect.model.request.SMSRequest;
import io.github.moudjames23.osmsconnect.model.request.SendSMSRequest;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Objects;

import static io.github.moudjames23.osmsconnect.util.JsonUtils.urlEncode;
import static io.github.moudjames23.osmsconnect.model.request.SendSMSRequest.OutboundSMSMessageRequest;
import static io.github.moudjames23.osmsconnect.model.request.SendSMSRequest.OutboundSMSTextMessageRequest;
import static io.github.moudjames23.osmsconnect.util.PhoneNumberUtil.*;


public class SendRequest extends BearAuthRequest {

    private static final String CONTENT_TYPE = "application/json";
    private final SMSRequest smsRequest;

    public SendRequest(String token, SMSRequest smsRequest) {
        super(token);
        this.smsRequest = smsRequest;
    }


    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUri() {
        return getBaseUrl().concat("/smsmessaging/v1/outbound/" + urlEncode(smsRequest.getFrom().getSenderName())
                .concat("/requests"));
    }

    @Override
    public RequestBody getBody() {

        RequestBody requestBody = null;

        OutboundSMSTextMessageRequest outboundSMSTextMessageRequest = new OutboundSMSTextMessageRequest(smsRequest.getMessage());

        if (!isValid(smsRequest.getTo())) {
            throw new SMSException("Phone number is not correct !");
        }

        OutboundSMSMessageRequest messageRequest = new OutboundSMSMessageRequest();
        messageRequest.setSenderAddress(smsRequest.getFrom().getSenderName());
        messageRequest.setAddress("tel:" + normalize(smsRequest.getTo()));
        messageRequest.setSenderName(Objects.toString(smsRequest.getSenderName(), ""));
        messageRequest.setOutboundSMSTextMessage(outboundSMSTextMessageRequest);


        SendSMSRequest sendSMSRequest = SendSMSRequest.builder()
                .outboundSMSMessageRequest(messageRequest)
                .build();

        try {
            requestBody = RequestBody.create(JsonUtils.getJsonStringFromObject(sendSMSRequest), MediaType.get(CONTENT_TYPE));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return requestBody;
    }



}
