package com.example.kafka_apache.config;

import java.util.List;

import lombok.NonNull;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.*;

@Configuration
public class CreateKeyspaceConfiguration extends AbstractCassandraConfiguration implements BeanClassLoaderAware {
    @Value("${spring.cassandra.keyspace-name}")
    private String keySpaceName;

    @Override
    @NonNull
    protected String getKeyspaceName() {
        return keySpaceName;
    }

    @Override
    @NonNull
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification =
                SpecificationBuilder.createKeyspace(keySpaceName)
                        .ifNotExists()
                        .with(KeyspaceOption.DURABLE_WRITES, true)
                        .withNetworkReplication(
                                DataCenterReplication.of("datacenter1", 1));
        return List.of(specification);
    }


}