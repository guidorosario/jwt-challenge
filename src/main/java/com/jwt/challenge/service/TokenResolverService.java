package com.jwt.challenge.service;

import com.google.gson.JsonObject;
import reactor.core.publisher.Mono;

public interface TokenResolverService {

    Mono<JsonObject> decodeJwt(String jwt);

}
