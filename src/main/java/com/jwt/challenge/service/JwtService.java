package com.jwt.challenge.service;

import reactor.core.publisher.Mono;

public interface JwtService {

    Mono<Boolean> validatorJwt(String jwt);
}
