package com.example.replicadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ReplicaDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReplicaDbApplication.class, args);
    }

}
