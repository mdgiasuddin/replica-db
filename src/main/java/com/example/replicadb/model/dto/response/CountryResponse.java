package com.example.replicadb.model.dto.response;

import com.example.replicadb.model.entity.Country;
import lombok.Getter;

@Getter
public class CountryResponse {
    private final long id;
    private final String name;

    public CountryResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
}
