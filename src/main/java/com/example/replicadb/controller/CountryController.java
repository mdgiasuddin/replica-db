package com.example.replicadb.controller;

import com.example.replicadb.model.dto.request.CountryRequest;
import com.example.replicadb.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    @PutMapping("/{id}")
    public void updateCountry(@PathVariable long id, @Valid @RequestBody CountryRequest request) {
        countryService.updateCountry(id, request);
    }
}
