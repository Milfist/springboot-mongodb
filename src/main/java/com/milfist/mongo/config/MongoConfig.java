package com.milfist.mongo.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.milfist.mongo.repository")
public class MongoConfig extends AbstractMongoConfiguration {

  private MongoProperties mongoProperties;

  @Autowired
  public MongoConfig(MongoProperties mongoProperties) {
    this.mongoProperties = mongoProperties;
  }

  @Override
  public MongoClient mongoClient() {
    return new MongoClient(mongoProperties.getHost(), mongoProperties.getPort());
  }

  @Override
  protected String getDatabaseName() {
    return mongoProperties.getDatabase();
  }

}
