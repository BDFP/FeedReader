package com.shakdwipeea.feed;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mongodb.async.client.MongoClient;
import io.dropwizard.Configuration;

public class FeedConfiguration extends Configuration {
	private String dbName;

	private MongoClient mongoClient;

	@JsonProperty
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
}
