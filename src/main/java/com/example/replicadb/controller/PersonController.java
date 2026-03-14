package com.example.replicadb.controller;

import com.example.replicadb.model.dto.request.PersonRequest;
import com.example.replicadb.model.dto.response.PersonResponse;
import com.example.replicadb.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public void createPerson(@Valid @RequestBody PersonRequest request) {
        personService.createPerson(request);
    }

    @GetMapping
    public List<PersonResponse> getAllPeople() {
        return personService.getAllPeople();
    }
}
