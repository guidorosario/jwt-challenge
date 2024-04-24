package com.jwt.challenge.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class UnprocessableJwtException extends JwtException {

    public UnprocessableJwtException() {
        super();
    }

    public UnprocessableJwtException(String errorCode, String description) {
        super(UNPROCESSABLE_ENTITY.value(), errorCode, description);
    }
}
