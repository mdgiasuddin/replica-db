package com.example.replicadb.service;

import com.example.replicadb.model.dto.request.PersonRequest;
import com.example.replicadb.model.dto.response.PersonResponse;
import com.example.replicadb.model.entity.Country;
import com.example.replicadb.model.entity.Person;
import com.example.replicadb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final CountryService countryService;
    private final PersonRepository personRepository;

    @Transactional
    public void createPerson(PersonRequest request) {
        Country country = countryService.findById(request.countryId());
        Person person = new Person();
        person.setName(request.name());
        person.setCountry(country);
        personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<PersonResponse> getAllPeople() {
        return personRepository.findAllByOrderById()
                .stream()
                .map(PersonResponse::new)
                .toList();
    }
}
