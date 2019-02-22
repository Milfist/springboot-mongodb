package com.milfist.mongo.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Operation {
  INTERCEPTION_START("Interception START"),
  INTERCEPTION_END("Interception END"),
  COMPLETE("Completed"),
  RETURN("Returned");

  private String description;

}


