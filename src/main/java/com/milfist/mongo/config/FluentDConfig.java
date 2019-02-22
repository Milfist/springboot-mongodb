package com.milfist.mongo.config;

import org.fluentd.logger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FluentDConfig {

  private FluentDProperties fluentDProperties;

  @Autowired
  public FluentDConfig(FluentDProperties fluentDProperties) {
    this.fluentDProperties = fluentDProperties;
  }

  @Primary
  @Bean
  public FluentLogger LOG() {
    return FluentLogger.getLogger(fluentDProperties.getTag(), fluentDProperties.getHost(), fluentDProperties.getPort());
  }

}
