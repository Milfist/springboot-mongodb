package com.milfist.mongo.service.impl;

import com.milfist.mongo.domain.Person;
import com.milfist.mongo.repository.PersonRepository;
import com.milfist.mongo.service.FindService;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService<Person, String> {

  private PersonRepository repository;

  @Autowired
  public FindServiceImpl(PersonRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Person> findAll() {
    return repository.findAll();
  }

  @Override
  public Person findById(String s) {
    return repository.findById(s).orElseThrow(()-> new NoSuchEntityException("No data for id " + s));
  }

  @Override
  public List<Person> findByField(String field, String value) {
    return null;
  }
}
