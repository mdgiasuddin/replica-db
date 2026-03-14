package com.example.replicadb.repository;

import com.example.replicadb.model.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = "country")
    List<Person> findAllByOrderById();
}
