package com.jwt.challenge.service;

import com.google.gson.JsonObject;

public interface ValidatorClaimService {
    Boolean verifyNumberOfClaims(JsonObject claim);

    Boolean verifyClaims(JsonObject claim);

    Boolean verifyDigitClaimName(JsonObject claim);

    Boolean verifyClaimRole(JsonObject claim);

    Boolean verifyPrimeNumberOnClaimSeed(JsonObject claim);

    Boolean verifySizeOnClaimName(JsonObject claim);
}
