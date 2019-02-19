package com.milfist.mongo.controller;

import com.milfist.mongo.domain.Person;
import com.milfist.mongo.service.FindService;
import com.milfist.mongo.service.impl.FindServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindController {

    private FindService<Person, String> findService;

    @Autowired
    public FindController(FindService<Person, String> findService) {
        this.findService = findService;
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        return new ResponseEntity<>(findService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(findService.findAll(), HttpStatus.OK);
    }
}
