package com.jwt.challenge.controller;

import com.jwt.challenge.model.JwtRequest;
import com.jwt.challenge.service.JwtService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping
    @RequestMapping("/v1")
    public Mono<Boolean> validateJwt(@RequestBody JwtRequest jwtRequest){
       return jwtService.validatorJwt(jwtRequest.jwt());
    }

    @PostMapping
    @RequestMapping("/v2")
    public Mono<Boolean> validateJwtV2(@RequestHeader (name="Authorization") String token) {
        return jwtService.validatorJwt(token);
    }

}
