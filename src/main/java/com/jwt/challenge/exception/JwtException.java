package com.jwt.challenge.exception;

import lombok.ToString;

@ToString
public class JwtException extends RuntimeException {

    private int httpStatus;
    private String error;

    public JwtException(int httpStatus, String message, String error) {
        super(message);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public JwtException() {

    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }
}



