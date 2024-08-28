package org.example.replicadb.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<Type> currentDataSource = ThreadLocal.withInitial(() -> Type.PRIMARY);

    DynamicRoutingDataSource(DataSource primaryDataSource, DataSource readOnlyDataSource) {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(Type.PRIMARY, primaryDataSource);
        dataSources.put(Type.READ_ONLY, readOnlyDataSource);

        super.setTargetDataSources(dataSources);

        super.setDefaultTargetDataSource(primaryDataSource);
    }

    public static void setReadonlyDataSource(boolean isReadonly) {
        currentDataSource.set(isReadonly ? Type.READ_ONLY : Type.PRIMARY);
    }

    public static void resetDataSourceType() {
        currentDataSource.set(Type.PRIMARY);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return currentDataSource.get();
    }

    public static void clearDataSourceType() {
        currentDataSource.remove();
    }

    private enum Type {
        PRIMARY, READ_ONLY;
    }
}
