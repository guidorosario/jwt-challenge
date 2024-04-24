package com.jwt.challenge.exception;

public class UnprocessableJwtException extends JwtException {

    public UnprocessableJwtException() {
        super();
    }

    public UnprocessableJwtException(String errorCode, String description) {
        super(500, errorCode, description);
    }
}
