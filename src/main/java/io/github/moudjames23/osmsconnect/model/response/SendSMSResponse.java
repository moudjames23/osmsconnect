package io.github.moudjames23.osmsconnect.model.response;

import lombok.*;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
public class SendSMSResponse {

    private OutboundSMSMessageResponse outboundSMSMessageRequest;

    public String getSMSId() throws URISyntaxException {

        URI uri = new URI(this.outboundSMSMessageRequest.resourceURL);
        String path = uri.getPath();
        String[] pathSegments = path.split("/");

        return pathSegments.length > 0 ? pathSegments[pathSegments.length - 1] : "";

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutboundSMSMessageResponse {

        private String[] address;

        private String senderAddress;

        private String senderName;

        private OutboundSMSTextMessageResponse outboundSMSTextMessage;

        private String resourceURL;


    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class OutboundSMSTextMessageResponse {

        private String message;
    }


}
