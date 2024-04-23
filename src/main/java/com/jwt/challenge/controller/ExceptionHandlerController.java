package com.jwt.challenge.controller;

import com.google.gson.stream.MalformedJsonException;
import com.jwt.challenge.exception.JwtException;
import com.jwt.challenge.model.StandardMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(JwtException.class)
    public Mono<ResponseEntity<StandardMessage>> handlePasswordException(JwtException e, ServerWebExchange exchange) {
        LOG.info("[{}] [{}]", e.getClass().getName(), e.getMessage());
        return Mono.just(ResponseEntity.status(e.getHttpStatus())
                .body(new StandardMessage(e.getError(), e.getMessage())));
    }


    @ExceptionHandler(MalformedJsonException.class)
    public Mono<ResponseEntity<StandardMessage>> handleMalformedJsonException(MalformedJsonException e, ServerWebExchange exchange) {
        LOG.info("[{}] [{}]", e.getLocalizedMessage(), e.getMessage());
        return Mono.just(ResponseEntity.status(500)
                .body(new StandardMessage(e.getLocalizedMessage(), e.getMessage())));
    }
}
