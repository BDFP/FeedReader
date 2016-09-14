package com.shakdwipeea.feed.model;

import com.shakdwipeea.feed.util.MongoHelper;
import org.bson.Document;

/**
 * @author Akash
 *         Created on 01:35 15-09-2016
 */
public class ErrorResponse {
    private Boolean error;
    private String message;

    public ErrorResponse(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Document toDocument(String message) {
        return MongoHelper.toDocument(new ErrorResponse(true, message));
    }
}
