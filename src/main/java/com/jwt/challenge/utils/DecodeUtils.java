package com.jwt.challenge.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jwt.challenge.service.impl.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecodeUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JwtServiceImpl.class);


    public static JsonObject decodeJwt (String jwt) {

            LOG.info("Decodificando o Jwt");

            String[] split_string = jwt.split("\\.");
            String base64EncodedBody = split_string[1];
            Base64.Decoder base64Url = Base64.getUrlDecoder();

            String body = new String(base64Url.decode(base64EncodedBody));

            try {
                var jsonObject = JsonParser.parseString(body)
                        .getAsJsonObject();
                LOG.info("Jwt Decodificado com sucesso");
                return jsonObject;

            } catch (Exception ex){
                LOG.info("Jwt invalido");
                return new JsonObject();
            }
    }
}
