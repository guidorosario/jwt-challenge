package com.jwt.challenge.service;

import com.jwt.challenge.exception.UnprocessableJwtException;
import com.jwt.challenge.service.impl.TokenResolverServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static com.jwt.challenge.model.JwtRequestMock.invalidJwtMock;
import static com.jwt.challenge.model.JwtRequestMock.validJwtMock;
import static com.jwt.challenge.utils.DecodeUtils.decodeJwt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class TokenResolverServiceTest {

    @InjectMocks
    private TokenResolverServiceImpl tokenResolverService;


    @Test
    @DisplayName("Deve decodificar um twt")
    void shouldDecodeTwtSuccess() {

        StepVerifier.create(tokenResolverService.decodeJwt(validJwtMock().jwt()))
                .expectSubscription()
                .assertNext(jsonObject -> assertEquals(jsonObject, decodeJwt(validJwtMock().jwt())))
                .verifyComplete();

    }


    @Test
    @DisplayName("Deve dar erro ao decodificar um jwt invalido")
    void shouldDecodeTwtError() {

        StepVerifier.create(tokenResolverService.decodeJwt(invalidJwtMock().jwt()))
                .expectError(UnprocessableJwtException.class)
                .log()
                .verify();
    }
}
