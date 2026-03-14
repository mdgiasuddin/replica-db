package com.example.replicadb.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.example.replicadb.config.db.DatabaseType.PRIMARY;
import static com.example.replicadb.config.db.DatabaseType.REPLICA;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // If current transaction is read-only → replica, else primary
        boolean txActive =
                TransactionSynchronizationManager.isActualTransactionActive();

        boolean readOnly =
                TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        log.info("Transaction active: {}", txActive);
        log.info("Read only: {}", readOnly);

        return readOnly ? REPLICA : PRIMARY;
    }
}
