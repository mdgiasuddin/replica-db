package com.example.replicadb.service;

import com.example.replicadb.model.dto.request.CountryRequest;
import com.example.replicadb.model.entity.Country;
import com.example.replicadb.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final ReadOnlyCountryService readOnlyCountryService;
    private final CountryRepository countryRepository;

    public Country findById(long id) {
        return readOnlyCountryService.findById(id);
    }

    @Transactional
    public void updateCountry(long id, CountryRequest request) {
        Country country = readOnlyCountryService.findById(id);
        country.setName(request.name());
        countryRepository.save(country);
    }
}
