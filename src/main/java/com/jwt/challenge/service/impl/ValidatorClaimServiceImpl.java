package com.jwt.challenge.service.impl;

import com.google.gson.JsonObject;
import com.jwt.challenge.enums.RoleEnum;
import com.jwt.challenge.service.ValidatorClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.jwt.challenge.constraint.ValidatorConstraints.DIGIT;
import static com.jwt.challenge.constraint.Claims.*;
import static com.jwt.challenge.constraint.Claims.NAME;

@Service
public class ValidatorClaimServiceImpl implements ValidatorClaimService {
    private static final Logger LOG = LoggerFactory.getLogger(ValidatorClaimServiceImpl.class);


    @Override
    public Boolean verifyNumberOfClaims(JsonObject claim) {
        if (claim.size() != 3) {
            LOG.info("jwt possui mais ou menos de 3 claims");
            return false;
        } else {
            LOG.info("jwt possui 3 claims");
            return true;
        }
    }

    @Override
    public Boolean verifyClaims(JsonObject claim) {
        if (claim.keySet().contains(NAME) && claim.keySet().contains(SEED) && claim.keySet().contains(ROLE)) {
            LOG.info("jwt possui todos os claim permitidos");
            return true;
        } else {
            LOG.info("jwt possui claim nao permitido");
            return false;
        }
    }

    @Override
    public Boolean verifyDigitClaimName(JsonObject claim) {
        if (claim.get(NAME).getAsString().matches(DIGIT)) {
            LOG.info("Claim Name possui digitos");
            return false;
        } else {
            LOG.info("Claim Name nao possui digitos");
            return true;
        }
    }

    @Override
    public Boolean verifyClaimRole(JsonObject claim) {
        if(RoleEnum.fromEnum(claim.get(ROLE).getAsString()).equals(RoleEnum.NONE)){
            LOG.info("Claim Role invalido");
            return false;
        } else {
            LOG.info("Claim Role valido");
            return true;
        }
    }

    @Override
    public Boolean verifyPrimeNumberOnClaimSeed(JsonObject claim) {
        if(!isPrime(claim.get(SEED).getAsInt())){
            LOG.info("Claim Seed nao é um numero primo");
            return false;
        } else {
            LOG.info("Claim Seed é um numero primo");
            return true;
        }
    }

    @Override
    public Boolean verifySizeOnClaimName(JsonObject claim) {
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
