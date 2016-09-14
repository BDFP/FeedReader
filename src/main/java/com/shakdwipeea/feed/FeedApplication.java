package com.shakdwipeea.feed;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoDatabase;
import com.shakdwipeea.feed.healthcheck.MongoHealthCheck;
import com.shakdwipeea.feed.lifecycle.MongoClientManager;
import com.shakdwipeea.feed.resources.ApiResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Akash  on 01-09-2016.
 */
public class FeedApplication extends Application<FeedConfiguration> {
    public static final String MONGO_DB_NAME = "feed";

    public static void main(String[] args) throws Exception {
        new FeedApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<FeedConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(FeedConfiguration configuration, Environment environment) throws Exception {
        // Init Mongoclient
        MongoClient mongoClient = MongoClients.create();
        MongoClientManager mongoClientManager = new MongoClientManager(mongoClient);
        environment.lifecycle().manage(mongoClientManager);

        // register healthcheck
        MongoDatabase database = mongoClient.getDatabase(configuration.getDbName());
        environment.healthChecks().register("database", new MongoHealthCheck(database));

        environment.jersey().register(new ApiResources(database));
    }
}
