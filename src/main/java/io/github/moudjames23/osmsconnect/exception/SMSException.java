package io.github.moudjames23.osmsconnect.exception;

public class SMSException extends RuntimeException {

    public SMSException(String message) {
        super(message);
    }

    public SMSException(String message, Exception e) {
        super(message, e);
    }
}
