package com.jwt.challenge.service;

import com.jwt.challenge.service.impl.ValidatorClaimServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.jwt.challenge.model.JwtRequestMock.*;
import static com.jwt.challenge.utils.DecodeUtils.decodeJwt;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ValidatorClaimServiceImplTest {

    @InjectMocks
    private ValidatorClaimServiceImpl validatorClaimServiceImpl;


    @Test
    @DisplayName("Deve retornar true para um jwt valido com 3 claims")
    void shouldTrueValidClaimNoExceed() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifyNumberOfClaims(claim));
    }

    @Test
    @DisplayName("Deve retornar false para jwt com mais de 3 claims")
    void shouldFalseClaimExceed() {

        var claim = decodeJwt(invalidJwtClaimExceedMock().jwt());

        assertFalse(validatorClaimServiceImpl.verifyNumberOfClaims(claim));
    }

    @Test
    @DisplayName("Deve retornar true para um claim que não tenha digitos")
    void shouldTrueValidClaimNameNoDigit() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifyDigitClaimName(claim));
    }

    @Test
    @DisplayName("Deve retornar false para um claim que tenha digitos")
    void shouldFalseClaimNameWithDigit() {

        var claim = decodeJwt(invalidJwtDigitClaimNameMock().jwt());

        assertFalse(validatorClaimServiceImpl.verifyDigitClaimName(claim));
    }

    @Test
    @DisplayName("Deve retornar true para claim permitido")
    void shouldTrueValidClaims() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifyClaims(claim));
    }

    @Test
    @DisplayName("Deve retornar false para claims não permitido")
    void shouldFalseInvalidClaims() {

        var claim = decodeJwt(invalidClaimMock().jwt());

        assertFalse(validatorClaimServiceImpl.verifyClaims(claim));
    }

    @Test
    @DisplayName("Deve retornar true para um Claim Role valido")
    void shouldTrueValidClaimRole() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifyClaimRole(claim));
    }

    @Test
    @DisplayName("Deve retornar false para um Claim Role invalido")
    void shouldFalseOnClaimRoleInvalid() {

        var claim = decodeJwt(invalidJwtClaimRoleMock().jwt());

        assertFalse(validatorClaimServiceImpl.verifyClaimRole(claim));

    }

    @Test
    @DisplayName("Deve retornar true para um Claim Seed Primo")
    void shouldTruePrimeNumberOnClaimSeed() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifyPrimeNumberOnClaimSeed(claim));
    }

    @Test
    @DisplayName("Deve retornar false para um Claim Seed Par")
    void shouldFalseEvenNumberOnClaimSeed() {

        var claim = decodeJwt(invalidJwtClaimSeedNoPrimeMock().jwt());

      assertFalse(validatorClaimServiceImpl.verifyPrimeNumberOnClaimSeed(claim));
    }

    @Test
    @DisplayName("Deve retornar true para um Claim Name com menos de 256 caracteres")
    void shouldTrueValidClaimNameNoExceedSize() {

        var claim = decodeJwt(validJwtMock().jwt());

        assertTrue(validatorClaimServiceImpl.verifySizeOnClaimName(claim));
    }

    @Test
    @DisplayName("Deve retornar false para um Claim Name com mais de 256 caracteres valido")
    void shouldFalseClaimNameExceedSize() {

        var claim = decodeJwt(invalidJwtClaimNameSizeExceedMock().jwt());

        assertFalse(validatorClaimServiceImpl.verifySizeOnClaimName(claim));

    }
}
