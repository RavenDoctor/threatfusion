package com.adamshaddick.threatfusion.exception;

public class InvalidIpAddressException extends RuntimeException {

    public InvalidIpAddressException(String message) {
        super(message);
    }
}