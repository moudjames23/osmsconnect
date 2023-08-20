package io.github.moudjames23.osmsconnect.model.request;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendSMSRequest {

    private OutboundSMSMessageRequest outboundSMSMessageRequest;



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutboundSMSMessageRequest {

        private String address;

        private String senderAddress;

        private String senderName;

        private OutboundSMSTextMessageRequest outboundSMSTextMessage;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutboundSMSTextMessageRequest {

        private String message;


    }
}
