package com.example.replicadb.model.dto.response;

import com.example.replicadb.model.entity.Person;
import lombok.Getter;

@Getter
public class PersonResponse {
    private final long id;
    private final String name;
    private final CountryResponse country;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.country = new CountryResponse(person.getCountry());
    }
}
