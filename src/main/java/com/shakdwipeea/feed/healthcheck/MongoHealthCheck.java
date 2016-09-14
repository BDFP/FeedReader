package com.shakdwipeea.feed.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.async.client.MongoDatabase;

/**
 * @author Akash
 *         Created on 14:49 14-09-2016
 */
public class MongoHealthCheck extends HealthCheck {
    private final MongoDatabase database;

    public MongoHealthCheck(MongoDatabase database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if (database.getName() != null) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Database not connected");
        }
    }
}
