package com.milfist.mongo.service;

import java.util.List;

public interface FindService<T, ID> {
  List<T> findAll();
  T findById(ID id);
  List<T> findByField(String field, String value);
}
