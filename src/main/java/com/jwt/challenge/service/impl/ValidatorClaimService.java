package com.jwt.challenge.service.impl;

import com.google.gson.JsonObject;
import com.jwt.challenge.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.jwt.challenge.constraint.ValidatorConstraints.DIGIT;
import static com.jwt.challenge.constraint.Claims.*;
import static com.jwt.challenge.constraint.Claims.NAME;

@Service
public class ValidatorClaimService {
    private static final Logger LOG = LoggerFactory.getLogger(JwtServiceImpl.class);


    public Boolean validateNumberOfClaims(JsonObject claim) {
        if (claim.size() != 3) {
            LOG.info("jwt possui mais ou menos de 3 claims");
            return false;
        } else {
            LOG.info("jwt possui 3 claims");
            return true;
        }
    }

    public Boolean validateDigitClaimName(JsonObject claim) {
        if (claim.get(NAME).getAsString().matches(DIGIT)) {
            LOG.info("Claim name possui digitos");
            return false;
        } else {
            LOG.info("Claim Name nao possui digitos");
            return true;
        }
    }

    public Boolean validateNameClaimRole(JsonObject claim) {
        if(RoleEnum.fromEnum(claim.get(ROLE).getAsString()).equals(RoleEnum.NONE)){
            LOG.info("Claim role invalido");
            return false;
        } else {
            LOG.info("Claim role valido");
            return true;
        }
    }

    public Boolean validatePrimeNumberOnClaimSeed(JsonObject claim) {
        if(!isPrime(claim.get(SEED).getAsInt())){
            LOG.info("Claim Seed nao é um numero primo");
            return false;
        } else {
            LOG.info("Claim Seed é um numero primo");
            return true;
        }
    }

    public Boolean validateSizeOnClaimName(JsonObject claim) {
        if(claim.get(NAME).getAsString().length()> 256){
            LOG.info("Claim Name possui mais que 256 carateres");
            return false;
        } else {
            LOG.info("Claim Name possui 256 caracteres ou menos");
            return true;
        }
    }

    private static boolean isPrime(int number) {
        for (int j = 2; j < number; j++) {
            if (number % j == 0)
                return false;
        }
        return true;
    }
}
