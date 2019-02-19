package com.milfist.mongo.service;

import java.util.List;

public interface InsertService<T> {
  T insert(T t);
  List<T> insertAll(List<T> t);
}
