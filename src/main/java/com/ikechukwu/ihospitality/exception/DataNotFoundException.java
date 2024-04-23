package com.ikechukwu.ihospitality.exception;

public class DataNotFoundException extends RuntimeException {

    private final String code;
    private final String message;

    public DataNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
