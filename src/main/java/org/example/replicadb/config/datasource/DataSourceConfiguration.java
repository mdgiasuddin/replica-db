package org.example.replicadb.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.example.replicadb.config.properties.PrimaryDB;
import org.example.replicadb.config.properties.ReadOnlyDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = {"org.example.replicadb.entity"})
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Primary
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(ReadOnlyDB readOnlyDB, PrimaryDB primaryDB) {
        final DataSource primaryDataSource = buildDataSource("PRIMARY", readOnlyDB, primaryDB);
        final DataSource readOnlyDataSource = buildDataSource("READ_ONLY", readOnlyDB, primaryDB);

        return new DynamicRoutingDataSource(primaryDataSource, readOnlyDataSource);
    }

    private DataSource buildDataSource(String dataSourceKey, ReadOnlyDB readOnlyDB, PrimaryDB primaryDB) {
        HikariDataSource dataSource = new HikariDataSource();

        if (dataSourceKey.equals("READ_ONLY")) {
            dataSource.setJdbcUrl(readOnlyDB.getUrl());
            dataSource.setUsername(readOnlyDB.getUsername());
            dataSource.setPassword(readOnlyDB.getPassword());
            dataSource.setDriverClassName(readOnlyDB.getDriverClassName());
            dataSource.setIdleTimeout(readOnlyDB.getIdleTimeout());
            dataSource.setMinimumIdle(readOnlyDB.getMinimumIdle());
        } else {
            dataSource.setJdbcUrl(primaryDB.getUrl());
            dataSource.setUsername(primaryDB.getUsername());
            dataSource.setPassword(primaryDB.getPassword());
            dataSource.setDriverClassName(primaryDB.getDriverClassName());
            dataSource.setIdleTimeout(primaryDB.getIdleTimeout());
            dataSource.setMinimumIdle(primaryDB.getMinimumIdle());
        }

        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("routingDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("org.example.replicadb.entity")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
