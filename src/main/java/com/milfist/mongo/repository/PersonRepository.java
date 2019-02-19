package com.milfist.mongo.repository;

import com.milfist.mongo.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
