package com.shakdwipeea.feed.util;

import org.bson.Document;

/**
 * @author Akash
 *         Created on 02:05 15-09-2016
 */
public class MongoHelper {
    public static Document toDocument(Object object) {
        return Document.parse(JsonHelper.toJson(object));
    }
}
