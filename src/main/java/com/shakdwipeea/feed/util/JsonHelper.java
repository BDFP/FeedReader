package com.shakdwipeea.feed.util;

import com.google.gson.Gson;

/**
 * @author Akash
 *         Created on 01:30 04-09-2016
 */
public class JsonHelper {
    static Gson gson = new Gson();

    public static Object fromJson(String json, Class klass) {
        return gson.fromJson(json, klass);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
