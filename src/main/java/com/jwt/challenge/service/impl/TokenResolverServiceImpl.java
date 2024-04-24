package com.jwt.challenge.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jwt.challenge.exception.UnprocessableJwtException;
import com.jwt.challenge.service.TokenResolverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Service
public class TokenResolverServiceImpl implements TokenResolverService {
    private static final Logger LOG = LoggerFactory.getLogger(TokenResolverServiceImpl.class);

    public Mono<JsonObject> decodeJwt(String jwt) {
        return Mono.just(jwt)
                .map(token -> token.split("\\.")[1])
                .map(base64EncodedBody -> new String(Base64.getUrlDecoder().decode(base64EncodedBody)))
                .map(body -> JsonParser.parseString(body).getAsJsonObject())
                .onErrorResume(throwable -> {
                    LOG.info("Jwt invalido");
                    return Mono.error(new UnprocessableJwtException("unprocessable_error", "Jwt invalido"));
                })
                .doFirst(() -> LOG.info("Decodificando o Jwt"));


    }
}
