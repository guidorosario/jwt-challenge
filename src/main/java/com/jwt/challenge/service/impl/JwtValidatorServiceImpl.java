package com.jwt.challenge.service.impl;

import com.jwt.challenge.exception.JwtException;
import com.jwt.challenge.service.JwtService;
import com.jwt.challenge.service.TokenResolverService;
import com.jwt.challenge.service.ValidatorClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JwtValidatorServiceImpl implements JwtService {

    private static final Logger LOG = LoggerFactory.getLogger(JwtValidatorServiceImpl.class);

    private final TokenResolverService tokenResolverService;
    private final ValidatorClaimService validatorClaimService;

    public JwtValidatorServiceImpl(TokenResolverService tokenResolverService, ValidatorClaimService validatorClaimService) {
        this.tokenResolverService = tokenResolverService;
        this.validatorClaimService = validatorClaimService;
    }

    @Override
    public Mono<Boolean> validatorJwt(String jwt) {

       return this.tokenResolverService.decodeJwt(jwt)
               .filter(validatorClaimService::verifyNumberOfClaims)
               .filter(validatorClaimService::verifyClaims)
               .filter(validatorClaimService::verifyDigitClaimName)
               .filter(validatorClaimService::verifyClaimRole)
               .filter(validatorClaimService::verifyPrimeNumberOnClaimSeed)
               .filter(validatorClaimService::verifySizeOnClaimName)
               .map(claim -> {
                   LOG.info("Jwt validado com sucesso");
                   return true;
               })
               .switchIfEmpty(Mono.defer(() -> Mono.just(false)))
               .onErrorResume(JwtException.class, e -> Mono.just(false));

    }

}

