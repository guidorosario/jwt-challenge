package com.jwt.challenge.service;


import com.jwt.challenge.service.impl.JwtServiceImpl;
import com.jwt.challenge.service.impl.ValidatorClaimService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static com.jwt.challenge.model.JwtRequestMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class JwtServiceTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    ValidatorClaimService validatorClaimService;

    @Test
    @DisplayName("Deve retornar true para um jwt valido")
    void shouldTrueValidJwt() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(true);

        when(validatorClaimService.verifyDigitClaimName(any())).thenReturn(true);

        when(validatorClaimService.verifyClaimRole(any())).thenReturn(true);

        when(validatorClaimService.verifyPrimeNumberOnClaimSeed(any())).thenReturn(true);

        when(validatorClaimService.verifySizeOnClaimName(any())).thenReturn(true);

        StepVerifier.create(jwtService.validatorJwt(validJwtMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertTrue)
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Deve retornar false para um jwt invalido")
    void shouldFalseInvalidJwt() {

        StepVerifier.create(jwtService.validatorJwt(invalidJwtMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Deve retornar false por exceder a quantidade de claims")
    void shouldFalseExcedNumberOfClaim() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(false);

        StepVerifier.create(jwtService.validatorJwt(invalidJwtClaimExceedMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Deve retornar false quando o Claim Name conter digitos")
    void shouldFalseDigitOnClaimName() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(true);

        when(validatorClaimService.verifyDigitClaimName(any())).thenReturn(false);

        StepVerifier.create(jwtService.validatorJwt(invalidJwtDigitClaimNameMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Deve retornar false para um Claim Role invalido")
    void shouldFalseOnClaimRoleInvalid() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(true);

        when(validatorClaimService.verifyDigitClaimName(any())).thenReturn(true);

        when(validatorClaimService.verifyClaimRole(any())).thenReturn(false);

        StepVerifier.create(jwtService.validatorJwt(invalidJwtClaimRoleMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Deve retornar false para um Claim Seed Par")
    void shouldFalseNoPrimeNumberOnClaimSeed() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(true);

        when(validatorClaimService.verifyDigitClaimName(any())).thenReturn(true);

        when(validatorClaimService.verifyClaimRole(any())).thenReturn(true);

        when(validatorClaimService.verifyPrimeNumberOnClaimSeed(any())).thenReturn(false);

        StepVerifier.create(jwtService.validatorJwt(invalidJwtClaimSeedNoPrimeMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Deve retornar false para um Claim Name com mais de 256 caracteres valido")
    void shouldFalseInvalidClaimNameExceedSize() {

        when(validatorClaimService.verifyNumberOfClaims(any())).thenReturn(true);

        when(validatorClaimService.verifyDigitClaimName(any())).thenReturn(true);

        when(validatorClaimService.verifyClaimRole(any())).thenReturn(true);

        when(validatorClaimService.verifyPrimeNumberOnClaimSeed(any())).thenReturn(true);

        when(validatorClaimService.verifySizeOnClaimName(any())).thenReturn(false);

        StepVerifier.create(jwtService.validatorJwt(invalidJwtClaimNameSizeExceedMock().jwt()))
                .expectSubscription()
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();

    }

}
