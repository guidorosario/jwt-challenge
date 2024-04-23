package com.jwt.challenge.controller;

import com.jwt.challenge.service.impl.JwtServiceImpl;
import com.jwt.challenge.service.impl.ValidatorClaimService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.jwt.challenge.model.JwtRequestMock.*;
import static com.jwt.challenge.model.JwtRequestMock.validJwtMock;

@WebFluxTest(controllers = JwtController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
@Import({ValidatorClaimService.class, JwtServiceImpl.class})
public class JwtControllerIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando jwt valido retornando true")
    void validJwtShouldTrue() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(validJwtMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Testando jwt invalido retornando false")
    void invalidJwtShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }


    @Test
    @DisplayName("Testando jwt com mais de 3 claims retornando false")
    void jwtClaimExceedShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtClaimExceedMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com menos de 3 claims retornando false")
    void jwtTwoClaimShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwt2ClaimMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com digito no Claim Name retornando false")
    void jwtDigitClaimNameShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtDigitClaimNameMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com Claim Role invalido retornando false")
    void jwtInvalidClaimRoleShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtClaimRoleMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com Claim Seed numero primo retornando false")
    void jwtNoPrimeClaimSeedShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtClaimSeedNoPrimeMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com Claim Name com mais de 256 caracteres retornando false")
    void jwtClaimNameExceedSizeShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v1")
                .body(BodyInserters.fromValue(invalidJwtClaimNameSizeExceedMock()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }


    @Test
    @DisplayName("Testando jwt no Header valido retornando true")
    void validHeaderJwtShouldTrue() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization", validJwtMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Testando jwt no header invalido retornando false")
    void invalidHeaderJwtShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization",invalidJwtMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }


    @Test
    @DisplayName("Testando jwt no header com mais de 3 claims retornando false")
    void jwtHeaderClaimExceedShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization", invalidJwtClaimExceedMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt no header com menos de 3 claims retornando false")
    void jwtHeaderTwoClaimShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization",invalidJwt2ClaimMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt no header com digito no Claim Name retornando false")
    void jwtHeaderDigitClaimNameShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization", invalidJwtDigitClaimNameMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt no header com Claim Role invalido retornando false")
    void jwtHeaderInvalidClaimRoleShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization",invalidJwtClaimRoleMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt com Claim Seed numero primo retornando false")
    void jwtHeaderNoPrimeClaimSeedShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization",invalidJwtClaimSeedNoPrimeMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Testando jwt no header com Claim Name com mais de 256 caracteres retornando false")
    void jwtHeaderClaimNameExceedSizeShouldFalse() {

        webTestClient
                .post()
                .uri("/jwt/v2")
                .header("Authorization",invalidJwtClaimNameSizeExceedMock().jwt())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isEqualTo(false);
    }

}
