package com.shakdwipeea.feed.model;

import com.shakdwipeea.feed.util.MongoHelper;
import org.bson.Document;

/**
 * @author Akash
 *         Created on 01:00 04-09-2016
 */
public class User {
    public static final String COLLECTION_NAME = "user";

    private int id;
    private String username;
    private String password;

    public User() {
        //for jackson
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Document toDocument() {
        return MongoHelper.toDocument(this);
    }
}
