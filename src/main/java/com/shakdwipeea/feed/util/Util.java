package com.shakdwipeea.feed.util;


import org.bson.Document;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Akash
 *         Created on 00:58 15-09-2016
 */
public class Util {
    private final static String MySecretKey = "1##&&HighlyConfidential$%#@";


    /**
     * @param key String to encrypt
     * @return Hashed String
     */
    public static String Encrypt(String key) {
        try {
            Mac sha256_hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(MySecretKey.getBytes(), "HmacSHA256");
            sha256_hmac.init(secretKeySpec);

            String hash = Base64.getEncoder().encodeToString(sha256_hmac.doFinal(key.getBytes()));
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Document addNoError(Document document) {
        document.putIfAbsent("error", false);
        return document;
    }

    public static Response.ResponseBuilder makeResponse(Document document) {
        Response.ResponseBuilder responseBuilder;
        if (document.getBoolean("error")) {
            responseBuilder = Response.status(403);
        } else {
            responseBuilder = Response.ok();
        }

        responseBuilder.entity(document);
        return responseBuilder;
    }
}
