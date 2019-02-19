package com.milfist.mongo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Data
public class MongoProperties {
  private Integer port;
  private String host;
  private String database;

}
