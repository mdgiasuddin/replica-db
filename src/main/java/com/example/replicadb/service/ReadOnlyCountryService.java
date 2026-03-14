package com.example.replicadb.service;

import com.example.replicadb.model.entity.Country;
import com.example.replicadb.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class ReadOnlyCountryService {
    private final CountryRepository countryRepository;

    @Transactional(propagation = REQUIRES_NEW, readOnly = true)
    public Country findById(long id) {
        return countryRepository.findById(id).orElseThrow();
    }
}
