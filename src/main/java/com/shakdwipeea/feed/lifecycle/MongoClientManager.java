package com.shakdwipeea.feed.lifecycle;

import com.mongodb.rx.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

/**
 * @author Akash
 *         Created on 14:45 14-09-2016
 */
public class MongoClientManager implements Managed {
    private final MongoClient mongoClient;

    public MongoClientManager(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
