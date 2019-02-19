package com.milfist.mongo.controller;

import com.milfist.mongo.domain.Person;
import com.milfist.mongo.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsertController {

    private InsertService<Person> insertService;

    @Autowired
    public InsertController(InsertService<Person> insertService) {
        this.insertService = insertService;
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> savePerson(Person person) {
        return new ResponseEntity<>(insertService.insert(person), HttpStatus.CREATED);
    }

    @PostMapping("/persons/all")
    public ResponseEntity<List<Person>> savePersons(@RequestBody List<Person> persons) {
        return new ResponseEntity<>(insertService.insertAll(persons), HttpStatus.CREATED);
    }
}
