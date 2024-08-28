package org.example.replicadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ReplicaDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReplicaDbApplication.class, args);
    }

}
