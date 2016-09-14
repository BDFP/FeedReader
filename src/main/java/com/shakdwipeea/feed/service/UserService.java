package com.shakdwipeea.feed.service;

import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import com.shakdwipeea.feed.model.User;
import org.bson.Document;
import rx.Observable;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * @author Akash
 *         Created on 01:58 15-09-2016
 */
public class UserService {
    private MongoCollection<Document> userCollection;

    public UserService(MongoDatabase database) {
        this.userCollection = database.getCollection(User.COLLECTION_NAME);
    }

    public Observable<Document> findUser(User user) {
        return userCollection
                .find(and(eq("username", user.getUsername()), eq("password", user.getPassword())))
                .first();
    }

    public Observable<Document> addUser(User user) {
        return userCollection.insertOne(user.toDocument())
                .map(success -> user.toDocument());
    }
}
