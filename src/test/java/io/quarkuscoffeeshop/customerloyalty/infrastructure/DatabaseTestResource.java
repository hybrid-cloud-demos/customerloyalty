package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class DatabaseTestResource implements QuarkusTestResourceLifecycleManager {

    static final PostgreSQLContainer CONTAINER = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("customerloyaltydb")
            .withUsername("customerloyaltyuser")
            .withPassword("redhat-20")
            .withInitScript("/compose/init.sql")
            .withCreateContainerCmdModifier(cmd -> {
                cmd.withHostName("localhost").withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)));
            });

    @Override
    public Map<String, String> start() {

        CONTAINER.start();
        return null;
    }

    @Override
    public void stop() {

        CONTAINER.stop();
    }
}
