package com.milfist.mongo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties(prefix = "fluentd")
@Data
public class FluentDProperties {
  private String tag;
  private String host;
  private Integer port;
}
