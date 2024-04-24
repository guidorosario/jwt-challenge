package com.jwt.challenge.service.impl;

import com.jwt.challenge.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.jwt.challenge.utils.DecodeUtils.decodeJwt;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Logger LOG = LoggerFactory.getLogger(JwtServiceImpl.class);
    private final ValidatorClaimService validatorClaimService;

    public JwtServiceImpl(ValidatorClaimService validatorClaimService) {
        this.validatorClaimService = validatorClaimService;
    }

    @Override
    public Mono<Boolean> validatorJwt(String jwt) {

       return Mono.just(decodeJwt(jwt))
               .filter(claim -> !claim.isEmpty())
               .filter(validatorClaimService::validateNumberOfClaims)
               .filter(validatorClaimService::validateDigitClaimName)
               .filter(validatorClaimService::validateNameClaimRole)
               .filter(validatorClaimService::validatePrimeNumberOnClaimSeed)
               .filter(validatorClaimService::validateSizeOnClaimName)
               .map(claim -> {
                   LOG.info("Jwt validado com sucesso");
                   return true;
               })
               .switchIfEmpty(Mono.defer(() -> Mono.just(false)));
    }

}

