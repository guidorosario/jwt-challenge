package com.jwt.challenge.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jwt.challenge.exception.JwtException;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecodeUtils {
    public static JsonObject decodeJwt (String jwt) {
        try {

            String[] split_string = jwt.split("\\.");
            String base64EncodedBody = split_string[1];
            Base64.Decoder base64Url = Base64.getUrlDecoder();

            String body = new String(base64Url.decode(base64EncodedBody));

            var jsonObject = JsonParser.parseString(body)
                        .getAsJsonObject();
            return jsonObject;
        } catch (Exception ex) {
            throw new JwtException(500, "", "");
        }
    }
}
