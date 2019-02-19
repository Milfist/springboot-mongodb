package com.milfist.mongo.service.impl;

import com.milfist.mongo.domain.Person;
import com.milfist.mongo.repository.PersonRepository;
import com.milfist.mongo.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertPersonService implements InsertService<Person> {

  private PersonRepository personRepository;

  @Autowired
  public InsertPersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Person insert(Person person) {
    return personRepository.insert(person);
  }

  @Override
  public List<Person> insertAll(List<Person> t) {
    return personRepository.insert(t);
  }
}
